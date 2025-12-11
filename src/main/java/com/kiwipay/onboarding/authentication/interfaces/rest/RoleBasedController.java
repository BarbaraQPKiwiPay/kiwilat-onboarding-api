package com.kiwipay.onboarding.authentication.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Role Examples", description = "Examples of role-based access control")
@SecurityRequirement(name = "bearerAuth")
public class RoleBasedController {

    @GetMapping("/comercial/dashboard")
    @PreAuthorize("hasRole('COMERCIAL')")
    @Operation(summary = "Commercial dashboard", description = "Access commercial team dashboard")
    public ResponseEntity<Map<String, Object>> getComercialDashboard() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Commercial Dashboard");
        response.put("role", "COMERCIAL");
        response.put("sections", new String[]{"Leads", "Sales", "Customers"});
        return ResponseEntity.ok(response);
    }

    @GetMapping("/adv/dashboard")
    @PreAuthorize("hasRole('ADV')")
    @Operation(summary = "ADV dashboard", description = "Access ADV team dashboard")
    public ResponseEntity<Map<String, Object>> getAdvDashboard() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to ADV Dashboard");
        response.put("role", "ADV");
        response.put("sections", new String[]{"Analysis", "Reports", "Validation"});
        return ResponseEntity.ok(response);
    }

    @GetMapping("/riesgos/dashboard")
    @PreAuthorize("hasRole('RIESGOS')")
    @Operation(summary = "Risk dashboard", description = "Access Risk team dashboard")
    public ResponseEntity<Map<String, Object>> getRiesgosDashboard() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Risk Dashboard");
        response.put("role", "RIESGOS");
        response.put("sections", new String[]{"Risk Analysis", "Credit Assessment", "Approvals"});
        return ResponseEntity.ok(response);
    }

    @GetMapping("/shared/profile")
    @PreAuthorize("hasAnyRole('COMERCIAL', 'ADV', 'RIESGOS', 'SUPERADMIN')")
    @Operation(summary = "User profile", description = "Access user profile (any authenticated user)")
    public ResponseEntity<Map<String, Object>> getUserProfile() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User Profile Information");
        response.put("accessible_by", "All authenticated users");
        return ResponseEntity.ok(response);
    }
}