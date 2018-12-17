package mx.gob.shcp.compras.service;

import mx.gob.shcp.compras.domain.LugarEntrega;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LugarEntrega.
 */
public interface LugarEntregaService {

    /**
     * Save a lugarEntrega.
     *
     * @param lugarEntrega the entity to save
     * @return the persisted entity
     */
    LugarEntrega save(LugarEntrega lugarEntrega);

    /**
     * Get all the lugarEntregas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LugarEntrega> findAll(Pageable pageable);


    /**
     * Get the "id" lugarEntrega.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LugarEntrega> findOne(Long id);

    /**
     * Delete the "id" lugarEntrega.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
