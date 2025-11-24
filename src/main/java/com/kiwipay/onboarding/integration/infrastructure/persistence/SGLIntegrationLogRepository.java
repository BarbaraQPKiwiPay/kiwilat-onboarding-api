package com.kiwipay.onboarding.integration.infrastructure.persistence;

import com.kiwipay.onboarding.integration.application.internal.dto.SGLLeadRequest;
import com.kiwipay.onboarding.integration.domain.model.aggregates.SGLIntegrationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for SGLIntegrationLog entity.
 * Provides data access operations for integration logging.
 */
@Repository
public interface SGLIntegrationLogRepository extends JpaRepository<SGLIntegrationLog, Long> {

    /**
     * Find integration log by SGL lead ID
     */
    Optional<SGLIntegrationLog> findBySglLeadId(Long sglLeadId);

    /**
     * Find integration logs by Onboarding client ID
     */
    List<SGLIntegrationLog> findByOnboardingClientId(Long onboardingClientId);

    /**
     * Find integration logs by success status
     */
    List<SGLIntegrationLog> findBySuccess(Boolean success);

    /**
     * Find integration logs within date range
     */
    @Query("SELECT log FROM SGLIntegrationLog log WHERE log.processedAt BETWEEN :startDate AND :endDate ORDER BY log.processedAt DESC")
    List<SGLIntegrationLog> findByProcessedAtBetween(
            @Param("startDate") LocalDateTime startDate, 
            @Param("endDate") LocalDateTime endDate);

    /**
     * Find recent failed integrations for monitoring
     */
    @Query("SELECT log FROM SGLIntegrationLog log WHERE log.success = false AND log.processedAt >= :since ORDER BY log.processedAt DESC")
    List<SGLIntegrationLog> findRecentFailures(@Param("since") LocalDateTime since);

    /**
     * Count successful integrations for a given period
     */
    @Query("SELECT COUNT(log) FROM SGLIntegrationLog log WHERE log.success = true AND log.processedAt >= :since")
    Long countSuccessfulIntegrationsSince(@Param("since") LocalDateTime since);

    /**
     * Check if SGL lead was already processed
     */
    boolean existsBySglLeadIdAndSuccess(Long sglLeadId, Boolean success);


}