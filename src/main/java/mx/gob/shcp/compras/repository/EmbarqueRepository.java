package mx.gob.shcp.compras.repository;

import mx.gob.shcp.compras.domain.Embarque;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Embarque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmbarqueRepository extends JpaRepository<Embarque, Long> {

}
