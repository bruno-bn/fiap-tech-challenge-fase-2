package fiap.restaurantefase2.core.domain.entity;

import fiap.restaurantefase2.core.domain.exception.RestauranteInvalidoException;
import fiap.restaurantefase2.core.domain.exception.UsuarioNaoAutorizadoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    @Test
    void deveCriarRestauranteComSucessoQuandoDadosForemValidos() {
        TipoUsuario tipoDono = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        Usuario dono = Usuario.reconstituir(1L, "João", "joao@email.com", tipoDono);

        Restaurante restaurante = Restaurante.criar("Bistrô", "Rua A", "Francesa", "18h-23h", dono);

        assertNotNull(restaurante);
        assertEquals("Bistrô", restaurante.getNome());
        assertEquals(dono, restaurante.getDono());
        assertNull(restaurante.getId());
    }

    @Test
    void deveLancarExcecaoQuandoNomeDoRestauranteForVazio() {
        TipoUsuario tipoDono = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        Usuario dono = Usuario.reconstituir(1L, "João", "joao@email.com", tipoDono);

        assertThrows(RestauranteInvalidoException.class, () -> {
            Restaurante.criar("", "Rua A", "Francesa", "18h-23h", dono);
        });
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForDonoDeRestaurante() {
        TipoUsuario tipoCliente = TipoUsuario.reconstituir(2L, "Cliente", CategoriaUsuario.CLIENTE);
        Usuario cliente = Usuario.reconstituir(2L, "Maria", "maria@email.com", tipoCliente);

        UsuarioNaoAutorizadoException exception = assertThrows(UsuarioNaoAutorizadoException.class, () -> {
            Restaurante.criar("Bistrô", "Rua A", "Francesa", "18h-23h", cliente);
        });

        assertEquals("O usuário atribuído como dono deve ter a categoria DONO_RESTAURANTE.", exception.getMessage());
    }
}
