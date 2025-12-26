package com.kiwipay.onboarding.keynua.infrastructure.document;

import com.kiwipay.onboarding.keynua.dto.keynua.KeynuaDocument;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Mock Document Service
 * Provides mock PDF documents in base64 format for testing Keynua integration
 * These are valid minimal PDFs that Keynua will accept
 */
@Service
public class MockDocumentService {

    /**
     * Generates mock contract documents in base64 for testing
     * These are valid PDFs with minimal content
     * 
     * @param loanId the loan ID (for logging/tracking)
     * @return list of mock documents ready for Keynua
     */
    public List<KeynuaDocument> getMockContractDocuments(String loanId) {
        return List.of(
                new KeynuaDocument("Contrato.pdf", getMockContratoBase64()),
                new KeynuaDocument("AcuerdoPagare.pdf", getMockAcuerdoPagareBase64()),
                new KeynuaDocument("Pagare.pdf", getMockPagareBase64()));
    }

    /**
     * Minimal valid PDF with text "CONTRATO DE PRÉSTAMO - MOCK"
     */
    private String getMockContratoBase64() {
        return "JVBERi0xLjQKJeLjz9MKMSAwIG9iago8PAovVHlwZSAvQ2F0YWxvZwovUGFnZXMgMiAwIFIKPj4KZW5kb2JqCjIgMCBvYmoKPDwKL1R5cGUgL1BhZ2VzCi9LaWRzIFszIDAgUl0KL0NvdW50IDEKPJ4KZW5kb2JqCjMgMCBvYmoKPDwKL1R5cGUgL1BhZ2UKL1BhcmVudCAyIDAgUgovUmVzb3VyY2VzIDw8Ci9Gb250IDw8Ci9GMSA0IDAgUgo+Pgo+PgovTWVkaWFCb3ggWzAgMCA2MTIgNzkyXQovQ29udGVudHMgNSAwIFIKPj4KZW5kb2JqCjQgMCBvYmoKPDwKL1R5cGUgL0ZvbnQKL1N1YnR5cGUgL1R5cGUxCi9CYXNlRm9udCAvSGVsdmV0aWNhCj4+CmVuZG9iago1IDAgb2JqCjw8Ci9MZW5ndGggNDQKPj4Kc3RyZWFtCkJUCi9GMSA0OCBUZgoxMCA3MDAgVGQKKENPTlRSQVRPIERFIFBSRVNUQU1PIC0gTU9DSykgVGoKRVQKZW5kc3RyZWFtCmVuZG9iagp4cmVmCjAgNgowMDAwMDAwMDAwIDY1NTM1IGYNCjAwMDAwMDAwMTUgMDAwMDAgbg0KMDAwMDAwMDA2NCAwMDAwMCBuDQowMDAwMDAwMTIxIDAwMDAwIG4NCjAwMDAwMDAyNDUgMDAwMDAgbg0KMDAwMDAwMDMzMiAwMDAwMCBuDQp0cmFpbGVyCjw8Ci9TaXplIDYKL1Jvb3QgMSAwIFIKPj4Kc3RhcnR4cmVmCjQyNgolJUVPRgo=";
    }

    /**
     * Minimal valid PDF with text "ACUERDO DE PAGARÉ - MOCK"
     */
    private String getMockAcuerdoPagareBase64() {
        return "JVBERi0xLjQKJeLjz9MKMSAwIG9iago8PAovVHlwZSAvQ2F0YWxvZwovUGFnZXMgMiAwIFIKPj4KZW5kb2JqCjIgMCBvYmoKPDwKL1R5cGUgL1BhZ2VzCi9LaWRzIFszIDAgUl0KL0NvdW50IDEKPJ4KZW5kb2JqCjMgMCBvYmoKPDwKL1R5cGUgL1BhZ2UKL1BhcmVudCAyIDAgUgovUmVzb3VyY2VzIDw8Ci9Gb250IDw8Ci9GMSA0IDAgUgo+Pgo+PgovTWVkaWFCb3ggWzAgMCA2MTIgNzkyXQovQ29udGVudHMgNSAwIFIKPj4KZW5kb2JqCjQgMCBvYmoKPDwKL1R5cGUgL0ZvbnQKL1N1YnR5cGUgL1R5cGUxCi9CYXNlRm9udCAvSGVsdmV0aWNhCj4+CmVuZG9iago1IDAgb2JqCjw8Ci9MZW5ndGggNDYKPj4Kc3RyZWFtCkJUCi9GMSA0OCBUZgoxMCA3MDAgVGQKKEFDVUVSRE8gREUgUEFHQVJFIC0gTU9DSykgVGoKRVQKZW5kc3RyZWFtCmVuZG9iagp4cmVmCjAgNgowMDAwMDAwMDAwIDY1NTM1IGYNCjAwMDAwMDAwMTUgMDAwMDAgbg0KMDAwMDAwMDA2NCAwMDAwMCBuDQowMDAwMDAwMTIxIDAwMDAwIG4NCjAwMDAwMDAyNDUgMDAwMDAgbg0KMDAwMDAwMDMzMiAwMDAwMCBuDQp0cmFpbGVyCjw8Ci9TaXplIDYKL1Jvb3QgMSAwIFIKPj4Kc3RhcnR4cmVmCjQyOAolJUVPRgo=";
    }

    /**
     * Minimal valid PDF with text "PAGARÉ - MOCK"
     */
    private String getMockPagareBase64() {
        return "JVBERi0xLjQKJeLjz9MKMSAwIG9iago8PAovVHlwZSAvQ2F0YWxvZwovUGFnZXMgMiAwIFIKPj4KZW5kb2JqCjIgMCBvYmoKPDwKL1R5cGUgL1BhZ2VzCi9LaWRzIFszIDAgUl0KL0NvdW50IDEKPJ4KZW5kb2JqCjMgMCBvYmoKPDwKL1R5cGUgL1BhZ2UKL1BhcmVudCAyIDAgUgovUmVzb3VyY2VzIDw8Ci9Gb250IDw8Ci9GMSA0IDAgUgo+Pgo+PgovTWVkaWFCb3ggWzAgMCA2MTIgNzkyXQovQ29udGVudHMgNSAwIFIKPj4KZW5kb2JqCjQgMCBvYmoKPDwKL1R5cGUgL0ZvbnQKL1N1YnR5cGUgL1R5cGUxCi9CYXNlRm9udCAvSGVsdmV0aWNhCj4+CmVuZG9iago1IDAgb2JqCjw8Ci9MZW5ndGggMzYKPj4Kc3RyZWFtCkJUCi9GMSA0OCBUZgoxMCA3MDAgVGQKKFBBR0FSRSBNT0NLKSBUagpFVAplbmRzdHJlYW0KZW5kb2JqCnhyZWYKMCA2CjAwMDAwMDAwMDAgNjU1MzUgZg0KMDAwMDAwMDAxNSAwMDAwMCBuDQowMDAwMDAwMDY0IDAwMDAwIG4NCjAwMDAwMDAxMjEgMDAwMDAgbg0KMDAwMDAwMDI0NSAwMDAwMCBuDQowMDAwMDAwMzMyIDAwMDAwIG4NCnRyYWlsZXIKPDwKL1NpemUgNgovUm9vdCAxIDAgUgo+PgpzdGFydHhyZWYKNDE4CiUlRU9GCg==";
    }
}
