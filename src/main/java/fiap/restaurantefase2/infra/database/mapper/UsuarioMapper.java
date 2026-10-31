package fiap.restaurantefase2.infra.database.mapper;

import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.infra.database.entity.UsuarioEntity;

public class UsuarioMapper {

    public static UsuarioEntity toEntity(Usuario domain) {
        if (domain == null) return null;
        return new UsuarioEntity(
                domain.getId(),
                domain.getNome(),
                domain.getEmail(),
                TipoUsuarioMapper.toEntity(domain.getTipoUsuario())
        );
    }

    public static Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;
        return Usuario.reconstituir(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                TipoUsuarioMapper.toDomain(entity.getTipoUsuario())
        );
    }
}
