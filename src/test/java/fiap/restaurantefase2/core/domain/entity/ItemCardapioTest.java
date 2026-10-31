package fiap.restaurantefase2.core.domain.entity;

import fiap.restaurantefase2.core.domain.exception.ItemCardapioInvalidoException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioTest {

    @Test
    void deveCriarItemCardapioComSucesso() {
        // Act
        ItemCardapio item = ItemCardapio.criar(
                10L,
                "Lasanha Bolonhesa",
                "Massa fresca com molho",
                new BigDecimal("45.50"),
                false,
                "/img/lasanha.jpg"
        );

        // Assert
        assertNotNull(item);
        assertEquals(10L, item.getRestauranteId());
        assertEquals("Lasanha Bolonhesa", item.getNome());
        assertEquals(new BigDecimal("45.50"), item.getPreco());
        assertFalse(item.isApenasNoLocal());
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteIdForNulo() {
        ItemCardapioInvalidoException exception = assertThrows(ItemCardapioInvalidoException.class, () -> {
            ItemCardapio.criar(null, "Prato", "Desc", new BigDecimal("10.0"), true, null);
        });
        assertEquals("O ID do restaurante é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        ItemCardapioInvalidoException exception = assertThrows(ItemCardapioInvalidoException.class, () -> {
            ItemCardapio.criar(1L, "  ", "Desc", new BigDecimal("10.0"), true, null);
        });
        assertEquals("O nome do item é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForZeroOuNegativo() {
        ItemCardapioInvalidoException exception1 = assertThrows(ItemCardapioInvalidoException.class, () -> {
            ItemCardapio.criar(1L, "Prato", "Desc", BigDecimal.ZERO, true, null);
        });
        assertEquals("O preço deve ser maior que zero.", exception1.getMessage());

        ItemCardapioInvalidoException exception2 = assertThrows(ItemCardapioInvalidoException.class, () -> {
            ItemCardapio.criar(1L, "Prato", "Desc", new BigDecimal("-5.00"), true, null);
        });
        assertEquals("O preço deve ser maior que zero.", exception2.getMessage());
    }
}