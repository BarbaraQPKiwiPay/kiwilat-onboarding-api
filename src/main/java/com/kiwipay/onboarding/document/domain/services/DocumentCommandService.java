package com.kiwipay.onboarding.document.domain.services;

import com.kiwipay.onboarding.document.application.internal.dto.DocumentUploadRequest;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;

public interface DocumentCommandService {
    DocumentResponse uploadDocument(Long clientId, DocumentUploadRequest request);
    void deleteDocument(Long clientId, String documentId);
}