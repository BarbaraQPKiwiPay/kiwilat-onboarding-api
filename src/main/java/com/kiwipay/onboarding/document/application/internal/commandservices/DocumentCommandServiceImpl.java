package com.kiwipay.onboarding.document.application.internal.commandservices;

import com.kiwipay.onboarding.client.infrastructure.persistence.jpa.repositories.ClientRepository;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentUploadRequest;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;
import com.kiwipay.onboarding.document.domain.model.aggregates.Document;
import com.kiwipay.onboarding.document.domain.model.exceptions.DocumentBusinessException;
import com.kiwipay.onboarding.document.domain.services.DocumentCommandService;
import com.kiwipay.onboarding.document.infrastructure.persistence.jpa.DocumentRepository;
import com.kiwipay.onboarding.document.infrastructure.persistence.jpa.DocumentTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class DocumentCommandServiceImpl implements DocumentCommandService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private ClientRepository clientRepository;

    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList(
        "application/pdf", "image/jpeg", "image/png"
    );
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int MAX_DOCUMENTS_PER_CLIENT = 10;

    @Override
    public DocumentResponse uploadDocument(Long clientId, DocumentUploadRequest request) {
        // Validar que el cliente existe
        if (!clientRepository.existsById(clientId)) {
            throw DocumentBusinessException.clientNotFound();
        }

        // Validar que el tipo de documento existe
        if (!documentTypeRepository.existsById(request.getDocumentTypeId())) {
            throw DocumentBusinessException.documentTypeNotFound();
        }

        // Validar MIME type
        if (!ALLOWED_MIME_TYPES.contains(request.getMimeType())) {
            throw DocumentBusinessException.invalidMimeType();
        }

        // Validar tamaño del archivo
        if (request.getSizeBytes() > MAX_FILE_SIZE) {
            throw DocumentBusinessException.fileSizeExceeded();
        }

        // Validar límite de documentos por cliente
        if (documentRepository.countByClientId(clientId) >= MAX_DOCUMENTS_PER_CLIENT) {
            throw DocumentBusinessException.maxDocumentsExceeded();
        }

        // Validar Base64
        try {
            Base64.getDecoder().decode(request.getContentBase64());
        } catch (IllegalArgumentException e) {
            throw DocumentBusinessException.invalidBase64();
        }

        // Generar ID único
        String documentId = generateDocumentId();

        // Crear documento
        Document document = new Document(
            documentId,
            clientId,
            request.getDocumentTypeId(),
            request.getFilename(),
            request.getMimeType(),
            request.getSizeBytes(),
            request.getComment(),
            request.getContentBase64()
        );

        Document savedDocument = documentRepository.save(document);

        DocumentResponse response = new DocumentResponse();
        BeanUtils.copyProperties(savedDocument, response);
        return response;
    }

    @Override
    public void deleteDocument(Long clientId, String documentId) {
        // Verificar que el documento existe y pertenece al cliente
        if (!documentRepository.existsByIdAndClientId(documentId, clientId)) {
            throw DocumentBusinessException.documentNotBelongsToClient();
        }

        documentRepository.deleteById(documentId);
    }

    private String generateDocumentId() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = documentRepository.count() + 1;
        return String.format("DOC-%s-%04d", timestamp, count);
    }
}