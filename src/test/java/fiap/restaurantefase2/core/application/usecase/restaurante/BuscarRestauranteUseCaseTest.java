package fiap.restaurantefase2.core.application.usecase.restaurante;

import fiap.restaurantefase2.core.application.gateway.RestauranteGateway;
import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.core.domain.exception.RestauranteInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarRestauranteUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private BuscarRestauranteUseCase buscarRestauranteUseCase;

    private Restaurante restauranteMock;

    @BeforeEach
    void setUp() {
        TipoUsuario tipoDono = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        Usuario dono = Usuario.reconstituir(1L, "João", "joao@email.com", tipoDono);
        restauranteMock = Restaurante.reconstituir(5L, "Bistrô", "Rua A", "Francesa", "18h-23h", dono);
    }

    @Test
    void deveRetornarRestauranteQuandoEncontrarPorId() {
        // Arrange
        when(restauranteGateway.buscarPorId(5L)).thenReturn(Optional.of(restauranteMock));

        // Act
        Restaurante resultado = buscarRestauranteUseCase.porId(5L);

        // Assert
        assertNotNull(resultado);
        assertEquals(5L, resultado.getId());
        assertEquals("Bistrô", resultado.getNome());
        verify(restauranteGateway, times(1)).buscarPorId(5L);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontradoPorId() {
        // Arrange
        when(restauranteGateway.buscarPorId(99L)).thenReturn(Optional.empty());

        // Act & Assert
        RestauranteInvalidoException exception = assertThrows(RestauranteInvalidoException.class, () -> {
            buscarRestauranteUseCase.porId(99L);
        });

        assertEquals("Restaurante não encontrado para o ID: 99", exception.getMessage());
        verify(restauranteGateway, times(1)).buscarPorId(99L);
    }

    @Test
    void deveRetornarListaDeRestaurantes() {
        // Arrange
        when(restauranteGateway.listarTodos()).thenReturn(List.of(restauranteMock));

        // Act
        List<Restaurante> resultados = buscarRestauranteUseCase.todos();

        // Assert
        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals("Bistrô", resultados.get(0).getNome());
        verify(restauranteGateway, times(1)).listarTodos();
    }
}