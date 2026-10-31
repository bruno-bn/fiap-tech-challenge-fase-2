package fiap.restaurantefase2.core.application.usecase.restaurante;


import fiap.restaurantefase2.core.application.gateway.RestauranteGateway;
import fiap.restaurantefase2.core.application.gateway.UsuarioGateway;
import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;

import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.core.domain.exception.UsuarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita o uso das anotações do Mockito
class CriarRestauranteUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private CriarRestauranteUseCase criarRestauranteUseCase;

    private Usuario donoValido;

    @BeforeEach
    void setUp() {
        // Prepara um dono válido antes de cada teste
        TipoUsuario tipo = TipoUsuario.reconstituir(1L, "Dono VIP", CategoriaUsuario.DONO_RESTAURANTE);
        donoValido = Usuario.reconstituir(1L, "João", "joao@email.com", tipo);
    }

    @Test
    void deveCriarESalvarRestauranteComSucesso() {
        // Arrange
        // quando tentarem procurar o ID 1, devolve o donoValido
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(donoValido));

        // quando tentarem salvar qualquer restaurante, devolve um restaurante com ID 10
        Restaurante restauranteSalvo = Restaurante.reconstituir(10L, "Pizza Express", "Rua B", "Italiana", "19h-23h", donoValido);
        when(restauranteGateway.salvar(any(Restaurante.class))).thenReturn(restauranteSalvo);

        // Act
        Restaurante resultado = criarRestauranteUseCase.executar("Pizza Express", "Rua B", "Italiana", "19h-23h", 1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());

        // Verifica se o método salvar foi chamado exatamente 1 vez
        verify(restauranteGateway, times(1)).salvar(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioDonoNaoForEncontrado() {
        // Arrange
        // quando tentarem procurar o ID 99, devolve vazio
        when(usuarioGateway.buscarPorId(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            criarRestauranteUseCase.executar("Pizza Express", "Rua B", "Italiana", "19h-23h", 99L);
        });

        // Verifica que se o usuário não existe, o sistema NUNCA deve tentar salvar o restaurante
        verify(restauranteGateway, never()).salvar(any());
    }
}