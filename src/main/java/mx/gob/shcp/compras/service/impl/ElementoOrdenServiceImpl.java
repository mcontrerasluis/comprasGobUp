package mx.gob.shcp.compras.service.impl;

import mx.gob.shcp.compras.service.ElementoOrdenService;
import mx.gob.shcp.compras.domain.ElementoOrden;
import mx.gob.shcp.compras.repository.ElementoOrdenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ElementoOrden.
 */
@Service
@Transactional
public class ElementoOrdenServiceImpl implements ElementoOrdenService {

    private final Logger log = LoggerFactory.getLogger(ElementoOrdenServiceImpl.class);

    private final ElementoOrdenRepository elementoOrdenRepository;

    public ElementoOrdenServiceImpl(ElementoOrdenRepository elementoOrdenRepository) {
        this.elementoOrdenRepository = elementoOrdenRepository;
    }

    /**
     * Save a elementoOrden.
     *
     * @param elementoOrden the entity to save
     * @return the persisted entity
     */
    @Override
    public ElementoOrden save(ElementoOrden elementoOrden) {
        log.debug("Request to save ElementoOrden : {}", elementoOrden);
        return elementoOrdenRepository.save(elementoOrden);
    }

    /**
     * Get all the elementoOrdens.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ElementoOrden> findAll(Pageable pageable) {
        log.debug("Request to get all ElementoOrdens");
        return elementoOrdenRepository.findAll(pageable);
    }


    /**
     * Get one elementoOrden by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ElementoOrden> findOne(Long id) {
        log.debug("Request to get ElementoOrden : {}", id);
        return elementoOrdenRepository.findById(id);
    }

    /**
     * Delete the elementoOrden by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElementoOrden : {}", id);
        elementoOrdenRepository.deleteById(id);
    }
}
