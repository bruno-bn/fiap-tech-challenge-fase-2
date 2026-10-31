package fiap.restaurantefase2.infra.database.mapper;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.infra.database.entity.RestauranteEntity;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import fiap.restaurantefase2.infra.database.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteMapperTest {

    @Test
    void deveConverterDominioParaEntidade() {
        TipoUsuario tipo = TipoUsuario.reconstituir(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        Usuario dono = Usuario.reconstituir(1L, "João", "joao@email.com", tipo);
        Restaurante dominio = Restaurante.reconstituir(10L, "Bistrô", "Rua A", "Francesa", "18h-23h", dono);

        RestauranteEntity entidade = RestauranteMapper.toEntity(dominio);

        assertNotNull(entidade);
        assertEquals(10L, entidade.getId());
        assertEquals("Bistrô", entidade.getNome());
        assertEquals("Rua A", entidade.getEndereco());
        assertEquals("Francesa", entidade.getTipoCozinha());
        assertEquals("18h-23h", entidade.getHorarioFuncionamento());
        assertNotNull(entidade.getDono());
        assertEquals(1L, entidade.getDono().getId());
    }

    @Test
    void deveRetornarNullQuandoDominioForNull() {
        assertNull(RestauranteMapper.toEntity(null));
    }

    @Test
    void deveConverterEntidadeParaDominio() {
        TipoUsuarioEntity tipoEntidade = new TipoUsuarioEntity(1L, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        UsuarioEntity donoEntidade = new UsuarioEntity(1L, "João", "joao@email.com", tipoEntidade);
        RestauranteEntity entidade = new RestauranteEntity(10L, "Bistrô", "Rua A", "Francesa", "18h-23h", donoEntidade);

        Restaurante dominio = RestauranteMapper.toDomain(entidade);

        assertNotNull(dominio);
        assertEquals(10L, dominio.getId());
        assertEquals("Bistrô", dominio.getNome());
        assertNotNull(dominio.getDono());
        assertEquals(1L, dominio.getDono().getId());
    }

    @Test
    void deveRetornarNullQuandoEntidadeForNull() {
        assertNull(RestauranteMapper.toDomain(null));
    }
}