package com.kiwipay.onboarding.client.application.internal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpouseUpdateRequest {
    private String firstNames;
    private String lastNames;
    private String email;
    private String phone;
}