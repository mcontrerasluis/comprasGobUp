package mx.gob.shcp.compras.service;

import mx.gob.shcp.compras.domain.Proveedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Proveedor.
 */
public interface ProveedorService {

    /**
     * Save a proveedor.
     *
     * @param proveedor the entity to save
     * @return the persisted entity
     */
    Proveedor save(Proveedor proveedor);

    /**
     * Get all the proveedors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Proveedor> findAll(Pageable pageable);


    /**
     * Get the "id" proveedor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Proveedor> findOne(Long id);

    /**
     * Delete the "id" proveedor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
