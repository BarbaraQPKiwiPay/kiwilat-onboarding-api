package com.kiwipay.onboarding.titular.application.internal.commandservices;

import com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kiwipay.onboarding.titular.application.internal.dto.ClientCreateRequest;
import com.kiwipay.onboarding.titular.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.client.domain.model.aggregates.Client;
import com.kiwipay.onboarding.client.domain.model.entities.Address;
import com.kiwipay.onboarding.client.domain.model.valueobjects.DocumentType;
import com.kiwipay.onboarding.client.domain.model.valueobjects.Gender;
import com.kiwipay.onboarding.client.domain.model.valueobjects.MaritalStatus;
import com.kiwipay.onboarding.client.domain.services.ClientCommandService;
import com.kiwipay.onboarding.titular.application.internal.dto.ClientUpdateRequest;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientResponse createClient(ClientCreateRequest request) {
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
			Gender.valueOf(request.getGender()),
			LocalDate.parse(request.getBirthDate()),
			request.getEmail(),
			request.getPhone(),
			address,
			OffsetDateTime.now(),
			null
		);
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
		existingClient.setFirstNames(request.getFirstNames());
		existingClient.setLastNames(request.getLastNames());
		existingClient.setMaritalStatus(MaritalStatus.valueOf(request.getMaritalStatus()));
		existingClient.setGender(Gender.valueOf(request.getGender()));
		existingClient.setBirthDate(LocalDate.parse(request.getBirthDate()));
		existingClient.setEmail(request.getEmail());
		existingClient.setPhone(request.getPhone());
		existingClient.setUpdatedAt(OffsetDateTime.now());

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
		return response;
	}
}
