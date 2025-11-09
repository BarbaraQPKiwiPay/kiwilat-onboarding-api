package com.kiwipay.onboarding.quote.domain.services;

import com.kiwipay.onboarding.quote.application.internal.dto.QuoteResponse;
import java.util.List;

public interface QuoteQueryService {
    QuoteResponse getQuoteById(Long quoteId);
    List<QuoteResponse> getQuotesByClientId(Long clientId);
    List<QuoteResponse> getAllQuotes();
}
