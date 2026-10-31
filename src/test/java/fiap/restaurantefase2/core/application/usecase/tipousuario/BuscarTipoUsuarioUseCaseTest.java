package fiap.restaurantefase2.core.application.usecase.tipousuario;

import fiap.restaurantefase2.core.application.gateway.TipoUsuarioGateway;
import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.core.domain.exception.TipoUsuarioInvalidoException;
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
class BuscarTipoUsuarioUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private BuscarTipoUsuarioUseCase buscarTipoUsuarioUseCase;

    private TipoUsuario tipoUsuarioMock;

    @BeforeEach
    void setUp() {
        tipoUsuarioMock = TipoUsuario.reconstituir(1L, "Cliente", CategoriaUsuario.CLIENTE);
    }

    @Test
    void deveRetornarTipoUsuarioQuandoEncontrarPorId() {
        // Arrange
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(tipoUsuarioMock));

        // Act
        TipoUsuario resultado = buscarTipoUsuarioUseCase.porId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Cliente", resultado.getNome());
        verify(tipoUsuarioGateway, times(1)).buscarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontradoPorId() {
        // Arrange
        when(tipoUsuarioGateway.buscarPorId(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TipoUsuarioInvalidoException.class, () -> {
            buscarTipoUsuarioUseCase.porId(99L);
        });

        verify(tipoUsuarioGateway, times(1)).buscarPorId(99L);
    }

    @Test
    void deveRetornarListaDeTiposDeUsuario() {
        // Arrange
        when(tipoUsuarioGateway.listarTodos()).thenReturn(List.of(tipoUsuarioMock));

        // Act
        List<TipoUsuario> resultados = buscarTipoUsuarioUseCase.todos();

        // Assert
        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals("Cliente", resultados.get(0).getNome());
        verify(tipoUsuarioGateway, times(1)).listarTodos();
    }
}