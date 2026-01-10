package com.kiwipay.onboarding.document.domain.services;

import com.kiwipay.onboarding.document.application.internal.dto.DocumentPreviewResponse;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;
import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentOwnerType;

import java.util.List;

public interface DocumentQueryService {
    List<DocumentResponse> getDocumentsByLoanId(Long loanId);

    List<DocumentResponse> getDocumentsByLoanIdAndOwnerType(Long loanId, DocumentOwnerType ownerType);

    List<DocumentResponse> getDocumentsByClientId(Long clientId);

    List<DocumentResponse> getDocumentsByGuarantorId(Long guarantorId);

    DocumentResponse getDocumentById(String documentId);

    DocumentPreviewResponse previewDocument(String documentId);
}