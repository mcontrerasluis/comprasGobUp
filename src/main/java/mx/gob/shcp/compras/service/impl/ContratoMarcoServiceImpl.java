package mx.gob.shcp.compras.service.impl;

import mx.gob.shcp.compras.service.ContratoMarcoService;
import mx.gob.shcp.compras.domain.ContratoMarco;
import mx.gob.shcp.compras.repository.ContratoMarcoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ContratoMarco.
 */
@Service
@Transactional
public class ContratoMarcoServiceImpl implements ContratoMarcoService {

    private final Logger log = LoggerFactory.getLogger(ContratoMarcoServiceImpl.class);

    private final ContratoMarcoRepository contratoMarcoRepository;

    public ContratoMarcoServiceImpl(ContratoMarcoRepository contratoMarcoRepository) {
        this.contratoMarcoRepository = contratoMarcoRepository;
    }

    /**
     * Save a contratoMarco.
     *
     * @param contratoMarco the entity to save
     * @return the persisted entity
     */
    @Override
    public ContratoMarco save(ContratoMarco contratoMarco) {
        log.debug("Request to save ContratoMarco : {}", contratoMarco);
        return contratoMarcoRepository.save(contratoMarco);
    }

    /**
     * Get all the contratoMarcos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContratoMarco> findAll(Pageable pageable) {
        log.debug("Request to get all ContratoMarcos");
        return contratoMarcoRepository.findAll(pageable);
    }

    /**
     * Get all the ContratoMarco with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ContratoMarco> findAllWithEagerRelationships(Pageable pageable) {
        return contratoMarcoRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one contratoMarco by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContratoMarco> findOne(Long id) {
        log.debug("Request to get ContratoMarco : {}", id);
        return contratoMarcoRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the contratoMarco by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContratoMarco : {}", id);
        contratoMarcoRepository.deleteById(id);
    }
}
