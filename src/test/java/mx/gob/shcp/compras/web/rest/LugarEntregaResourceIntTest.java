package mx.gob.shcp.compras.web.rest;

import mx.gob.shcp.compras.ComprasGobUpApp;

import mx.gob.shcp.compras.domain.LugarEntrega;
import mx.gob.shcp.compras.repository.LugarEntregaRepository;
import mx.gob.shcp.compras.service.LugarEntregaService;
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
import java.util.List;


import static mx.gob.shcp.compras.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LugarEntregaResource REST controller.
 *
 * @see LugarEntregaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComprasGobUpApp.class)
public class LugarEntregaResourceIntTest {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_MUNICIPIO = "AAAAAAAAAA";
    private static final String UPDATED_MUNICIPIO = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUD = "AAAAAAAAAA";
    private static final String UPDATED_LATITUD = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUD = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUD = "BBBBBBBBBB";

    @Autowired
    private LugarEntregaRepository lugarEntregaRepository;

    @Autowired
    private LugarEntregaService lugarEntregaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLugarEntregaMockMvc;

    private LugarEntrega lugarEntrega;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LugarEntregaResource lugarEntregaResource = new LugarEntregaResource(lugarEntregaService);
        this.restLugarEntregaMockMvc = MockMvcBuilders.standaloneSetup(lugarEntregaResource)
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
    public static LugarEntrega createEntity(EntityManager em) {
        LugarEntrega lugarEntrega = new LugarEntrega()
            .descripcion(DEFAULT_DESCRIPCION)
            .estado(DEFAULT_ESTADO)
            .municipio(DEFAULT_MUNICIPIO)
            .direccion(DEFAULT_DIRECCION)
            .latitud(DEFAULT_LATITUD)
            .longitud(DEFAULT_LONGITUD);
        return lugarEntrega;
    }

    @Before
    public void initTest() {
        lugarEntrega = createEntity(em);
    }

    @Test
    @Transactional
    public void createLugarEntrega() throws Exception {
        int databaseSizeBeforeCreate = lugarEntregaRepository.findAll().size();

        // Create the LugarEntrega
        restLugarEntregaMockMvc.perform(post("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lugarEntrega)))
            .andExpect(status().isCreated());

        // Validate the LugarEntrega in the database
        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeCreate + 1);
        LugarEntrega testLugarEntrega = lugarEntregaList.get(lugarEntregaList.size() - 1);
        assertThat(testLugarEntrega.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testLugarEntrega.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testLugarEntrega.getMunicipio()).isEqualTo(DEFAULT_MUNICIPIO);
        assertThat(testLugarEntrega.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testLugarEntrega.getLatitud()).isEqualTo(DEFAULT_LATITUD);
        assertThat(testLugarEntrega.getLongitud()).isEqualTo(DEFAULT_LONGITUD);
    }

    @Test
    @Transactional
    public void createLugarEntregaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lugarEntregaRepository.findAll().size();

        // Create the LugarEntrega with an existing ID
        lugarEntrega.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLugarEntregaMockMvc.perform(post("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lugarEntrega)))
            .andExpect(status().isBadRequest());

        // Validate the LugarEntrega in the database
        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = lugarEntregaRepository.findAll().size();
        // set the field null
        lugarEntrega.setDescripcion(null);

        // Create the LugarEntrega, which fails.

        restLugarEntregaMockMvc.perform(post("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lugarEntrega)))
            .andExpect(status().isBadRequest());

        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = lugarEntregaRepository.findAll().size();
        // set the field null
        lugarEntrega.setEstado(null);

        // Create the LugarEntrega, which fails.

        restLugarEntregaMockMvc.perform(post("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lugarEntrega)))
            .andExpect(status().isBadRequest());

        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMunicipioIsRequired() throws Exception {
        int databaseSizeBeforeTest = lugarEntregaRepository.findAll().size();
        // set the field null
        lugarEntrega.setMunicipio(null);

        // Create the LugarEntrega, which fails.

        restLugarEntregaMockMvc.perform(post("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lugarEntrega)))
            .andExpect(status().isBadRequest());

        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDireccionIsRequired() throws Exception {
        int databaseSizeBeforeTest = lugarEntregaRepository.findAll().size();
        // set the field null
        lugarEntrega.setDireccion(null);

        // Create the LugarEntrega, which fails.

        restLugarEntregaMockMvc.perform(post("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lugarEntrega)))
            .andExpect(status().isBadRequest());

        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudIsRequired() throws Exception {
        int databaseSizeBeforeTest = lugarEntregaRepository.findAll().size();
        // set the field null
        lugarEntrega.setLatitud(null);

        // Create the LugarEntrega, which fails.

        restLugarEntregaMockMvc.perform(post("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lugarEntrega)))
            .andExpect(status().isBadRequest());

        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudIsRequired() throws Exception {
        int databaseSizeBeforeTest = lugarEntregaRepository.findAll().size();
        // set the field null
        lugarEntrega.setLongitud(null);

        // Create the LugarEntrega, which fails.

        restLugarEntregaMockMvc.perform(post("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lugarEntrega)))
            .andExpect(status().isBadRequest());

        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLugarEntregas() throws Exception {
        // Initialize the database
        lugarEntregaRepository.saveAndFlush(lugarEntrega);

        // Get all the lugarEntregaList
        restLugarEntregaMockMvc.perform(get("/api/lugar-entregas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lugarEntrega.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].municipio").value(hasItem(DEFAULT_MUNICIPIO.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].latitud").value(hasItem(DEFAULT_LATITUD.toString())))
            .andExpect(jsonPath("$.[*].longitud").value(hasItem(DEFAULT_LONGITUD.toString())));
    }
    
    @Test
    @Transactional
    public void getLugarEntrega() throws Exception {
        // Initialize the database
        lugarEntregaRepository.saveAndFlush(lugarEntrega);

        // Get the lugarEntrega
        restLugarEntregaMockMvc.perform(get("/api/lugar-entregas/{id}", lugarEntrega.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lugarEntrega.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.municipio").value(DEFAULT_MUNICIPIO.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.latitud").value(DEFAULT_LATITUD.toString()))
            .andExpect(jsonPath("$.longitud").value(DEFAULT_LONGITUD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLugarEntrega() throws Exception {
        // Get the lugarEntrega
        restLugarEntregaMockMvc.perform(get("/api/lugar-entregas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLugarEntrega() throws Exception {
        // Initialize the database
        lugarEntregaService.save(lugarEntrega);

        int databaseSizeBeforeUpdate = lugarEntregaRepository.findAll().size();

        // Update the lugarEntrega
        LugarEntrega updatedLugarEntrega = lugarEntregaRepository.findById(lugarEntrega.getId()).get();
        // Disconnect from session so that the updates on updatedLugarEntrega are not directly saved in db
        em.detach(updatedLugarEntrega);
        updatedLugarEntrega
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO)
            .municipio(UPDATED_MUNICIPIO)
            .direccion(UPDATED_DIRECCION)
            .latitud(UPDATED_LATITUD)
            .longitud(UPDATED_LONGITUD);

        restLugarEntregaMockMvc.perform(put("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLugarEntrega)))
            .andExpect(status().isOk());

        // Validate the LugarEntrega in the database
        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeUpdate);
        LugarEntrega testLugarEntrega = lugarEntregaList.get(lugarEntregaList.size() - 1);
        assertThat(testLugarEntrega.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testLugarEntrega.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testLugarEntrega.getMunicipio()).isEqualTo(UPDATED_MUNICIPIO);
        assertThat(testLugarEntrega.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testLugarEntrega.getLatitud()).isEqualTo(UPDATED_LATITUD);
        assertThat(testLugarEntrega.getLongitud()).isEqualTo(UPDATED_LONGITUD);
    }

    @Test
    @Transactional
    public void updateNonExistingLugarEntrega() throws Exception {
        int databaseSizeBeforeUpdate = lugarEntregaRepository.findAll().size();

        // Create the LugarEntrega

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLugarEntregaMockMvc.perform(put("/api/lugar-entregas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lugarEntrega)))
            .andExpect(status().isBadRequest());

        // Validate the LugarEntrega in the database
        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLugarEntrega() throws Exception {
        // Initialize the database
        lugarEntregaService.save(lugarEntrega);

        int databaseSizeBeforeDelete = lugarEntregaRepository.findAll().size();

        // Get the lugarEntrega
        restLugarEntregaMockMvc.perform(delete("/api/lugar-entregas/{id}", lugarEntrega.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LugarEntrega> lugarEntregaList = lugarEntregaRepository.findAll();
        assertThat(lugarEntregaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LugarEntrega.class);
        LugarEntrega lugarEntrega1 = new LugarEntrega();
        lugarEntrega1.setId(1L);
        LugarEntrega lugarEntrega2 = new LugarEntrega();
        lugarEntrega2.setId(lugarEntrega1.getId());
        assertThat(lugarEntrega1).isEqualTo(lugarEntrega2);
        lugarEntrega2.setId(2L);
        assertThat(lugarEntrega1).isNotEqualTo(lugarEntrega2);
        lugarEntrega1.setId(null);
        assertThat(lugarEntrega1).isNotEqualTo(lugarEntrega2);
    }
}
