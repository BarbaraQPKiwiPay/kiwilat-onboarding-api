package com.kiwipay.onboarding.catalog.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * District Entity
 * Represents a geographic district (third administrative level in Peru)
 * Belongs to a Province
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class District {
    @Id
    private String id;

    private String name;

    @Column(name = "province_id")
    private String provinceId;

    public District(String id, String name, String provinceId) {
        this.id = id;
        this.name = name;
        this.provinceId = provinceId;
    }
}
