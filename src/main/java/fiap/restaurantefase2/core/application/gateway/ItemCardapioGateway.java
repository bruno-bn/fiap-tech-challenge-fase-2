package fiap.restaurantefase2.core.application.gateway;

import fiap.restaurantefase2.core.domain.entity.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface ItemCardapioGateway {

    ItemCardapio salvar(ItemCardapio item);

    Optional<ItemCardapio> buscarPorId(Long id);

    List<ItemCardapio> buscarPorRestaurante(Long restauranteId);

    void deletar(Long id);
}
