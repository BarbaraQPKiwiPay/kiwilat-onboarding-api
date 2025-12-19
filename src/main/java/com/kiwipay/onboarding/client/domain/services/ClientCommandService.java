package com.kiwipay.onboarding.client.domain.services;

import com.kiwipay.onboarding.client.application.internal.dto.ClientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.ClientStateChangeRequest;
import com.kiwipay.onboarding.client.application.internal.dto.ClientUpdateRequest;

public interface ClientCommandService {
	ClientResponse createClient(ClientCreateRequest request);
	ClientResponse updateClient(Long id, ClientUpdateRequest request);
	void deleteClient(Long id);
	
	// Métodos específicos para cambios de estado
	ClientResponse markDocumentosCompletados(Long clientId, ClientStateChangeRequest request);
	ClientResponse aprobarPorAdv(Long clientId, ClientStateChangeRequest request);
	ClientResponse observarPorAdv(Long clientId, ClientStateChangeRequest request);
	ClientResponse aprobarPorRiesgos(Long clientId, ClientStateChangeRequest request);
	ClientResponse rechazarPorRiesgos(Long clientId, ClientStateChangeRequest request);
	ClientResponse observarPorRiesgos(Long clientId, ClientStateChangeRequest request);
}
