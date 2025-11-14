package com.kiwipay.onboarding.document.domain.services;

import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentTypeResponse;

import java.util.List;

public interface DocumentQueryService {
    List<DocumentTypeResponse> getAllDocumentTypes();
    List<DocumentResponse> getDocumentsByClientId(Long clientId);
    byte[] getDocumentContent(String documentId);
    DocumentResponse getDocumentById(String documentId);
    
    // New methods for filtered documents
    List<DocumentResponse> getNonRiskDocumentsByClientId(Long clientId);
    List<DocumentResponse> getRiskDocumentsByClientId(Long clientId);
}