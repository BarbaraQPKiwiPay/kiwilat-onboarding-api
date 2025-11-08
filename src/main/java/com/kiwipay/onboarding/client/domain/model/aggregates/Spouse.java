package com.kiwipay.onboarding.client.domain.model.aggregates;

import com.kiwipay.onboarding.client.domain.model.valueobjects.DocumentType;
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
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_id"}),
    @UniqueConstraint(columnNames = {"document_number"})
})
public class Spouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "client_id", nullable = false, unique = true)
    private Long clientId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;
    
    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;
    
    @Column(nullable = false)
    private String firstNames;
    
    @Column(nullable = false)
    private String lastNames;
    
    private String email;
    private String phone;
    
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Spouse(Long clientId, DocumentType documentType, String documentNumber, 
                 String firstNames, String lastNames, String email, String phone) {
        this.clientId = clientId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.phone = phone;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spouse spouse = (Spouse) o;
        return Objects.equals(id, spouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}