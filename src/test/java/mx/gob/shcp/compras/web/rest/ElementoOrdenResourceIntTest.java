package mx.gob.shcp.compras.web.rest;

import mx.gob.shcp.compras.ComprasGobUpApp;

import mx.gob.shcp.compras.domain.ElementoOrden;
import mx.gob.shcp.compras.repository.ElementoOrdenRepository;
import mx.gob.shcp.compras.service.ElementoOrdenService;
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
import java.math.BigDecimal;
import java.util.List;


import static mx.gob.shcp.compras.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import mx.gob.shcp.compras.domain.enumeration.EstatusElementoOrden;
/**
 * Test class for the ElementoOrdenResource REST controller.
 *
 * @see ElementoOrdenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComprasGobUpApp.class)
public class ElementoOrdenResourceIntTest {

    private static final Integer DEFAULT_CANTIDAD = 1;
    private static final Integer UPDATED_CANTIDAD = 2;

    private static final BigDecimal DEFAULT_PRECIO_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRECIO_TOTAL = new BigDecimal(2);

    private static final EstatusElementoOrden DEFAULT_ESTATUS = EstatusElementoOrden.DISPONIBLE;
    private static final EstatusElementoOrden UPDATED_ESTATUS = EstatusElementoOrden.AGOTADO;

    private static final String DEFAULT_PROVEEDOR_D = "AAAAAAAAAA";
    private static final String UPDATED_PROVEEDOR_D = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRATO_MARCO_D = "AAAAAAAAAA";
    private static final String UPDATED_CONTRATO_MARCO_D = "BBBBBBBBBB";

    @Autowired
    private ElementoOrdenRepository elementoOrdenRepository;

    @Autowired
    private ElementoOrdenService elementoOrdenService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restElementoOrdenMockMvc;

    private ElementoOrden elementoOrden;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ElementoOrdenResource elementoOrdenResource = new ElementoOrdenResource(elementoOrdenService);
        this.restElementoOrdenMockMvc = MockMvcBuilders.standaloneSetup(elementoOrdenResource)
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
    public static ElementoOrden createEntity(EntityManager em) {
        ElementoOrden elementoOrden = new ElementoOrden()
            .cantidad(DEFAULT_CANTIDAD)
            .precioTotal(DEFAULT_PRECIO_TOTAL)
            .estatus(DEFAULT_ESTATUS)
            .proveedorD(DEFAULT_PROVEEDOR_D)
            .contratoMarcoD(DEFAULT_CONTRATO_MARCO_D);
        return elementoOrden;
    }

    @Before
    public void initTest() {
        elementoOrden = createEntity(em);
    }

    @Test
    @Transactional
    public void createElementoOrden() throws Exception {
        int databaseSizeBeforeCreate = elementoOrdenRepository.findAll().size();

        // Create the ElementoOrden
        restElementoOrdenMockMvc.perform(post("/api/elemento-ordens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoOrden)))
            .andExpect(status().isCreated());

        // Validate the ElementoOrden in the database
        List<ElementoOrden> elementoOrdenList = elementoOrdenRepository.findAll();
        assertThat(elementoOrdenList).hasSize(databaseSizeBeforeCreate + 1);
        ElementoOrden testElementoOrden = elementoOrdenList.get(elementoOrdenList.size() - 1);
        assertThat(testElementoOrden.getCantidad()).isEqualTo(DEFAULT_CANTIDAD);
        assertThat(testElementoOrden.getPrecioTotal()).isEqualTo(DEFAULT_PRECIO_TOTAL);
        assertThat(testElementoOrden.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testElementoOrden.getProveedorD()).isEqualTo(DEFAULT_PROVEEDOR_D);
        assertThat(testElementoOrden.getContratoMarcoD()).isEqualTo(DEFAULT_CONTRATO_MARCO_D);
    }

    @Test
    @Transactional
    public void createElementoOrdenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elementoOrdenRepository.findAll().size();

        // Create the ElementoOrden with an existing ID
        elementoOrden.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElementoOrdenMockMvc.perform(post("/api/elemento-ordens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoOrden)))
            .andExpect(status().isBadRequest());

        // Validate the ElementoOrden in the database
        List<ElementoOrden> elementoOrdenList = elementoOrdenRepository.findAll();
        assertThat(elementoOrdenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCantidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = elementoOrdenRepository.findAll().size();
        // set the field null
        elementoOrden.setCantidad(null);

        // Create the ElementoOrden, which fails.

        restElementoOrdenMockMvc.perform(post("/api/elemento-ordens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoOrden)))
            .andExpect(status().isBadRequest());

        List<ElementoOrden> elementoOrdenList = elementoOrdenRepository.findAll();
        assertThat(elementoOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrecioTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = elementoOrdenRepository.findAll().size();
        // set the field null
        elementoOrden.setPrecioTotal(null);

        // Create the ElementoOrden, which fails.

        restElementoOrdenMockMvc.perform(post("/api/elemento-ordens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoOrden)))
            .andExpect(status().isBadRequest());

        List<ElementoOrden> elementoOrdenList = elementoOrdenRepository.findAll();
        assertThat(elementoOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = elementoOrdenRepository.findAll().size();
        // set the field null
        elementoOrden.setEstatus(null);

        // Create the ElementoOrden, which fails.

        restElementoOrdenMockMvc.perform(post("/api/elemento-ordens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoOrden)))
            .andExpect(status().isBadRequest());

        List<ElementoOrden> elementoOrdenList = elementoOrdenRepository.findAll();
        assertThat(elementoOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllElementoOrdens() throws Exception {
        // Initialize the database
        elementoOrdenRepository.saveAndFlush(elementoOrden);

        // Get all the elementoOrdenList
        restElementoOrdenMockMvc.perform(get("/api/elemento-ordens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elementoOrden.getId().intValue())))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)))
            .andExpect(jsonPath("$.[*].precioTotal").value(hasItem(DEFAULT_PRECIO_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS.toString())))
            .andExpect(jsonPath("$.[*].proveedorD").value(hasItem(DEFAULT_PROVEEDOR_D.toString())))
            .andExpect(jsonPath("$.[*].contratoMarcoD").value(hasItem(DEFAULT_CONTRATO_MARCO_D.toString())));
    }
    
    @Test
    @Transactional
    public void getElementoOrden() throws Exception {
        // Initialize the database
        elementoOrdenRepository.saveAndFlush(elementoOrden);

        // Get the elementoOrden
        restElementoOrdenMockMvc.perform(get("/api/elemento-ordens/{id}", elementoOrden.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(elementoOrden.getId().intValue()))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD))
            .andExpect(jsonPath("$.precioTotal").value(DEFAULT_PRECIO_TOTAL.intValue()))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS.toString()))
            .andExpect(jsonPath("$.proveedorD").value(DEFAULT_PROVEEDOR_D.toString()))
            .andExpect(jsonPath("$.contratoMarcoD").value(DEFAULT_CONTRATO_MARCO_D.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingElementoOrden() throws Exception {
        // Get the elementoOrden
        restElementoOrdenMockMvc.perform(get("/api/elemento-ordens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElementoOrden() throws Exception {
        // Initialize the database
        elementoOrdenService.save(elementoOrden);

        int databaseSizeBeforeUpdate = elementoOrdenRepository.findAll().size();

        // Update the elementoOrden
        ElementoOrden updatedElementoOrden = elementoOrdenRepository.findById(elementoOrden.getId()).get();
        // Disconnect from session so that the updates on updatedElementoOrden are not directly saved in db
        em.detach(updatedElementoOrden);
        updatedElementoOrden
            .cantidad(UPDATED_CANTIDAD)
            .precioTotal(UPDATED_PRECIO_TOTAL)
            .estatus(UPDATED_ESTATUS)
            .proveedorD(UPDATED_PROVEEDOR_D)
            .contratoMarcoD(UPDATED_CONTRATO_MARCO_D);

        restElementoOrdenMockMvc.perform(put("/api/elemento-ordens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedElementoOrden)))
            .andExpect(status().isOk());

        // Validate the ElementoOrden in the database
        List<ElementoOrden> elementoOrdenList = elementoOrdenRepository.findAll();
        assertThat(elementoOrdenList).hasSize(databaseSizeBeforeUpdate);
        ElementoOrden testElementoOrden = elementoOrdenList.get(elementoOrdenList.size() - 1);
        assertThat(testElementoOrden.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
        assertThat(testElementoOrden.getPrecioTotal()).isEqualTo(UPDATED_PRECIO_TOTAL);
        assertThat(testElementoOrden.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testElementoOrden.getProveedorD()).isEqualTo(UPDATED_PROVEEDOR_D);
        assertThat(testElementoOrden.getContratoMarcoD()).isEqualTo(UPDATED_CONTRATO_MARCO_D);
    }

    @Test
    @Transactional
    public void updateNonExistingElementoOrden() throws Exception {
        int databaseSizeBeforeUpdate = elementoOrdenRepository.findAll().size();

        // Create the ElementoOrden

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElementoOrdenMockMvc.perform(put("/api/elemento-ordens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoOrden)))
            .andExpect(status().isBadRequest());

        // Validate the ElementoOrden in the database
        List<ElementoOrden> elementoOrdenList = elementoOrdenRepository.findAll();
        assertThat(elementoOrdenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElementoOrden() throws Exception {
        // Initialize the database
        elementoOrdenService.save(elementoOrden);

        int databaseSizeBeforeDelete = elementoOrdenRepository.findAll().size();

        // Get the elementoOrden
        restElementoOrdenMockMvc.perform(delete("/api/elemento-ordens/{id}", elementoOrden.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ElementoOrden> elementoOrdenList = elementoOrdenRepository.findAll();
        assertThat(elementoOrdenList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElementoOrden.class);
        ElementoOrden elementoOrden1 = new ElementoOrden();
        elementoOrden1.setId(1L);
        ElementoOrden elementoOrden2 = new ElementoOrden();
        elementoOrden2.setId(elementoOrden1.getId());
        assertThat(elementoOrden1).isEqualTo(elementoOrden2);
        elementoOrden2.setId(2L);
        assertThat(elementoOrden1).isNotEqualTo(elementoOrden2);
        elementoOrden1.setId(null);
        assertThat(elementoOrden1).isNotEqualTo(elementoOrden2);
    }
}
