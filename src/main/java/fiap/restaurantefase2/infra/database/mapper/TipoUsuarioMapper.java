package fiap.restaurantefase2.infra.database.mapper;

import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;

public class TipoUsuarioMapper {

    // Converte de Domínio para JPA (usado na hora de salvar no banco)
    public static TipoUsuarioEntity toEntity(TipoUsuario domain) {
        if (domain == null) return null;
        return new TipoUsuarioEntity(domain.getId(), domain.getNome(), domain.getCategoria());
    }

    // Converte de JPA para Domínio (usado na hora de devolver os dados para o UseCase)
    public static TipoUsuario toDomain(TipoUsuarioEntity entity) {
        if (entity == null) return null;
        // Perceba o uso do nosso método de fábrica "reconstituir" que criamos no domínio
        return TipoUsuario.reconstituir(entity.getId(), entity.getNome(), entity.getCategoria());
    }
}
