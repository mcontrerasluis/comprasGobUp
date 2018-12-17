package mx.gob.shcp.compras.service;

import mx.gob.shcp.compras.domain.CUCOP;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CUCOP.
 */
public interface CUCOPService {

    /**
     * Save a cUCOP.
     *
     * @param cUCOP the entity to save
     * @return the persisted entity
     */
    CUCOP save(CUCOP cUCOP);

    /**
     * Get all the cUCOPS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CUCOP> findAll(Pageable pageable);


    /**
     * Get the "id" cUCOP.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CUCOP> findOne(Long id);

    /**
     * Delete the "id" cUCOP.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
