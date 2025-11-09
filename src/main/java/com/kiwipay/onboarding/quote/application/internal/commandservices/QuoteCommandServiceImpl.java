package com.kiwipay.onboarding.quote.application.internal.commandservices;

import com.kiwipay.onboarding.client.domain.model.aggregates.Client;
import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.ClientRepository;
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

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class QuoteCommandServiceImpl implements QuoteCommandService {
    
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private ClientRepository clientRepository; // Inyectar ClientRepository

    @Override
    public QuoteResponse createQuote(Long clientId, QuoteCreateRequest request) {
        // Verificar si el cliente existe
        Client client = clientRepository.findById(clientId)
                .orElseThrow(QuoteBusinessException::clientNotFound);

        // Validar que el documentNumber del cliente coincide con el de la solicitud
        if (!client.getDocumentNumber().equals(request.getDocumentNumber())) {
            throw QuoteBusinessException.invalidDocumentNumber();
        }
        Quote quote = new Quote(
            request.getDocumentType(),
            request.getDocumentNumber(),
            request.getMonthlyIncome(),
            request.getBranchId(),
            clientId
        );
        
        Quote savedQuote = quoteRepository.save(quote);
        QuoteResponse response = new QuoteResponse();
        BeanUtils.copyProperties(savedQuote, response);
        return response;
    }

    @Override
    public QuoteResponse updateQuote(Long quoteId, QuoteCreateRequest request) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(QuoteBusinessException::quoteNotFound);

        quote.setDocumentType(request.getDocumentType());
        quote.setDocumentNumber(request.getDocumentNumber());
        quote.setMonthlyIncome(request.getMonthlyIncome());
        quote.setBranchId(request.getBranchId());

        Quote updatedQuote = quoteRepository.save(quote);
        QuoteResponse response = new QuoteResponse();
        BeanUtils.copyProperties(updatedQuote, response);
        return response;
    }

    @Override
    public QuoteResponse patchQuote(Long quoteId, Map<String, Object> updates) {
        return null;
    }


    @Override
    public void deleteQuote(Long quoteId) {
        if (!quoteRepository.existsById(quoteId)) {
            throw QuoteBusinessException.quoteNotFound();
        }
        quoteRepository.deleteById(quoteId);
    }
}
