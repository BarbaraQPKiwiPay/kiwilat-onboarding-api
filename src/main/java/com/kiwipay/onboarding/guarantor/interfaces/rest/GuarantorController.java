package com.kiwipay.onboarding.guarantor.interfaces.rest;

import com.kiwipay.onboarding.guarantor.application.internal.dto.*;
import com.kiwipay.onboarding.guarantor.domain.services.GuarantorCommandService;
import com.kiwipay.onboarding.guarantor.domain.services.GuarantorQueryService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Guarantor Management", description = "Managing guarantors and their documents")
public class GuarantorController {

    @Autowired
    private GuarantorCommandService guarantorCommandService;

    @Autowired
    private GuarantorQueryService guarantorQueryService;

    // =============== GUARANTOR CRUD ===============

    @GetMapping("/clients/{clientId}/guarantor")
    @Operation(summary = "Get guarantor by client ID", description = "Retrieves guarantor information for a specific client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Guarantor retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Guarantor not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GuarantorResponse> getGuarantorByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(guarantorQueryService.getGuarantorByClientId(clientId));
    }

    @PutMapping("/clients/{clientId}/guarantor")
    @Operation(summary = "Create or update guarantor", description = "Creates a new guarantor or updates an existing one for a client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Guarantor created or updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Client not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GuarantorResponse> createOrUpdateGuarantor(
            @PathVariable Long clientId,
            @Valid @RequestBody GuarantorCreateRequest request) {
        return ResponseEntity.ok(guarantorCommandService.createOrUpdateGuarantor(clientId, request));
    }

    @PatchMapping("/clients/{clientId}/guarantor")
    @Operation(summary = "Partially update guarantor", description = "Updates specific fields of an existing guarantor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Guarantor updated successfully"),
        @ApiResponse(responseCode = "404", description = "Guarantor not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GuarantorResponse> patchGuarantor(
            @PathVariable Long clientId,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(guarantorCommandService.patchGuarantor(clientId, updates));
    }

    @DeleteMapping("/clients/{clientId}/guarantor")
    @Operation(summary = "Delete guarantor", description = "Deletes the guarantor for a specific client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Guarantor deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Guarantor not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteGuarantor(@PathVariable Long clientId) {
        guarantorCommandService.deleteGuarantor(clientId);
        return ResponseEntity.noContent().build();
    }

    // =============== GUARANTOR DOCUMENTS CRUD ===============

    @PostMapping("/clients/{clientId}/guarantor/documents")
    @Operation(summary = "Upload guarantor document", description = "Uploads a new document for a guarantor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Document uploaded successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid MIME type or Base64 content"),
        @ApiResponse(responseCode = "404", description = "Client or document type not found"),
        @ApiResponse(responseCode = "409", description = "Maximum number of documents exceeded"),
        @ApiResponse(responseCode = "413", description = "File size exceeds maximum limit"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GuarantorDocumentResponse> uploadGuarantorDocument(
            @PathVariable Long clientId,
            @Valid @RequestBody GuarantorDocumentUploadRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(guarantorCommandService.uploadDocument(clientId, request));
    }

    @GetMapping("/clients/{clientId}/guarantor/documents")
    @Operation(summary = "Get guarantor documents", description = "Retrieves all documents for a guarantor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documents retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<GuarantorDocumentResponse>> getGuarantorDocuments(@PathVariable Long clientId) {
        return ResponseEntity.ok(guarantorQueryService.getDocumentsByClientId(clientId));
    }

    @GetMapping("/guarantor-documents/{documentId}/content")
    @Operation(summary = "Download guarantor document content", description = "Downloads the binary content of a guarantor document")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Document content retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Document not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<byte[]> getGuarantorDocumentContent(@PathVariable String documentId) {
        GuarantorDocumentResponse documentInfo = guarantorQueryService.getDocumentById(documentId);
        byte[] content = guarantorQueryService.getDocumentContent(documentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(documentInfo.getMimeType()));
        headers.add("Content-Disposition", "inline; filename=\"" + documentInfo.getFilename() + "\"");
        headers.setContentLength(content.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
    }

    @PatchMapping("/guarantor-documents/{documentId}")
    @Operation(summary = "Update guarantor document", description = "Updates comment or document type of a guarantor document")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Document updated successfully"),
        @ApiResponse(responseCode = "404", description = "Document not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GuarantorDocumentResponse> patchGuarantorDocument(
            @PathVariable String documentId,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(guarantorCommandService.patchDocument(documentId, updates));
    }

    @DeleteMapping("/clients/{clientId}/guarantor/documents/{documentId}")
    @Operation(summary = "Delete guarantor document", description = "Deletes a specific guarantor document")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Document deleted successfully"),
        @ApiResponse(responseCode = "403", description = "Document does not belong to the specified client"),
        @ApiResponse(responseCode = "404", description = "Document not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteGuarantorDocument(
            @PathVariable Long clientId,
            @PathVariable String documentId) {
        guarantorCommandService.deleteDocument(clientId, documentId);
        return ResponseEntity.noContent().build();
    }

    // =============== DOCUMENT REVIEW OPERATIONS ===============

    @PatchMapping("/guarantor-documents/{documentId}/review")
    @Operation(summary = "Update status of document", description = "Updates the review status of a guarantor document")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Document status updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid review status"),
        @ApiResponse(responseCode = "404", description = "Document not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GuarantorDocumentResponse> reviewGuarantorDocument(
            @PathVariable String documentId,
            @Valid @RequestBody GuarantorDocumentReviewRequest request) {
        return ResponseEntity.ok(guarantorCommandService.reviewDocument(documentId, request));
    }
}