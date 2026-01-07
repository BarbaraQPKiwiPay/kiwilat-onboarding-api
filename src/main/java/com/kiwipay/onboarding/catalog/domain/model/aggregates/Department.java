package com.kiwipay.onboarding.catalog.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Department Aggregate Root
 * Represents a geographic department (first administrative level in Peru)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    private String id;

    private String name;

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
