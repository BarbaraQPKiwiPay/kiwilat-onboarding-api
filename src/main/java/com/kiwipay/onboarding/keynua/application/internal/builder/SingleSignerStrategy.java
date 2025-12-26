package com.kiwipay.onboarding.keynua.application.internal.builder;

import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.keynua.application.internal.util.CivilStatusMapper;
import com.kiwipay.onboarding.keynua.application.internal.util.DocumentTypeDetector;
import com.kiwipay.onboarding.keynua.application.internal.util.UniqueCodeGenerator;
import com.kiwipay.onboarding.keynua.dto.keynua.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * SingleSignerStrategy
 * Builds payload for single signer (client only) contracts
 */
@Component
public class SingleSignerStrategy implements SignerStrategy {

    private final com.kiwipay.onboarding.keynua.infrastructure.document.MockDocumentService mockDocumentService;

    public SingleSignerStrategy(
            com.kiwipay.onboarding.keynua.infrastructure.document.MockDocumentService mockDocumentService) {
        this.mockDocumentService = mockDocumentService;
    }

    @Override
    public KeynuaContractRequest buildPayload(PayloadBuilderContext context) {
        KeynuaContractRequest request = new KeynuaContractRequest();

        // Set basic contract info
        request.setTemplateId(context.getTemplateId());
        request.setTitle("Firma de contrato y validación biométrica");
        request.setDescription("Proceso de firma electrónica y validación biométrica para contrato de crédito");
        request.setLanguage("es");
        request.setUserEmailNotification(false);

        // Build users and prefilledItems
        List<KeynuaUser> users = new ArrayList<>();
        List<KeynuaPrefilledItem> prefilledItems = new ArrayList<>();

        ClientResponse client = context.getClient();

        // Validate client data
        validateAndAddClientUser(client, users, prefilledItems, context);

        request.setUsers(users);
        request.setPrefilledItems(prefilledItems);

        // Build cavaliData
        KeynuaCavaliData cavaliData = buildCavaliData(client, context);
        request.setFlags(new KeynuaFlags(cavaliData));

        // Add documents - Using mock PDFs with valid base64 content
        List<KeynuaDocument> documents = mockDocumentService.getMockContractDocuments(context.getLoanId());
        request.setDocuments(documents);

        return request;
    }

    private void validateAndAddClientUser(ClientResponse client, List<KeynuaUser> users,
            List<KeynuaPrefilledItem> prefilledItems,
            PayloadBuilderContext context) {
        // Validate required fields
        String email = client.getEmail();
        if (email == null || email.isEmpty()) {
            context.addMissingField("client.email");
            email = "noemail@example.com"; // Fallback
        }

        String documentNumber = client.getDocumentNumber();
        if (documentNumber == null || documentNumber.isEmpty()) {
            context.addMissingField("client.documentNumber");
            documentNumber = "00000000"; // Fallback
        }

        String firstNames = client.getFirstNames();
        if (firstNames == null || firstNames.isEmpty()) {
            context.addMissingField("client.firstNames");
            firstNames = "Sin Nombre";
        }

        String lastNames = client.getLastNames();
        if (lastNames == null || lastNames.isEmpty()) {
            context.addMissingField("client.lastNames");
            lastNames = "Sin Apellido";
        }

        // Create user
        KeynuaUser user = new KeynuaUser(
                firstNames,
                lastNames,
                email,
                documentNumber,
                List.of("firmante-1"));
        users.add(user);

        // Create prefilled item with target "4"
        String docType = DocumentTypeDetector.detectDocumentType(documentNumber);
        String docTitle = DocumentTypeDetector.getDocumentTitle(docType);

        KeynuaPrefilledItem prefilledItem = new KeynuaPrefilledItem(
                "4", // Always "4" as per requirements
                docTitle,
                new KeynuaDocumentValue(docType, documentNumber));
        prefilledItems.add(prefilledItem);
    }

    private KeynuaCavaliData buildCavaliData(ClientResponse client, PayloadBuilderContext context) {
        KeynuaCavaliData cavaliData = new KeynuaCavaliData();

        // Set fixed values as per legacy flow
        cavaliData.setBanking("4");
        cavaliData.setProduct("41");
        cavaliData.setUniqueCode(UniqueCodeGenerator.generate8DigitCode());

        // Set client data
        String clientName = (client.getFirstNames() != null ? client.getFirstNames() : "") + " " +
                (client.getLastNames() != null ? client.getLastNames() : "");
        cavaliData.setClientName(clientName.trim());

        // Set domicile (with fallback to "lima")
        String domicile = "lima"; // Default
        if (client.getAddress() != null && client.getAddress().getLine1() != null) {
            domicile = client.getAddress().getLine1();
        }
        cavaliData.setDomicile(domicile);

        // Set issue date (YYYY-MM-DD format)
        cavaliData.setIssueDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        cavaliData.setIssuePlace("Peru");

        // Set client with userId=0
        Integer civilStatus = CivilStatusMapper.mapToCavaliCivilStatus(client.getMaritalStatus());
        KeynuaCavaliClient cavaliClient = new KeynuaCavaliClient(0, civilStatus);
        cavaliData.setClient(cavaliClient);

        // No representatives or guarantees for single signer
        cavaliData.setRepresentatives(null);
        cavaliData.setGuarantees(null);

        return cavaliData;
    }
}
