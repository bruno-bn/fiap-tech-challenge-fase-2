package fiap.restaurantefase2.core.application.gateway;

import fiap.restaurantefase2.core.domain.entity.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Restaurante salvar(Restaurante restaurante);

    Optional<Restaurante> buscarPorId(Long id);

    List<Restaurante> listarTodos();

    void deletar(Long id);
}
