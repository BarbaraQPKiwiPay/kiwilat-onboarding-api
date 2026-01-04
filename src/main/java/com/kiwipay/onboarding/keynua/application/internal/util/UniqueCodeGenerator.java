package com.kiwipay.onboarding.keynua.application.internal.util;

import java.security.SecureRandom;

/**
 * Utility class to generate unique 8-digit codes
 */
public class UniqueCodeGenerator {

    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates a random 8-digit unique code
     * 
     * @return 8-digit code as string
     */
    public static String generate8DigitCode() {
        // Generate random number between 10000000 and 99999999
        int code = 10000000 + random.nextInt(90000000);
        return String.valueOf(code);
    }
}
