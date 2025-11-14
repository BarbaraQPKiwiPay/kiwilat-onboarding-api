package com.kiwipay.onboarding.guarantor.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuarantorAddressResponse {
    private String departmentId;
    private String provinceId;
    private String districtId;
    private String line1;
}