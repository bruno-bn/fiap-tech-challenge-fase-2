package fiap.restaurantefase2.core.domain.entity;

import fiap.restaurantefase2.core.domain.exception.TipoUsuarioInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioTest {

    @Test
    void deveCriarTipoUsuarioComSucesso() {
        // Act
        TipoUsuario tipo = TipoUsuario.criar("Dono VIP", CategoriaUsuario.DONO_RESTAURANTE);

        // Assert
        assertNotNull(tipo);
        assertEquals("Dono VIP", tipo.getNome());
        assertEquals(CategoriaUsuario.DONO_RESTAURANTE, tipo.getCategoria());
        assertNull(tipo.getId());
    }

    @Test
    void deveReconstituirTipoUsuarioComSucesso() {
        // Act
        TipoUsuario tipo = TipoUsuario.reconstituir(1L, "Cliente Padrão", CategoriaUsuario.CLIENTE);

        // Assert
        assertEquals(1L, tipo.getId());
        assertEquals("Cliente Padrão", tipo.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        // Act & Assert
        TipoUsuarioInvalidoException exception = assertThrows(TipoUsuarioInvalidoException.class, () -> {
            TipoUsuario.criar("", CategoriaUsuario.CLIENTE);
        });
        assertEquals("O nome do tipo de usuário é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaForNula() {
        // Act & Assert
        TipoUsuarioInvalidoException exception = assertThrows(TipoUsuarioInvalidoException.class, () -> {
            TipoUsuario.criar("Admin", null);
        });
        assertEquals("A categoria do tipo de usuário é obrigatória.", exception.getMessage());
    }
}