package com.kiwipay.onboarding.client.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientResponse {
    private Long id;
    private Long clientId;
    private String documentType;
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private String gender;
    private String phone;
    private String email;
    private AddressDto address;
    private String createdAt;

    @Data
    @NoArgsConstructor
    public static class AddressDto {
        private String departmentId;
        private String provinceId;
        private String districtId;
        private String line1;
    }
}