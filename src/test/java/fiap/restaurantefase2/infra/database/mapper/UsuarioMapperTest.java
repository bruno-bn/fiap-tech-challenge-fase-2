package fiap.restaurantefase2.infra.database.mapper;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import fiap.restaurantefase2.infra.database.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {

    @Test
    void deveConverterDominioParaEntidade() {
        TipoUsuario tipoDominio = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        Usuario dominio = Usuario.reconstituir(1L, "João", "joao@email.com", tipoDominio);

        UsuarioEntity entidade = UsuarioMapper.toEntity(dominio);

        assertNotNull(entidade);
        assertEquals(1L, entidade.getId());
        assertEquals("João", entidade.getNome());
        assertEquals("joao@email.com", entidade.getEmail());
        assertNotNull(entidade.getTipoUsuario());
        assertEquals(1L, entidade.getTipoUsuario().getId());
    }

    @Test
    void deveRetornarNullQuandoDominioForNull() {
        assertNull(UsuarioMapper.toEntity(null));
    }

    @Test
    void deveConverterEntidadeParaDominio() {
        TipoUsuarioEntity tipoEntidade = new TipoUsuarioEntity(2L, "Cliente", CategoriaUsuario.CLIENTE);
        UsuarioEntity entidade = new UsuarioEntity(3L, "Maria", "maria@email.com", tipoEntidade);

        Usuario dominio = UsuarioMapper.toDomain(entidade);

        assertNotNull(dominio);
        assertEquals(3L, dominio.getId());
        assertEquals("Maria", dominio.getNome());
        assertEquals("maria@email.com", dominio.getEmail());
        assertNotNull(dominio.getTipoUsuario());
        assertEquals(2L, dominio.getTipoUsuario().getId());
    }

    @Test
    void deveRetornarNullQuandoEntidadeForNull() {
        assertNull(UsuarioMapper.toDomain(null));
    }
}