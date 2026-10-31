package fiap.restaurantefase2.infra.database.gateway;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.infra.database.entity.RestauranteEntity;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import fiap.restaurantefase2.infra.database.entity.UsuarioEntity;
import fiap.restaurantefase2.infra.database.repository.RestauranteRepository;
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
class RestauranteGatewayImplTest {

    @Mock
    private RestauranteRepository repository;

    @InjectMocks
    private RestauranteGatewayImpl gateway;

    private Restaurante restauranteDominio;
    private RestauranteEntity restauranteEntity;

    @BeforeEach
    void setUp() {
        // Mock de Domínio
        TipoUsuario tipo = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        Usuario dono = Usuario.reconstituir(1L, "João", "joao@email.com", tipo);
        restauranteDominio = Restaurante.reconstituir(10L, "Bistrô", "Rua A", "Francesa", "18h-23h", dono);

        // Mock de Entidade
        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        UsuarioEntity donoEntity = new UsuarioEntity(1L, "João", "joao@email.com", tipoEntity);
        restauranteEntity = new RestauranteEntity(10L, "Bistrô", "Rua A", "Francesa", "18h-23h", donoEntity);
    }

    @Test
    void deveSalvarRestaurante() {
        when(repository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);

        Restaurante salvo = gateway.salvar(restauranteDominio);

        assertNotNull(salvo);
        assertEquals(10L, salvo.getId());
        assertEquals("Bistrô", salvo.getNome());
        verify(repository, times(1)).save(any(RestauranteEntity.class));
    }

    @Test
    void deveBuscarRestaurantePorId() {
        when(repository.findById(10L)).thenReturn(Optional.of(restauranteEntity));

        Optional<Restaurante> encontrado = gateway.buscarPorId(10L);

        assertTrue(encontrado.isPresent());
        assertEquals(10L, encontrado.get().getId());
        verify(repository, times(1)).findById(10L);
    }

    @Test
    void deveRetornarVazioQuandoBuscarPorIdInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Restaurante> encontrado = gateway.buscarPorId(99L);

        assertFalse(encontrado.isPresent());
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void deveListarTodosOsRestaurantes() {
        when(repository.findAll()).thenReturn(List.of(restauranteEntity));

        List<Restaurante> lista = gateway.listarTodos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(10L, lista.get(0).getId());
        verify(repository, times(1)).findAll();
    }
}