package mx.gob.shcp.compras.web.rest;

import mx.gob.shcp.compras.ComprasGobUpApp;

import mx.gob.shcp.compras.domain.Embarque;
import mx.gob.shcp.compras.repository.EmbarqueRepository;
import mx.gob.shcp.compras.service.EmbarqueService;
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

/**
 * Test class for the EmbarqueResource REST controller.
 *
 * @see EmbarqueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComprasGobUpApp.class)
public class EmbarqueResourceIntTest {

    private static final String DEFAULT_CODIGO_RASTREO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_RASTREO = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DETALLES = "AAAAAAAAAA";
    private static final String UPDATED_DETALLES = "BBBBBBBBBB";

    @Autowired
    private EmbarqueRepository embarqueRepository;

    @Autowired
    private EmbarqueService embarqueService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmbarqueMockMvc;

    private Embarque embarque;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmbarqueResource embarqueResource = new EmbarqueResource(embarqueService);
        this.restEmbarqueMockMvc = MockMvcBuilders.standaloneSetup(embarqueResource)
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
    public static Embarque createEntity(EntityManager em) {
        Embarque embarque = new Embarque()
            .codigoRastreo(DEFAULT_CODIGO_RASTREO)
            .fecha(DEFAULT_FECHA)
            .detalles(DEFAULT_DETALLES);
        return embarque;
    }

    @Before
    public void initTest() {
        embarque = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmbarque() throws Exception {
        int databaseSizeBeforeCreate = embarqueRepository.findAll().size();

        // Create the Embarque
        restEmbarqueMockMvc.perform(post("/api/embarques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(embarque)))
            .andExpect(status().isCreated());

        // Validate the Embarque in the database
        List<Embarque> embarqueList = embarqueRepository.findAll();
        assertThat(embarqueList).hasSize(databaseSizeBeforeCreate + 1);
        Embarque testEmbarque = embarqueList.get(embarqueList.size() - 1);
        assertThat(testEmbarque.getCodigoRastreo()).isEqualTo(DEFAULT_CODIGO_RASTREO);
        assertThat(testEmbarque.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testEmbarque.getDetalles()).isEqualTo(DEFAULT_DETALLES);
    }

    @Test
    @Transactional
    public void createEmbarqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = embarqueRepository.findAll().size();

        // Create the Embarque with an existing ID
        embarque.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmbarqueMockMvc.perform(post("/api/embarques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(embarque)))
            .andExpect(status().isBadRequest());

        // Validate the Embarque in the database
        List<Embarque> embarqueList = embarqueRepository.findAll();
        assertThat(embarqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = embarqueRepository.findAll().size();
        // set the field null
        embarque.setFecha(null);

        // Create the Embarque, which fails.

        restEmbarqueMockMvc.perform(post("/api/embarques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(embarque)))
            .andExpect(status().isBadRequest());

        List<Embarque> embarqueList = embarqueRepository.findAll();
        assertThat(embarqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDetallesIsRequired() throws Exception {
        int databaseSizeBeforeTest = embarqueRepository.findAll().size();
        // set the field null
        embarque.setDetalles(null);

        // Create the Embarque, which fails.

        restEmbarqueMockMvc.perform(post("/api/embarques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(embarque)))
            .andExpect(status().isBadRequest());

        List<Embarque> embarqueList = embarqueRepository.findAll();
        assertThat(embarqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmbarques() throws Exception {
        // Initialize the database
        embarqueRepository.saveAndFlush(embarque);

        // Get all the embarqueList
        restEmbarqueMockMvc.perform(get("/api/embarques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(embarque.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigoRastreo").value(hasItem(DEFAULT_CODIGO_RASTREO.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].detalles").value(hasItem(DEFAULT_DETALLES.toString())));
    }
    
    @Test
    @Transactional
    public void getEmbarque() throws Exception {
        // Initialize the database
        embarqueRepository.saveAndFlush(embarque);

        // Get the embarque
        restEmbarqueMockMvc.perform(get("/api/embarques/{id}", embarque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(embarque.getId().intValue()))
            .andExpect(jsonPath("$.codigoRastreo").value(DEFAULT_CODIGO_RASTREO.toString()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.detalles").value(DEFAULT_DETALLES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEmbarque() throws Exception {
        // Get the embarque
        restEmbarqueMockMvc.perform(get("/api/embarques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmbarque() throws Exception {
        // Initialize the database
        embarqueService.save(embarque);

        int databaseSizeBeforeUpdate = embarqueRepository.findAll().size();

        // Update the embarque
        Embarque updatedEmbarque = embarqueRepository.findById(embarque.getId()).get();
        // Disconnect from session so that the updates on updatedEmbarque are not directly saved in db
        em.detach(updatedEmbarque);
        updatedEmbarque
            .codigoRastreo(UPDATED_CODIGO_RASTREO)
            .fecha(UPDATED_FECHA)
            .detalles(UPDATED_DETALLES);

        restEmbarqueMockMvc.perform(put("/api/embarques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmbarque)))
            .andExpect(status().isOk());

        // Validate the Embarque in the database
        List<Embarque> embarqueList = embarqueRepository.findAll();
        assertThat(embarqueList).hasSize(databaseSizeBeforeUpdate);
        Embarque testEmbarque = embarqueList.get(embarqueList.size() - 1);
        assertThat(testEmbarque.getCodigoRastreo()).isEqualTo(UPDATED_CODIGO_RASTREO);
        assertThat(testEmbarque.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testEmbarque.getDetalles()).isEqualTo(UPDATED_DETALLES);
    }

    @Test
    @Transactional
    public void updateNonExistingEmbarque() throws Exception {
        int databaseSizeBeforeUpdate = embarqueRepository.findAll().size();

        // Create the Embarque

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmbarqueMockMvc.perform(put("/api/embarques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(embarque)))
            .andExpect(status().isBadRequest());

        // Validate the Embarque in the database
        List<Embarque> embarqueList = embarqueRepository.findAll();
        assertThat(embarqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmbarque() throws Exception {
        // Initialize the database
        embarqueService.save(embarque);

        int databaseSizeBeforeDelete = embarqueRepository.findAll().size();

        // Get the embarque
        restEmbarqueMockMvc.perform(delete("/api/embarques/{id}", embarque.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Embarque> embarqueList = embarqueRepository.findAll();
        assertThat(embarqueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Embarque.class);
        Embarque embarque1 = new Embarque();
        embarque1.setId(1L);
        Embarque embarque2 = new Embarque();
        embarque2.setId(embarque1.getId());
        assertThat(embarque1).isEqualTo(embarque2);
        embarque2.setId(2L);
        assertThat(embarque1).isNotEqualTo(embarque2);
        embarque1.setId(null);
        assertThat(embarque1).isNotEqualTo(embarque2);
    }
}
