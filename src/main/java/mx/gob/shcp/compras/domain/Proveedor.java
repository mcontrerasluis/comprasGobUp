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
 * A Proveedor.
 */
@Entity
@Table(name = "proveedor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    @NotNull
    @Column(name = "r_fc", nullable = false)
    private String rFC;

    @NotNull
    @Column(name = "domicilio_fiscal", nullable = false)
    private String domicilioFiscal;

    @NotNull
    @Column(name = "persona_autorizada", nullable = false)
    private String personaAutorizada;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @NotNull
    @Column(name = "telefono_contacto", nullable = false)
    private String telefonoContacto;

    @NotNull
    @Column(name = "telefono_contacto_dos", nullable = false)
    private String telefonoContactoDos;

    @OneToOne    @JoinColumn(unique = true)
    private User user;

    @ManyToMany(mappedBy = "proveedors")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<ContratoMarco> contratoMarcos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Proveedor nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public Proveedor razonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
        return this;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getrFC() {
        return rFC;
    }

    public Proveedor rFC(String rFC) {
        this.rFC = rFC;
        return this;
    }

    public void setrFC(String rFC) {
        this.rFC = rFC;
    }

    public String getDomicilioFiscal() {
        return domicilioFiscal;
    }

    public Proveedor domicilioFiscal(String domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
        return this;
    }

    public void setDomicilioFiscal(String domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
    }

    public String getPersonaAutorizada() {
        return personaAutorizada;
    }

    public Proveedor personaAutorizada(String personaAutorizada) {
        this.personaAutorizada = personaAutorizada;
        return this;
    }

    public void setPersonaAutorizada(String personaAutorizada) {
        this.personaAutorizada = personaAutorizada;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Proveedor correoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public Proveedor telefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
        return this;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getTelefonoContactoDos() {
        return telefonoContactoDos;
    }

    public Proveedor telefonoContactoDos(String telefonoContactoDos) {
        this.telefonoContactoDos = telefonoContactoDos;
        return this;
    }

    public void setTelefonoContactoDos(String telefonoContactoDos) {
        this.telefonoContactoDos = telefonoContactoDos;
    }

    public User getUser() {
        return user;
    }

    public Proveedor user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ContratoMarco> getContratoMarcos() {
        return contratoMarcos;
    }

    public Proveedor contratoMarcos(Set<ContratoMarco> contratoMarcos) {
        this.contratoMarcos = contratoMarcos;
        return this;
    }

    public Proveedor addContratoMarco(ContratoMarco contratoMarco) {
        this.contratoMarcos.add(contratoMarco);
        contratoMarco.getProveedors().add(this);
        return this;
    }

    public Proveedor removeContratoMarco(ContratoMarco contratoMarco) {
        this.contratoMarcos.remove(contratoMarco);
        contratoMarco.getProveedors().remove(this);
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
        Proveedor proveedor = (Proveedor) o;
        if (proveedor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proveedor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Proveedor{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", razonSocial='" + getRazonSocial() + "'" +
            ", rFC='" + getrFC() + "'" +
            ", domicilioFiscal='" + getDomicilioFiscal() + "'" +
            ", personaAutorizada='" + getPersonaAutorizada() + "'" +
            ", correoElectronico='" + getCorreoElectronico() + "'" +
            ", telefonoContacto='" + getTelefonoContacto() + "'" +
            ", telefonoContactoDos='" + getTelefonoContactoDos() + "'" +
            "}";
    }
}
