package com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.client.domain.model.aggregates.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	boolean existsByDocumentNumber(String documentNumber);
	boolean existsByEmail(String email);
	Client findByDocumentNumber(String documentNumber);
}
