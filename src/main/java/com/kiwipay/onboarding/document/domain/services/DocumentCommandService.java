package com.kiwipay.onboarding.document.domain.services;

import com.kiwipay.onboarding.document.application.internal.dto.DocumentUploadRequest;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentReviewRequest;

public interface DocumentCommandService {
    DocumentResponse uploadDocument(Long clientId, DocumentUploadRequest request);
    void deleteDocument(Long clientId, String documentId);
    DocumentResponse reviewDocument(String documentId, DocumentReviewRequest request);
}