package mx.gob.shcp.compras.repository;

import mx.gob.shcp.compras.domain.LugarEntrega;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LugarEntrega entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LugarEntregaRepository extends JpaRepository<LugarEntrega, Long> {

}
