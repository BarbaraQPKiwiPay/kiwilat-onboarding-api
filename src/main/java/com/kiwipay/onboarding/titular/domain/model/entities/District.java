package com.kiwipay.onboarding.titular.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class District {
    @Id
    private String id;
    private String name;

    @ManyToOne
    @JsonBackReference("prov-districts")
    private Province province;
}
