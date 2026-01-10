package com.kiwipay.onboarding.document.interfaces.rest;

import com.kiwipay.onboarding.document.application.internal.dto.*;
import com.kiwipay.onboarding.document.domain.model.valueobjects.DocumentOwnerType;
import com.kiwipay.onboarding.document.domain.services.DocumentCommandService;
import com.kiwipay.onboarding.document.domain.services.DocumentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Document Controller", description = "Manages documents for loans (client and guarantor documents)")
public class DocumentController {

    @Autowired
    private DocumentCommandService documentCommandService;

    @Autowired
    private DocumentQueryService documentQueryService;

    // ============================================================
    // CLIENT DOCUMENTS
    // ============================================================

    @PostMapping("/loans/{loanId}/clients/{clientId}/documents")
    @Operation(summary = "Upload client document", description = "Upload a document for a client associated with a loan")
    public ResponseEntity<DocumentResponse> uploadClientDocument(
            @PathVariable Long loanId,
            @PathVariable Long clientId,
            @Valid @RequestBody DocumentUploadRequest request) {
        request.setOwnerType(DocumentOwnerType.CLIENT);
        DocumentResponse response = documentCommandService.uploadDocument(loanId, clientId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/loans/{loanId}/clients/{clientId}/documents")
    @Operation(summary = "Get client documents", description = "Retrieve all documents for a specific client in a loan")
    public ResponseEntity<List<DocumentResponse>> getClientDocuments(
            @PathVariable Long loanId,
            @PathVariable Long clientId) {
        List<DocumentResponse> documents = documentQueryService.getDocumentsByClientId(clientId);
        return ResponseEntity.ok(documents);
    }

    // ============================================================
    // GUARANTOR DOCUMENTS
    // ============================================================

    @PostMapping("/loans/{loanId}/guarantors/{guarantorId}/documents")
    @Operation(summary = "Upload guarantor document", description = "Upload a document for a guarantor associated with a loan")
    public ResponseEntity<DocumentResponse> uploadGuarantorDocument(
            @PathVariable Long loanId,
            @PathVariable Long guarantorId,
            @Valid @RequestBody DocumentUploadRequest request) {
        request.setOwnerType(DocumentOwnerType.GUARANTOR);
        DocumentResponse response = documentCommandService.uploadDocument(loanId, guarantorId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/loans/{loanId}/guarantors/{guarantorId}/documents")
    @Operation(summary = "Get guarantor documents", description = "Retrieve all documents for a specific guarantor in a loan")
    public ResponseEntity<List<DocumentResponse>> getGuarantorDocuments(
            @PathVariable Long loanId,
            @PathVariable Long guarantorId) {
        List<DocumentResponse> documents = documentQueryService.getDocumentsByGuarantorId(guarantorId);
        return ResponseEntity.ok(documents);
    }

    // ============================================================
    // LOAN DOCUMENTS (ALL)
    // ============================================================

    @GetMapping("/loans/{loanId}/documents")
    @Operation(summary = "Get all loan documents", description = "Retrieve all documents for a loan, optionally filtered by owner type")
    public ResponseEntity<List<DocumentResponse>> getLoanDocuments(
            @PathVariable Long loanId,
            @RequestParam(required = false) DocumentOwnerType ownerType) {
        List<DocumentResponse> documents;
        if (ownerType != null) {
            documents = documentQueryService.getDocumentsByLoanIdAndOwnerType(loanId, ownerType);
        } else {
            documents = documentQueryService.getDocumentsByLoanId(loanId);
        }
        return ResponseEntity.ok(documents);
    }

    // ============================================================
    // DOCUMENT OPERATIONS (BY ID)
    // ============================================================

    @GetMapping("/documents/{documentId}")
    @Operation(summary = "Get document by ID", description = "Retrieve a specific document")
    public ResponseEntity<DocumentResponse> getDocumentById(@PathVariable String documentId) {
        DocumentResponse document = documentQueryService.getDocumentById(documentId);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/documents/{documentId}/preview")
    @Operation(summary = "Preview document", description = "Get document with base64 content for preview")
    public ResponseEntity<DocumentPreviewResponse> previewDocument(@PathVariable String documentId) {
        DocumentPreviewResponse preview = documentQueryService.previewDocument(documentId);
        return ResponseEntity.ok(preview);
    }

    @GetMapping("/documents/{documentId}/download")
    @Operation(summary = "Download document", description = "Download document file with appropriate headers")
    public ResponseEntity<Resource> downloadDocument(@PathVariable String documentId) {
        DocumentPreviewResponse document = documentQueryService.previewDocument(documentId);

        // Decode base64 content
        byte[] fileContent = Base64.getDecoder().decode(document.getContentBase64());
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        // Set headers for download
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFilename() + "\"");
        headers.setContentType(MediaType.parseMediaType(document.getMimeType()));
        headers.setContentLength(fileContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @PatchMapping("/documents/{documentId}/review")
    @Operation(summary = "Review document", description = "Approve or reject a document")
    public ResponseEntity<DocumentResponse> reviewDocument(
            @PathVariable String documentId,
            @Valid @RequestBody DocumentReviewRequest request) {
        DocumentResponse response = documentCommandService.reviewDocument(documentId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/documents/{documentId}")
    @Operation(summary = "Delete document", description = "Delete a document")
    public ResponseEntity<Void> deleteDocument(@PathVariable String documentId) {
        documentCommandService.deleteDocument(documentId);
        return ResponseEntity.noContent().build();
    }

    // ============================================================
    // RISK DOCUMENT (FICHA DE RIESGOS)
    // ============================================================

    @PostMapping("/loans/{loanId}/risk-document")
    @Operation(summary = "Upload risk document", description = "Upload FICHA_DE_RIESGOS for a loan. Only one risk document allowed per loan. Up loading a new one replaces the existing.")
    public ResponseEntity<DocumentResponse> uploadRiskDocument(
            @PathVariable Long loanId,
            @Valid @RequestBody DocumentUploadRequest request) {
        DocumentResponse response = documentCommandService.uploadRiskDocument(loanId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}