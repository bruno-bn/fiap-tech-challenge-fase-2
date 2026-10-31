package fiap.restaurantefase2.core.application.usecase.itemcardapio;

import fiap.restaurantefase2.core.application.gateway.ItemCardapioGateway;
import fiap.restaurantefase2.core.domain.entity.ItemCardapio;
import fiap.restaurantefase2.core.domain.exception.ItemCardapioInvalidoException;
import fiap.restaurantefase2.core.domain.exception.ItemNaoPertenceAoRestauranteException;

import java.util.List;

public class BuscarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public BuscarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public List<ItemCardapio> porRestaurante(Long restauranteId) {
        return itemCardapioGateway.buscarPorRestaurante(restauranteId);
    }

    public ItemCardapio porIdERestaurante(Long itemId, Long restauranteId) {
        ItemCardapio item = itemCardapioGateway.buscarPorId(itemId)
                .orElseThrow(() -> new ItemCardapioInvalidoException("Item não encontrado para o ID: " + itemId));

        if (!item.getRestauranteId().equals(restauranteId)) {
            throw new ItemNaoPertenceAoRestauranteException("Item não pertence a este restaurante.");
        }
        return item;
    }
}
