package com.kiwipay.onboarding.document.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "document_types")
public class DocumentType {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    public DocumentType() {}

    public DocumentType(String id, String name) {
        this.id = id;
        this.name = name;
    }
}