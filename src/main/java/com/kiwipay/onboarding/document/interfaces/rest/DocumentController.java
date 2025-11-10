package com.kiwipay.onboarding.document.interfaces.rest;

import com.kiwipay.onboarding.document.application.internal.dto.DocumentResponse;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentTypeResponse;
import com.kiwipay.onboarding.document.application.internal.dto.DocumentUploadRequest;
import com.kiwipay.onboarding.document.domain.services.DocumentCommandService;
import com.kiwipay.onboarding.document.domain.services.DocumentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Document Management", description = "API endpoints for managing documents and document types")
public class DocumentController {

    @Autowired
    private DocumentCommandService documentCommandService;

    @Autowired
    private DocumentQueryService documentQueryService;

    @GetMapping("/document-types")
    @Operation(summary = "Get all document types", description = "Retrieves a list of all available document types")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Document types retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<DocumentTypeResponse>> getAllDocumentTypes() {
        return ResponseEntity.ok(documentQueryService.getAllDocumentTypes());
    }

    @PostMapping("/clients/{clientId}/documents")
    @Operation(summary = "Upload a document", description = "Uploads a new document for a specific client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Document uploaded successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid MIME type or Base64 content"),
        @ApiResponse(responseCode = "404", description = "Client or document type not found"),
        @ApiResponse(responseCode = "409", description = "Maximum number of documents exceeded"),
        @ApiResponse(responseCode = "413", description = "File size exceeds maximum limit"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DocumentResponse> uploadDocument(
            @PathVariable Long clientId,
            @Valid @RequestBody DocumentUploadRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentCommandService.uploadDocument(clientId, request));
    }

    @GetMapping("/clients/{clientId}/documents")
    @Operation(summary = "Get client documents", description = "Retrieves all documents for a specific client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documents retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<DocumentResponse>> getClientDocuments(@PathVariable Long clientId) {
        return ResponseEntity.ok(documentQueryService.getDocumentsByClientId(clientId));
    }

    @GetMapping("/documents/{documentId}/content")
    @Operation(summary = "Download document content", description = "Downloads the binary content of a document")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Document content retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Document not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<byte[]> getDocumentContent(@PathVariable String documentId) {
        // Obtener información del documento para los headers
        DocumentResponse documentInfo = documentQueryService.getDocumentById(documentId);
        byte[] content = documentQueryService.getDocumentContent(documentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(documentInfo.getMimeType()));
        headers.setContentDispositionFormData("inline", documentInfo.getFilename());
        headers.setContentLength(content.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
    }

    @DeleteMapping("/clients/{clientId}/documents/{documentId}")
    @Operation(summary = "Delete a document", description = "Deletes a specific document belonging to a client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Document deleted successfully"),
        @ApiResponse(responseCode = "403", description = "Document does not belong to the specified client"),
        @ApiResponse(responseCode = "404", description = "Document not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteDocument(
            @PathVariable Long clientId,
            @PathVariable String documentId) {
        documentCommandService.deleteDocument(clientId, documentId);
        return ResponseEntity.noContent().build();
    }
}