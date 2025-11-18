package com.kiwipay.onboarding.integration.infrastructure.persistence.jpa;

import com.kiwipay.onboarding.integration.domain.model.entities.LeadQuotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeadQuotationRepository extends JpaRepository<LeadQuotation, Long> {

    /**
     * Busca cotizaciones por ID del lead
     * @param leadId ID del lead
     * @return Lista de cotizaciones del lead
     */
    List<LeadQuotation> findByLeadId(Long leadId);

    /**
     * Busca cotizaciones activas por ID del lead
     * @param leadId ID del lead
     * @return Lista de cotizaciones activas del lead
     */
    List<LeadQuotation> findByLeadIdAndIsActiveTrue(Long leadId);

    /**
     * Busca cotización por ID original de SGL
     * @param idLeadCotizacionDetalle ID original de la cotización
     * @return Optional con la cotización si existe
     */
    Optional<LeadQuotation> findByIdLeadCotizacionDetalle(Long idLeadCotizacionDetalle);

    /**
     * Busca cotizaciones por resultado final
     * @param resultadoFinalEstado Estado del resultado final
     * @return Lista de cotizaciones con ese estado
     */
    List<LeadQuotation> findByResultadoFinalEstado(String resultadoFinalEstado);

    /**
     * Busca cotizaciones por clasificación
     * @param clasificacion Clasificación de la cotización
     * @return Lista de cotizaciones con esa clasificación
     */
    List<LeadQuotation> findByClasificacion(String clasificacion);

    /**
     * Busca cotizaciones por plazo
     * @param plazo Plazo en meses
     * @return Lista de cotizaciones con ese plazo
     */
    List<LeadQuotation> findByPlazo(Integer plazo);

    /**
     * Busca cotizaciones por rango de MAF
     * @param mafMin MAF mínimo
     * @param mafMax MAF máximo
     * @return Lista de cotizaciones en ese rango de MAF
     */
    List<LeadQuotation> findByMafBetween(BigDecimal mafMin, BigDecimal mafMax);

    /**
     * Busca cotizaciones por grupo y segmento
     * @param grupo Grupo de la cotización
     * @param segmento Segmento de la cotización
     * @return Lista de cotizaciones con ese grupo y segmento
     */
    List<LeadQuotation> findByGrupoAndSegmento(String grupo, String segmento);

    /**
     * Busca cotizaciones que son campañas
     * @return Lista de cotizaciones que son campañas
     */
    List<LeadQuotation> findByEsCampaniaTrue();

    /**
     * Consulta custom para obtener la mejor cotización por lead (menor cuota)
     * @param leadId ID del lead
     * @return Optional con la cotización de menor cuota
     */
    @Query("SELECT q FROM LeadQuotation q WHERE q.lead.id = :leadId AND q.isActive = true ORDER BY q.cuotaKiwi ASC")
    Optional<LeadQuotation> findBestQuotationByLeadId(@Param("leadId") Long leadId);

    /**
     * Consulta custom para estadísticas de cotizaciones por estado
     * @return Lista de objetos con estado y cantidad
     */
    @Query("SELECT q.resultadoFinalEstado, COUNT(q) FROM LeadQuotation q GROUP BY q.resultadoFinalEstado")
    List<Object[]> getQuotationStatsByStatus();

    /**
     * Busca cotizaciones por documento del lead
     * @param documentNumber Número de documento del lead
     * @return Lista de cotizaciones del lead con ese documento
     */
    @Query("SELECT q FROM LeadQuotation q JOIN q.lead l WHERE l.nroDocumento = :documentNumber")
    List<LeadQuotation> findByLeadDocumentNumber(@Param("documentNumber") String documentNumber);
}