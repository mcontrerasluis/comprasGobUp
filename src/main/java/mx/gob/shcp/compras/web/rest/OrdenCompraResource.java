package mx.gob.shcp.compras.web.rest;

import com.codahale.metrics.annotation.Timed;
import mx.gob.shcp.compras.domain.OrdenCompra;
import mx.gob.shcp.compras.service.OrdenCompraService;
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
 * REST controller for managing OrdenCompra.
 */
@RestController
@RequestMapping("/api")
public class OrdenCompraResource {

    private final Logger log = LoggerFactory.getLogger(OrdenCompraResource.class);

    private static final String ENTITY_NAME = "ordenCompra";

    private final OrdenCompraService ordenCompraService;

    public OrdenCompraResource(OrdenCompraService ordenCompraService) {
        this.ordenCompraService = ordenCompraService;
    }

    /**
     * POST  /orden-compras : Create a new ordenCompra.
     *
     * @param ordenCompra the ordenCompra to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ordenCompra, or with status 400 (Bad Request) if the ordenCompra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/orden-compras")
    @Timed
    public ResponseEntity<OrdenCompra> createOrdenCompra(@Valid @RequestBody OrdenCompra ordenCompra) throws URISyntaxException {
        log.debug("REST request to save OrdenCompra : {}", ordenCompra);
        if (ordenCompra.getId() != null) {
            throw new BadRequestAlertException("A new ordenCompra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrdenCompra result = ordenCompraService.save(ordenCompra);
        return ResponseEntity.created(new URI("/api/orden-compras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /orden-compras : Updates an existing ordenCompra.
     *
     * @param ordenCompra the ordenCompra to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ordenCompra,
     * or with status 400 (Bad Request) if the ordenCompra is not valid,
     * or with status 500 (Internal Server Error) if the ordenCompra couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/orden-compras")
    @Timed
    public ResponseEntity<OrdenCompra> updateOrdenCompra(@Valid @RequestBody OrdenCompra ordenCompra) throws URISyntaxException {
        log.debug("REST request to update OrdenCompra : {}", ordenCompra);
        if (ordenCompra.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrdenCompra result = ordenCompraService.save(ordenCompra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ordenCompra.getId().toString()))
            .body(result);
    }

    /**
     * GET  /orden-compras : get all the ordenCompras.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ordenCompras in body
     */
    @GetMapping("/orden-compras")
    @Timed
    public ResponseEntity<List<OrdenCompra>> getAllOrdenCompras(Pageable pageable) {
        log.debug("REST request to get a page of OrdenCompras");
        Page<OrdenCompra> page = ordenCompraService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/orden-compras");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /orden-compras/:id : get the "id" ordenCompra.
     *
     * @param id the id of the ordenCompra to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ordenCompra, or with status 404 (Not Found)
     */
    @GetMapping("/orden-compras/{id}")
    @Timed
    public ResponseEntity<OrdenCompra> getOrdenCompra(@PathVariable Long id) {
        log.debug("REST request to get OrdenCompra : {}", id);
        Optional<OrdenCompra> ordenCompra = ordenCompraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ordenCompra);
    }

    /**
     * DELETE  /orden-compras/:id : delete the "id" ordenCompra.
     *
     * @param id the id of the ordenCompra to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/orden-compras/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrdenCompra(@PathVariable Long id) {
        log.debug("REST request to delete OrdenCompra : {}", id);
        ordenCompraService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
