package com.kiwipay.onboarding.quote.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.quote.domain.model.aggregates.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByClientId(Long clientId);
    boolean existsByClientIdAndId(Long clientId, Long quoteId);
}
