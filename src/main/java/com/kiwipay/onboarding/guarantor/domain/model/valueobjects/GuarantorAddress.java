package com.kiwipay.onboarding.guarantor.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class GuarantorAddress {
    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "district_id")
    private String districtId;

    @Column(name = "address_line1")
    private String line1;

    public GuarantorAddress(String departmentId, String provinceId, String districtId, String line1) {
        this.departmentId = departmentId;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.line1 = line1;
    }
}