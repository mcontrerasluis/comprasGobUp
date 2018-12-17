package mx.gob.shcp.compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A CUCOP.
 */
@Entity
@Table(name = "cucop")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CUCOP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tipo", nullable = false)
    private Integer tipo;

    @Column(name = "clave_cucop")
    private Integer claveCUCOP;

    @Column(name = "partida_esp")
    private Integer partidaEsp;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "nivel", nullable = false)
    private Integer nivel;

    @Column(name = "c_abm")
    private String cABM;

    @Column(name = "unidad_med")
    private String unidadMed;

    @NotNull
    @Column(name = "tipo_contrata", nullable = false)
    private String tipoContrata;

    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @Column(name = "imagen_content_type")
    private String imagenContentType;

    @OneToMany(mappedBy = "cUCOP")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ContratoMarco> contratoMarcos = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public CUCOP tipo(Integer tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getClaveCUCOP() {
        return claveCUCOP;
    }

    public CUCOP claveCUCOP(Integer claveCUCOP) {
        this.claveCUCOP = claveCUCOP;
        return this;
    }

    public void setClaveCUCOP(Integer claveCUCOP) {
        this.claveCUCOP = claveCUCOP;
    }

    public Integer getPartidaEsp() {
        return partidaEsp;
    }

    public CUCOP partidaEsp(Integer partidaEsp) {
        this.partidaEsp = partidaEsp;
        return this;
    }

    public void setPartidaEsp(Integer partidaEsp) {
        this.partidaEsp = partidaEsp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public CUCOP descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNivel() {
        return nivel;
    }

    public CUCOP nivel(Integer nivel) {
        this.nivel = nivel;
        return this;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getcABM() {
        return cABM;
    }

    public CUCOP cABM(String cABM) {
        this.cABM = cABM;
        return this;
    }

    public void setcABM(String cABM) {
        this.cABM = cABM;
    }

    public String getUnidadMed() {
        return unidadMed;
    }

    public CUCOP unidadMed(String unidadMed) {
        this.unidadMed = unidadMed;
        return this;
    }

    public void setUnidadMed(String unidadMed) {
        this.unidadMed = unidadMed;
    }

    public String getTipoContrata() {
        return tipoContrata;
    }

    public CUCOP tipoContrata(String tipoContrata) {
        this.tipoContrata = tipoContrata;
        return this;
    }

    public void setTipoContrata(String tipoContrata) {
        this.tipoContrata = tipoContrata;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public CUCOP imagen(byte[] imagen) {
        this.imagen = imagen;
        return this;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public CUCOP imagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
        return this;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    public Set<ContratoMarco> getContratoMarcos() {
        return contratoMarcos;
    }

    public CUCOP contratoMarcos(Set<ContratoMarco> contratoMarcos) {
        this.contratoMarcos = contratoMarcos;
        return this;
    }

    public CUCOP addContratoMarco(ContratoMarco contratoMarco) {
        this.contratoMarcos.add(contratoMarco);
        contratoMarco.setCUCOP(this);
        return this;
    }

    public CUCOP removeContratoMarco(ContratoMarco contratoMarco) {
        this.contratoMarcos.remove(contratoMarco);
        contratoMarco.setCUCOP(null);
        return this;
    }

    public void setContratoMarcos(Set<ContratoMarco> contratoMarcos) {
        this.contratoMarcos = contratoMarcos;
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
        CUCOP cUCOP = (CUCOP) o;
        if (cUCOP.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cUCOP.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CUCOP{" +
            "id=" + getId() +
            ", tipo=" + getTipo() +
            ", claveCUCOP=" + getClaveCUCOP() +
            ", partidaEsp=" + getPartidaEsp() +
            ", descripcion='" + getDescripcion() + "'" +
            ", nivel=" + getNivel() +
            ", cABM='" + getcABM() + "'" +
            ", unidadMed='" + getUnidadMed() + "'" +
            ", tipoContrata='" + getTipoContrata() + "'" +
            ", imagen='" + getImagen() + "'" +
            ", imagenContentType='" + getImagenContentType() + "'" +
            "}";
    }
}
