package com.kiwipay.onboarding.integration.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/integrations")
@CrossOrigin(origins = "*")
public class SimpleIntegrationController {

    @PostMapping("/leads")
    public ResponseEntity<Map<String, Object>> receiveLeadFromSgl(@RequestBody Map<String, Object> leadData) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Log básico de lo que recibimos
            System.out.println("=== DATOS RECIBIDOS DE SGL ===");
            System.out.println("IdLead: " + leadData.get("IdLead"));
            System.out.println("Nombres: " + leadData.get("Nombres"));
            System.out.println("NroDocumento: " + leadData.get("NroDocumento"));
            System.out.println("Email: " + leadData.get("Email"));
            System.out.println("InteresProcedimiento: " + leadData.get("InteresProcedimiento"));
            
            // Verificar si tiene cotizaciones
            Object cotizacionDetalle = leadData.get("CotizacionDetalle");
            if (cotizacionDetalle instanceof java.util.List) {
                java.util.List<?> cotizaciones = (java.util.List<?>) cotizacionDetalle;
                System.out.println("Número de cotizaciones: " + cotizaciones.size());
                
                if (!cotizaciones.isEmpty() && cotizaciones.get(0) instanceof Map) {
                    Map<?, ?> firstCotizacion = (Map<?, ?>) cotizaciones.get(0);
                    System.out.println("Primera cotización - Plazo: " + firstCotizacion.get("Plazo"));
                    System.out.println("Primera cotización - MAF: " + firstCotizacion.get("Maf"));
                    System.out.println("Primera cotización - CuotaKiwi: " + firstCotizacion.get("CuotaKiwi"));
                }
            }
            
            // Simular procesamiento exitoso
            response.put("success", true);
            response.put("message", "Lead recibido correctamente");
            response.put("leadId", leadData.get("IdLead"));
            response.put("timestamp", java.time.LocalDateTime.now());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            System.err.println("Error procesando lead: " + e.getMessage());
            e.printStackTrace();
            
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "API Integration funcionando correctamente");
        response.put("timestamp", java.time.LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}