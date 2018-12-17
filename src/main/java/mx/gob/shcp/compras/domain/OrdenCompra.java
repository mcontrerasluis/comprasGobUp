package mx.gob.shcp.compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import mx.gob.shcp.compras.domain.enumeration.EstatusOrden;

/**
 * A OrdenCompra.
 */
@Entity
@Table(name = "orden_compra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "fecha_entrada", nullable = false)
    private Instant fechaEntrada;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private EstatusOrden estatus;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "lugar_entrega_d")
    private String lugarEntregaD;

    @OneToMany(mappedBy = "ordenCompra")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ElementoOrden> elementoOrdens = new HashSet<>();
    @OneToMany(mappedBy = "ordenCompra")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Factura> facturas = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private LugarEntrega lugarEntrega;

    @ManyToOne
    @JsonIgnoreProperties("ordenCompras")
    private Dependencia dependencia;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaEntrada() {
        return fechaEntrada;
    }

    public OrdenCompra fechaEntrada(Instant fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public void setFechaEntrada(Instant fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public EstatusOrden getEstatus() {
        return estatus;
    }

    public OrdenCompra estatus(EstatusOrden estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(EstatusOrden estatus) {
        this.estatus = estatus;
    }

    public String getCodigo() {
        return codigo;
    }

    public OrdenCompra codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLugarEntregaD() {
        return lugarEntregaD;
    }

    public OrdenCompra lugarEntregaD(String lugarEntregaD) {
        this.lugarEntregaD = lugarEntregaD;
        return this;
    }

    public void setLugarEntregaD(String lugarEntregaD) {
        this.lugarEntregaD = lugarEntregaD;
    }

    public Set<ElementoOrden> getElementoOrdens() {
        return elementoOrdens;
    }

    public OrdenCompra elementoOrdens(Set<ElementoOrden> elementoOrdens) {
        this.elementoOrdens = elementoOrdens;
        return this;
    }

    public OrdenCompra addElementoOrden(ElementoOrden elementoOrden) {
        this.elementoOrdens.add(elementoOrden);
        elementoOrden.setOrdenCompra(this);
        return this;
    }

    public OrdenCompra removeElementoOrden(ElementoOrden elementoOrden) {
        this.elementoOrdens.remove(elementoOrden);
        elementoOrden.setOrdenCompra(null);
        return this;
    }

    public void setElementoOrdens(Set<ElementoOrden> elementoOrdens) {
        this.elementoOrdens = elementoOrdens;
    }

    public Set<Factura> getFacturas() {
        return facturas;
    }

    public OrdenCompra facturas(Set<Factura> facturas) {
        this.facturas = facturas;
        return this;
    }

    public OrdenCompra addFactura(Factura factura) {
        this.facturas.add(factura);
        factura.setOrdenCompra(this);
        return this;
    }

    public OrdenCompra removeFactura(Factura factura) {
        this.facturas.remove(factura);
        factura.setOrdenCompra(null);
        return this;
    }

    public void setFacturas(Set<Factura> facturas) {
        this.facturas = facturas;
    }

    public LugarEntrega getLugarEntrega() {
        return lugarEntrega;
    }

    public OrdenCompra lugarEntrega(LugarEntrega lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
        return this;
    }

    public void setLugarEntrega(LugarEntrega lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public OrdenCompra dependencia(Dependencia dependencia) {
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
        OrdenCompra ordenCompra = (OrdenCompra) o;
        if (ordenCompra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ordenCompra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrdenCompra{" +
            "id=" + getId() +
            ", fechaEntrada='" + getFechaEntrada() + "'" +
            ", estatus='" + getEstatus() + "'" +
            ", codigo='" + getCodigo() + "'" +
            ", lugarEntregaD='" + getLugarEntregaD() + "'" +
            "}";
    }
}
