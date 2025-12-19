package com.kiwipay.onboarding.client.application.internal.commandservices;

import com.kiwipay.onboarding.client.domain.model.exceptions.ClientBusinessException;
import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kiwipay.onboarding.client.application.internal.dto.ClientCreateRequest;
import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.ClientStateChangeRequest;
import com.kiwipay.onboarding.client.domain.model.aggregates.Client;
import com.kiwipay.onboarding.client.domain.model.entities.Address;
import com.kiwipay.onboarding.client.domain.model.valueobjects.DocumentType;
import com.kiwipay.onboarding.client.domain.model.valueobjects.Gender;
import com.kiwipay.onboarding.client.domain.model.valueobjects.MaritalStatus;
import com.kiwipay.onboarding.client.domain.services.ClientCommandService;
import com.kiwipay.onboarding.client.domain.services.ClientStateService;
import com.kiwipay.onboarding.client.application.internal.dto.ClientUpdateRequest;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientStateService clientStateService;

	@Override
	public ClientResponse createClient(ClientCreateRequest request) {
		// Validar unicidad de documento y email
		if (clientRepository.existsByDocumentNumber(request.getDocumentNumber())) {
			throw ClientBusinessException.documentAlreadyExists();
		}
		if (clientRepository.existsByEmail(request.getEmail())) {
			throw ClientBusinessException.emailAlreadyExists();
		}
		// Validar gender
		Gender gender;
		try {
			gender = Gender.valueOf(request.getGender());
		} catch (IllegalArgumentException | NullPointerException e) {
			throw ClientBusinessException.invalidGender();
		}
		Address address = new Address(
			request.getAddress().getDepartmentId(),
			request.getAddress().getProvinceId(),
			request.getAddress().getDistrictId(),
			request.getAddress().getLine1()
		);
		Client client = new Client(
			DocumentType.valueOf(request.getDocumentType()),
			request.getDocumentNumber(),
			request.getFirstNames(),
			request.getLastNames(),
			MaritalStatus.valueOf(request.getMaritalStatus()),
			gender,
			LocalDate.parse(request.getBirthDate()),
			request.getEmail(),
			request.getPhone(),
			address,
			OffsetDateTime.now(),
			null
		);

        // Setear si sufre de algún padecimiento
        client.setSuffersCondition(request.getSuffersCondition());
		// Persistir el cliente
		client = clientRepository.save(client);

		// Construir respuesta usando método helper
		return toClientResponse(client);
	}

	@Override
	public ClientResponse updateClient(Long id, ClientUpdateRequest request) {
		Client existingClient = clientRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

		// Actualizar campos
		if (request.getDocumentType() != null) {
			existingClient.setDocumentType(DocumentType.valueOf(request.getDocumentType()));
		}
		if (request.getDocumentNumber() != null) {
			existingClient.setDocumentNumber(request.getDocumentNumber());
		}
		existingClient.setFirstNames(request.getFirstNames());
		existingClient.setLastNames(request.getLastNames());
		existingClient.setMaritalStatus(MaritalStatus.valueOf(request.getMaritalStatus()));
		existingClient.setGender(Gender.valueOf(request.getGender()));
		existingClient.setBirthDate(LocalDate.parse(request.getBirthDate()));
		existingClient.setEmail(request.getEmail());
		existingClient.setPhone(request.getPhone());
		existingClient.setUpdatedAt(OffsetDateTime.now());

		// Setear si sufre de algún padecimiento
		existingClient.setSuffersCondition(request.getSuffersCondition());

		if (request.getAddress() != null) {
			Address updatedAddress = new Address(
				request.getAddress().getDepartmentId(),
				request.getAddress().getProvinceId(),
				request.getAddress().getDistrictId(),
				request.getAddress().getLine1()
			);
			existingClient.setAddress(updatedAddress);
		}

		existingClient = clientRepository.save(existingClient);
		return toClientResponse(existingClient);
	}

	@Override
	public void deleteClient(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new RuntimeException("Client not found with id: " + id);
		}
		clientRepository.deleteById(id);
	}

	private ClientResponse toClientResponse(Client client) {
		ClientResponse response = new ClientResponse();
		response.setId(client.getId());
		response.setDocumentType(client.getDocumentType().name());
		response.setDocumentNumber(client.getDocumentNumber());
		response.setFirstNames(client.getFirstNames());
		response.setLastNames(client.getLastNames());
		response.setMaritalStatus(client.getMaritalStatus().name());
		response.setGender(client.getGender().name());
		response.setBirthDate(client.getBirthDate().toString());
		response.setEmail(client.getEmail());
		response.setPhone(client.getPhone());
		
		if (client.getAddress() != null) {
			ClientResponse.AddressDto addressDto = new ClientResponse.AddressDto();
			addressDto.setDepartmentId(client.getAddress().getDepartmentId());
			addressDto.setProvinceId(client.getAddress().getProvinceId());
			addressDto.setDistrictId(client.getAddress().getDistrictId());
			addressDto.setLine1(client.getAddress().getLine1());
			response.setAddress(addressDto);
		}
		
		response.setCreatedAt(client.getCreatedAt().toString());

        // Exponer si sufre de algún padecimiento
        response.setSuffersCondition(client.getSuffersCondition());
        
        // Estado y acciones permitidas
        response.setStatus(client.getStatus().name());
        response.setAllowedActions(clientStateService.getAllowedActions(client.getStatus()));
        
		return response;
	}
	
	@Override
	public ClientResponse markDocumentosCompletados(Long clientId, ClientStateChangeRequest request) {
		Client client = clientRepository.findById(clientId)
			.orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
			
		clientStateService.markDocumentosCompletados(client, request.getReason());
		client = clientRepository.save(client);
		return toClientResponse(client);
	}
	
	@Override
	public ClientResponse aprobarPorAdv(Long clientId, ClientStateChangeRequest request) {
		Client client = clientRepository.findById(clientId)
			.orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
			
		clientStateService.aprobarPorAdv(client, request.getReason());
		client = clientRepository.save(client);
		return toClientResponse(client);
	}
	
	@Override
	public ClientResponse observarPorAdv(Long clientId, ClientStateChangeRequest request) {
		Client client = clientRepository.findById(clientId)
			.orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
			
		clientStateService.observarPorAdv(client, request.getReason());
		client = clientRepository.save(client);
		return toClientResponse(client);
	}
	
	@Override
	public ClientResponse aprobarPorRiesgos(Long clientId, ClientStateChangeRequest request) {
		Client client = clientRepository.findById(clientId)
			.orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
			
		clientStateService.aprobarPorRiesgos(client, request.getReason());
		client = clientRepository.save(client);
		return toClientResponse(client);
	}
	
	@Override
	public ClientResponse rechazarPorRiesgos(Long clientId, ClientStateChangeRequest request) {
		Client client = clientRepository.findById(clientId)
			.orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
			
		clientStateService.rechazarPorRiesgos(client, request.getReason());
		client = clientRepository.save(client);
		return toClientResponse(client);
	}
	
	@Override
	public ClientResponse observarPorRiesgos(Long clientId, ClientStateChangeRequest request) {
		Client client = clientRepository.findById(clientId)
			.orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
			
		clientStateService.observarPorRiesgos(client, request.getReason());
		client = clientRepository.save(client);
		return toClientResponse(client);
	}
}
