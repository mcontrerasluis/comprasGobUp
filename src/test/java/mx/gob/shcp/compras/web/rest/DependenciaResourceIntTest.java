package mx.gob.shcp.compras.web.rest;

import mx.gob.shcp.compras.ComprasGobUpApp;

import mx.gob.shcp.compras.domain.Dependencia;
import mx.gob.shcp.compras.repository.DependenciaRepository;
import mx.gob.shcp.compras.service.DependenciaService;
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
 * Test class for the DependenciaResource REST controller.
 *
 * @see DependenciaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComprasGobUpApp.class)
public class DependenciaResourceIntTest {

    private static final Integer DEFAULT_RAMO = 1;
    private static final Integer UPDATED_RAMO = 2;

    private static final String DEFAULT_NOMBRE_RAMO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_RAMO = "BBBBBBBBBB";

    private static final String DEFAULT_UNIDAD = "AAAAAAAAAA";
    private static final String UPDATED_UNIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_UNIDAD = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_UNIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTO = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO_ELECTRONICO = "?B@7M.F";
    private static final String UPDATED_CORREO_ELECTRONICO = "8;@5.-?";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    @Autowired
    private DependenciaRepository dependenciaRepository;

    @Autowired
    private DependenciaService dependenciaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDependenciaMockMvc;

    private Dependencia dependencia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DependenciaResource dependenciaResource = new DependenciaResource(dependenciaService);
        this.restDependenciaMockMvc = MockMvcBuilders.standaloneSetup(dependenciaResource)
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
    public static Dependencia createEntity(EntityManager em) {
        Dependencia dependencia = new Dependencia()
            .ramo(DEFAULT_RAMO)
            .nombreRamo(DEFAULT_NOMBRE_RAMO)
            .unidad(DEFAULT_UNIDAD)
            .nombreUnidad(DEFAULT_NOMBRE_UNIDAD)
            .contacto(DEFAULT_CONTACTO)
            .correoElectronico(DEFAULT_CORREO_ELECTRONICO)
            .telefono(DEFAULT_TELEFONO);
        return dependencia;
    }

    @Before
    public void initTest() {
        dependencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createDependencia() throws Exception {
        int databaseSizeBeforeCreate = dependenciaRepository.findAll().size();

        // Create the Dependencia
        restDependenciaMockMvc.perform(post("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isCreated());

        // Validate the Dependencia in the database
        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Dependencia testDependencia = dependenciaList.get(dependenciaList.size() - 1);
        assertThat(testDependencia.getRamo()).isEqualTo(DEFAULT_RAMO);
        assertThat(testDependencia.getNombreRamo()).isEqualTo(DEFAULT_NOMBRE_RAMO);
        assertThat(testDependencia.getUnidad()).isEqualTo(DEFAULT_UNIDAD);
        assertThat(testDependencia.getNombreUnidad()).isEqualTo(DEFAULT_NOMBRE_UNIDAD);
        assertThat(testDependencia.getContacto()).isEqualTo(DEFAULT_CONTACTO);
        assertThat(testDependencia.getCorreoElectronico()).isEqualTo(DEFAULT_CORREO_ELECTRONICO);
        assertThat(testDependencia.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
    }

    @Test
    @Transactional
    public void createDependenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dependenciaRepository.findAll().size();

        // Create the Dependencia with an existing ID
        dependencia.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDependenciaMockMvc.perform(post("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isBadRequest());

        // Validate the Dependencia in the database
        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRamoIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependenciaRepository.findAll().size();
        // set the field null
        dependencia.setRamo(null);

        // Create the Dependencia, which fails.

        restDependenciaMockMvc.perform(post("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isBadRequest());

        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreRamoIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependenciaRepository.findAll().size();
        // set the field null
        dependencia.setNombreRamo(null);

        // Create the Dependencia, which fails.

        restDependenciaMockMvc.perform(post("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isBadRequest());

        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependenciaRepository.findAll().size();
        // set the field null
        dependencia.setUnidad(null);

        // Create the Dependencia, which fails.

        restDependenciaMockMvc.perform(post("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isBadRequest());

        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreUnidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependenciaRepository.findAll().size();
        // set the field null
        dependencia.setNombreUnidad(null);

        // Create the Dependencia, which fails.

        restDependenciaMockMvc.perform(post("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isBadRequest());

        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactoIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependenciaRepository.findAll().size();
        // set the field null
        dependencia.setContacto(null);

        // Create the Dependencia, which fails.

        restDependenciaMockMvc.perform(post("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isBadRequest());

        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCorreoElectronicoIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependenciaRepository.findAll().size();
        // set the field null
        dependencia.setCorreoElectronico(null);

        // Create the Dependencia, which fails.

        restDependenciaMockMvc.perform(post("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isBadRequest());

        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelefonoIsRequired() throws Exception {
        int databaseSizeBeforeTest = dependenciaRepository.findAll().size();
        // set the field null
        dependencia.setTelefono(null);

        // Create the Dependencia, which fails.

        restDependenciaMockMvc.perform(post("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isBadRequest());

        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDependencias() throws Exception {
        // Initialize the database
        dependenciaRepository.saveAndFlush(dependencia);

        // Get all the dependenciaList
        restDependenciaMockMvc.perform(get("/api/dependencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dependencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].ramo").value(hasItem(DEFAULT_RAMO)))
            .andExpect(jsonPath("$.[*].nombreRamo").value(hasItem(DEFAULT_NOMBRE_RAMO.toString())))
            .andExpect(jsonPath("$.[*].unidad").value(hasItem(DEFAULT_UNIDAD.toString())))
            .andExpect(jsonPath("$.[*].nombreUnidad").value(hasItem(DEFAULT_NOMBRE_UNIDAD.toString())))
            .andExpect(jsonPath("$.[*].contacto").value(hasItem(DEFAULT_CONTACTO.toString())))
            .andExpect(jsonPath("$.[*].correoElectronico").value(hasItem(DEFAULT_CORREO_ELECTRONICO.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO.toString())));
    }
    
    @Test
    @Transactional
    public void getDependencia() throws Exception {
        // Initialize the database
        dependenciaRepository.saveAndFlush(dependencia);

        // Get the dependencia
        restDependenciaMockMvc.perform(get("/api/dependencias/{id}", dependencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dependencia.getId().intValue()))
            .andExpect(jsonPath("$.ramo").value(DEFAULT_RAMO))
            .andExpect(jsonPath("$.nombreRamo").value(DEFAULT_NOMBRE_RAMO.toString()))
            .andExpect(jsonPath("$.unidad").value(DEFAULT_UNIDAD.toString()))
            .andExpect(jsonPath("$.nombreUnidad").value(DEFAULT_NOMBRE_UNIDAD.toString()))
            .andExpect(jsonPath("$.contacto").value(DEFAULT_CONTACTO.toString()))
            .andExpect(jsonPath("$.correoElectronico").value(DEFAULT_CORREO_ELECTRONICO.toString()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDependencia() throws Exception {
        // Get the dependencia
        restDependenciaMockMvc.perform(get("/api/dependencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDependencia() throws Exception {
        // Initialize the database
        dependenciaService.save(dependencia);

        int databaseSizeBeforeUpdate = dependenciaRepository.findAll().size();

        // Update the dependencia
        Dependencia updatedDependencia = dependenciaRepository.findById(dependencia.getId()).get();
        // Disconnect from session so that the updates on updatedDependencia are not directly saved in db
        em.detach(updatedDependencia);
        updatedDependencia
            .ramo(UPDATED_RAMO)
            .nombreRamo(UPDATED_NOMBRE_RAMO)
            .unidad(UPDATED_UNIDAD)
            .nombreUnidad(UPDATED_NOMBRE_UNIDAD)
            .contacto(UPDATED_CONTACTO)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO)
            .telefono(UPDATED_TELEFONO);

        restDependenciaMockMvc.perform(put("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDependencia)))
            .andExpect(status().isOk());

        // Validate the Dependencia in the database
        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeUpdate);
        Dependencia testDependencia = dependenciaList.get(dependenciaList.size() - 1);
        assertThat(testDependencia.getRamo()).isEqualTo(UPDATED_RAMO);
        assertThat(testDependencia.getNombreRamo()).isEqualTo(UPDATED_NOMBRE_RAMO);
        assertThat(testDependencia.getUnidad()).isEqualTo(UPDATED_UNIDAD);
        assertThat(testDependencia.getNombreUnidad()).isEqualTo(UPDATED_NOMBRE_UNIDAD);
        assertThat(testDependencia.getContacto()).isEqualTo(UPDATED_CONTACTO);
        assertThat(testDependencia.getCorreoElectronico()).isEqualTo(UPDATED_CORREO_ELECTRONICO);
        assertThat(testDependencia.getTelefono()).isEqualTo(UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void updateNonExistingDependencia() throws Exception {
        int databaseSizeBeforeUpdate = dependenciaRepository.findAll().size();

        // Create the Dependencia

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDependenciaMockMvc.perform(put("/api/dependencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dependencia)))
            .andExpect(status().isBadRequest());

        // Validate the Dependencia in the database
        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDependencia() throws Exception {
        // Initialize the database
        dependenciaService.save(dependencia);

        int databaseSizeBeforeDelete = dependenciaRepository.findAll().size();

        // Get the dependencia
        restDependenciaMockMvc.perform(delete("/api/dependencias/{id}", dependencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Dependencia> dependenciaList = dependenciaRepository.findAll();
        assertThat(dependenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dependencia.class);
        Dependencia dependencia1 = new Dependencia();
        dependencia1.setId(1L);
        Dependencia dependencia2 = new Dependencia();
        dependencia2.setId(dependencia1.getId());
        assertThat(dependencia1).isEqualTo(dependencia2);
        dependencia2.setId(2L);
        assertThat(dependencia1).isNotEqualTo(dependencia2);
        dependencia1.setId(null);
        assertThat(dependencia1).isNotEqualTo(dependencia2);
    }
}
