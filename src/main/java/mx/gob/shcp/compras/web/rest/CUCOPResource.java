package mx.gob.shcp.compras.web.rest;

import com.codahale.metrics.annotation.Timed;
import mx.gob.shcp.compras.domain.CUCOP;
import mx.gob.shcp.compras.service.CUCOPService;
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
 * REST controller for managing CUCOP.
 */
@RestController
@RequestMapping("/api")
public class CUCOPResource {

    private final Logger log = LoggerFactory.getLogger(CUCOPResource.class);

    private static final String ENTITY_NAME = "cUCOP";

    private final CUCOPService cUCOPService;

    public CUCOPResource(CUCOPService cUCOPService) {
        this.cUCOPService = cUCOPService;
    }

    /**
     * POST  /cucops : Create a new cUCOP.
     *
     * @param cUCOP the cUCOP to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cUCOP, or with status 400 (Bad Request) if the cUCOP has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cucops")
    @Timed
    public ResponseEntity<CUCOP> createCUCOP(@Valid @RequestBody CUCOP cUCOP) throws URISyntaxException {
        log.debug("REST request to save CUCOP : {}", cUCOP);
        if (cUCOP.getId() != null) {
            throw new BadRequestAlertException("A new cUCOP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CUCOP result = cUCOPService.save(cUCOP);
        return ResponseEntity.created(new URI("/api/cucops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cucops : Updates an existing cUCOP.
     *
     * @param cUCOP the cUCOP to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cUCOP,
     * or with status 400 (Bad Request) if the cUCOP is not valid,
     * or with status 500 (Internal Server Error) if the cUCOP couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cucops")
    @Timed
    public ResponseEntity<CUCOP> updateCUCOP(@Valid @RequestBody CUCOP cUCOP) throws URISyntaxException {
        log.debug("REST request to update CUCOP : {}", cUCOP);
        if (cUCOP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CUCOP result = cUCOPService.save(cUCOP);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cUCOP.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cucops : get all the cUCOPS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cUCOPS in body
     */
    @GetMapping("/cucops")
    @Timed
    public ResponseEntity<List<CUCOP>> getAllCUCOPS(Pageable pageable) {
        log.debug("REST request to get a page of CUCOPS");
        Page<CUCOP> page = cUCOPService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cucops");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /cucops/:id : get the "id" cUCOP.
     *
     * @param id the id of the cUCOP to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cUCOP, or with status 404 (Not Found)
     */
    @GetMapping("/cucops/{id}")
    @Timed
    public ResponseEntity<CUCOP> getCUCOP(@PathVariable Long id) {
        log.debug("REST request to get CUCOP : {}", id);
        Optional<CUCOP> cUCOP = cUCOPService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cUCOP);
    }

    /**
     * DELETE  /cucops/:id : delete the "id" cUCOP.
     *
     * @param id the id of the cUCOP to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cucops/{id}")
    @Timed
    public ResponseEntity<Void> deleteCUCOP(@PathVariable Long id) {
        log.debug("REST request to delete CUCOP : {}", id);
        cUCOPService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
