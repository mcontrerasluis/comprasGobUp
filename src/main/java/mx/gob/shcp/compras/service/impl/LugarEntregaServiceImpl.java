package mx.gob.shcp.compras.service.impl;

import mx.gob.shcp.compras.service.LugarEntregaService;
import mx.gob.shcp.compras.domain.LugarEntrega;
import mx.gob.shcp.compras.repository.LugarEntregaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LugarEntrega.
 */
@Service
@Transactional
public class LugarEntregaServiceImpl implements LugarEntregaService {

    private final Logger log = LoggerFactory.getLogger(LugarEntregaServiceImpl.class);

    private final LugarEntregaRepository lugarEntregaRepository;

    public LugarEntregaServiceImpl(LugarEntregaRepository lugarEntregaRepository) {
        this.lugarEntregaRepository = lugarEntregaRepository;
    }

    /**
     * Save a lugarEntrega.
     *
     * @param lugarEntrega the entity to save
     * @return the persisted entity
     */
    @Override
    public LugarEntrega save(LugarEntrega lugarEntrega) {
        log.debug("Request to save LugarEntrega : {}", lugarEntrega);
        return lugarEntregaRepository.save(lugarEntrega);
    }

    /**
     * Get all the lugarEntregas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LugarEntrega> findAll(Pageable pageable) {
        log.debug("Request to get all LugarEntregas");
        return lugarEntregaRepository.findAll(pageable);
    }


    /**
     * Get one lugarEntrega by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LugarEntrega> findOne(Long id) {
        log.debug("Request to get LugarEntrega : {}", id);
        return lugarEntregaRepository.findById(id);
    }

    /**
     * Delete the lugarEntrega by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LugarEntrega : {}", id);
        lugarEntregaRepository.deleteById(id);
    }
}
