package mx.gob.shcp.compras.service.impl;

import mx.gob.shcp.compras.service.OrdenCompraService;
import mx.gob.shcp.compras.domain.OrdenCompra;
import mx.gob.shcp.compras.repository.OrdenCompraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing OrdenCompra.
 */
@Service
@Transactional
public class OrdenCompraServiceImpl implements OrdenCompraService {

    private final Logger log = LoggerFactory.getLogger(OrdenCompraServiceImpl.class);

    private final OrdenCompraRepository ordenCompraRepository;

    public OrdenCompraServiceImpl(OrdenCompraRepository ordenCompraRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
    }

    /**
     * Save a ordenCompra.
     *
     * @param ordenCompra the entity to save
     * @return the persisted entity
     */
    @Override
    public OrdenCompra save(OrdenCompra ordenCompra) {
        log.debug("Request to save OrdenCompra : {}", ordenCompra);
        return ordenCompraRepository.save(ordenCompra);
    }

    /**
     * Get all the ordenCompras.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrdenCompra> findAll(Pageable pageable) {
        log.debug("Request to get all OrdenCompras");
        return ordenCompraRepository.findAll(pageable);
    }


    /**
     * Get one ordenCompra by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenCompra> findOne(Long id) {
        log.debug("Request to get OrdenCompra : {}", id);
        return ordenCompraRepository.findById(id);
    }

    /**
     * Delete the ordenCompra by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrdenCompra : {}", id);
        ordenCompraRepository.deleteById(id);
    }
}
