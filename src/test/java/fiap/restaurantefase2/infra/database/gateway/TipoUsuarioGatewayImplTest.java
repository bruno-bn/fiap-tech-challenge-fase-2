package fiap.restaurantefase2.infra.database.gateway;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import fiap.restaurantefase2.infra.database.repository.TipoUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoUsuarioGatewayImplTest {

    @Mock
    private TipoUsuarioRepository repository;

    @InjectMocks
    private TipoUsuarioGatewayImpl gateway;

    private TipoUsuario tipoDominio;
    private TipoUsuarioEntity tipoEntity;

    @BeforeEach
    void setUp() {
        tipoDominio = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        tipoEntity = new TipoUsuarioEntity(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
    }

    @Test
    void deveSalvarTipoUsuario() {
        // Arrange
        when(repository.save(any(TipoUsuarioEntity.class))).thenReturn(tipoEntity);

        // Act
        TipoUsuario salvo = gateway.salvar(tipoDominio);

        // Assert
        assertNotNull(salvo);
        assertEquals(1L, salvo.getId());
        assertEquals("Dono", salvo.getNome());
        verify(repository, times(1)).save(any(TipoUsuarioEntity.class));
    }

    @Test
    void deveBuscarTipoUsuarioPorId() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(tipoEntity));

        // Act
        Optional<TipoUsuario> encontrado = gateway.buscarPorId(1L);

        // Assert
        assertTrue(encontrado.isPresent());
        assertEquals(1L, encontrado.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveRetornarVazioQuandoBuscarPorIdInexistente() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<TipoUsuario> encontrado = gateway.buscarPorId(99L);

        // Assert
        assertFalse(encontrado.isPresent());
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void deveListarTodosOsTiposUsuario() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of(tipoEntity));

        // Act
        List<TipoUsuario> lista = gateway.listarTodos();

        // Assert
        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(1L, lista.get(0).getId());
        verify(repository, times(1)).findAll();
    }
}