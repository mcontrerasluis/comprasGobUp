package mx.gob.shcp.compras.web.rest;

import com.codahale.metrics.annotation.Timed;
import mx.gob.shcp.compras.domain.LugarEntrega;
import mx.gob.shcp.compras.service.LugarEntregaService;
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
 * REST controller for managing LugarEntrega.
 */
@RestController
@RequestMapping("/api")
public class LugarEntregaResource {

    private final Logger log = LoggerFactory.getLogger(LugarEntregaResource.class);

    private static final String ENTITY_NAME = "lugarEntrega";

    private final LugarEntregaService lugarEntregaService;

    public LugarEntregaResource(LugarEntregaService lugarEntregaService) {
        this.lugarEntregaService = lugarEntregaService;
    }

    /**
     * POST  /lugar-entregas : Create a new lugarEntrega.
     *
     * @param lugarEntrega the lugarEntrega to create
     * @return the ResponseEntity with status 201 (Created) and with body the new lugarEntrega, or with status 400 (Bad Request) if the lugarEntrega has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/lugar-entregas")
    @Timed
    public ResponseEntity<LugarEntrega> createLugarEntrega(@Valid @RequestBody LugarEntrega lugarEntrega) throws URISyntaxException {
        log.debug("REST request to save LugarEntrega : {}", lugarEntrega);
        if (lugarEntrega.getId() != null) {
            throw new BadRequestAlertException("A new lugarEntrega cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LugarEntrega result = lugarEntregaService.save(lugarEntrega);
        return ResponseEntity.created(new URI("/api/lugar-entregas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lugar-entregas : Updates an existing lugarEntrega.
     *
     * @param lugarEntrega the lugarEntrega to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated lugarEntrega,
     * or with status 400 (Bad Request) if the lugarEntrega is not valid,
     * or with status 500 (Internal Server Error) if the lugarEntrega couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/lugar-entregas")
    @Timed
    public ResponseEntity<LugarEntrega> updateLugarEntrega(@Valid @RequestBody LugarEntrega lugarEntrega) throws URISyntaxException {
        log.debug("REST request to update LugarEntrega : {}", lugarEntrega);
        if (lugarEntrega.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LugarEntrega result = lugarEntregaService.save(lugarEntrega);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, lugarEntrega.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lugar-entregas : get all the lugarEntregas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of lugarEntregas in body
     */
    @GetMapping("/lugar-entregas")
    @Timed
    public ResponseEntity<List<LugarEntrega>> getAllLugarEntregas(Pageable pageable) {
        log.debug("REST request to get a page of LugarEntregas");
        Page<LugarEntrega> page = lugarEntregaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/lugar-entregas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /lugar-entregas/:id : get the "id" lugarEntrega.
     *
     * @param id the id of the lugarEntrega to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the lugarEntrega, or with status 404 (Not Found)
     */
    @GetMapping("/lugar-entregas/{id}")
    @Timed
    public ResponseEntity<LugarEntrega> getLugarEntrega(@PathVariable Long id) {
        log.debug("REST request to get LugarEntrega : {}", id);
        Optional<LugarEntrega> lugarEntrega = lugarEntregaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lugarEntrega);
    }

    /**
     * DELETE  /lugar-entregas/:id : delete the "id" lugarEntrega.
     *
     * @param id the id of the lugarEntrega to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/lugar-entregas/{id}")
    @Timed
    public ResponseEntity<Void> deleteLugarEntrega(@PathVariable Long id) {
        log.debug("REST request to delete LugarEntrega : {}", id);
        lugarEntregaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
