package com.kiwipay.onboarding.titular.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientUpdateRequest {
    private String firstNames;
    private String lastNames;
    private String maritalStatus;
    private String gender;
    private String birthDate;
    private String email;
    private String phone;
    private AddressDto address;

    @Data
    @NoArgsConstructor
    public static class AddressDto {
        private String departmentId;
        private String provinceId;
        private String districtId;
        private String line1;
    }
}