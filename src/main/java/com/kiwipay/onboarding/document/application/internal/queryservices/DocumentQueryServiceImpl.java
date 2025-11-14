package com.kiwipay.onboarding.document.application.internal.queryservices;

import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentTypeResponse;
import com.kiwipay.onboarding.document.domain.model.aggregates.Document;
import com.kiwipay.onboarding.document.domain.model.entities.DocumentType;
import com.kiwipay.onboarding.document.domain.model.exceptions.DocumentBusinessException;
import com.kiwipay.onboarding.document.domain.services.DocumentQueryService;
import com.kiwipay.onboarding.document.infrastructure.persistence.jpa.DocumentRepository;
import com.kiwipay.onboarding.document.infrastructure.persistence.jpa.DocumentTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
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
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        return documentTypes.stream()
            .map(documentType -> {
                DocumentTypeResponse response = new DocumentTypeResponse();
                BeanUtils.copyProperties(documentType, response);
                return response;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<DocumentResponse> getDocumentsByClientId(Long clientId) {
        List<Document> documents = documentRepository.findByClientIdOrderByCreatedAtDesc(clientId);
        return documents.stream()
            .map(document -> {
                DocumentResponse response = new DocumentResponse();
                BeanUtils.copyProperties(document, response);
                // No incluir el contenido Base64 en la lista
                return response;
            })
            .collect(Collectors.toList());
    }

    @Override
    public byte[] getDocumentContent(String documentId) {
        Document document = documentRepository.findById(documentId)
            .orElseThrow(DocumentBusinessException::documentNotFound);

        try {
            return Base64.getDecoder().decode(document.getContentBase64());
        } catch (IllegalArgumentException e) {
            throw DocumentBusinessException.invalidBase64();
        }
    }

    @Override
    public DocumentResponse getDocumentById(String documentId) {
        Document document = documentRepository.findById(documentId)
            .orElseThrow(DocumentBusinessException::documentNotFound);

        DocumentResponse response = new DocumentResponse();
        BeanUtils.copyProperties(document, response);
        return response;
    }

    @Override
    public List<DocumentResponse> getNonRiskDocumentsByClientId(Long clientId) {
        List<Document> documents = documentRepository.findByClientIdAndDocumentTypeIdNotOrderByCreatedAtDesc(clientId, "FICHA_RIESGO");
        return documents.stream()
            .map(document -> {
                DocumentResponse response = new DocumentResponse();
                BeanUtils.copyProperties(document, response);
                return response;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<DocumentResponse> getRiskDocumentsByClientId(Long clientId) {
        List<Document> documents = documentRepository.findByClientIdAndDocumentTypeIdOrderByCreatedAtDesc(clientId, "FICHA_RIESGO");
        return documents.stream()
            .map(document -> {
                DocumentResponse response = new DocumentResponse();
                BeanUtils.copyProperties(document, response);
                return response;
            })
            .collect(Collectors.toList());
    }
}