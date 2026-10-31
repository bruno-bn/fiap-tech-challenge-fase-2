package fiap.restaurantefase2.infra.api.dto;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;

public record TipoUsuarioResponse(Long id, String nome, CategoriaUsuario categoria) {

    // Método utilitário para converter de Domínio para DTO de saída
    public static TipoUsuarioResponse de(TipoUsuario tipoUsuario) {
        return new TipoUsuarioResponse(
                tipoUsuario.getId(),
                tipoUsuario.getNome(),
                tipoUsuario.getCategoria()
        );
    }
}
