package mx.gob.shcp.compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import mx.gob.shcp.compras.domain.enumeration.EstatusFactura;

import mx.gob.shcp.compras.domain.enumeration.MetodoPago;

/**
 * A Factura.
 */
@Entity
@Table(name = "factura")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private Instant fecha;

    @Column(name = "detalles")
    private String detalles;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private EstatusFactura estatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @NotNull
    @Column(name = "fecha_pago", nullable = false)
    private Instant fechaPago;

    @NotNull
    @Column(name = "monto_pagado", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoPagado;

    @OneToMany(mappedBy = "factura")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Embarque> embarques = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("facturas")
    private OrdenCompra ordenCompra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFecha() {
        return fecha;
    }

    public Factura fecha(Instant fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public String getDetalles() {
        return detalles;
    }

    public Factura detalles(String detalles) {
        this.detalles = detalles;
        return this;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public EstatusFactura getEstatus() {
        return estatus;
    }

    public Factura estatus(EstatusFactura estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(EstatusFactura estatus) {
        this.estatus = estatus;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public Factura metodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
        return this;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Instant getFechaPago() {
        return fechaPago;
    }

    public Factura fechaPago(Instant fechaPago) {
        this.fechaPago = fechaPago;
        return this;
    }

    public void setFechaPago(Instant fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public Factura montoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
        return this;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Set<Embarque> getEmbarques() {
        return embarques;
    }

    public Factura embarques(Set<Embarque> embarques) {
        this.embarques = embarques;
        return this;
    }

    public Factura addEmbarque(Embarque embarque) {
        this.embarques.add(embarque);
        embarque.setFactura(this);
        return this;
    }

    public Factura removeEmbarque(Embarque embarque) {
        this.embarques.remove(embarque);
        embarque.setFactura(null);
        return this;
    }

    public void setEmbarques(Set<Embarque> embarques) {
        this.embarques = embarques;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public Factura ordenCompra(OrdenCompra ordenCompra) {
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
        Factura factura = (Factura) o;
        if (factura.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), factura.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Factura{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", detalles='" + getDetalles() + "'" +
            ", estatus='" + getEstatus() + "'" +
            ", metodoPago='" + getMetodoPago() + "'" +
            ", fechaPago='" + getFechaPago() + "'" +
            ", montoPagado=" + getMontoPagado() +
            "}";
    }
}
