package com.kiwipay.onboarding.guarantor.domain.services;

import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorCreateRequest;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorDocumentUploadRequest;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorDocumentResponse;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorDocumentReviewRequest;

import java.util.Map;

public interface GuarantorCommandService {
    // Guarantor CRUD
    GuarantorResponse createOrUpdateGuarantor(Long clientId, GuarantorCreateRequest request);
    GuarantorResponse patchGuarantor(Long clientId, Map<String, Object> updates);
    void deleteGuarantor(Long clientId);
    
    // GuarantorDocument CRUD
    GuarantorDocumentResponse uploadDocument(Long clientId, GuarantorDocumentUploadRequest request);
    GuarantorDocumentResponse patchDocument(String documentId, Map<String, Object> updates);
    void deleteDocument(Long clientId, String documentId);
    
    // Review operations
    GuarantorDocumentResponse reviewDocument(String documentId, GuarantorDocumentReviewRequest request);
}