package fiap.restaurantefase2.core.application.usecase.itemcardapio;

import fiap.restaurantefase2.core.application.gateway.ItemCardapioGateway;
import fiap.restaurantefase2.core.application.gateway.RestauranteGateway;
import fiap.restaurantefase2.core.domain.entity.ItemCardapio;
import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.exception.RestauranteInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarItemCardapioUseCaseTest {

    @Mock
    private ItemCardapioGateway itemCardapioGateway;

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private CriarItemCardapioUseCase criarItemCardapioUseCase;

    @Test
    void deveCriarESalvarItemQuandoRestauranteExistir() {
        // Arrange
        TipoUsuario tipo = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        Usuario dono = Usuario.reconstituir(1L, "João", "email@email.com", tipo);
        Restaurante restauranteMock = Restaurante.reconstituir(10L, "Bistrô", "Rua A", "Francesa", "18h-23h", dono);

        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.of(restauranteMock));

        ItemCardapio itemSalvo = ItemCardapio.reconstituir(100L, 10L, "Prato", "Desc", new BigDecimal("50.0"), false, null);
        when(itemCardapioGateway.salvar(any(ItemCardapio.class))).thenReturn(itemSalvo);

        // Act
        ItemCardapio resultado = criarItemCardapioUseCase.executar(10L, "Prato", "Desc", new BigDecimal("50.0"), false, null);

        // Assert
        assertNotNull(resultado);
        assertEquals(100L, resultado.getId());
        verify(itemCardapioGateway, times(1)).salvar(any(ItemCardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
        // Arrange
        when(restauranteGateway.buscarPorId(99L)).thenReturn(Optional.empty());

        // Act & Assert
        RestauranteInvalidoException exception = assertThrows(RestauranteInvalidoException.class, () -> {
            criarItemCardapioUseCase.executar(99L, "Prato", "Desc", new BigDecimal("50.0"), false, null);
        });

        assertEquals("Restaurante não encontrado. Não é possível adicionar o item.", exception.getMessage());
        verify(itemCardapioGateway, never()).salvar(any()); // Garante que nunca tentou salvar
    }
}