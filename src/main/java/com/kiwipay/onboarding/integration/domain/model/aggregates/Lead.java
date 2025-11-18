package com.kiwipay.onboarding.integration.domain.model.aggregates;

import com.kiwipay.onboarding.integration.domain.model.entities.LeadQuotation;
import com.kiwipay.onboarding.integration.domain.model.exceptions.IntegrationBusinessException;
import com.kiwipay.onboarding.client.domain.model.valueobjects.DocumentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
    name = "leads",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sgl_lead_id"}),
        @UniqueConstraint(columnNames = {"document_number"})
    }
)
public class Lead {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // ID original de SGL para trazabilidad
    @Column(name = "sgl_lead_id", nullable = false, unique = true)
    private Long sglLeadId;
    
    // Datos básicos del cliente (Pantalla Datos clientes)
    @Column(name = "tdd_tipo_documento", nullable = false)
    private Integer tddTipoDocumento;
    
    @Column(name = "document_number", nullable = false)
    private String documentNumber;
    
    @Column(name = "first_names", nullable = false)
    private String firstNames;
    
    @Column(name = "paternal_surname")
    private String paternalSurname;
    
    @Column(name = "maternal_surname")
    private String maternalSurname;
    
    @Column(name = "marital_status")
    private String maritalStatus;
    
    private String email;
    
    @Column(name = "cell_phone")
    private String cellPhone;
    
    private String phone;
    
    private String address;
    
    // Datos clínicos (Pantalla Datos clínicas)
    @Column(name = "medical_interest")
    private String medicalInterest;
    
    @Column(name = "tdd_sede")
    private Integer tddSede;
    
    @Column(name = "sede_lead")
    private String sedeLead;
    
    @Column(name = "monthly_income", precision = 10, scale = 2)
    private BigDecimal monthlyIncome;
    
    // Datos adicionales para cotizador y riesgo
    @Column(name = "laboral_situation")
    private String laboralSituation;
    
    private Integer age;
    
    // Datos adicionales del lead
    @Column(name = "spouse_names")
    private String spouseNames;
    
    // Metadatos de integración
    @Column(name = "migration_origin_id")
    private Integer migrationOriginId;
    
    @Column(name = "registered_at")
    private LocalDateTime registeredAt;
    
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;
    
    // Timestamps de la entidad
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
    
    // Relación con cotizaciones - OneToMany
    @OneToMany(mappedBy = "lead", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LeadQuotation> quotations = new ArrayList<>();
    
    // Constructor principal para crear desde integración
    public Lead(Long sglLeadId, Integer tddTipoDocumento, String documentNumber, String firstNames,
                String paternalSurname, String maternalSurname, String maritalStatus, String email,
                String cellPhone, String phone, String address, String medicalInterest, Integer tddSede,
                String sedeLead, BigDecimal monthlyIncome, String laboralSituation, Integer age,
                String spouseNames, Integer migrationOriginId, LocalDateTime registeredAt,
                LocalDateTime lastModifiedAt) {
        
        // Validaciones de dominio
        validateRequiredFields(documentNumber, firstNames, email);
        validateDocumentType(tddTipoDocumento);
        
        this.sglLeadId = sglLeadId;
        this.tddTipoDocumento = tddTipoDocumento;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.maritalStatus = maritalStatus;
        this.email = email;
        this.cellPhone = cellPhone;
        this.phone = phone;
        this.address = address;
        this.medicalInterest = medicalInterest;
        this.tddSede = tddSede;
        this.sedeLead = sedeLead;
        this.monthlyIncome = monthlyIncome;
        this.laboralSituation = laboralSituation;
        this.age = age;
        this.spouseNames = spouseNames;
        this.migrationOriginId = migrationOriginId;
        this.registeredAt = registeredAt;
        this.lastModifiedAt = lastModifiedAt;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = null;
    }
    
    // El factory method se creará en el servicio de aplicación para evitar dependencias circulares
    
    // Método para agregar cotización
    public void addQuotation(LeadQuotation quotation) {
        quotation.setLead(this);
        this.quotations.add(quotation);
    }
    
    // Método para obtener nombre completo
    public String getFullName() {
        StringBuilder fullName = new StringBuilder(firstNames);
        if (paternalSurname != null && !paternalSurname.trim().isEmpty()) {
            fullName.append(" ").append(paternalSurname);
        }
        if (maternalSurname != null && !maternalSurname.trim().isEmpty()) {
            fullName.append(" ").append(maternalSurname);
        }
        return fullName.toString();
    }
    
    // Obtener tipo de documento como enum
    public DocumentType getDocumentType() {
        return switch (tddTipoDocumento) {
            case 1 -> DocumentType.DNI;
            case 2 -> DocumentType.CE;
            case 3 -> DocumentType.PASS;
            case 4 -> DocumentType.RUC;
            default -> throw IntegrationBusinessException.invalidDocumentType();
        };
    }
    
    // Validaciones privadas de dominio
    private void validateRequiredFields(String documentNumber, String firstNames, String email) {
        if (documentNumber == null || documentNumber.trim().isEmpty() ||
            firstNames == null || firstNames.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {
            throw IntegrationBusinessException.missingRequiredFields();
        }
    }
    
    private void validateDocumentType(Integer tddTipoDocumento) {
        if (tddTipoDocumento == null || tddTipoDocumento < 1 || tddTipoDocumento > 4) {
            throw IntegrationBusinessException.invalidDocumentType();
        }
    }
    
    // Método para actualizar timestamps
    public void markAsUpdated() {
        this.updatedAt = OffsetDateTime.now();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lead lead = (Lead) o;
        return Objects.equals(id, lead.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}