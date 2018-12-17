package mx.gob.shcp.compras.web.rest;

import com.codahale.metrics.annotation.Timed;
import mx.gob.shcp.compras.domain.ElementoOrden;
import mx.gob.shcp.compras.service.ElementoOrdenService;
import mx.gob.shcp.compras.web.rest.errors.BadRequestAlertException;
import mx.gob.shcp.compras.web.rest.util.HeaderUtil;
import mx.gob.shcp.compras.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ElementoOrden.
 */
@RestController
@RequestMapping("/api")
public class ElementoOrdenResource {

    private final Logger log = LoggerFactory.getLogger(ElementoOrdenResource.class);

    private static final String ENTITY_NAME = "elementoOrden";

    private final ElementoOrdenService elementoOrdenService;

    public ElementoOrdenResource(ElementoOrdenService elementoOrdenService) {
        this.elementoOrdenService = elementoOrdenService;
    }

    /**
     * POST  /elemento-ordens : Create a new elementoOrden.
     *
     * @param elementoOrden the elementoOrden to create
     * @return the ResponseEntity with status 201 (Created) and with body the new elementoOrden, or with status 400 (Bad Request) if the elementoOrden has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/elemento-ordens")
    @Timed
    public ResponseEntity<ElementoOrden> createElementoOrden(@Valid @RequestBody ElementoOrden elementoOrden) throws URISyntaxException {
        log.debug("REST request to save ElementoOrden : {}", elementoOrden);
        if (elementoOrden.getId() != null) {
            throw new BadRequestAlertException("A new elementoOrden cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElementoOrden result = elementoOrdenService.save(elementoOrden);
        return ResponseEntity.created(new URI("/api/elemento-ordens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /elemento-ordens : Updates an existing elementoOrden.
     *
     * @param elementoOrden the elementoOrden to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated elementoOrden,
     * or with status 400 (Bad Request) if the elementoOrden is not valid,
     * or with status 500 (Internal Server Error) if the elementoOrden couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/elemento-ordens")
    @Timed
    public ResponseEntity<ElementoOrden> updateElementoOrden(@Valid @RequestBody ElementoOrden elementoOrden) throws URISyntaxException {
        log.debug("REST request to update ElementoOrden : {}", elementoOrden);
        if (elementoOrden.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElementoOrden result = elementoOrdenService.save(elementoOrden);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, elementoOrden.getId().toString()))
            .body(result);
    }

    /**
     * GET  /elemento-ordens : get all the elementoOrdens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of elementoOrdens in body
     */
    @GetMapping("/elemento-ordens")
    @Timed
    public ResponseEntity<List<ElementoOrden>> getAllElementoOrdens(Pageable pageable) {
        log.debug("REST request to get a page of ElementoOrdens");
        Page<ElementoOrden> page = elementoOrdenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/elemento-ordens");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /elemento-ordens/:id : get the "id" elementoOrden.
     *
     * @param id the id of the elementoOrden to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the elementoOrden, or with status 404 (Not Found)
     */
    @GetMapping("/elemento-ordens/{id}")
    @Timed
    public ResponseEntity<ElementoOrden> getElementoOrden(@PathVariable Long id) {
        log.debug("REST request to get ElementoOrden : {}", id);
        Optional<ElementoOrden> elementoOrden = elementoOrdenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elementoOrden);
    }

    /**
     * DELETE  /elemento-ordens/:id : delete the "id" elementoOrden.
     *
     * @param id the id of the elementoOrden to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/elemento-ordens/{id}")
    @Timed
    public ResponseEntity<Void> deleteElementoOrden(@PathVariable Long id) {
        log.debug("REST request to delete ElementoOrden : {}", id);
        elementoOrdenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
