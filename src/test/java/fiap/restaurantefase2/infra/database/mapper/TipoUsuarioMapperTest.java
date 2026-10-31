package fiap.restaurantefase2.infra.database.mapper;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioMapperTest {

    @Test
    void deveConverterDominioParaEntidade() {
        TipoUsuario dominio = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        TipoUsuarioEntity entidade = TipoUsuarioMapper.toEntity(dominio);

        assertNotNull(entidade);
        assertEquals(1L, entidade.getId());
        assertEquals("Dono", entidade.getNome());
        assertEquals(CategoriaUsuario.DONO_RESTAURANTE, entidade.getCategoria());
    }

    @Test
    void deveRetornarNullQuandoDominioForNull() {
        assertNull(TipoUsuarioMapper.toEntity(null));
    }

    @Test
    void deveConverterEntidadeParaDominio() {
        TipoUsuarioEntity entidade = new TipoUsuarioEntity(2L, "Cliente", CategoriaUsuario.CLIENTE);
        TipoUsuario dominio = TipoUsuarioMapper.toDomain(entidade);

        assertNotNull(dominio);
        assertEquals(2L, dominio.getId());
        assertEquals("Cliente", dominio.getNome());
        assertEquals(CategoriaUsuario.CLIENTE, dominio.getCategoria());
    }

    @Test
    void deveRetornarNullQuandoEntidadeForNull() {
        assertNull(TipoUsuarioMapper.toDomain(null));
    }
}