package com.kiwipay.onboarding.document.domain.services;

import com.kiwipay.onboarding.document.application.internal.dto.DocumentUploadRequest;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentReviewRequest;

public interface DocumentCommandService {
    DocumentResponse uploadDocument(Long loanId, Long ownerId, DocumentUploadRequest request);

    DocumentResponse uploadRiskDocument(Long loanId, DocumentUploadRequest request);

    void deleteDocument(String documentId);

    DocumentResponse reviewDocument(String documentId, DocumentReviewRequest request);
}