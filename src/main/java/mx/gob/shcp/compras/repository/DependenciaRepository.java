package mx.gob.shcp.compras.repository;

import mx.gob.shcp.compras.domain.Dependencia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Dependencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DependenciaRepository extends JpaRepository<Dependencia, Long> {

    @Query("select dependencia from Dependencia dependencia where dependencia.user.login = ?#{principal.username}")
    List<Dependencia> findByUserIsCurrentUser();

}
