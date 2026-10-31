package fiap.restaurantefase2.core.domain.entity;

import fiap.restaurantefase2.core.domain.exception.UsuarioInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveCriarUsuarioComSucesso() {
        // Arrange
        TipoUsuario tipo = TipoUsuario.reconstituir(1L, "Cliente", CategoriaUsuario.CLIENTE);

        // Act
        Usuario usuario = Usuario.criar("Ana", "ana@email.com", tipo);

        // Assert
        assertNotNull(usuario);
        assertEquals("Ana", usuario.getNome());
        assertEquals("ana@email.com", usuario.getEmail());
        assertEquals(tipo, usuario.getTipoUsuario());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        TipoUsuario tipo = TipoUsuario.reconstituir(1L, "Cliente", CategoriaUsuario.CLIENTE);

        assertThrows(UsuarioInvalidoException.class, () -> {
            Usuario.criar(null, "ana@email.com", tipo);
        });
    }

    @Test
    void deveLancarExcecaoQuandoEmailForVazio() {
        TipoUsuario tipo = TipoUsuario.reconstituir(1L, "Cliente", CategoriaUsuario.CLIENTE);

        assertThrows(UsuarioInvalidoException.class, () -> {
            Usuario.criar("Ana", "   ", tipo);
        });
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioForNulo() {
        assertThrows(UsuarioInvalidoException.class, () -> {
            Usuario.criar("Ana", "ana@email.com", null);
        });
    }

    @Test
    void deveRetornarTrueSeUsuarioForDonoDeRestaurante() {
        // Arrange
        TipoUsuario tipoDono = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        Usuario dono = Usuario.criar("João", "joao@email.com", tipoDono);

        // Act & Assert
        assertTrue(dono.isDonoDeRestaurante());
    }

    @Test
    void deveRetornarFalseSeUsuarioForCliente() {
        // Arrange
        TipoUsuario tipoCliente = TipoUsuario.reconstituir(2L, "Cliente", CategoriaUsuario.CLIENTE);
        Usuario cliente = Usuario.criar("Maria", "maria@email.com", tipoCliente);

        // Act & Assert
        assertFalse(cliente.isDonoDeRestaurante());
    }
}