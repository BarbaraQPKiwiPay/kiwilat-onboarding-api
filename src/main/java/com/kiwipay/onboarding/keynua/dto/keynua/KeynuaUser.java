package com.kiwipay.onboarding.keynua.dto.keynua;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeynuaUser {

    private String name;
    private String lastname;
    private String email;
    private String nationalId;
    private List<String> groups; // e.g., ["firmante-1"]
}
