package com.kiwipay.onboarding.client.domain.services;

import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import java.util.List;

public interface ClientQueryService {
	ClientResponse getClientById(Long id);
	List<ClientResponse> getAllClients();
}
