package com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories;

import com.kiwipay.onboarding.client.domain.model.aggregates.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
