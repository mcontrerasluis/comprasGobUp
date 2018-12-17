package mx.gob.shcp.compras.web.rest;

import mx.gob.shcp.compras.ComprasGobUpApp;

import mx.gob.shcp.compras.domain.OrdenCompra;
import mx.gob.shcp.compras.repository.OrdenCompraRepository;
import mx.gob.shcp.compras.service.OrdenCompraService;
import mx.gob.shcp.compras.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static mx.gob.shcp.compras.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import mx.gob.shcp.compras.domain.enumeration.EstatusOrden;
/**
 * Test class for the OrdenCompraResource REST controller.
 *
 * @see OrdenCompraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComprasGobUpApp.class)
public class OrdenCompraResourceIntTest {

    private static final Instant DEFAULT_FECHA_ENTRADA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_ENTRADA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final EstatusOrden DEFAULT_ESTATUS = EstatusOrden.COMPLETADA;
    private static final EstatusOrden UPDATED_ESTATUS = EstatusOrden.PENDIENTE;

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_LUGAR_ENTREGA_D = "AAAAAAAAAA";
    private static final String UPDATED_LUGAR_ENTREGA_D = "BBBBBBBBBB";

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private OrdenCompraService ordenCompraService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrdenCompraMockMvc;

    private OrdenCompra ordenCompra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrdenCompraResource ordenCompraResource = new OrdenCompraResource(ordenCompraService);
        this.restOrdenCompraMockMvc = MockMvcBuilders.standaloneSetup(ordenCompraResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrdenCompra createEntity(EntityManager em) {
        OrdenCompra ordenCompra = new OrdenCompra()
            .fechaEntrada(DEFAULT_FECHA_ENTRADA)
            .estatus(DEFAULT_ESTATUS)
            .codigo(DEFAULT_CODIGO)
            .lugarEntregaD(DEFAULT_LUGAR_ENTREGA_D);
        return ordenCompra;
    }

    @Before
    public void initTest() {
        ordenCompra = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrdenCompra() throws Exception {
        int databaseSizeBeforeCreate = ordenCompraRepository.findAll().size();

        // Create the OrdenCompra
        restOrdenCompraMockMvc.perform(post("/api/orden-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordenCompra)))
            .andExpect(status().isCreated());

        // Validate the OrdenCompra in the database
        List<OrdenCompra> ordenCompraList = ordenCompraRepository.findAll();
        assertThat(ordenCompraList).hasSize(databaseSizeBeforeCreate + 1);
        OrdenCompra testOrdenCompra = ordenCompraList.get(ordenCompraList.size() - 1);
        assertThat(testOrdenCompra.getFechaEntrada()).isEqualTo(DEFAULT_FECHA_ENTRADA);
        assertThat(testOrdenCompra.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testOrdenCompra.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testOrdenCompra.getLugarEntregaD()).isEqualTo(DEFAULT_LUGAR_ENTREGA_D);
    }

    @Test
    @Transactional
    public void createOrdenCompraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordenCompraRepository.findAll().size();

        // Create the OrdenCompra with an existing ID
        ordenCompra.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdenCompraMockMvc.perform(post("/api/orden-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordenCompra)))
            .andExpect(status().isBadRequest());

        // Validate the OrdenCompra in the database
        List<OrdenCompra> ordenCompraList = ordenCompraRepository.findAll();
        assertThat(ordenCompraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFechaEntradaIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenCompraRepository.findAll().size();
        // set the field null
        ordenCompra.setFechaEntrada(null);

        // Create the OrdenCompra, which fails.

        restOrdenCompraMockMvc.perform(post("/api/orden-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordenCompra)))
            .andExpect(status().isBadRequest());

        List<OrdenCompra> ordenCompraList = ordenCompraRepository.findAll();
        assertThat(ordenCompraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenCompraRepository.findAll().size();
        // set the field null
        ordenCompra.setEstatus(null);

        // Create the OrdenCompra, which fails.

        restOrdenCompraMockMvc.perform(post("/api/orden-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordenCompra)))
            .andExpect(status().isBadRequest());

        List<OrdenCompra> ordenCompraList = ordenCompraRepository.findAll();
        assertThat(ordenCompraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordenCompraRepository.findAll().size();
        // set the field null
        ordenCompra.setCodigo(null);

        // Create the OrdenCompra, which fails.

        restOrdenCompraMockMvc.perform(post("/api/orden-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordenCompra)))
            .andExpect(status().isBadRequest());

        List<OrdenCompra> ordenCompraList = ordenCompraRepository.findAll();
        assertThat(ordenCompraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrdenCompras() throws Exception {
        // Initialize the database
        ordenCompraRepository.saveAndFlush(ordenCompra);

        // Get all the ordenCompraList
        restOrdenCompraMockMvc.perform(get("/api/orden-compras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordenCompra.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaEntrada").value(hasItem(DEFAULT_FECHA_ENTRADA.toString())))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS.toString())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())))
            .andExpect(jsonPath("$.[*].lugarEntregaD").value(hasItem(DEFAULT_LUGAR_ENTREGA_D.toString())));
    }
    
    @Test
    @Transactional
    public void getOrdenCompra() throws Exception {
        // Initialize the database
        ordenCompraRepository.saveAndFlush(ordenCompra);

        // Get the ordenCompra
        restOrdenCompraMockMvc.perform(get("/api/orden-compras/{id}", ordenCompra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ordenCompra.getId().intValue()))
            .andExpect(jsonPath("$.fechaEntrada").value(DEFAULT_FECHA_ENTRADA.toString()))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS.toString()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()))
            .andExpect(jsonPath("$.lugarEntregaD").value(DEFAULT_LUGAR_ENTREGA_D.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrdenCompra() throws Exception {
        // Get the ordenCompra
        restOrdenCompraMockMvc.perform(get("/api/orden-compras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdenCompra() throws Exception {
        // Initialize the database
        ordenCompraService.save(ordenCompra);

        int databaseSizeBeforeUpdate = ordenCompraRepository.findAll().size();

        // Update the ordenCompra
        OrdenCompra updatedOrdenCompra = ordenCompraRepository.findById(ordenCompra.getId()).get();
        // Disconnect from session so that the updates on updatedOrdenCompra are not directly saved in db
        em.detach(updatedOrdenCompra);
        updatedOrdenCompra
            .fechaEntrada(UPDATED_FECHA_ENTRADA)
            .estatus(UPDATED_ESTATUS)
            .codigo(UPDATED_CODIGO)
            .lugarEntregaD(UPDATED_LUGAR_ENTREGA_D);

        restOrdenCompraMockMvc.perform(put("/api/orden-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrdenCompra)))
            .andExpect(status().isOk());

        // Validate the OrdenCompra in the database
        List<OrdenCompra> ordenCompraList = ordenCompraRepository.findAll();
        assertThat(ordenCompraList).hasSize(databaseSizeBeforeUpdate);
        OrdenCompra testOrdenCompra = ordenCompraList.get(ordenCompraList.size() - 1);
        assertThat(testOrdenCompra.getFechaEntrada()).isEqualTo(UPDATED_FECHA_ENTRADA);
        assertThat(testOrdenCompra.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testOrdenCompra.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testOrdenCompra.getLugarEntregaD()).isEqualTo(UPDATED_LUGAR_ENTREGA_D);
    }

    @Test
    @Transactional
    public void updateNonExistingOrdenCompra() throws Exception {
        int databaseSizeBeforeUpdate = ordenCompraRepository.findAll().size();

        // Create the OrdenCompra

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdenCompraMockMvc.perform(put("/api/orden-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordenCompra)))
            .andExpect(status().isBadRequest());

        // Validate the OrdenCompra in the database
        List<OrdenCompra> ordenCompraList = ordenCompraRepository.findAll();
        assertThat(ordenCompraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrdenCompra() throws Exception {
        // Initialize the database
        ordenCompraService.save(ordenCompra);

        int databaseSizeBeforeDelete = ordenCompraRepository.findAll().size();

        // Get the ordenCompra
        restOrdenCompraMockMvc.perform(delete("/api/orden-compras/{id}", ordenCompra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrdenCompra> ordenCompraList = ordenCompraRepository.findAll();
        assertThat(ordenCompraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdenCompra.class);
        OrdenCompra ordenCompra1 = new OrdenCompra();
        ordenCompra1.setId(1L);
        OrdenCompra ordenCompra2 = new OrdenCompra();
        ordenCompra2.setId(ordenCompra1.getId());
        assertThat(ordenCompra1).isEqualTo(ordenCompra2);
        ordenCompra2.setId(2L);
        assertThat(ordenCompra1).isNotEqualTo(ordenCompra2);
        ordenCompra1.setId(null);
        assertThat(ordenCompra1).isNotEqualTo(ordenCompra2);
    }
}
