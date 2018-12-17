package mx.gob.shcp.compras.repository;

import mx.gob.shcp.compras.domain.ContratoMarco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ContratoMarco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContratoMarcoRepository extends JpaRepository<ContratoMarco, Long> {

    @Query(value = "select distinct contrato_marco from ContratoMarco contrato_marco left join fetch contrato_marco.proveedors",
        countQuery = "select count(distinct contrato_marco) from ContratoMarco contrato_marco")
    Page<ContratoMarco> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct contrato_marco from ContratoMarco contrato_marco left join fetch contrato_marco.proveedors")
    List<ContratoMarco> findAllWithEagerRelationships();

    @Query("select contrato_marco from ContratoMarco contrato_marco left join fetch contrato_marco.proveedors where contrato_marco.id =:id")
    Optional<ContratoMarco> findOneWithEagerRelationships(@Param("id") Long id);

}
