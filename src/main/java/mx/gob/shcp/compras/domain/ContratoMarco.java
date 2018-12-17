package mx.gob.shcp.compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ContratoMarco.
 */
@Entity
@Table(name = "contrato_marco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContratoMarco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "autor", nullable = false)
    private String autor;

    @NotNull
    @Column(name = "titulo", nullable = false)
    private String titulo;

    
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "fecha_vigencia", nullable = false)
    private LocalDate fechaVigencia;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @Column(name = "imagen_content_type")
    private String imagenContentType;

    @Lob
    @Column(name = "contrato")
    private byte[] contrato;

    @Column(name = "contrato_content_type")
    private String contratoContentType;

    @Lob
    @Column(name = "anexo")
    private byte[] anexo;

    @Column(name = "anexo_content_type")
    private String anexoContentType;

    @Lob
    @Column(name = "convenio")
    private byte[] convenio;

    @Column(name = "convenio_content_type")
    private String convenioContentType;

    @Lob
    @Column(name = "requisitos")
    private byte[] requisitos;

    @Column(name = "requisitos_content_type")
    private String requisitosContentType;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "contrato_marco_proveedor",
               joinColumns = @JoinColumn(name = "contrato_marcos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "proveedors_id", referencedColumnName = "id"))
    private Set<Proveedor> proveedors = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("contratoMarcos")
    private CUCOP cUCOP;

    @ManyToOne
    @JsonIgnoreProperties("contratoMarcos")
    private Dependencia dependencia;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public ContratoMarco autor(String autor) {
        this.autor = autor;
        return this;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public ContratoMarco titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ContratoMarco descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaVigencia() {
        return fechaVigencia;
    }

    public ContratoMarco fechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
        return this;
    }

    public void setFechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public Double getMonto() {
        return monto;
    }

    public ContratoMarco monto(Double monto) {
        this.monto = monto;
        return this;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public ContratoMarco cantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public ContratoMarco imagen(byte[] imagen) {
        this.imagen = imagen;
        return this;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public ContratoMarco imagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
        return this;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    public byte[] getContrato() {
        return contrato;
    }

    public ContratoMarco contrato(byte[] contrato) {
        this.contrato = contrato;
        return this;
    }

    public void setContrato(byte[] contrato) {
        this.contrato = contrato;
    }

    public String getContratoContentType() {
        return contratoContentType;
    }

    public ContratoMarco contratoContentType(String contratoContentType) {
        this.contratoContentType = contratoContentType;
        return this;
    }

    public void setContratoContentType(String contratoContentType) {
        this.contratoContentType = contratoContentType;
    }

    public byte[] getAnexo() {
        return anexo;
    }

    public ContratoMarco anexo(byte[] anexo) {
        this.anexo = anexo;
        return this;
    }

    public void setAnexo(byte[] anexo) {
        this.anexo = anexo;
    }

    public String getAnexoContentType() {
        return anexoContentType;
    }

    public ContratoMarco anexoContentType(String anexoContentType) {
        this.anexoContentType = anexoContentType;
        return this;
    }

    public void setAnexoContentType(String anexoContentType) {
        this.anexoContentType = anexoContentType;
    }

    public byte[] getConvenio() {
        return convenio;
    }

    public ContratoMarco convenio(byte[] convenio) {
        this.convenio = convenio;
        return this;
    }

    public void setConvenio(byte[] convenio) {
        this.convenio = convenio;
    }

    public String getConvenioContentType() {
        return convenioContentType;
    }

    public ContratoMarco convenioContentType(String convenioContentType) {
        this.convenioContentType = convenioContentType;
        return this;
    }

    public void setConvenioContentType(String convenioContentType) {
        this.convenioContentType = convenioContentType;
    }

    public byte[] getRequisitos() {
        return requisitos;
    }

    public ContratoMarco requisitos(byte[] requisitos) {
        this.requisitos = requisitos;
        return this;
    }

    public void setRequisitos(byte[] requisitos) {
        this.requisitos = requisitos;
    }

    public String getRequisitosContentType() {
        return requisitosContentType;
    }

    public ContratoMarco requisitosContentType(String requisitosContentType) {
        this.requisitosContentType = requisitosContentType;
        return this;
    }

    public void setRequisitosContentType(String requisitosContentType) {
        this.requisitosContentType = requisitosContentType;
    }

    public Set<Proveedor> getProveedors() {
        return proveedors;
    }

    public ContratoMarco proveedors(Set<Proveedor> proveedors) {
        this.proveedors = proveedors;
        return this;
    }

    public ContratoMarco addProveedor(Proveedor proveedor) {
        this.proveedors.add(proveedor);
        proveedor.getContratoMarcos().add(this);
        return this;
    }

    public ContratoMarco removeProveedor(Proveedor proveedor) {
        this.proveedors.remove(proveedor);
        proveedor.getContratoMarcos().remove(this);
        return this;
    }

    public void setProveedors(Set<Proveedor> proveedors) {
        this.proveedors = proveedors;
    }

    public CUCOP getCUCOP() {
        return cUCOP;
    }

    public ContratoMarco cUCOP(CUCOP cUCOP) {
        this.cUCOP = cUCOP;
        return this;
    }

    public void setCUCOP(CUCOP cUCOP) {
        this.cUCOP = cUCOP;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public ContratoMarco dependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
        return this;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContratoMarco contratoMarco = (ContratoMarco) o;
        if (contratoMarco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contratoMarco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContratoMarco{" +
            "id=" + getId() +
            ", autor='" + getAutor() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", fechaVigencia='" + getFechaVigencia() + "'" +
            ", monto=" + getMonto() +
            ", cantidad=" + getCantidad() +
            ", imagen='" + getImagen() + "'" +
            ", imagenContentType='" + getImagenContentType() + "'" +
            ", contrato='" + getContrato() + "'" +
            ", contratoContentType='" + getContratoContentType() + "'" +
            ", anexo='" + getAnexo() + "'" +
            ", anexoContentType='" + getAnexoContentType() + "'" +
            ", convenio='" + getConvenio() + "'" +
            ", convenioContentType='" + getConvenioContentType() + "'" +
            ", requisitos='" + getRequisitos() + "'" +
            ", requisitosContentType='" + getRequisitosContentType() + "'" +
            "}";
    }
}
