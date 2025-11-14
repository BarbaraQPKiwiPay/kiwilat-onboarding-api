package com.kiwipay.onboarding.guarantor.application.internal.queryservices;

import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorAddressResponse;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorDocumentResponse;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor;
import com.kiwipay.onboarding.guarantor.domain.model.aggregates.GuarantorDocument;
import com.kiwipay.onboarding.guarantor.domain.model.exceptions.GuarantorBusinessException;
import com.kiwipay.onboarding.guarantor.domain.services.GuarantorQueryService;
import com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa.GuarantorDocumentRepository;
import com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa.GuarantorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuarantorQueryServiceImpl implements GuarantorQueryService {

    @Autowired
    private GuarantorRepository guarantorRepository;

    @Autowired
    private GuarantorDocumentRepository guarantorDocumentRepository;

    @Override
    public GuarantorResponse getGuarantorByClientId(Long clientId) {
        Guarantor guarantor = guarantorRepository.findByClientId(clientId)
            .orElseThrow(GuarantorBusinessException::guarantorNotFound);

        GuarantorResponse response = new GuarantorResponse();
        BeanUtils.copyProperties(guarantor, response);
        
        GuarantorAddressResponse addressResponse = new GuarantorAddressResponse();
        BeanUtils.copyProperties(guarantor.getAddress(), addressResponse);
        response.setAddress(addressResponse);
        
        return response;
    }

    @Override
    public List<GuarantorDocumentResponse> getDocumentsByClientId(Long clientId) {
        List<GuarantorDocument> documents = guarantorDocumentRepository.findByClientIdOrderByUploadedAtDesc(clientId);
        return documents.stream()
            .map(document -> {
                GuarantorDocumentResponse response = new GuarantorDocumentResponse();
                BeanUtils.copyProperties(document, response);
                // No incluir contenido Base64 en la lista
                return response;
            })
            .collect(Collectors.toList());
    }

    @Override
    public byte[] getDocumentContent(String documentId) {
        GuarantorDocument document = guarantorDocumentRepository.findById(documentId)
            .orElseThrow(GuarantorBusinessException::guarantorDocumentNotFound);

        try {
            return Base64.getDecoder().decode(document.getContentBase64());
        } catch (IllegalArgumentException e) {
            throw GuarantorBusinessException.invalidBase64();
        }
    }

    @Override
    public GuarantorDocumentResponse getDocumentById(String documentId) {
        GuarantorDocument document = guarantorDocumentRepository.findById(documentId)
            .orElseThrow(GuarantorBusinessException::guarantorDocumentNotFound);

        GuarantorDocumentResponse response = new GuarantorDocumentResponse();
        BeanUtils.copyProperties(document, response);
        return response;
    }
}