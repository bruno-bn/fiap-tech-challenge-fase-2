package fiap.restaurantefase2.infra.database.repository;

import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {
}
