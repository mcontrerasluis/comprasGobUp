package mx.gob.shcp.compras.service.impl;

import mx.gob.shcp.compras.service.DependenciaService;
import mx.gob.shcp.compras.domain.Dependencia;
import mx.gob.shcp.compras.repository.DependenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Dependencia.
 */
@Service
@Transactional
public class DependenciaServiceImpl implements DependenciaService {

    private final Logger log = LoggerFactory.getLogger(DependenciaServiceImpl.class);

    private final DependenciaRepository dependenciaRepository;

    public DependenciaServiceImpl(DependenciaRepository dependenciaRepository) {
        this.dependenciaRepository = dependenciaRepository;
    }

    /**
     * Save a dependencia.
     *
     * @param dependencia the entity to save
     * @return the persisted entity
     */
    @Override
    public Dependencia save(Dependencia dependencia) {
        log.debug("Request to save Dependencia : {}", dependencia);
        return dependenciaRepository.save(dependencia);
    }

    /**
     * Get all the dependencias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Dependencia> findAll(Pageable pageable) {
        log.debug("Request to get all Dependencias");
        return dependenciaRepository.findAll(pageable);
    }


    /**
     * Get one dependencia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Dependencia> findOne(Long id) {
        log.debug("Request to get Dependencia : {}", id);
        return dependenciaRepository.findById(id);
    }

    /**
     * Delete the dependencia by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dependencia : {}", id);
        dependenciaRepository.deleteById(id);
    }
}
