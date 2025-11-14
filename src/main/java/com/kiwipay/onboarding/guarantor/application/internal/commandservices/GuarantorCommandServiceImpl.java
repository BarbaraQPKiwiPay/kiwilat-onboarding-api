package com.kiwipay.onboarding.guarantor.application.internal.commandservices;

import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.ClientRepository;
import com.kiwipay.onboarding.document.infrastructure.persistence.jpa.DocumentTypeRepository;
import com.kiwipay.onboarding.guarantor.application.internal.dto.*;
import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor;
import com.kiwipay.onboarding.guarantor.domain.model.aggregates.GuarantorDocument;
import com.kiwipay.onboarding.guarantor.domain.model.exceptions.GuarantorBusinessException;
import com.kiwipay.onboarding.guarantor.domain.model.valueobjects.GuarantorAddress;
import com.kiwipay.onboarding.guarantor.domain.services.GuarantorCommandService;
import com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa.GuarantorDocumentRepository;
import com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa.GuarantorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class GuarantorCommandServiceImpl implements GuarantorCommandService {

    @Autowired
    private GuarantorRepository guarantorRepository;

    @Autowired
    private GuarantorDocumentRepository guarantorDocumentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList(
        "application/pdf", "image/jpeg", "image/png"
    );
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int MAX_DOCUMENTS_PER_CLIENT = 10;

    @Override
    public GuarantorResponse createOrUpdateGuarantor(Long clientId, GuarantorCreateRequest request) {
        // Verificar que el cliente existe
        if (!clientRepository.existsById(clientId)) {
            throw GuarantorBusinessException.clientNotFound();
        }

        Optional<Guarantor> existingGuarantor = guarantorRepository.findByClientId(clientId);
        Guarantor guarantor;

        GuarantorAddress address = new GuarantorAddress(
            request.getAddress().getDepartmentId(),
            request.getAddress().getProvinceId(),
            request.getAddress().getDistrictId(),
            request.getAddress().getLine1()
        );

        if (existingGuarantor.isPresent()) {
            // Actualizar
            guarantor = existingGuarantor.get();
            guarantor.updateDetails(
                request.getDocumentType(),
                request.getDocumentNumber(),
                request.getMonthlyIncome(),
                request.getFirstNames(),
                request.getLastNames(),
                request.getGender(),
                request.getMaritalStatus(),
                request.getEmail(),
                request.getPhone(),
                address
            );
        } else {
            // Crear
            String guarantorId = generateGuarantorId();
            guarantor = new Guarantor(
                guarantorId,
                clientId,
                request.getDocumentType(),
                request.getDocumentNumber(),
                request.getMonthlyIncome(),
                request.getFirstNames(),
                request.getLastNames(),
                request.getGender(),
                request.getMaritalStatus(),
                request.getEmail(),
                request.getPhone(),
                address
            );
        }

        Guarantor savedGuarantor = guarantorRepository.save(guarantor);
        return mapToGuarantorResponse(savedGuarantor);
    }

    @Override
    public GuarantorResponse patchGuarantor(Long clientId, Map<String, Object> updates) {
        Guarantor guarantor = guarantorRepository.findByClientId(clientId)
            .orElseThrow(GuarantorBusinessException::guarantorNotFound);

        updates.forEach((key, value) -> {
            switch (key) {
                case "documentType":
                    guarantor.setDocumentType((String) value);
                    break;
                case "documentNumber":
                    guarantor.setDocumentNumber((String) value);
                    break;
                case "monthlyIncome":
                    if (value instanceof Number) {
                        guarantor.setMonthlyIncome(new BigDecimal(value.toString()));
                    }
                    break;
                case "firstNames":
                    guarantor.setFirstNames((String) value);
                    break;
                case "lastNames":
                    guarantor.setLastNames((String) value);
                    break;
                case "email":
                    guarantor.setEmail((String) value);
                    break;
                case "phone":
                    guarantor.setPhone((String) value);
                    break;
            }
        });

        guarantor.setUpdatedAt(LocalDateTime.now());
        Guarantor updatedGuarantor = guarantorRepository.save(guarantor);
        return mapToGuarantorResponse(updatedGuarantor);
    }

    @Override
    public void deleteGuarantor(Long clientId) {
        if (!guarantorRepository.existsByClientId(clientId)) {
            throw GuarantorBusinessException.guarantorNotFound();
        }
        guarantorRepository.deleteByClientId(clientId);
    }

    @Override
    public GuarantorDocumentResponse uploadDocument(Long clientId, GuarantorDocumentUploadRequest request) {
        // Validaciones
        if (!clientRepository.existsById(clientId)) {
            throw GuarantorBusinessException.clientNotFound();
        }

        if (!documentTypeRepository.existsById(request.getDocumentTypeId())) {
            throw GuarantorBusinessException.documentTypeNotFound();
        }

        if (!ALLOWED_MIME_TYPES.contains(request.getMimeType())) {
            throw GuarantorBusinessException.invalidMimeType();
        }

        if (request.getSizeBytes() > MAX_FILE_SIZE) {
            throw GuarantorBusinessException.fileSizeExceeded();
        }

        if (guarantorDocumentRepository.countByClientId(clientId) >= MAX_DOCUMENTS_PER_CLIENT) {
            throw GuarantorBusinessException.maxDocumentsExceeded();
        }

        // Validar Base64
        try {
            Base64.getDecoder().decode(request.getContentBase64());
        } catch (IllegalArgumentException e) {
            throw GuarantorBusinessException.invalidBase64();
        }

        String documentId = generateGuarantorDocumentId();
        GuarantorDocument document = new GuarantorDocument(
            documentId,
            clientId,
            request.getDocumentTypeId(),
            request.getFilename(),
            request.getMimeType(),
            request.getSizeBytes(),
            request.getComment(),
            request.getContentBase64()
        );

        GuarantorDocument savedDocument = guarantorDocumentRepository.save(document);
        return mapToGuarantorDocumentResponse(savedDocument);
    }

    @Override
    public GuarantorDocumentResponse patchDocument(String documentId, Map<String, Object> updates) {
        GuarantorDocument document = guarantorDocumentRepository.findById(documentId)
            .orElseThrow(GuarantorBusinessException::guarantorDocumentNotFound);

        updates.forEach((key, value) -> {
            switch (key) {
                case "comment":
                    document.setComment((String) value);
                    break;
                case "documentTypeId":
                    document.setDocumentTypeId((String) value);
                    break;
            }
        });

        GuarantorDocument updatedDocument = guarantorDocumentRepository.save(document);
        return mapToGuarantorDocumentResponse(updatedDocument);
    }

    @Override
    public void deleteDocument(Long clientId, String documentId) {
        if (!guarantorDocumentRepository.existsByIdAndClientId(documentId, clientId)) {
            throw GuarantorBusinessException.documentNotBelongsToClient();
        }
        guarantorDocumentRepository.deleteById(documentId);
    }

    @Override
    public GuarantorDocumentResponse reviewDocument(String documentId, GuarantorDocumentReviewRequest request) {
        GuarantorDocument document = guarantorDocumentRepository.findById(documentId)
            .orElseThrow(GuarantorBusinessException::guarantorDocumentNotFound);

        document.updateReviewStatus(request.getReviewStatus(), null);
        GuarantorDocument reviewedDocument = guarantorDocumentRepository.save(document);
        return mapToGuarantorDocumentResponse(reviewedDocument);
    }

    private String generateGuarantorId() {
        long count = guarantorRepository.count() + 1;
        return String.format("GUA-%03d", count);
    }

    private String generateGuarantorDocumentId() {
        long count = guarantorDocumentRepository.count() + 1;
        return String.format("GUD-%04d", count);
    }

    private GuarantorResponse mapToGuarantorResponse(Guarantor guarantor) {
        GuarantorResponse response = new GuarantorResponse();
        BeanUtils.copyProperties(guarantor, response);
        
        GuarantorAddressResponse addressResponse = new GuarantorAddressResponse();
        BeanUtils.copyProperties(guarantor.getAddress(), addressResponse);
        response.setAddress(addressResponse);
        
        return response;
    }

    private GuarantorDocumentResponse mapToGuarantorDocumentResponse(GuarantorDocument document) {
        GuarantorDocumentResponse response = new GuarantorDocumentResponse();
        BeanUtils.copyProperties(document, response);
        return response;
    }
}