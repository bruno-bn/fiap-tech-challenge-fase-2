package fiap.restaurantefase2.infra.database.gateway;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import fiap.restaurantefase2.infra.database.entity.UsuarioEntity;
import fiap.restaurantefase2.infra.database.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioGatewayImplTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioGatewayImpl gateway;

    @Test
    void deveBuscarUsuarioPorId() {
        // Arrange
        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        UsuarioEntity usuarioEntity = new UsuarioEntity(5L, "João", "joao@email.com", tipoEntity);

        when(repository.findById(5L)).thenReturn(Optional.of(usuarioEntity));

        // Act
        Optional<Usuario> encontrado = gateway.buscarPorId(5L);

        // Assert
        assertTrue(encontrado.isPresent());
        assertEquals(5L, encontrado.get().getId());
        assertEquals("João", encontrado.get().getNome());
        verify(repository, times(1)).findById(5L);
    }

    @Test
    void deveRetornarVazioQuandoBuscarPorIdInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Usuario> encontrado = gateway.buscarPorId(99L);

        assertFalse(encontrado.isPresent());
        verify(repository, times(1)).findById(99L);
    }
}