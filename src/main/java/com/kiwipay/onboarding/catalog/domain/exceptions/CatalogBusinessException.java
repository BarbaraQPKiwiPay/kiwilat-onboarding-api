package com.kiwipay.onboarding.catalog.domain.exceptions;

public class CatalogBusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;

    public CatalogBusinessException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    // Business rule exceptions
    public static CatalogBusinessException departmentNotFound() {
        return new CatalogBusinessException("Department not found", "DEPARTMENT_NOT_FOUND", 404);
    }
    public static CatalogBusinessException provinceNotFound() {
        return new CatalogBusinessException("Province not found", "PROVINCE_NOT_FOUND", 404);
    }
    public static CatalogBusinessException districtNotFound() {
        return new CatalogBusinessException("District not found", "DISTRICT_NOT_FOUND", 404);
    }
    public static CatalogBusinessException clinicNotFound() {
        return new CatalogBusinessException("Clinic not found", "CLINIC_NOT_FOUND", 404);
    }
    public static CatalogBusinessException branchNotFound() {
        return new CatalogBusinessException("Clinic branch not found", "BRANCH_NOT_FOUND", 404);
    }
    public static CatalogBusinessException medicalCategoryNotFound() {
        return new CatalogBusinessException("Medical category not found", "MEDICAL_CATEGORY_NOT_FOUND", 404);
    }
    public static CatalogBusinessException invalidBranchForClinic() {
        return new CatalogBusinessException("The branch does not belong to the specified clinic", "INVALID_BRANCH_FOR_CLINIC", 422);
    }
}
