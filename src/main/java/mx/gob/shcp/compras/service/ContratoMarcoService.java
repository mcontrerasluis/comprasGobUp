package mx.gob.shcp.compras.service;

import mx.gob.shcp.compras.domain.ContratoMarco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ContratoMarco.
 */
public interface ContratoMarcoService {

    /**
     * Save a contratoMarco.
     *
     * @param contratoMarco the entity to save
     * @return the persisted entity
     */
    ContratoMarco save(ContratoMarco contratoMarco);

    /**
     * Get all the contratoMarcos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ContratoMarco> findAll(Pageable pageable);

    /**
     * Get all the ContratoMarco with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<ContratoMarco> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" contratoMarco.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ContratoMarco> findOne(Long id);

    /**
     * Delete the "id" contratoMarco.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
