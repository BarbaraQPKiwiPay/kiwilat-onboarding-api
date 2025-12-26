package com.kiwipay.onboarding.keynua.dto.keynua;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeynuaCavaliGuarantee {

    /**
     * User ID in users array
     */
    private Integer userId;

    /**
     * Civil/marital status of guarantor (integer)
     * 1=Single, 2=Married, 3=Widowed, 4=Divorced, 5=Cohabiting
     */
    private Integer civilStatus;

    /**
     * Guarantor's representatives (e.g., spouse) - optional
     */
    private List<KeynuaCavaliRepresentative> representatives;
}
