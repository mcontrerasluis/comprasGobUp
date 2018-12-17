package mx.gob.shcp.compras.service.impl;

import mx.gob.shcp.compras.service.CUCOPService;
import mx.gob.shcp.compras.domain.CUCOP;
import mx.gob.shcp.compras.repository.CUCOPRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CUCOP.
 */
@Service
@Transactional
public class CUCOPServiceImpl implements CUCOPService {

    private final Logger log = LoggerFactory.getLogger(CUCOPServiceImpl.class);

    private final CUCOPRepository cUCOPRepository;

    public CUCOPServiceImpl(CUCOPRepository cUCOPRepository) {
        this.cUCOPRepository = cUCOPRepository;
    }

    /**
     * Save a cUCOP.
     *
     * @param cUCOP the entity to save
     * @return the persisted entity
     */
    @Override
    public CUCOP save(CUCOP cUCOP) {
        log.debug("Request to save CUCOP : {}", cUCOP);
        return cUCOPRepository.save(cUCOP);
    }

    /**
     * Get all the cUCOPS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CUCOP> findAll(Pageable pageable) {
        log.debug("Request to get all CUCOPS");
        return cUCOPRepository.findAll(pageable);
    }


    /**
     * Get one cUCOP by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CUCOP> findOne(Long id) {
        log.debug("Request to get CUCOP : {}", id);
        return cUCOPRepository.findById(id);
    }

    /**
     * Delete the cUCOP by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CUCOP : {}", id);
        cUCOPRepository.deleteById(id);
    }
}
