package com.kiwipay.onboarding.titular.domain.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Embeddable;

import java.util.Objects;
@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class Address {
    private String departmentId;
    private String provinceId;
    private String districtId;
    private String line1;

    public Address(String departmentId, String provinceId, String districtId, String line1) {
        this.departmentId = departmentId;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.line1 = line1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(departmentId, address.departmentId) &&
               Objects.equals(provinceId, address.provinceId) &&
               Objects.equals(districtId, address.districtId) &&
               Objects.equals(line1, address.line1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, provinceId, districtId, line1);
    }
}
