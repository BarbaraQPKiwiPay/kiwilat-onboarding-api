package com.kiwipay.onboarding.document.application.internal.commandservices;

import com.kiwipay.onboarding.document.application.internal.dto.DocumentReviewRequest;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentUploadRequest;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;
import com.kiwipay.onboarding.document.domain.model.aggregates.Document;
import com.kiwipay.onboarding.document.domain.model.exceptions.DocumentBusinessException;
import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentOwnerType;
import com.kiwipay.onboarding.document.domain.services.DocumentCommandService;
import com.kiwipay.onboarding.document.infrastructure.persistence.jpa.DocumentRepository;
import com.kiwipay.onboarding.loan.infrastructure.persistence.jpa.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class DocumentCommandServiceImpl implements DocumentCommandService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private LoanRepository loanRepository;

    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList(
            "application/pdf", "image/jpeg", "image/png");
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

    @Override
    public DocumentResponse uploadDocument(Long loanId, Long ownerId, DocumentUploadRequest request) {
        // Validar que el loan existe
        if (!loanRepository.existsById(loanId)) {
            throw DocumentBusinessException.loanNotFound();
        }

        // Validar MIME type
        if (!ALLOWED_MIME_TYPES.contains(request.getMimeType())) {
            throw DocumentBusinessException.invalidMimeType();
        }

        // Validar tamaño del archivo
        if (request.getSizeBytes() > MAX_FILE_SIZE) {
            throw DocumentBusinessException.fileSizeExceeded();
        }

        // Validar Base64
        try {
            Base64.getDecoder().decode(request.getContentBase64());
        } catch (IllegalArgumentException e) {
            throw DocumentBusinessException.invalidBase64();
        }

        // Determinar clientId/guarantorId según ownerType
        // NO validamos existencia - eso es responsabilidad del caller
        Long clientId = null;
        Long guarantorId = null;

        if (request.getOwnerType() == DocumentOwnerType.CLIENT) {
            clientId = ownerId;
        } else if (request.getOwnerType() == DocumentOwnerType.GUARANTOR) {
            guarantorId = ownerId;
        }

        // Generar ID único
        String documentId = generateDocumentId();

        // Crear documento
        Document document = new Document(
                documentId,
                loanId,
                request.getOwnerType(),
                clientId,
                guarantorId,
                request.getDocumentType(),
                request.getFilename(),
                request.getMimeType(),
                request.getSizeBytes(),
                request.getComment(),
                request.getContentBase64());

        Document savedDocument = documentRepository.save(document);

        DocumentResponse response = new DocumentResponse();
        BeanUtils.copyProperties(savedDocument, response);
        return response;
    }

    @Override
    public DocumentResponse uploadRiskDocument(Long loanId, DocumentUploadRequest request) {
        // Validar que el loan existe
        if (!loanRepository.existsById(loanId)) {
            throw DocumentBusinessException.loanNotFound();
        }

        // Validar MIME type
        if (!ALLOWED_MIME_TYPES.contains(request.getMimeType())) {
            throw DocumentBusinessException.invalidMimeType();
        }

        // Validar tamaño del archivo
        if (request.getSizeBytes() > MAX_FILE_SIZE) {
            throw DocumentBusinessException.fileSizeExceeded();
        }

        // Validar Base64
        try {
            Base64.getDecoder().decode(request.getContentBase64());
        } catch (IllegalArgumentException e) {
            throw DocumentBusinessException.invalidBase64();
        }

        // Generar ID único
        String documentId = generateDocumentId();

        // Crear documento de riesgo (sin clientId ni guarantorId específico)
        Document document = new Document(
                documentId,
                loanId,
                request.getOwnerType(),
                null, // clientId - no aplica para documento de riesgo
                null, // guarantorId - no aplica para documento de riesgo
                request.getDocumentType(),
                request.getFilename(),
                request.getMimeType(),
                request.getSizeBytes(),
                request.getComment(),
                request.getContentBase64());

        Document savedDocument = documentRepository.save(document);

        DocumentResponse response = new DocumentResponse();
        BeanUtils.copyProperties(savedDocument, response);
        return response;
    }

    @Override
    public void deleteDocument(String documentId) {
        if (!documentRepository.existsById(documentId)) {
            throw DocumentBusinessException.documentNotFound();
        }

        documentRepository.deleteById(documentId);
    }

    @Override
    public DocumentResponse reviewDocument(String documentId, DocumentReviewRequest request) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(DocumentBusinessException::documentNotFound);

        document.updateReviewStatus(request.getReviewStatus(), request.getComment());
        Document reviewedDocument = documentRepository.save(document);

        DocumentResponse response = new DocumentResponse();
        BeanUtils.copyProperties(reviewedDocument, response);
        return response;
    }

    private String generateDocumentId() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = documentRepository.count() + 1;
        return String.format("DOC-%s-%04d", timestamp, count);
    }
}