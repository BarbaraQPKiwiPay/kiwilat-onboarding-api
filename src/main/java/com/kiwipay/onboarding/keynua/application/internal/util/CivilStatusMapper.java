package com.kiwipay.onboarding.keynua.application.internal.util;

/**
 * Utility class to map marital status to Cavali civil status codes
 */
public class CivilStatusMapper {

    /**
     * Maps domain marital status to Cavali civil status code (integer)
     * 
     * Keynua/Cavali civil status codes:
     * 1 = Single (Soltero/a)
     * 2 = Married (Casado/a)
     * 3 = Widowed (Viudo/a)
     * 4 = Divorced (Divorciado/a)
     * 5 = Cohabiting (Conviviente)
     * 
     * @param maritalStatus marital status from domain model
     * @return Cavali civil status code as integer
     */
    public static Integer mapToCavaliCivilStatus(String maritalStatus) {
        if (maritalStatus == null) {
            return 1; // Default to single
        }

        return switch (maritalStatus.toUpperCase()) {
            case "SINGLE", "SOLTERO", "SOLTERA" -> 1;
            case "MARRIED", "CASADO", "CASADA" -> 2;
            case "DIVORCED", "DIVORCIADO", "DIVORCIADA" -> 4;
            case "WIDOWED", "VIUDO", "VIUDA" -> 3;
            case "COHABITING", "CONVIVIENTE" -> 5;
            default -> 1; // Default to single
        };
    }
}
