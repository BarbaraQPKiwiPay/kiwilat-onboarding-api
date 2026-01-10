package com.kiwipay.onboarding.keynua.application.internal.commandservices;

import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.partner.application.internal.dto.PartnerResponse;
import com.kiwipay.onboarding.client.domain.services.ClientQueryService;

import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
import com.kiwipay.onboarding.guarantor.domain.services.GuarantorQueryService;
import com.kiwipay.onboarding.keynua.application.internal.builder.KeynuaPayloadBuilder;
import com.kiwipay.onboarding.keynua.config.KeynuaProperties;
import com.kiwipay.onboarding.keynua.domain.model.aggregates.KeynuaContractEntity;
import com.kiwipay.onboarding.keynua.domain.services.KeynuaSigningService;
import com.kiwipay.onboarding.keynua.domain.services.SglDataProvider;
import com.kiwipay.onboarding.keynua.dto.SglLoanData;
import com.kiwipay.onboarding.keynua.dto.keynua.KeynuaContractResponse;
import com.kiwipay.onboarding.keynua.dto.request.CreateContractPayloadRequest;
import com.kiwipay.onboarding.keynua.dto.request.CreateContractRequest;
import com.kiwipay.onboarding.keynua.dto.response.ContractCreationResponse;
import com.kiwipay.onboarding.keynua.dto.response.ContractPayloadResponse;
import com.kiwipay.onboarding.keynua.infrastructure.client.KeynuaClient;
import com.kiwipay.onboarding.keynua.infrastructure.persistence.KeynuaContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * Keynua Signing Service Implementation
 * Handles contract payload generation and creation with idempotency
 */
@Service
public class KeynuaSigningServiceImpl implements KeynuaSigningService {

    private static final Logger log = LoggerFactory.getLogger(KeynuaSigningServiceImpl.class);

    private final KeynuaPayloadBuilder payloadBuilder;
    private final KeynuaClient keynuaClient;
    private final KeynuaContractRepository contractRepository;
    private final SglDataProvider sglDataProvider;
    private final KeynuaProperties keynuaProperties;

    // TODO: these services need to be adapted to fetch data by loanId
    // For now, this is a placeholder structure showing the integration points
    private final ClientQueryService clientQueryService;
    private final GuarantorQueryService guarantorQueryService;

    public KeynuaSigningServiceImpl(
            KeynuaPayloadBuilder payloadBuilder,
            KeynuaClient keynuaClient,
            KeynuaContractRepository contractRepository,
            SglDataProvider sglDataProvider,
            KeynuaProperties keynuaProperties,
            ClientQueryService clientQueryService,
            GuarantorQueryService guarantorQueryService) {
        this.payloadBuilder = payloadBuilder;
        this.keynuaClient = keynuaClient;
        this.contractRepository = contractRepository;
        this.sglDataProvider = sglDataProvider;
        this.keynuaProperties = keynuaProperties;
        this.clientQueryService = clientQueryService;
        this.guarantorQueryService = guarantorQueryService;
    }

    @Override
    public ContractPayloadResponse generateContractPayload(CreateContractPayloadRequest request) {
        log.info("Generating Keynua contract payload for loanId: {}, requestType: {}",
                request.getLoanId(), request.getRequestType());

        // Fetch SGL data
        SglLoanData sglData = sglDataProvider.getLoanData(request.getLoanId());

        // Fetch client and related data
        // TODO: Adapt this to fetch by loanId from the actual data model
        // For now, we'll throw an informative exception
        ClientDataBundle dataBundle = fetchClientDataBundle(request.getLoanId(), request.getRequestType());

        // Build payload
        KeynuaPayloadBuilder.PayloadResult result = payloadBuilder.buildPayload(
                request.getLoanId(),
                request.getRequestType(),
                keynuaProperties.getDocumentTemplateId(),
                dataBundle.client,
                dataBundle.clientPartner,
                dataBundle.guarantors,
                dataBundle.guarantorPartners,
                sglData);

        log.info("Payload generated successfully. Missing fields: {}", result.getMissingFields());

        return new ContractPayloadResponse(result.getPayload(), result.getMissingFields());
    }

    @Override
    @Transactional
    public ContractCreationResponse createContract(CreateContractRequest request) {
        log.info("Creating Keynua contract for loanId: {}, requestType: {}",
                request.getLoanId(), request.getRequestType());

        // Check idempotency: if an active contract exists for this loanId, return it
        List<String> activeStatuses = Arrays.asList("CREATED", "PENDING", "SIGNED");
        Optional<KeynuaContractEntity> existingContract = contractRepository
                .findByLoanIdAndStatusIn(request.getLoanId(), activeStatuses);

        if (existingContract.isPresent()) {
            log.info("Active contract already exists for loanId: {}. Returning existing contract.",
                    request.getLoanId());
            return buildResponseFromEntity(existingContract.get());
        }

        // Generate payload
        ContractPayloadResponse payloadResponse = generateContractPayload(
                new CreateContractPayloadRequest(
                        request.getLoanId(),
                        request.getRequestType(),
                        request.getUseMockSgl()));

        // Validate critical fields
        if (hasCriticalMissingFields(payloadResponse.getMissingFields())) {
            String missingFieldsStr = String.join(", ", payloadResponse.getMissingFields());
            log.error("Cannot create contract due to critical missing fields: {}", missingFieldsStr);
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot create contract. Missing required fields: " + missingFieldsStr);
        }

        // Call Keynua API
        log.info("=== PAYLOAD BEING SENT TO KEYNUA ===");
        log.debug("Request type: {}, Loan ID: {}", request.getRequestType(), request.getLoanId());

        KeynuaContractResponse keynuaResponse = keynuaClient.createContract(
                (com.kiwipay.onboarding.keynua.dto.keynua.KeynuaContractRequest) payloadResponse.getPayload());

        log.info("=== KEYNUA API RESPONSE ===");
        log.info("Contract ID: {}", keynuaResponse.getContractId());
        log.info("Status: {}", keynuaResponse.getStatus());
        log.info("Short Code: {}", keynuaResponse.getShortCode());
        log.info("Users count: {}", keynuaResponse.getUsers() != null ? keynuaResponse.getUsers().size() : 0);

        // Validate Keynua response
        if (keynuaResponse == null) {
            log.error("Keynua API returned null response for loanId: {}", request.getLoanId());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Keynua API returned null response");
        }

        if (keynuaResponse.getContractId() == null || keynuaResponse.getContractId().isEmpty()) {
            log.error("Keynua API returned response without contractId for loanId: {}", request.getLoanId());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Keynua API did not return a valid contractId");
        }

        log.info("Keynua contract created successfully: contractId={}", keynuaResponse.getContractId());

        // Extract signer token from users array (first user is main signer)
        String signerToken = null;
        if (keynuaResponse.getUsers() != null && !keynuaResponse.getUsers().isEmpty()) {
            KeynuaContractResponse.KeynuaUserResponse mainSigner = keynuaResponse.getUsers().get(0);
            signerToken = mainSigner.getToken();
            log.info("Extracted signer token from users array for contractId: {}", keynuaResponse.getContractId());
        } else {
            log.warn("No users found in Keynua response for contractId: {}", keynuaResponse.getContractId());
        }

        // Persist entity
        KeynuaContractEntity entity = new KeynuaContractEntity();
        entity.setLoanId(request.getLoanId());
        entity.setContractId(keynuaResponse.getContractId());
        entity.setTemplateId(keynuaProperties.getDocumentTemplateId());
        entity.setRequestType(request.getRequestType());
        entity.setStatus(keynuaResponse.getStatus() != null ? keynuaResponse.getStatus() : "CREATED");
        entity.setShortCode(keynuaResponse.getShortCode());
        entity.setSignerToken(signerToken);

        contractRepository.save(entity);
        log.info("Contract entity persisted in database");

        // Build and return response with signUrl
        return buildResponseFromKeynuaResponse(keynuaResponse, signerToken);
    }

    /**
     * Fetches client data bundle including client, spouse, guarantors, and
     * guarantor spouses
     * 
     * TODO: This is currently using MOCK DATA for testing
     * Replace this with real database queries when ready for production
     */
    private ClientDataBundle fetchClientDataBundle(String loanId,
            com.kiwipay.onboarding.keynua.dto.request.RequestType requestType) {

        log.info("Fetching client data bundle for loanId: {} (USING MOCK DATA)", loanId);

        // Create mock client
        ClientResponse client = createMockClient(loanId);

        // Create mock client partner (optional - can be null)
        PartnerResponse clientPartner = createMockClientPartner();

        // Create mock guarantors if MULTIPLE_SIGNERS
        List<GuarantorResponse> guarantors = new ArrayList<>();
        Map<Long, PartnerResponse> guarantorPartners = new HashMap<>();

        if (requestType == com.kiwipay.onboarding.keynua.dto.request.RequestType.MULTIPLE_SIGNERS) {
            // Add 2 mock guarantors
            GuarantorResponse guarantor1 = createMockGuarantor(101L, 201L);
            GuarantorResponse guarantor2 = createMockGuarantor(102L, 202L);
            guarantors.add(guarantor1);
            guarantors.add(guarantor2);

            // Add partner for first guarantor
            guarantorPartners.put(101L, createMockGuarantorPartner(101L));
        }

        return new ClientDataBundle(client, clientPartner, guarantors, guarantorPartners);
    }

    private ClientResponse createMockClient(String loanId) {
        ClientResponse client = new ClientResponse();
        client.setId(1L);
        client.setDocumentType("DNI");
        client.setDocumentNumber("12345678");
        client.setFirstNames("Juan Carlos");
        client.setLastNames("Pérez García");
        client.setMaritalStatus("MARRIED");
        client.setGender("M");
        client.setBirthDate("1985-05-15");
        client.setEmail("juan.perez@example.com");
        client.setPhone("+51987654321");
        client.setStatus("ACTIVE");

        ClientResponse.AddressDto address = new ClientResponse.AddressDto();
        address.setLine1("Av. Los Olivos 123");
        address.setDepartmentId("15");
        address.setProvinceId("01");
        address.setDistrictId("01");
        client.setAddress(address);

        return client;
    }

    private PartnerResponse createMockClientPartner() {
        PartnerResponse partner = new PartnerResponse();
        partner.setId(2L);
        partner.setLoanId(1L);
        partner.setPartnerType(com.kiwipay.onboarding.partner.domain.model.valueobjects.PartnerType.CLIENT);
        partner.setClientId(1L);
        partner.setDocumentType(com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType.DNI);
        partner.setDocumentNumber("87654321");
        partner.setFirstNames("María Elena");
        partner.setLastNames("Torres Vega");
        partner.setEmail("maria.torres@example.com");
        partner.setPhone("+51912345678");
        return partner;
    }

    private GuarantorResponse createMockGuarantor(Long guarantorId, Long loanId) {
        GuarantorResponse guarantor = new GuarantorResponse();
        guarantor.setId(guarantorId);
        guarantor.setLoanId(loanId);
        guarantor.setDocumentType(com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType.DNI);
        guarantor.setDocumentNumber("111" + String.format("%03d", guarantorId));
        guarantor.setFirstNames("Garante " + guarantorId);
        guarantor.setLastNames("Apellido " + guarantorId);
        guarantor.setEmail("garantor" + guarantorId + "@example.com");
        guarantor.setPhone("+51900000" + String.format("%03d", guarantorId));
        guarantor.setMaritalStatus(
                com.kiwipay.onboarding.shared.domain.valueobjects.MaritalStatus.MARRIED);
        return guarantor;
    }

    private PartnerResponse createMockGuarantorPartner(Long guarantorId) {
        PartnerResponse partner = new PartnerResponse();
        partner.setId(guarantorId + 100L);
        partner.setLoanId(200L + guarantorId);
        partner.setPartnerType(com.kiwipay.onboarding.partner.domain.model.valueobjects.PartnerType.GUARANTOR);
        partner.setGuarantorId(guarantorId);
        partner.setDocumentType(com.kiwipay.onboarding.shared.domain.valueobjects.DocumentType.DNI);
        partner.setDocumentNumber("222" + String.format("%03d", guarantorId));
        partner.setFirstNames("Cónyuge Garante");
        partner.setLastNames("Apellido " + guarantorId);
        partner.setEmail("spouse" + guarantorId + "@example.com");
        partner.setPhone("+51900001" + String.format("%03d", guarantorId));
        return partner;
    }

    private boolean hasCriticalMissingFields(List<String> missingFields) {
        if (missingFields == null || missingFields.isEmpty()) {
            return false;
        }

        // Define critical fields that should block contract creation
        List<String> criticalFields = Arrays.asList(
                "client.email",
                "client.documentNumber");

        return missingFields.stream()
                .anyMatch(criticalFields::contains);
    }

    private ContractCreationResponse buildResponseFromEntity(KeynuaContractEntity entity) {
        String signUrl = null;
        if (entity.getSignerToken() != null && keynuaProperties.getSignerBaseUrl() != null) {
            signUrl = keynuaProperties.getSignerBaseUrl() + "/" + entity.getSignerToken();
        }

        return new ContractCreationResponse(
                entity.getContractId(),
                entity.getShortCode(),
                entity.getSignerToken(),
                signUrl,
                entity.getStatus());
    }

    private ContractCreationResponse buildResponseFromKeynuaResponse(KeynuaContractResponse keynuaResponse,
            String signerToken) {
        // Construct signUrl if we have a token
        String signUrl = null;
        if (signerToken != null && keynuaProperties.getWebappUrl() != null) {
            // Keynua format: https://sign.stg.keynua.com/index.html?token={token}
            signUrl = keynuaProperties.getWebappUrl() + "/index.html?token=" + signerToken;
            log.info("Constructed signUrl: {}", signUrl);
        } else if (signerToken != null && keynuaProperties.getSignerBaseUrl() != null) {
            // Fallback to legacy signerBaseUrl
            signUrl = keynuaProperties.getSignerBaseUrl() + "/index.html?token=" + signerToken;
            log.info("Constructed signUrl using legacy signerBaseUrl: {}", signUrl);
        } else {
            log.warn("Cannot construct signUrl: signerToken={}, webappUrl={}, signerBaseUrl={}",
                    signerToken, keynuaProperties.getWebappUrl(), keynuaProperties.getSignerBaseUrl());
        }

        return new ContractCreationResponse(
                keynuaResponse.getContractId(),
                keynuaResponse.getShortCode(),
                signerToken, // ✅ USE THE EXTRACTED TOKEN, NOT keynuaResponse.getSignerToken()
                signUrl,
                keynuaResponse.getStatus());
    }

    /**
     * Helper class to bundle client data
     */
    private static class ClientDataBundle {
        ClientResponse client;
        PartnerResponse clientPartner;
        List<GuarantorResponse> guarantors;
        Map<Long, PartnerResponse> guarantorPartners;

        ClientDataBundle(ClientResponse client, PartnerResponse clientPartner,
                List<GuarantorResponse> guarantors,
                Map<Long, PartnerResponse> guarantorPartners) {
            this.client = client;
            this.clientPartner = clientPartner;
            this.guarantors = guarantors;
            this.guarantorPartners = guarantorPartners;
        }
    }
}
