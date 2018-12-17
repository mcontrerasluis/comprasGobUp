package mx.gob.shcp.compras.web.rest;

import com.codahale.metrics.annotation.Timed;
import mx.gob.shcp.compras.domain.ContratoMarco;
import mx.gob.shcp.compras.service.ContratoMarcoService;
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
 * REST controller for managing ContratoMarco.
 */
@RestController
@RequestMapping("/api")
public class ContratoMarcoResource {

    private final Logger log = LoggerFactory.getLogger(ContratoMarcoResource.class);

    private static final String ENTITY_NAME = "contratoMarco";

    private final ContratoMarcoService contratoMarcoService;

    public ContratoMarcoResource(ContratoMarcoService contratoMarcoService) {
        this.contratoMarcoService = contratoMarcoService;
    }

    /**
     * POST  /contrato-marcos : Create a new contratoMarco.
     *
     * @param contratoMarco the contratoMarco to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contratoMarco, or with status 400 (Bad Request) if the contratoMarco has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contrato-marcos")
    @Timed
    public ResponseEntity<ContratoMarco> createContratoMarco(@Valid @RequestBody ContratoMarco contratoMarco) throws URISyntaxException {
        log.debug("REST request to save ContratoMarco : {}", contratoMarco);
        if (contratoMarco.getId() != null) {
            throw new BadRequestAlertException("A new contratoMarco cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContratoMarco result = contratoMarcoService.save(contratoMarco);
        return ResponseEntity.created(new URI("/api/contrato-marcos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contrato-marcos : Updates an existing contratoMarco.
     *
     * @param contratoMarco the contratoMarco to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contratoMarco,
     * or with status 400 (Bad Request) if the contratoMarco is not valid,
     * or with status 500 (Internal Server Error) if the contratoMarco couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contrato-marcos")
    @Timed
    public ResponseEntity<ContratoMarco> updateContratoMarco(@Valid @RequestBody ContratoMarco contratoMarco) throws URISyntaxException {
        log.debug("REST request to update ContratoMarco : {}", contratoMarco);
        if (contratoMarco.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContratoMarco result = contratoMarcoService.save(contratoMarco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contratoMarco.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contrato-marcos : get all the contratoMarcos.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of contratoMarcos in body
     */
    @GetMapping("/contrato-marcos")
    @Timed
    public ResponseEntity<List<ContratoMarco>> getAllContratoMarcos(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of ContratoMarcos");
        Page<ContratoMarco> page;
        if (eagerload) {
            page = contratoMarcoService.findAllWithEagerRelationships(pageable);
        } else {
            page = contratoMarcoService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/contrato-marcos?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /contrato-marcos/:id : get the "id" contratoMarco.
     *
     * @param id the id of the contratoMarco to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contratoMarco, or with status 404 (Not Found)
     */
    @GetMapping("/contrato-marcos/{id}")
    @Timed
    public ResponseEntity<ContratoMarco> getContratoMarco(@PathVariable Long id) {
        log.debug("REST request to get ContratoMarco : {}", id);
        Optional<ContratoMarco> contratoMarco = contratoMarcoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contratoMarco);
    }

    /**
     * DELETE  /contrato-marcos/:id : delete the "id" contratoMarco.
     *
     * @param id the id of the contratoMarco to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contrato-marcos/{id}")
    @Timed
    public ResponseEntity<Void> deleteContratoMarco(@PathVariable Long id) {
        log.debug("REST request to delete ContratoMarco : {}", id);
        contratoMarcoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
