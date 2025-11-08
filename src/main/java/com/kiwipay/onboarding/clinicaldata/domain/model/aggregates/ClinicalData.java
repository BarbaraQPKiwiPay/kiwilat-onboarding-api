package com.kiwipay.onboarding.clinicaldata.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_id"})
})
public class ClinicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false, unique = true)
    private Long clientId;

    @Column(name = "medical_category_id", nullable = false)
    private String medicalCategoryId;

    @Column(name = "clinic_id", nullable = false)
    private String clinicId;

    @Column(name = "branch_id", nullable = false)
    private String branchId;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public ClinicalData(Long clientId, String medicalCategoryId, String clinicId, String branchId) {
        this.clientId = clientId;
        this.medicalCategoryId = medicalCategoryId;
        this.clinicId = clinicId;
        this.branchId = branchId;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClinicalData that = (ClinicalData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}