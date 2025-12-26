package com.kiwipay.onboarding.keynua.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateContractRequest {

    @NotBlank(message = "loanId is required")
    private String loanId;

    @NotNull(message = "requestType is required")
    private RequestType requestType;

    private Boolean useMockSgl = true;
}
