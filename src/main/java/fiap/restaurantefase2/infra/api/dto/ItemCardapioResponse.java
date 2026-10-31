package fiap.restaurantefase2.infra.api.dto;

import fiap.restaurantefase2.core.domain.entity.ItemCardapio;

import java.math.BigDecimal;

public record ItemCardapioResponse(
        Long id,
        Long restauranteId,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean apenasNoLocal,
        String caminhoFoto
) {
    public static ItemCardapioResponse de(ItemCardapio item) {
        return new ItemCardapioResponse(
                item.getId(),
                item.getRestauranteId(),
                item.getNome(),
                item.getDescricao(),
                item.getPreco(),
                item.isApenasNoLocal(),
                item.getCaminhoFoto()
        );
    }
}
