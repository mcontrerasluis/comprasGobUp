package mx.gob.shcp.compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Dependencia.
 */
@Entity
@Table(name = "dependencia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dependencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ramo", nullable = false)
    private Integer ramo;

    @NotNull
    @Column(name = "nombre_ramo", nullable = false)
    private String nombreRamo;

    @NotNull
    @Column(name = "unidad", nullable = false)
    private String unidad;

    @NotNull
    @Column(name = "nombre_unidad", nullable = false)
    private String nombreUnidad;

    @NotNull
    @Column(name = "contacto", nullable = false)
    private String contacto;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @NotNull
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "dependencia")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrdenCompra> ordenCompras = new HashSet<>();
    @OneToMany(mappedBy = "dependencia")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ContratoMarco> contratoMarcos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @OneToMany(mappedBy = "dependencia")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LugarEntrega> lugarEntregas = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRamo() {
        return ramo;
    }

    public Dependencia ramo(Integer ramo) {
        this.ramo = ramo;
        return this;
    }

    public void setRamo(Integer ramo) {
        this.ramo = ramo;
    }

    public String getNombreRamo() {
        return nombreRamo;
    }

    public Dependencia nombreRamo(String nombreRamo) {
        this.nombreRamo = nombreRamo;
        return this;
    }

    public void setNombreRamo(String nombreRamo) {
        this.nombreRamo = nombreRamo;
    }

    public String getUnidad() {
        return unidad;
    }

    public Dependencia unidad(String unidad) {
        this.unidad = unidad;
        return this;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public Dependencia nombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
        return this;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getContacto() {
        return contacto;
    }

    public Dependencia contacto(String contacto) {
        this.contacto = contacto;
        return this;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Dependencia correoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public Dependencia telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<OrdenCompra> getOrdenCompras() {
        return ordenCompras;
    }

    public Dependencia ordenCompras(Set<OrdenCompra> ordenCompras) {
        this.ordenCompras = ordenCompras;
        return this;
    }

    public Dependencia addOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompras.add(ordenCompra);
        ordenCompra.setDependencia(this);
        return this;
    }

    public Dependencia removeOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompras.remove(ordenCompra);
        ordenCompra.setDependencia(null);
        return this;
    }

    public void setOrdenCompras(Set<OrdenCompra> ordenCompras) {
        this.ordenCompras = ordenCompras;
    }

    public Set<ContratoMarco> getContratoMarcos() {
        return contratoMarcos;
    }

    public Dependencia contratoMarcos(Set<ContratoMarco> contratoMarcos) {
        this.contratoMarcos = contratoMarcos;
        return this;
    }

    public Dependencia addContratoMarco(ContratoMarco contratoMarco) {
        this.contratoMarcos.add(contratoMarco);
        contratoMarco.setDependencia(this);
        return this;
    }

    public Dependencia removeContratoMarco(ContratoMarco contratoMarco) {
        this.contratoMarcos.remove(contratoMarco);
        contratoMarco.setDependencia(null);
        return this;
    }

    public void setContratoMarcos(Set<ContratoMarco> contratoMarcos) {
        this.contratoMarcos = contratoMarcos;
    }

    public User getUser() {
        return user;
    }

    public Dependencia user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<LugarEntrega> getLugarEntregas() {
        return lugarEntregas;
    }

    public Dependencia lugarEntregas(Set<LugarEntrega> lugarEntregas) {
        this.lugarEntregas = lugarEntregas;
        return this;
    }

    public Dependencia addLugarEntrega(LugarEntrega lugarEntrega) {
        this.lugarEntregas.add(lugarEntrega);
        lugarEntrega.setDependencia(this);
        return this;
    }

    public Dependencia removeLugarEntrega(LugarEntrega lugarEntrega) {
        this.lugarEntregas.remove(lugarEntrega);
        lugarEntrega.setDependencia(null);
        return this;
    }

    public void setLugarEntregas(Set<LugarEntrega> lugarEntregas) {
        this.lugarEntregas = lugarEntregas;
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
        Dependencia dependencia = (Dependencia) o;
        if (dependencia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dependencia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Dependencia{" +
            "id=" + getId() +
            ", ramo=" + getRamo() +
            ", nombreRamo='" + getNombreRamo() + "'" +
            ", unidad='" + getUnidad() + "'" +
            ", nombreUnidad='" + getNombreUnidad() + "'" +
            ", contacto='" + getContacto() + "'" +
            ", correoElectronico='" + getCorreoElectronico() + "'" +
            ", telefono='" + getTelefono() + "'" +
            "}";
    }
}
