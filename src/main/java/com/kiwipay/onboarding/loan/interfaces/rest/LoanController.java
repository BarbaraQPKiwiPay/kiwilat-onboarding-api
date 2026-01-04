package com.kiwipay.onboarding.loan.interfaces.rest;

import com.kiwipay.onboarding.loan.application.internal.dto.LoanCreateRequest;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanResponse;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanStatusChangeRequest;
import com.kiwipay.onboarding.loan.application.internal.dto.LoanUpdateRequest;
import com.kiwipay.onboarding.loan.domain.model.exceptions.LoanBusinessException;
import com.kiwipay.onboarding.loan.domain.model.valueobjects.LoanStatus;
import com.kiwipay.onboarding.loan.domain.services.LoanCommandService;
import com.kiwipay.onboarding.loan.domain.services.LoanQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@Tag(name = "Loan Management", description = "Managing loan information and status workflow")
public class LoanController {

    @Autowired
    private LoanCommandService loanCommandService;

    @Autowired
    private LoanQueryService loanQueryService;

    @PostMapping
    @Operation(summary = "Create a new loan", description = "Creates a new loan for a client with initial status PENDING")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Loan created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> createLoan(@Valid @RequestBody LoanCreateRequest request) {
        try {
            LoanResponse response = loanCommandService.createLoan(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (LoanBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @GetMapping("/{loanId}")
    @Operation(summary = "Get loan by ID", description = "Retrieves detailed information for a specific loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Loan not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getLoanById(@PathVariable Long loanId) {
        try {
            LoanResponse response = loanQueryService.getLoanById(loanId);
            return ResponseEntity.ok(response);
        } catch (LoanBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get all loans with pagination", description = "Retrieves all loans with pagination (50 loans per page by default)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loans retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Page<LoanResponse>> getAllLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<LoanResponse> loans = loanQueryService.getAllLoans(pageable);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get loans by client", description = "Retrieves all loans for a specific client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loans retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<LoanResponse>> getLoansByClient(@PathVariable Long clientId) {
        List<LoanResponse> loans = loanQueryService.getLoansByClientId(clientId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get loans by status", description = "Retrieves all loans with a specific status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loans retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getLoansByStatus(@PathVariable String status) {
        try {
            LoanStatus loanStatus = LoanStatus.valueOf(status.toUpperCase());
            List<LoanResponse> loans = loanQueryService.getLoansByStatus(loanStatus);
            return ResponseEntity.ok(loans);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("INVALID_STATUS", "Invalid loan status: " + status));
        }
    }

    @PutMapping("/{loanId}")
    @Operation(summary = "Update loan information", description = "Updates any field of an existing loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Loan not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> updateLoan(
            @PathVariable Long loanId,
            @Valid @RequestBody LoanUpdateRequest request) {
        try {
            LoanResponse response = loanCommandService.updateLoan(loanId, request);
            return ResponseEntity.ok(response);
        } catch (LoanBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @PutMapping("/{loanId}/status")
    @Operation(summary = "Change loan status", description = "Changes the status of a loan and records who made the change")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status changed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Loan not found"),
            @ApiResponse(responseCode = "409", description = "Invalid status transition"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> changeStatus(
            @PathVariable Long loanId,
            @Valid @RequestBody LoanStatusChangeRequest request) {
        try {
            LoanResponse response = loanCommandService.changeStatus(loanId, request);
            return ResponseEntity.ok(response);
        } catch (LoanBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{loanId}")
    @Operation(summary = "Delete loan", description = "Deletes a loan (only if not in final state)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Loan deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Loan not found"),
            @ApiResponse(responseCode = "409", description = "Cannot delete loan in final state"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> deleteLoan(@PathVariable Long loanId) {
        try {
            loanCommandService.deleteLoan(loanId);
            return ResponseEntity.noContent().build();
        } catch (LoanBusinessException e) {
            return ResponseEntity.status(e.getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    public static class ErrorResponse {
        private String errorCode;
        private String message;

        public ErrorResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }
    }
}
