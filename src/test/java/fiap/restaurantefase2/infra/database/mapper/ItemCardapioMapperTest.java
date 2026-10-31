package fiap.restaurantefase2.infra.database.mapper;

import fiap.restaurantefase2.core.domain.entity.ItemCardapio;
import fiap.restaurantefase2.infra.database.entity.ItemCardapioEntity;
import fiap.restaurantefase2.infra.database.entity.RestauranteEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioMapperTest {

    @Test
    void deveConverterDominioParaEntidade() {
        ItemCardapio dominio = ItemCardapio.reconstituir(100L, 10L, "Prato", "Desc", new BigDecimal("50.0"), false, null);

        ItemCardapioEntity entidade = ItemCardapioMapper.toEntity(dominio);

        assertNotNull(entidade);
        assertEquals(100L, entidade.getId());
        assertEquals("Prato", entidade.getNome());
        assertEquals(new BigDecimal("50.0"), entidade.getPreco());
        assertNotNull(entidade.getRestaurante());
        assertEquals(10L, entidade.getRestaurante().getId());
    }

    @Test
    void deveRetornarNullQuandoDominioForNull() {
        assertNull(ItemCardapioMapper.toEntity(null));
    }

    @Test
    void deveConverterEntidadeParaDominio() {
        RestauranteEntity restauranteRef = new RestauranteEntity();
        restauranteRef.setId(10L);
        ItemCardapioEntity entidade = new ItemCardapioEntity(100L, restauranteRef, "Prato", "Desc", new BigDecimal("50.0"), false, null);

        ItemCardapio dominio = ItemCardapioMapper.toDomain(entidade);

        assertNotNull(dominio);
        assertEquals(100L, dominio.getId());
        assertEquals(10L, dominio.getRestauranteId());
        assertEquals("Prato", dominio.getNome());
        assertEquals(new BigDecimal("50.0"), dominio.getPreco());
    }

    @Test
    void deveRetornarNullQuandoEntidadeForNull() {
        assertNull(ItemCardapioMapper.toDomain(null));
    }
}