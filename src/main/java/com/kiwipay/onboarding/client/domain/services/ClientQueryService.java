package com.kiwipay.onboarding.titular.domain.services;

import com.kiwipay.onboarding.titular.application.internal.dto.ClientResponse;
import java.util.List;

public interface ClientQueryService {
	ClientResponse getClientById(Long id);
	List<ClientResponse> getAllClients();
}
