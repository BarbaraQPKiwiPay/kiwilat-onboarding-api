package com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.guarantor.domain.model.entities.Spouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuarantorSpouseRepository extends JpaRepository<Spouse, Long> {
    
    Optional<Spouse> findByGuarantorId(String guarantorId);
    
    boolean existsByGuarantorId(String guarantorId);
    
    void deleteByGuarantorId(String guarantorId);
    
    boolean existsByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
    
    boolean existsByDocumentTypeAndDocumentNumberAndGuarantorIdNot(String documentType, String documentNumber, String guarantorId);
}