package fiap.restaurantefase2.core.application.usecase.restaurante;

import fiap.restaurantefase2.core.application.gateway.RestauranteGateway;
import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.core.domain.exception.RestauranteInvalidoException;

import java.util.List;

public class BuscarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public BuscarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public Restaurante porId(Long id) {
        return restauranteGateway.buscarPorId(id)
                .orElseThrow(() -> new RestauranteInvalidoException("Restaurante não encontrado para o ID: " + id));
    }

    public List<Restaurante> todos() {
        return restauranteGateway.listarTodos();
    }
}
