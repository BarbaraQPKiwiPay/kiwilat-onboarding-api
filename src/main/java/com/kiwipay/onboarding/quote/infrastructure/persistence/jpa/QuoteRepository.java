package com.kiwipay.onboarding.quote.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.quote.domain.model.aggregates.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    /**
     * Find all quotes associated with a specific loan.
     * 
     * @param loanId the loan ID
     * @return list of quotes for the loan
     */
    List<Quote> findByLoanId(Long loanId);

    /**
     * Check if a quote belongs to a specific loan.
     * 
     * @param loanId  the loan ID
     * @param quoteId the quote ID
     * @return true if the quote exists and belongs to the loan
     */
    boolean existsByLoanIdAndId(Long loanId, Long quoteId);
}
