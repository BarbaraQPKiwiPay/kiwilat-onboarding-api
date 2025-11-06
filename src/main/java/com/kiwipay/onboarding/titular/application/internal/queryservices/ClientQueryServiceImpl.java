package com.kiwipay.onboarding.titular.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kiwipay.onboarding.titular.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.titular.domain.model.aggregates.Client;
import com.kiwipay.onboarding.titular.domain.services.ClientQueryService;
import com.kiwipay.onboarding.titular.infrastructure.persistence.jpa.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

//handlers "read"
@Service
public class ClientQueryServiceImpl implements ClientQueryService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientResponse getClientById(Long id) {
		return clientRepository.findById(id)
			.map(this::toClientResponse)
			.orElse(null);
	}

	@Override
	public List<ClientResponse> getAllClients() {
		return clientRepository.findAll().stream()
			.map(this::toClientResponse)
			.collect(Collectors.toList());
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
