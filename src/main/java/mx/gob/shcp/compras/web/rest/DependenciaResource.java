package mx.gob.shcp.compras.web.rest;

import com.codahale.metrics.annotation.Timed;
import mx.gob.shcp.compras.domain.Dependencia;
import mx.gob.shcp.compras.service.DependenciaService;
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
 * REST controller for managing Dependencia.
 */
@RestController
@RequestMapping("/api")
public class DependenciaResource {

    private final Logger log = LoggerFactory.getLogger(DependenciaResource.class);

    private static final String ENTITY_NAME = "dependencia";

    private final DependenciaService dependenciaService;

    public DependenciaResource(DependenciaService dependenciaService) {
        this.dependenciaService = dependenciaService;
    }

    /**
     * POST  /dependencias : Create a new dependencia.
     *
     * @param dependencia the dependencia to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dependencia, or with status 400 (Bad Request) if the dependencia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dependencias")
    @Timed
    public ResponseEntity<Dependencia> createDependencia(@Valid @RequestBody Dependencia dependencia) throws URISyntaxException {
        log.debug("REST request to save Dependencia : {}", dependencia);
        if (dependencia.getId() != null) {
            throw new BadRequestAlertException("A new dependencia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Dependencia result = dependenciaService.save(dependencia);
        return ResponseEntity.created(new URI("/api/dependencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dependencias : Updates an existing dependencia.
     *
     * @param dependencia the dependencia to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dependencia,
     * or with status 400 (Bad Request) if the dependencia is not valid,
     * or with status 500 (Internal Server Error) if the dependencia couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dependencias")
    @Timed
    public ResponseEntity<Dependencia> updateDependencia(@Valid @RequestBody Dependencia dependencia) throws URISyntaxException {
        log.debug("REST request to update Dependencia : {}", dependencia);
        if (dependencia.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Dependencia result = dependenciaService.save(dependencia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dependencia.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dependencias : get all the dependencias.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dependencias in body
     */
    @GetMapping("/dependencias")
    @Timed
    public ResponseEntity<List<Dependencia>> getAllDependencias(Pageable pageable) {
        log.debug("REST request to get a page of Dependencias");
        Page<Dependencia> page = dependenciaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dependencias");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /dependencias/:id : get the "id" dependencia.
     *
     * @param id the id of the dependencia to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dependencia, or with status 404 (Not Found)
     */
    @GetMapping("/dependencias/{id}")
    @Timed
    public ResponseEntity<Dependencia> getDependencia(@PathVariable Long id) {
        log.debug("REST request to get Dependencia : {}", id);
        Optional<Dependencia> dependencia = dependenciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dependencia);
    }

    /**
     * DELETE  /dependencias/:id : delete the "id" dependencia.
     *
     * @param id the id of the dependencia to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dependencias/{id}")
    @Timed
    public ResponseEntity<Void> deleteDependencia(@PathVariable Long id) {
        log.debug("REST request to delete Dependencia : {}", id);
        dependenciaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
