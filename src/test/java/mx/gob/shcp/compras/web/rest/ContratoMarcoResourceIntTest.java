package mx.gob.shcp.compras.web.rest;

import mx.gob.shcp.compras.ComprasGobUpApp;

import mx.gob.shcp.compras.domain.ContratoMarco;
import mx.gob.shcp.compras.repository.ContratoMarcoRepository;
import mx.gob.shcp.compras.service.ContratoMarcoService;
import mx.gob.shcp.compras.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import static mx.gob.shcp.compras.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ContratoMarcoResource REST controller.
 *
 * @see ContratoMarcoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComprasGobUpApp.class)
public class ContratoMarcoResourceIntTest {

    private static final String DEFAULT_AUTOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTOR = "BBBBBBBBBB";

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_VIGENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_VIGENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MONTO = 1D;
    private static final Double UPDATED_MONTO = 2D;

    private static final Integer DEFAULT_CANTIDAD = 1;
    private static final Integer UPDATED_CANTIDAD = 2;

    private static final byte[] DEFAULT_IMAGEN = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CONTRATO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CONTRATO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CONTRATO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CONTRATO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_ANEXO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ANEXO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ANEXO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ANEXO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CONVENIO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CONVENIO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CONVENIO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CONVENIO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_REQUISITOS = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_REQUISITOS = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_REQUISITOS_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_REQUISITOS_CONTENT_TYPE = "image/png";

    @Autowired
    private ContratoMarcoRepository contratoMarcoRepository;

    @Mock
    private ContratoMarcoRepository contratoMarcoRepositoryMock;

    @Mock
    private ContratoMarcoService contratoMarcoServiceMock;

    @Autowired
    private ContratoMarcoService contratoMarcoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContratoMarcoMockMvc;

    private ContratoMarco contratoMarco;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContratoMarcoResource contratoMarcoResource = new ContratoMarcoResource(contratoMarcoService);
        this.restContratoMarcoMockMvc = MockMvcBuilders.standaloneSetup(contratoMarcoResource)
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
    public static ContratoMarco createEntity(EntityManager em) {
        ContratoMarco contratoMarco = new ContratoMarco()
            .autor(DEFAULT_AUTOR)
            .titulo(DEFAULT_TITULO)
            .descripcion(DEFAULT_DESCRIPCION)
            .fechaVigencia(DEFAULT_FECHA_VIGENCIA)
            .monto(DEFAULT_MONTO)
            .cantidad(DEFAULT_CANTIDAD)
            .imagen(DEFAULT_IMAGEN)
            .imagenContentType(DEFAULT_IMAGEN_CONTENT_TYPE)
            .contrato(DEFAULT_CONTRATO)
            .contratoContentType(DEFAULT_CONTRATO_CONTENT_TYPE)
            .anexo(DEFAULT_ANEXO)
            .anexoContentType(DEFAULT_ANEXO_CONTENT_TYPE)
            .convenio(DEFAULT_CONVENIO)
            .convenioContentType(DEFAULT_CONVENIO_CONTENT_TYPE)
            .requisitos(DEFAULT_REQUISITOS)
            .requisitosContentType(DEFAULT_REQUISITOS_CONTENT_TYPE);
        return contratoMarco;
    }

    @Before
    public void initTest() {
        contratoMarco = createEntity(em);
    }

    @Test
    @Transactional
    public void createContratoMarco() throws Exception {
        int databaseSizeBeforeCreate = contratoMarcoRepository.findAll().size();

        // Create the ContratoMarco
        restContratoMarcoMockMvc.perform(post("/api/contrato-marcos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoMarco)))
            .andExpect(status().isCreated());

        // Validate the ContratoMarco in the database
        List<ContratoMarco> contratoMarcoList = contratoMarcoRepository.findAll();
        assertThat(contratoMarcoList).hasSize(databaseSizeBeforeCreate + 1);
        ContratoMarco testContratoMarco = contratoMarcoList.get(contratoMarcoList.size() - 1);
        assertThat(testContratoMarco.getAutor()).isEqualTo(DEFAULT_AUTOR);
        assertThat(testContratoMarco.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testContratoMarco.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testContratoMarco.getFechaVigencia()).isEqualTo(DEFAULT_FECHA_VIGENCIA);
        assertThat(testContratoMarco.getMonto()).isEqualTo(DEFAULT_MONTO);
        assertThat(testContratoMarco.getCantidad()).isEqualTo(DEFAULT_CANTIDAD);
        assertThat(testContratoMarco.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testContratoMarco.getImagenContentType()).isEqualTo(DEFAULT_IMAGEN_CONTENT_TYPE);
        assertThat(testContratoMarco.getContrato()).isEqualTo(DEFAULT_CONTRATO);
        assertThat(testContratoMarco.getContratoContentType()).isEqualTo(DEFAULT_CONTRATO_CONTENT_TYPE);
        assertThat(testContratoMarco.getAnexo()).isEqualTo(DEFAULT_ANEXO);
        assertThat(testContratoMarco.getAnexoContentType()).isEqualTo(DEFAULT_ANEXO_CONTENT_TYPE);
        assertThat(testContratoMarco.getConvenio()).isEqualTo(DEFAULT_CONVENIO);
        assertThat(testContratoMarco.getConvenioContentType()).isEqualTo(DEFAULT_CONVENIO_CONTENT_TYPE);
        assertThat(testContratoMarco.getRequisitos()).isEqualTo(DEFAULT_REQUISITOS);
        assertThat(testContratoMarco.getRequisitosContentType()).isEqualTo(DEFAULT_REQUISITOS_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createContratoMarcoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contratoMarcoRepository.findAll().size();

        // Create the ContratoMarco with an existing ID
        contratoMarco.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContratoMarcoMockMvc.perform(post("/api/contrato-marcos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoMarco)))
            .andExpect(status().isBadRequest());

        // Validate the ContratoMarco in the database
        List<ContratoMarco> contratoMarcoList = contratoMarcoRepository.findAll();
        assertThat(contratoMarcoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAutorIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratoMarcoRepository.findAll().size();
        // set the field null
        contratoMarco.setAutor(null);

        // Create the ContratoMarco, which fails.

        restContratoMarcoMockMvc.perform(post("/api/contrato-marcos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoMarco)))
            .andExpect(status().isBadRequest());

        List<ContratoMarco> contratoMarcoList = contratoMarcoRepository.findAll();
        assertThat(contratoMarcoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTituloIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratoMarcoRepository.findAll().size();
        // set the field null
        contratoMarco.setTitulo(null);

        // Create the ContratoMarco, which fails.

        restContratoMarcoMockMvc.perform(post("/api/contrato-marcos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoMarco)))
            .andExpect(status().isBadRequest());

        List<ContratoMarco> contratoMarcoList = contratoMarcoRepository.findAll();
        assertThat(contratoMarcoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaVigenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratoMarcoRepository.findAll().size();
        // set the field null
        contratoMarco.setFechaVigencia(null);

        // Create the ContratoMarco, which fails.

        restContratoMarcoMockMvc.perform(post("/api/contrato-marcos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoMarco)))
            .andExpect(status().isBadRequest());

        List<ContratoMarco> contratoMarcoList = contratoMarcoRepository.findAll();
        assertThat(contratoMarcoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContratoMarcos() throws Exception {
        // Initialize the database
        contratoMarcoRepository.saveAndFlush(contratoMarco);

        // Get all the contratoMarcoList
        restContratoMarcoMockMvc.perform(get("/api/contrato-marcos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contratoMarco.getId().intValue())))
            .andExpect(jsonPath("$.[*].autor").value(hasItem(DEFAULT_AUTOR.toString())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].fechaVigencia").value(hasItem(DEFAULT_FECHA_VIGENCIA.toString())))
            .andExpect(jsonPath("$.[*].monto").value(hasItem(DEFAULT_MONTO.doubleValue())))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)))
            .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))))
            .andExpect(jsonPath("$.[*].contratoContentType").value(hasItem(DEFAULT_CONTRATO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contrato").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONTRATO))))
            .andExpect(jsonPath("$.[*].anexoContentType").value(hasItem(DEFAULT_ANEXO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].anexo").value(hasItem(Base64Utils.encodeToString(DEFAULT_ANEXO))))
            .andExpect(jsonPath("$.[*].convenioContentType").value(hasItem(DEFAULT_CONVENIO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].convenio").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONVENIO))))
            .andExpect(jsonPath("$.[*].requisitosContentType").value(hasItem(DEFAULT_REQUISITOS_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].requisitos").value(hasItem(Base64Utils.encodeToString(DEFAULT_REQUISITOS))));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllContratoMarcosWithEagerRelationshipsIsEnabled() throws Exception {
        ContratoMarcoResource contratoMarcoResource = new ContratoMarcoResource(contratoMarcoServiceMock);
        when(contratoMarcoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restContratoMarcoMockMvc = MockMvcBuilders.standaloneSetup(contratoMarcoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restContratoMarcoMockMvc.perform(get("/api/contrato-marcos?eagerload=true"))
        .andExpect(status().isOk());

        verify(contratoMarcoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllContratoMarcosWithEagerRelationshipsIsNotEnabled() throws Exception {
        ContratoMarcoResource contratoMarcoResource = new ContratoMarcoResource(contratoMarcoServiceMock);
            when(contratoMarcoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restContratoMarcoMockMvc = MockMvcBuilders.standaloneSetup(contratoMarcoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restContratoMarcoMockMvc.perform(get("/api/contrato-marcos?eagerload=true"))
        .andExpect(status().isOk());

            verify(contratoMarcoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getContratoMarco() throws Exception {
        // Initialize the database
        contratoMarcoRepository.saveAndFlush(contratoMarco);

        // Get the contratoMarco
        restContratoMarcoMockMvc.perform(get("/api/contrato-marcos/{id}", contratoMarco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contratoMarco.getId().intValue()))
            .andExpect(jsonPath("$.autor").value(DEFAULT_AUTOR.toString()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.fechaVigencia").value(DEFAULT_FECHA_VIGENCIA.toString()))
            .andExpect(jsonPath("$.monto").value(DEFAULT_MONTO.doubleValue()))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD))
            .andExpect(jsonPath("$.imagenContentType").value(DEFAULT_IMAGEN_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen").value(Base64Utils.encodeToString(DEFAULT_IMAGEN)))
            .andExpect(jsonPath("$.contratoContentType").value(DEFAULT_CONTRATO_CONTENT_TYPE))
            .andExpect(jsonPath("$.contrato").value(Base64Utils.encodeToString(DEFAULT_CONTRATO)))
            .andExpect(jsonPath("$.anexoContentType").value(DEFAULT_ANEXO_CONTENT_TYPE))
            .andExpect(jsonPath("$.anexo").value(Base64Utils.encodeToString(DEFAULT_ANEXO)))
            .andExpect(jsonPath("$.convenioContentType").value(DEFAULT_CONVENIO_CONTENT_TYPE))
            .andExpect(jsonPath("$.convenio").value(Base64Utils.encodeToString(DEFAULT_CONVENIO)))
            .andExpect(jsonPath("$.requisitosContentType").value(DEFAULT_REQUISITOS_CONTENT_TYPE))
            .andExpect(jsonPath("$.requisitos").value(Base64Utils.encodeToString(DEFAULT_REQUISITOS)));
    }

    @Test
    @Transactional
    public void getNonExistingContratoMarco() throws Exception {
        // Get the contratoMarco
        restContratoMarcoMockMvc.perform(get("/api/contrato-marcos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContratoMarco() throws Exception {
        // Initialize the database
        contratoMarcoService.save(contratoMarco);

        int databaseSizeBeforeUpdate = contratoMarcoRepository.findAll().size();

        // Update the contratoMarco
        ContratoMarco updatedContratoMarco = contratoMarcoRepository.findById(contratoMarco.getId()).get();
        // Disconnect from session so that the updates on updatedContratoMarco are not directly saved in db
        em.detach(updatedContratoMarco);
        updatedContratoMarco
            .autor(UPDATED_AUTOR)
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .fechaVigencia(UPDATED_FECHA_VIGENCIA)
            .monto(UPDATED_MONTO)
            .cantidad(UPDATED_CANTIDAD)
            .imagen(UPDATED_IMAGEN)
            .imagenContentType(UPDATED_IMAGEN_CONTENT_TYPE)
            .contrato(UPDATED_CONTRATO)
            .contratoContentType(UPDATED_CONTRATO_CONTENT_TYPE)
            .anexo(UPDATED_ANEXO)
            .anexoContentType(UPDATED_ANEXO_CONTENT_TYPE)
            .convenio(UPDATED_CONVENIO)
            .convenioContentType(UPDATED_CONVENIO_CONTENT_TYPE)
            .requisitos(UPDATED_REQUISITOS)
            .requisitosContentType(UPDATED_REQUISITOS_CONTENT_TYPE);

        restContratoMarcoMockMvc.perform(put("/api/contrato-marcos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedContratoMarco)))
            .andExpect(status().isOk());

        // Validate the ContratoMarco in the database
        List<ContratoMarco> contratoMarcoList = contratoMarcoRepository.findAll();
        assertThat(contratoMarcoList).hasSize(databaseSizeBeforeUpdate);
        ContratoMarco testContratoMarco = contratoMarcoList.get(contratoMarcoList.size() - 1);
        assertThat(testContratoMarco.getAutor()).isEqualTo(UPDATED_AUTOR);
        assertThat(testContratoMarco.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testContratoMarco.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testContratoMarco.getFechaVigencia()).isEqualTo(UPDATED_FECHA_VIGENCIA);
        assertThat(testContratoMarco.getMonto()).isEqualTo(UPDATED_MONTO);
        assertThat(testContratoMarco.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
        assertThat(testContratoMarco.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testContratoMarco.getImagenContentType()).isEqualTo(UPDATED_IMAGEN_CONTENT_TYPE);
        assertThat(testContratoMarco.getContrato()).isEqualTo(UPDATED_CONTRATO);
        assertThat(testContratoMarco.getContratoContentType()).isEqualTo(UPDATED_CONTRATO_CONTENT_TYPE);
        assertThat(testContratoMarco.getAnexo()).isEqualTo(UPDATED_ANEXO);
        assertThat(testContratoMarco.getAnexoContentType()).isEqualTo(UPDATED_ANEXO_CONTENT_TYPE);
        assertThat(testContratoMarco.getConvenio()).isEqualTo(UPDATED_CONVENIO);
        assertThat(testContratoMarco.getConvenioContentType()).isEqualTo(UPDATED_CONVENIO_CONTENT_TYPE);
        assertThat(testContratoMarco.getRequisitos()).isEqualTo(UPDATED_REQUISITOS);
        assertThat(testContratoMarco.getRequisitosContentType()).isEqualTo(UPDATED_REQUISITOS_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingContratoMarco() throws Exception {
        int databaseSizeBeforeUpdate = contratoMarcoRepository.findAll().size();

        // Create the ContratoMarco

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContratoMarcoMockMvc.perform(put("/api/contrato-marcos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoMarco)))
            .andExpect(status().isBadRequest());

        // Validate the ContratoMarco in the database
        List<ContratoMarco> contratoMarcoList = contratoMarcoRepository.findAll();
        assertThat(contratoMarcoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContratoMarco() throws Exception {
        // Initialize the database
        contratoMarcoService.save(contratoMarco);

        int databaseSizeBeforeDelete = contratoMarcoRepository.findAll().size();

        // Get the contratoMarco
        restContratoMarcoMockMvc.perform(delete("/api/contrato-marcos/{id}", contratoMarco.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ContratoMarco> contratoMarcoList = contratoMarcoRepository.findAll();
        assertThat(contratoMarcoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContratoMarco.class);
        ContratoMarco contratoMarco1 = new ContratoMarco();
        contratoMarco1.setId(1L);
        ContratoMarco contratoMarco2 = new ContratoMarco();
        contratoMarco2.setId(contratoMarco1.getId());
        assertThat(contratoMarco1).isEqualTo(contratoMarco2);
        contratoMarco2.setId(2L);
        assertThat(contratoMarco1).isNotEqualTo(contratoMarco2);
        contratoMarco1.setId(null);
        assertThat(contratoMarco1).isNotEqualTo(contratoMarco2);
    }
}
