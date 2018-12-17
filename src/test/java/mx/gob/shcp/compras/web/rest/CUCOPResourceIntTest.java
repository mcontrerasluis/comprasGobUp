package mx.gob.shcp.compras.web.rest;

import mx.gob.shcp.compras.ComprasGobUpApp;

import mx.gob.shcp.compras.domain.CUCOP;
import mx.gob.shcp.compras.repository.CUCOPRepository;
import mx.gob.shcp.compras.service.CUCOPService;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static mx.gob.shcp.compras.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CUCOPResource REST controller.
 *
 * @see CUCOPResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComprasGobUpApp.class)
public class CUCOPResourceIntTest {

    private static final Integer DEFAULT_TIPO = 1;
    private static final Integer UPDATED_TIPO = 2;

    private static final Integer DEFAULT_CLAVE_CUCOP = 1;
    private static final Integer UPDATED_CLAVE_CUCOP = 2;

    private static final Integer DEFAULT_PARTIDA_ESP = 1;
    private static final Integer UPDATED_PARTIDA_ESP = 2;

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_NIVEL = 1;
    private static final Integer UPDATED_NIVEL = 2;

    private static final String DEFAULT_C_ABM = "AAAAAAAAAA";
    private static final String UPDATED_C_ABM = "BBBBBBBBBB";

    private static final String DEFAULT_UNIDAD_MED = "AAAAAAAAAA";
    private static final String UPDATED_UNIDAD_MED = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_CONTRATA = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_CONTRATA = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGEN = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_CONTENT_TYPE = "image/png";

    @Autowired
    private CUCOPRepository cUCOPRepository;

    @Autowired
    private CUCOPService cUCOPService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCUCOPMockMvc;

    private CUCOP cUCOP;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CUCOPResource cUCOPResource = new CUCOPResource(cUCOPService);
        this.restCUCOPMockMvc = MockMvcBuilders.standaloneSetup(cUCOPResource)
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
    public static CUCOP createEntity(EntityManager em) {
        CUCOP cUCOP = new CUCOP()
            .tipo(DEFAULT_TIPO)
            .claveCUCOP(DEFAULT_CLAVE_CUCOP)
            .partidaEsp(DEFAULT_PARTIDA_ESP)
            .descripcion(DEFAULT_DESCRIPCION)
            .nivel(DEFAULT_NIVEL)
            .cABM(DEFAULT_C_ABM)
            .unidadMed(DEFAULT_UNIDAD_MED)
            .tipoContrata(DEFAULT_TIPO_CONTRATA)
            .imagen(DEFAULT_IMAGEN)
            .imagenContentType(DEFAULT_IMAGEN_CONTENT_TYPE);
        return cUCOP;
    }

    @Before
    public void initTest() {
        cUCOP = createEntity(em);
    }

    @Test
    @Transactional
    public void createCUCOP() throws Exception {
        int databaseSizeBeforeCreate = cUCOPRepository.findAll().size();

        // Create the CUCOP
        restCUCOPMockMvc.perform(post("/api/cucops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cUCOP)))
            .andExpect(status().isCreated());

        // Validate the CUCOP in the database
        List<CUCOP> cUCOPList = cUCOPRepository.findAll();
        assertThat(cUCOPList).hasSize(databaseSizeBeforeCreate + 1);
        CUCOP testCUCOP = cUCOPList.get(cUCOPList.size() - 1);
        assertThat(testCUCOP.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testCUCOP.getClaveCUCOP()).isEqualTo(DEFAULT_CLAVE_CUCOP);
        assertThat(testCUCOP.getPartidaEsp()).isEqualTo(DEFAULT_PARTIDA_ESP);
        assertThat(testCUCOP.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testCUCOP.getNivel()).isEqualTo(DEFAULT_NIVEL);
        assertThat(testCUCOP.getcABM()).isEqualTo(DEFAULT_C_ABM);
        assertThat(testCUCOP.getUnidadMed()).isEqualTo(DEFAULT_UNIDAD_MED);
        assertThat(testCUCOP.getTipoContrata()).isEqualTo(DEFAULT_TIPO_CONTRATA);
        assertThat(testCUCOP.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testCUCOP.getImagenContentType()).isEqualTo(DEFAULT_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createCUCOPWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cUCOPRepository.findAll().size();

        // Create the CUCOP with an existing ID
        cUCOP.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCUCOPMockMvc.perform(post("/api/cucops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cUCOP)))
            .andExpect(status().isBadRequest());

        // Validate the CUCOP in the database
        List<CUCOP> cUCOPList = cUCOPRepository.findAll();
        assertThat(cUCOPList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cUCOPRepository.findAll().size();
        // set the field null
        cUCOP.setTipo(null);

        // Create the CUCOP, which fails.

        restCUCOPMockMvc.perform(post("/api/cucops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cUCOP)))
            .andExpect(status().isBadRequest());

        List<CUCOP> cUCOPList = cUCOPRepository.findAll();
        assertThat(cUCOPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cUCOPRepository.findAll().size();
        // set the field null
        cUCOP.setDescripcion(null);

        // Create the CUCOP, which fails.

        restCUCOPMockMvc.perform(post("/api/cucops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cUCOP)))
            .andExpect(status().isBadRequest());

        List<CUCOP> cUCOPList = cUCOPRepository.findAll();
        assertThat(cUCOPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNivelIsRequired() throws Exception {
        int databaseSizeBeforeTest = cUCOPRepository.findAll().size();
        // set the field null
        cUCOP.setNivel(null);

        // Create the CUCOP, which fails.

        restCUCOPMockMvc.perform(post("/api/cucops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cUCOP)))
            .andExpect(status().isBadRequest());

        List<CUCOP> cUCOPList = cUCOPRepository.findAll();
        assertThat(cUCOPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoContrataIsRequired() throws Exception {
        int databaseSizeBeforeTest = cUCOPRepository.findAll().size();
        // set the field null
        cUCOP.setTipoContrata(null);

        // Create the CUCOP, which fails.

        restCUCOPMockMvc.perform(post("/api/cucops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cUCOP)))
            .andExpect(status().isBadRequest());

        List<CUCOP> cUCOPList = cUCOPRepository.findAll();
        assertThat(cUCOPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCUCOPS() throws Exception {
        // Initialize the database
        cUCOPRepository.saveAndFlush(cUCOP);

        // Get all the cUCOPList
        restCUCOPMockMvc.perform(get("/api/cucops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cUCOP.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)))
            .andExpect(jsonPath("$.[*].claveCUCOP").value(hasItem(DEFAULT_CLAVE_CUCOP)))
            .andExpect(jsonPath("$.[*].partidaEsp").value(hasItem(DEFAULT_PARTIDA_ESP)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].nivel").value(hasItem(DEFAULT_NIVEL)))
            .andExpect(jsonPath("$.[*].cABM").value(hasItem(DEFAULT_C_ABM.toString())))
            .andExpect(jsonPath("$.[*].unidadMed").value(hasItem(DEFAULT_UNIDAD_MED.toString())))
            .andExpect(jsonPath("$.[*].tipoContrata").value(hasItem(DEFAULT_TIPO_CONTRATA.toString())))
            .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))));
    }
    
    @Test
    @Transactional
    public void getCUCOP() throws Exception {
        // Initialize the database
        cUCOPRepository.saveAndFlush(cUCOP);

        // Get the cUCOP
        restCUCOPMockMvc.perform(get("/api/cucops/{id}", cUCOP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cUCOP.getId().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO))
            .andExpect(jsonPath("$.claveCUCOP").value(DEFAULT_CLAVE_CUCOP))
            .andExpect(jsonPath("$.partidaEsp").value(DEFAULT_PARTIDA_ESP))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.nivel").value(DEFAULT_NIVEL))
            .andExpect(jsonPath("$.cABM").value(DEFAULT_C_ABM.toString()))
            .andExpect(jsonPath("$.unidadMed").value(DEFAULT_UNIDAD_MED.toString()))
            .andExpect(jsonPath("$.tipoContrata").value(DEFAULT_TIPO_CONTRATA.toString()))
            .andExpect(jsonPath("$.imagenContentType").value(DEFAULT_IMAGEN_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen").value(Base64Utils.encodeToString(DEFAULT_IMAGEN)));
    }

    @Test
    @Transactional
    public void getNonExistingCUCOP() throws Exception {
        // Get the cUCOP
        restCUCOPMockMvc.perform(get("/api/cucops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCUCOP() throws Exception {
        // Initialize the database
        cUCOPService.save(cUCOP);

        int databaseSizeBeforeUpdate = cUCOPRepository.findAll().size();

        // Update the cUCOP
        CUCOP updatedCUCOP = cUCOPRepository.findById(cUCOP.getId()).get();
        // Disconnect from session so that the updates on updatedCUCOP are not directly saved in db
        em.detach(updatedCUCOP);
        updatedCUCOP
            .tipo(UPDATED_TIPO)
            .claveCUCOP(UPDATED_CLAVE_CUCOP)
            .partidaEsp(UPDATED_PARTIDA_ESP)
            .descripcion(UPDATED_DESCRIPCION)
            .nivel(UPDATED_NIVEL)
            .cABM(UPDATED_C_ABM)
            .unidadMed(UPDATED_UNIDAD_MED)
            .tipoContrata(UPDATED_TIPO_CONTRATA)
            .imagen(UPDATED_IMAGEN)
            .imagenContentType(UPDATED_IMAGEN_CONTENT_TYPE);

        restCUCOPMockMvc.perform(put("/api/cucops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCUCOP)))
            .andExpect(status().isOk());

        // Validate the CUCOP in the database
        List<CUCOP> cUCOPList = cUCOPRepository.findAll();
        assertThat(cUCOPList).hasSize(databaseSizeBeforeUpdate);
        CUCOP testCUCOP = cUCOPList.get(cUCOPList.size() - 1);
        assertThat(testCUCOP.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testCUCOP.getClaveCUCOP()).isEqualTo(UPDATED_CLAVE_CUCOP);
        assertThat(testCUCOP.getPartidaEsp()).isEqualTo(UPDATED_PARTIDA_ESP);
        assertThat(testCUCOP.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testCUCOP.getNivel()).isEqualTo(UPDATED_NIVEL);
        assertThat(testCUCOP.getcABM()).isEqualTo(UPDATED_C_ABM);
        assertThat(testCUCOP.getUnidadMed()).isEqualTo(UPDATED_UNIDAD_MED);
        assertThat(testCUCOP.getTipoContrata()).isEqualTo(UPDATED_TIPO_CONTRATA);
        assertThat(testCUCOP.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testCUCOP.getImagenContentType()).isEqualTo(UPDATED_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCUCOP() throws Exception {
        int databaseSizeBeforeUpdate = cUCOPRepository.findAll().size();

        // Create the CUCOP

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCUCOPMockMvc.perform(put("/api/cucops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cUCOP)))
            .andExpect(status().isBadRequest());

        // Validate the CUCOP in the database
        List<CUCOP> cUCOPList = cUCOPRepository.findAll();
        assertThat(cUCOPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCUCOP() throws Exception {
        // Initialize the database
        cUCOPService.save(cUCOP);

        int databaseSizeBeforeDelete = cUCOPRepository.findAll().size();

        // Get the cUCOP
        restCUCOPMockMvc.perform(delete("/api/cucops/{id}", cUCOP.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CUCOP> cUCOPList = cUCOPRepository.findAll();
        assertThat(cUCOPList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CUCOP.class);
        CUCOP cUCOP1 = new CUCOP();
        cUCOP1.setId(1L);
        CUCOP cUCOP2 = new CUCOP();
        cUCOP2.setId(cUCOP1.getId());
        assertThat(cUCOP1).isEqualTo(cUCOP2);
        cUCOP2.setId(2L);
        assertThat(cUCOP1).isNotEqualTo(cUCOP2);
        cUCOP1.setId(null);
        assertThat(cUCOP1).isNotEqualTo(cUCOP2);
    }
}
