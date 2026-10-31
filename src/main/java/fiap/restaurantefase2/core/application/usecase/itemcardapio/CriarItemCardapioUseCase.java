package fiap.restaurantefase2.core.application.usecase.itemcardapio;

import fiap.restaurantefase2.core.application.gateway.ItemCardapioGateway;
import fiap.restaurantefase2.core.application.gateway.RestauranteGateway;
import fiap.restaurantefase2.core.domain.entity.ItemCardapio;
import fiap.restaurantefase2.core.domain.exception.RestauranteInvalidoException;

import java.math.BigDecimal;

public class CriarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;
    private final RestauranteGateway restauranteGateway;

    public CriarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway, RestauranteGateway restauranteGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public ItemCardapio executar(Long restauranteId, String nome, String descricao, BigDecimal preco, boolean apenasNoLocal, String caminhoFoto) {
        // 1. Garante que o restaurante existe antes de adicionar um item
        restauranteGateway.buscarPorId(restauranteId)
                .orElseThrow(() -> new RestauranteInvalidoException("Restaurante não encontrado. Não é possível adicionar o item."));

        // 2. Cria o item (regras e formatações de preço validadas no Domínio)
        ItemCardapio novoItem = ItemCardapio.criar(restauranteId, nome, descricao, preco, apenasNoLocal, caminhoFoto);

        // 3. Salva e retorna
        return itemCardapioGateway.salvar(novoItem);
    }
}
