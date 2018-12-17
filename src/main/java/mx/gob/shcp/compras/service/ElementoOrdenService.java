package mx.gob.shcp.compras.service;

import mx.gob.shcp.compras.domain.ElementoOrden;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ElementoOrden.
 */
public interface ElementoOrdenService {

    /**
     * Save a elementoOrden.
     *
     * @param elementoOrden the entity to save
     * @return the persisted entity
     */
    ElementoOrden save(ElementoOrden elementoOrden);

    /**
     * Get all the elementoOrdens.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ElementoOrden> findAll(Pageable pageable);


    /**
     * Get the "id" elementoOrden.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ElementoOrden> findOne(Long id);

    /**
     * Delete the "id" elementoOrden.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
