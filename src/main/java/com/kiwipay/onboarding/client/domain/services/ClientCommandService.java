package com.kiwipay.onboarding.client.domain.services;

import com.kiwipay.onboarding.client.application.internal.dto.ClientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.ClientUpdateRequest;

public interface ClientCommandService {
	ClientResponse createClient(ClientCreateRequest request);
	ClientResponse updateClient(Long id, ClientUpdateRequest request);
	void deleteClient(Long id);
}
