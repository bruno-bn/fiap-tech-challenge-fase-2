package fiap.restaurantefase2.infra.database.mapper;

import fiap.restaurantefase2.core.domain.entity.ItemCardapio;
import fiap.restaurantefase2.infra.database.entity.ItemCardapioEntity;
import fiap.restaurantefase2.infra.database.entity.RestauranteEntity;

public class ItemCardapioMapper {

    public static ItemCardapioEntity toEntity(ItemCardapio domain) {
        if (domain == null) return null;

        // Cria uma referência leve do restaurante apenas com o ID para a chave estrangeira
        RestauranteEntity restauranteRef = new RestauranteEntity();
        restauranteRef.setId(domain.getRestauranteId());

        return new ItemCardapioEntity(
                domain.getId(),
                restauranteRef,
                domain.getNome(),
                domain.getDescricao(),
                domain.getPreco(),
                domain.isApenasNoLocal(),
                domain.getCaminhoFoto()
        );
    }

    public static ItemCardapio toDomain(ItemCardapioEntity entity) {
        if (entity == null) return null;

        Long restauranteId = entity.getRestaurante() != null ? entity.getRestaurante().getId() : null;

        return ItemCardapio.reconstituir(
                entity.getId(),
                restauranteId,
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.isApenasNoLocal(),
                entity.getCaminhoFoto()
        );
    }
}