package mx.gob.shcp.compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import mx.gob.shcp.compras.domain.enumeration.EstatusElementoOrden;

/**
 * A ElementoOrden.
 */
@Entity
@Table(name = "elemento_orden")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ElementoOrden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull
    @Column(name = "precio_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioTotal;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private EstatusElementoOrden estatus;

    @Column(name = "proveedor_d")
    private String proveedorD;

    @Column(name = "contrato_marco_d")
    private String contratoMarcoD;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ContratoMarco contratoMarco;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Proveedor proveedor;

    @ManyToOne
    @JsonIgnoreProperties("elementoOrdens")
    private OrdenCompra ordenCompra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public ElementoOrden cantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public ElementoOrden precioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
        return this;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstatusElementoOrden getEstatus() {
        return estatus;
    }

    public ElementoOrden estatus(EstatusElementoOrden estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(EstatusElementoOrden estatus) {
        this.estatus = estatus;
    }

    public String getProveedorD() {
        return proveedorD;
    }

    public ElementoOrden proveedorD(String proveedorD) {
        this.proveedorD = proveedorD;
        return this;
    }

    public void setProveedorD(String proveedorD) {
        this.proveedorD = proveedorD;
    }

    public String getContratoMarcoD() {
        return contratoMarcoD;
    }

    public ElementoOrden contratoMarcoD(String contratoMarcoD) {
        this.contratoMarcoD = contratoMarcoD;
        return this;
    }

    public void setContratoMarcoD(String contratoMarcoD) {
        this.contratoMarcoD = contratoMarcoD;
    }

    public ContratoMarco getContratoMarco() {
        return contratoMarco;
    }

    public ElementoOrden contratoMarco(ContratoMarco contratoMarco) {
        this.contratoMarco = contratoMarco;
        return this;
    }

    public void setContratoMarco(ContratoMarco contratoMarco) {
        this.contratoMarco = contratoMarco;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public ElementoOrden proveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        return this;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public ElementoOrden ordenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
        return this;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
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
        ElementoOrden elementoOrden = (ElementoOrden) o;
        if (elementoOrden.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), elementoOrden.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ElementoOrden{" +
            "id=" + getId() +
            ", cantidad=" + getCantidad() +
            ", precioTotal=" + getPrecioTotal() +
            ", estatus='" + getEstatus() + "'" +
            ", proveedorD='" + getProveedorD() + "'" +
            ", contratoMarcoD='" + getContratoMarcoD() + "'" +
            "}";
    }
}
