package mx.gob.shcp.compras.service;

import mx.gob.shcp.compras.domain.OrdenCompra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OrdenCompra.
 */
public interface OrdenCompraService {

    /**
     * Save a ordenCompra.
     *
     * @param ordenCompra the entity to save
     * @return the persisted entity
     */
    OrdenCompra save(OrdenCompra ordenCompra);

    /**
     * Get all the ordenCompras.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrdenCompra> findAll(Pageable pageable);


    /**
     * Get the "id" ordenCompra.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrdenCompra> findOne(Long id);

    /**
     * Delete the "id" ordenCompra.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
