package com.kiwipay.onboarding.quote.application.internal.queryservices;

import com.kiwipay.onboarding.quote.application.internal.dto.QuoteResponse;
import com.kiwipay.onboarding.quote.domain.model.aggregates.Quote;
import com.kiwipay.onboarding.quote.domain.model.exceptions.QuoteBusinessException;
import com.kiwipay.onboarding.quote.domain.services.QuoteQueryService;
import com.kiwipay.onboarding.quote.infrastructure.persistence.jpa.QuoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of QuoteQueryService.
 * Handles read operations for quotes.
 */
@Service
public class QuoteQueryServiceImpl implements QuoteQueryService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public QuoteResponse getQuoteById(Long quoteId) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(QuoteBusinessException::quoteNotFound);

        QuoteResponse response = new QuoteResponse();
        BeanUtils.copyProperties(quote, response);
        return response;
    }

    @Override
    public List<QuoteResponse> getQuotesByLoanId(Long loanId) {
        List<Quote> quotes = quoteRepository.findByLoanId(loanId);
        return quotes.stream()
                .map(quote -> {
                    QuoteResponse response = new QuoteResponse();
                    BeanUtils.copyProperties(quote, response);
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<QuoteResponse> getAllQuotes() {
        List<Quote> quotes = quoteRepository.findAll();
        return quotes.stream()
                .map(quote -> {
                    QuoteResponse response = new QuoteResponse();
                    BeanUtils.copyProperties(quote, response);
                    return response;
                })
                .collect(Collectors.toList());
    }
}
