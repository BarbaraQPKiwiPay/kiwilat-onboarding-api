package com.kiwipay.onboarding.guarantor.application.internal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuarantorAddressRequest {
    @NotBlank(message = "Department ID is required")
    private String departmentId;

    @NotBlank(message = "Province ID is required")
    private String provinceId;

    @NotBlank(message = "District ID is required")
    private String districtId;

    @NotBlank(message = "Address line 1 is required")
    private String line1;
}