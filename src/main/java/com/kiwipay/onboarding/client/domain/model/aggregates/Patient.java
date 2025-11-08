package com.kiwipay.onboarding.client.domain.model.aggregates;

import com.kiwipay.onboarding.client.domain.model.entities.Address;
import com.kiwipay.onboarding.client.domain.model.valueobjects.DocumentType;
import com.kiwipay.onboarding.client.domain.model.valueobjects.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Objects;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    private String phone;
    private String email;

    @Embedded
    private Address address;
    
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Patient(Long clientId, DocumentType documentType, String documentNumber, String firstNames, 
                  String lastNames, Gender gender, String phone, String email, Address address) {
        this.clientId = clientId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}