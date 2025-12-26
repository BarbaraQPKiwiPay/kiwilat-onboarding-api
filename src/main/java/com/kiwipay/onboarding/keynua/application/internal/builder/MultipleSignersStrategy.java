package com.kiwipay.onboarding.keynua.application.internal.builder;

import com.kiwipay.onboarding.client.application.internal.dto.ClientResponse;
import com.kiwipay.onboarding.client.application.internal.dto.SpouseResponse;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
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
 * MultipleSignersStrategy
 * Builds payload for multiple signers (client + spouse + guarantors + guarantor
 * spouses)
 */
@Component
public class MultipleSignersStrategy implements SignerStrategy {

    private final com.kiwipay.onboarding.keynua.infrastructure.document.MockDocumentService mockDocumentService;

    public MultipleSignersStrategy(
            com.kiwipay.onboarding.keynua.infrastructure.document.MockDocumentService mockDocumentService) {
        this.mockDocumentService = mockDocumentService;
    }

    @Override
    public KeynuaContractRequest buildPayload(PayloadBuilderContext context) {
        KeynuaContractRequest request = new KeynuaContractRequest();

        // Set basic contract info (same as single signer)
        request.setTemplateId(context.getTemplateId());
        request.setTitle("Firma de contrato y validación biométrica");
        request.setDescription("Proceso de firma electrónica y validación biométrica para contrato de crédito");
        request.setLanguage("es");
        request.setUserEmailNotification(false);

        // For multiple signers
        request.setExpirationInHours(0);
        request.setDisableNotification(true);

        // Build users and prefilledItems
        List<KeynuaUser> users = new ArrayList<>();
        List<KeynuaPrefilledItem> prefilledItems = new ArrayList<>();

        // Track userId incrementally
        int[] currentUserId = { 0 }; // Using array to allow modification in lambda/method

        ClientResponse client = context.getClient();

        // Add main client (always userId=0)
        addUser(client.getFirstNames(), client.getLastNames(), client.getEmail(),
                client.getDocumentNumber(), users, prefilledItems, context);
        currentUserId[0]++;

        // Add client spouse/representative if exists
        List<KeynuaCavaliRepresentative> clientRepresentatives = new ArrayList<>();
        if (context.getClientSpouse() != null) {
            SpouseResponse spouse = context.getClientSpouse();
            addUser(spouse.getFirstNames(), spouse.getLastNames(), spouse.getEmail(),
                    spouse.getDocumentNumber(), users, prefilledItems, context);
            clientRepresentatives.add(new KeynuaCavaliRepresentative(currentUserId[0]));
            currentUserId[0]++;
        }

        // Build guarantees list
        List<KeynuaCavaliGuarantee> guarantees = new ArrayList<>();

        if (context.getGuarantors() != null && !context.getGuarantors().isEmpty()) {
            for (GuarantorResponse guarantor : context.getGuarantors()) {
                // Add guarantor user
                addUser(guarantor.getFirstNames(), guarantor.getLastNames(), guarantor.getEmail(),
                        guarantor.getDocumentNumber(), users, prefilledItems, context);
                int guarantorUserId = currentUserId[0];
                currentUserId[0]++;

                // Create guarantee entry
                Integer guarantorCivilStatus = CivilStatusMapper.mapToCavaliCivilStatus(
                        guarantor.getMaritalStatus() != null ? guarantor.getMaritalStatus().toString() : null);
                KeynuaCavaliGuarantee guarantee = new KeynuaCavaliGuarantee();
                guarantee.setUserId(guarantorUserId);
                guarantee.setCivilStatus(guarantorCivilStatus);

                // Add guarantor spouse/representative if exists
                if (context.getGuarantorSpouses() != null &&
                        context.getGuarantorSpouses().containsKey(guarantor.getGuarantorId())) {
                    com.kiwipay.onboarding.guarantor.application.internal.dto.SpouseResponse guarantorSpouse = context
                            .getGuarantorSpouses().get(guarantor.getGuarantorId());
                    addUser(guarantorSpouse.getFirstNames(), guarantorSpouse.getLastNames(),
                            guarantorSpouse.getEmail(), guarantorSpouse.getDocumentNumber(),
                            users, prefilledItems, context);

                    List<KeynuaCavaliRepresentative> guarantorReps = new ArrayList<>();
                    guarantorReps.add(new KeynuaCavaliRepresentative(currentUserId[0]));
                    guarantee.setRepresentatives(guarantorReps);
                    currentUserId[0]++;
                }

                guarantees.add(guarantee);
            }
        }

        request.setUsers(users);
        request.setPrefilledItems(prefilledItems);

        // Build cavaliData
        KeynuaCavaliData cavaliData = buildCavaliData(client, clientRepresentatives, guarantees, context);
        request.setFlags(new KeynuaFlags(cavaliData));

        // Add documents - Using mock PDFs with valid base64 content
        List<KeynuaDocument> documents = mockDocumentService.getMockContractDocuments(context.getLoanId());
        request.setDocuments(documents);

        return request;
    }

    private void addUser(String firstNames, String lastNames, String email, String documentNumber,
            List<KeynuaUser> users, List<KeynuaPrefilledItem> prefilledItems,
            PayloadBuilderContext context) {
        // Validate and use fallbacks
        if (email == null || email.isEmpty()) {
            context.addMissingField("user.email");
            email = "noemail@example.com";
        }

        if (documentNumber == null || documentNumber.isEmpty()) {
            context.addMissingField("user.documentNumber");
            documentNumber = "00000000";
        }

        if (firstNames == null || firstNames.isEmpty()) {
            context.addMissingField("user.firstNames");
            firstNames = "Sin Nombre";
        }

        if (lastNames == null || lastNames.isEmpty()) {
            context.addMissingField("user.lastNames");
            lastNames = "Sin Apellido";
        }

        // Create user with groups ["firmante-1"]
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

    private KeynuaCavaliData buildCavaliData(ClientResponse client,
            List<KeynuaCavaliRepresentative> clientRepresentatives,
            List<KeynuaCavaliGuarantee> guarantees,
            PayloadBuilderContext context) {
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

        // Set representatives if client has spouse
        if (!clientRepresentatives.isEmpty()) {
            cavaliData.setRepresentatives(clientRepresentatives);
        } else {
            cavaliData.setRepresentatives(null);
        }

        // Set guarantees if any
        if (!guarantees.isEmpty()) {
            cavaliData.setGuarantees(guarantees);
        } else {
            cavaliData.setGuarantees(null);
        }

        return cavaliData;
    }
}
