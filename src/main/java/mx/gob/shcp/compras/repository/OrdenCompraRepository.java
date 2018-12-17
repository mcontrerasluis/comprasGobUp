package mx.gob.shcp.compras.repository;

import mx.gob.shcp.compras.domain.OrdenCompra;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrdenCompra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

}
