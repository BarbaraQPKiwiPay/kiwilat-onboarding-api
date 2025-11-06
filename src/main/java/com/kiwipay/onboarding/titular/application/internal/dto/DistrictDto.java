package com.kiwipay.onboarding.titular.application.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDto {
    private String id;
    private String name;
    private String provinceId;
}
