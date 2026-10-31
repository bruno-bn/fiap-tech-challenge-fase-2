package fiap.restaurantefase2.infra.database.mapper;

import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.infra.database.entity.RestauranteEntity;

public class RestauranteMapper {

    public static RestauranteEntity toEntity(Restaurante domain) {
        if (domain == null) return null;
        return new RestauranteEntity(
                domain.getId(),
                domain.getNome(),
                domain.getEndereco(),
                domain.getTipoCozinha(),
                domain.getHorarioFuncionamento(),
                UsuarioMapper.toEntity(domain.getDono())
        );
    }

    public static Restaurante toDomain(RestauranteEntity entity) {
        if (entity == null) return null;
        return Restaurante.reconstituir(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getTipoCozinha(),
                entity.getHorarioFuncionamento(),
                UsuarioMapper.toDomain(entity.getDono())
        );
    }
}
