package com.kiwipay.onboarding.quote.domain.services;

import com.kiwipay.onboarding.quote.application.internal.dto.QuoteCreateRequest;
import com.kiwipay.onboarding.quote.application.internal.dto.QuoteResponse;
import java.util.Map;

public interface QuoteCommandService {
    QuoteResponse createQuote(Long clientId, QuoteCreateRequest request);
    QuoteResponse updateQuote(Long quoteId, QuoteCreateRequest request);
    QuoteResponse patchQuote(Long quoteId, Map<String, Object> updates);
    void deleteQuote(Long quoteId);
}
