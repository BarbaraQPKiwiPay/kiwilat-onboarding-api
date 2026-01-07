package com.kiwipay.onboarding.catalog.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Province Entity
 * Represents a geographic province (second administrative level in Peru)
 * Belongs to a Department
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Province {
    @Id
    private String id;

    private String name;

    @Column(name = "department_id")
    private String departmentId;

    public Province(String id, String name, String departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }
}
