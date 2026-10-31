package fiap.restaurantefase2.infra.database.repository;

import fiap.restaurantefase2.infra.database.entity.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {
    List<ItemCardapioEntity> findByRestauranteId(Long restauranteId);
}
