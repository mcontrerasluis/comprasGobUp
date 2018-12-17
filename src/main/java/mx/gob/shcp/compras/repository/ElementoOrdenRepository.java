package mx.gob.shcp.compras.repository;

import mx.gob.shcp.compras.domain.ElementoOrden;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ElementoOrden entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElementoOrdenRepository extends JpaRepository<ElementoOrden, Long> {

}
