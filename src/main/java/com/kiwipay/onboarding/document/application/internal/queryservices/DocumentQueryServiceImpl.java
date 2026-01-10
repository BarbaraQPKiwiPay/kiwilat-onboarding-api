package com.kiwipay.onboarding.document.application.internal.queryservices;

import com.kiwipay.onboarding.document.application.internal.dto.DocumentPreviewResponse;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentTypeResponse;
import com.kiwipay.onboarding.document.domain.model.aggregates.Document;
import com.kiwipay.onboarding.document.domain.model.entities.DocumentTypeEntity;
import com.kiwipay.onboarding.document.domain.model.exceptions.DocumentBusinessException;
import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentOwnerType;
import com.kiwipay.onboarding.document.domain.services.DocumentQueryService;
import com.kiwipay.onboarding.document.infrastructure.persistence.jpa.DocumentRepository;
import com.kiwipay.onboarding.document.infrastructure.persistence.jpa.DocumentTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentQueryServiceImpl implements DocumentQueryService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentTypeResponse> getAllDocumentTypes() {
        return documentTypeRepository.findAll().stream()
                .map(this::toDocumentTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentResponse> getDocumentsByLoanId(Long loanId) {
        return documentRepository.findByLoanId(loanId).stream()
                .map(this::toDocumentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentResponse> getDocumentsByLoanIdAndOwnerType(Long loanId, DocumentOwnerType ownerType) {
        return documentRepository.findByLoanIdAndOwnerType(loanId, ownerType).stream()
                .map(this::toDocumentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentResponse> getDocumentsByClientId(Long clientId) {
        return documentRepository.findByClientId(clientId).stream()
                .map(this::toDocumentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentResponse> getDocumentsByGuarantorId(Long guarantorId) {
        return documentRepository.findByGuarantorId(guarantorId).stream()
                .map(this::toDocumentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentResponse getDocumentById(String documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(DocumentBusinessException::documentNotFound);
        return toDocumentResponse(document);
    }

    @Override
    public DocumentPreviewResponse previewDocument(String documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(DocumentBusinessException::documentNotFound);

        DocumentPreviewResponse preview = new DocumentPreviewResponse();
        preview.setId(document.getId());
        preview.setLoanId(document.getLoanId());
        preview.setOwnerType(document.getOwnerType());
        preview.setDocumentTypeId(document.getDocumentTypeId());
        preview.setFilename(document.getFilename());
        preview.setMimeType(document.getMimeType());
        preview.setSizeBytes(document.getSizeBytes());
        preview.setComment(document.getComment());
        preview.setContentBase64(document.getContentBase64());
        preview.setCreatedAt(document.getCreatedAt());

        return preview;
    }

    private DocumentResponse toDocumentResponse(Document document) {
        DocumentResponse response = new DocumentResponse();
        BeanUtils.copyProperties(document, response);
        return response;
    }

    private DocumentTypeResponse toDocumentTypeResponse(DocumentTypeEntity entity) {
        DocumentTypeResponse response = new DocumentTypeResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }
}