package com.kiwipay.onboarding.quote.application.internal.commandservices;

import com.kiwipay.onboarding.loan.domain.model.aggregates.Loan;
import com.kiwipay.onboarding.loan.infrastructure.persistence.jpa.LoanRepository;
import com.kiwipay.onboarding.quote.application.internal.dto.QuoteCreateRequest;
import com.kiwipay.onboarding.quote.application.internal.dto.QuoteResponse;
import com.kiwipay.onboarding.quote.domain.model.aggregates.Quote;
import com.kiwipay.onboarding.quote.domain.model.exceptions.QuoteBusinessException;
import com.kiwipay.onboarding.quote.domain.services.QuoteCommandService;
import com.kiwipay.onboarding.quote.infrastructure.persistence.jpa.QuoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Implementation of QuoteCommandService.
 * Handles write operations for quotes.
 */
@Service
@Transactional
public class QuoteCommandServiceImpl implements QuoteCommandService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public QuoteResponse createQuote(Long loanId, QuoteCreateRequest request) {
        // Validate that the loan exists
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(QuoteBusinessException::loanNotFound);

        // Create new quote
        Quote quote = new Quote(
                loanId,
                request.getDocumentType(),
                request.getDocumentNumber(),
                request.getMonthlyIncome(),
                request.getBranchId());

        Quote savedQuote = quoteRepository.save(quote);
        QuoteResponse response = new QuoteResponse();
        BeanUtils.copyProperties(savedQuote, response);
        return response;
    }

    @Override
    public QuoteResponse updateQuote(Long quoteId, QuoteCreateRequest request) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(QuoteBusinessException::quoteNotFound);

        // Update fields
        quote.setDocumentType(request.getDocumentType());
        quote.setDocumentNumber(request.getDocumentNumber());
        quote.setMonthlyIncome(request.getMonthlyIncome());
        quote.setBranchId(request.getBranchId());

        // Validate updated quote
        quote.validate();

        Quote updatedQuote = quoteRepository.save(quote);
        QuoteResponse response = new QuoteResponse();
        BeanUtils.copyProperties(updatedQuote, response);
        return response;
    }

    @Override
    public QuoteResponse patchQuote(Long quoteId, Map<String, Object> updates) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(QuoteBusinessException::quoteNotFound);

        // Apply partial updates
        updates.forEach((key, value) -> {
            switch (key) {
                case "documentType":
                    quote.setDocumentType((String) value);
                    break;
                case "documentNumber":
                    quote.setDocumentNumber((String) value);
                    break;
                case "monthlyIncome":
                    if (value instanceof Number) {
                        quote.setMonthlyIncome(new BigDecimal(value.toString()));
                    }
                    break;
                case "branchId":
                    quote.setBranchId((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        // Validate after updates
        quote.validate();

        Quote updatedQuote = quoteRepository.save(quote);
        QuoteResponse response = new QuoteResponse();
        BeanUtils.copyProperties(updatedQuote, response);
        return response;
    }

    @Override
    public void deleteQuote(Long quoteId) {
        if (!quoteRepository.existsById(quoteId)) {
            throw QuoteBusinessException.quoteNotFound();
        }
        quoteRepository.deleteById(quoteId);
    }
}
