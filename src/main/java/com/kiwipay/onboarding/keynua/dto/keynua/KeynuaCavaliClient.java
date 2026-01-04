package com.kiwipay.onboarding.keynua.dto.keynua;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeynuaCavaliClient {

    /**
     * User ID in users array - always 0 for main client
     */
    private Integer userId;

    /**
     * Civil/marital status code (integer)
     * 1=Single, 2=Married, 3=Widowed, 4=Divorced, 5=Cohabiting
     */
    private Integer civilStatus;
}
