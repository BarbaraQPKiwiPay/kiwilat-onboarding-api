package com.kiwipay.onboarding.titular.application.internal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpousePatchRequest {
    private String firstNames;
    private String lastNames;
    private String email;
    private String phone;
}