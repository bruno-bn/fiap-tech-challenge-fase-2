package fiap.restaurantefase2.infra.database.gateway;

import fiap.restaurantefase2.core.domain.entity.ItemCardapio;
import fiap.restaurantefase2.infra.database.entity.ItemCardapioEntity;
import fiap.restaurantefase2.infra.database.entity.RestauranteEntity;
import fiap.restaurantefase2.infra.database.repository.ItemCardapioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemCardapioGatewayImplTest {

    @Mock
    private ItemCardapioRepository repository;

    @InjectMocks
    private ItemCardapioGatewayImpl gateway;

    private ItemCardapio itemDominio;
    private ItemCardapioEntity itemEntity;

    @BeforeEach
    void setUp() {
        itemDominio = ItemCardapio.reconstituir(100L, 10L, "Prato", "Desc", new BigDecimal("50.0"), false, null);

        RestauranteEntity restauranteRef = new RestauranteEntity();
        restauranteRef.setId(10L);
        itemEntity = new ItemCardapioEntity(100L, restauranteRef, "Prato", "Desc", new BigDecimal("50.0"), false, null);
    }

    @Test
    void deveSalvarItemCardapio() {
        when(repository.save(any(ItemCardapioEntity.class))).thenReturn(itemEntity);

        ItemCardapio salvo = gateway.salvar(itemDominio);

        assertNotNull(salvo);
        assertEquals(100L, salvo.getId());
        assertEquals("Prato", salvo.getNome());
        verify(repository, times(1)).save(any(ItemCardapioEntity.class));
    }

    @Test
    void deveBuscarItemCardapioPorId() {
        when(repository.findById(100L)).thenReturn(Optional.of(itemEntity));

        Optional<ItemCardapio> encontrado = gateway.buscarPorId(100L);

        assertTrue(encontrado.isPresent());
        assertEquals(100L, encontrado.get().getId());
        verify(repository, times(1)).findById(100L);
    }

    @Test
    void deveBuscarItensPorRestauranteId() {
        when(repository.findByRestauranteId(10L)).thenReturn(List.of(itemEntity));

        List<ItemCardapio> lista = gateway.buscarPorRestaurante(10L);

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(10L, lista.get(0).getRestauranteId());
        verify(repository, times(1)).findByRestauranteId(10L);
    }
}