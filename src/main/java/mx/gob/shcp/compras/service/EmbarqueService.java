package mx.gob.shcp.compras.service;

import mx.gob.shcp.compras.domain.Embarque;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Embarque.
 */
public interface EmbarqueService {

    /**
     * Save a embarque.
     *
     * @param embarque the entity to save
     * @return the persisted entity
     */
    Embarque save(Embarque embarque);

    /**
     * Get all the embarques.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Embarque> findAll(Pageable pageable);


    /**
     * Get the "id" embarque.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Embarque> findOne(Long id);

    /**
     * Delete the "id" embarque.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
