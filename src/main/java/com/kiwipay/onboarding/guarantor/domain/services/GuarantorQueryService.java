package com.kiwipay.onboarding.guarantor.domain.services;

import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorDocumentResponse;

import java.util.List;

public interface GuarantorQueryService {
    // Guarantor queries
    GuarantorResponse getGuarantorByClientId(Long clientId);
    
    // GuarantorDocument queries
    List<GuarantorDocumentResponse> getDocumentsByClientId(Long clientId);
    byte[] getDocumentContent(String documentId);
    GuarantorDocumentResponse getDocumentById(String documentId);
}