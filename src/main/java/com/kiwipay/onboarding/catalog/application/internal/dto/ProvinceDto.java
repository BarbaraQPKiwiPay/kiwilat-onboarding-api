package com.kiwipay.onboarding.catalog.application.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDto {
    private String id;
    private String name;
    private String departmentId;
}
