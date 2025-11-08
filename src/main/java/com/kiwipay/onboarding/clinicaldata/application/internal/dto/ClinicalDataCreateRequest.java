package com.kiwipay.onboarding.clinicaldata.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClinicalDataCreateRequest {
    private String medicalCategoryId;
    private String clinicId;
    private String branchId;
}