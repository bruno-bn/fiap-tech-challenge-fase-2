package fiap.restaurantefase2.infra.api.dto;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TipoUsuarioRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "A categoria é obrigatória")
        CategoriaUsuario categoria
) {
}
