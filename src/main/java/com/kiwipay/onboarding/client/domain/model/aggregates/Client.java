package com.kiwipay.onboarding.client.domain.model.aggregates;

import com.kiwipay.onboarding.client.domain.model.entities.Address;
import com.kiwipay.onboarding.client.domain.model.valueobjects.DocumentType;
import com.kiwipay.onboarding.client.domain.model.valueobjects.Gender;
import com.kiwipay.onboarding.client.domain.model.valueobjects.MaritalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"documentNumber"}),
        @UniqueConstraint(columnNames = {"email"})
    }
)
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DocumentType documentType;
    @Column(unique = true, nullable = false)
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private MaritalStatus maritalStatus;
    private Gender gender;
    private LocalDate birthDate;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;

    // Embeddable
    @Embedded
    private Address address;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Client( DocumentType documentType, String documentNumber, String firstNames, String lastNames,
                  MaritalStatus maritalStatus, Gender gender, LocalDate birthDate, String email, String phone,
                  Address address, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.maritalStatus = maritalStatus;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
