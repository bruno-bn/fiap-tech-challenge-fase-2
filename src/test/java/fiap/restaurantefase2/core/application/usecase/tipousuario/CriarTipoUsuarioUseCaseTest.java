package fiap.restaurantefase2.core.application.usecase.tipousuario;

import fiap.restaurantefase2.core.application.gateway.TipoUsuarioGateway;
import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarTipoUsuarioUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;

    @Test
    void deveCriarESalvarTipoUsuarioComSucesso() {
        // Arrange
        TipoUsuario tipoSalvo = TipoUsuario.reconstituir(1L, "Dono VIP", CategoriaUsuario.DONO_RESTAURANTE);

        // quando tentarem salvar qualquer TipoUsuario, devolve o tipoSalvo com ID 1
        when(tipoUsuarioGateway.salvar(any(TipoUsuario.class))).thenReturn(tipoSalvo);

        // Act
        TipoUsuario resultado = criarTipoUsuarioUseCase.executar("Dono VIP", CategoriaUsuario.DONO_RESTAURANTE);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Dono VIP", resultado.getNome());
        assertEquals(CategoriaUsuario.DONO_RESTAURANTE, resultado.getCategoria());

        // Garante que o método salvar do banco de dados (mockado) foi chamado
        verify(tipoUsuarioGateway, times(1)).salvar(any(TipoUsuario.class));
    }
}