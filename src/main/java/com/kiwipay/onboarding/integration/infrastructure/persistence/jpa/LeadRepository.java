package com.kiwipay.onboarding.integration.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.integration.domain.model.aggregates.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    /**
     * Busca un lead por número de documento
     * @param documentNumber Número de documento
     * @return Optional con el lead si existe
     */
    Optional<Lead> findByNroDocumento(String documentNumber);

    /**
     * Busca un lead por ID original de SGL
     * @param idLead ID original del sistema SGL
     * @return Optional con el lead si existe
     */
    Optional<Lead> findByIdLead(Long idLead);

    /**
     * Busca leads por tipo y número de documento
     * @param tipoDocumento Tipo de documento
     * @param nroDocumento Número de documento
     * @return Optional con el lead si existe
     */
    Optional<Lead> findByTddTipoDocumentoAndNroDocumento(Integer tipoDocumento, String nroDocumento);

    /**
     * Busca leads por email
     * @param email Email del lead
     * @return Lista de leads con ese email
     */
    List<Lead> findByEmailContainingIgnoreCase(String email);

    /**
     * Busca leads por teléfono celular
     * @param celular Número de celular
     * @return Lista de leads con ese celular
     */
    List<Lead> findByCelularContaining(String celular);

    /**
     * Busca leads creados después de una fecha específica
     * @param fecha Fecha de corte
     * @return Lista de leads creados después de la fecha
     */
    List<Lead> findByFechaRegistroAfter(LocalDateTime fecha);

    /**
     * Busca leads por interés de procedimiento (categoría médica)
     * @param interesProcedimiento Procedimiento de interés
     * @return Lista de leads con ese interés
     */
    List<Lead> findByInteresProcedimientoContainingIgnoreCase(String interesProcedimiento);

    /**
     * Busca leads por sede
     * @param tddSede ID de la sede
     * @return Lista de leads de esa sede
     */
    List<Lead> findByTddSede(Integer tddSede);

    /**
     * Consulta custom para buscar leads con cotizaciones activas
     * @return Lista de leads que tienen al menos una cotización activa
     */
    @Query("SELECT DISTINCT l FROM Lead l JOIN l.quotations q WHERE q.isActive = true")
    List<Lead> findLeadsWithActiveQuotations();

    /**
     * Consulta custom para buscar leads con cotizaciones en estado específico
     * @param estado Estado de la cotización
     * @return Lista de leads con cotizaciones en ese estado
     */
    @Query("SELECT DISTINCT l FROM Lead l JOIN l.quotations q WHERE q.resultadoFinalEstado = :estado")
    List<Lead> findLeadsByQuotationStatus(@Param("estado") String estado);

    /**
     * Verifica si ya existe un lead con el mismo documento y origen
     * @param tipoDocumento Tipo de documento
     * @param nroDocumento Número de documento
     * @param migrationOriginId ID de origen de migración
     * @return true si existe, false si no
     */
    boolean existsByTddTipoDocumentoAndNroDocumentoAndMigrationOriginId(
        Integer tipoDocumento, 
        String nroDocumento, 
        Integer migrationOriginId
    );
}