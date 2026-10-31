package fiap.restaurantefase2.core.application.usecase.itemcardapio;

import fiap.restaurantefase2.core.application.gateway.ItemCardapioGateway;
import fiap.restaurantefase2.core.domain.entity.ItemCardapio;
import fiap.restaurantefase2.core.domain.exception.ItemCardapioInvalidoException;
import fiap.restaurantefase2.core.domain.exception.ItemNaoPertenceAoRestauranteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarItemCardapioUseCaseTest {

    @Mock
    private ItemCardapioGateway itemCardapioGateway;

    @InjectMocks
    private BuscarItemCardapioUseCase buscarItemCardapioUseCase;

    private ItemCardapio itemMock;

    @BeforeEach
    void setUp() {
        itemMock = ItemCardapio.reconstituir(100L, 10L, "Prato", "Desc", new BigDecimal("50.0"), false, null);
    }

    @Test
    void deveRetornarItemQuandoEncontrarPorIdERestauranteCorreto() {
        // Arrange
        when(itemCardapioGateway.buscarPorId(100L)).thenReturn(Optional.of(itemMock));

        // Act
        ItemCardapio resultado = buscarItemCardapioUseCase.porIdERestaurante(100L, 10L);

        // Assert
        assertNotNull(resultado);
        assertEquals(100L, resultado.getId());
        assertEquals(10L, resultado.getRestauranteId());
        verify(itemCardapioGateway, times(1)).buscarPorId(100L);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoEncontrado() {
        // Arrange
        when(itemCardapioGateway.buscarPorId(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ItemCardapioInvalidoException.class, () -> {
            buscarItemCardapioUseCase.porIdERestaurante(99L, 10L);
        });

        verify(itemCardapioGateway, times(1)).buscarPorId(99L);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoPertenceAoRestaurante() {
        // Arrange
        // O mock tem restauranteId = 10L
        when(itemCardapioGateway.buscarPorId(100L)).thenReturn(Optional.of(itemMock));

        // Act & Assert
        // Tentamos buscar passando o restauranteId = 99L (incompatível)
        assertThrows(ItemNaoPertenceAoRestauranteException.class, () -> {
            buscarItemCardapioUseCase.porIdERestaurante(100L, 99L);
        });

        verify(itemCardapioGateway, times(1)).buscarPorId(100L);
    }

    @Test
    void deveRetornarListaDeItensPorRestaurante() {
        // Arrange
        when(itemCardapioGateway.buscarPorRestaurante(10L)).thenReturn(List.of(itemMock));

        // Act
        List<ItemCardapio> resultados = buscarItemCardapioUseCase.porRestaurante(10L);

        // Assert
        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals(10L, resultados.get(0).getRestauranteId());
        verify(itemCardapioGateway, times(1)).buscarPorRestaurante(10L);
    }
}