package mx.gob.shcp.compras.service;

import mx.gob.shcp.compras.domain.Dependencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Dependencia.
 */
public interface DependenciaService {

    /**
     * Save a dependencia.
     *
     * @param dependencia the entity to save
     * @return the persisted entity
     */
    Dependencia save(Dependencia dependencia);

    /**
     * Get all the dependencias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Dependencia> findAll(Pageable pageable);


    /**
     * Get the "id" dependencia.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Dependencia> findOne(Long id);

    /**
     * Delete the "id" dependencia.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
