package mx.gob.shcp.compras.repository;

import mx.gob.shcp.compras.domain.CUCOP;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CUCOP entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CUCOPRepository extends JpaRepository<CUCOP, Long> {

}
