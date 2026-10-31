package fiap.restaurantefase2.infra.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestauranteRequest(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O endereço é obrigatório") String endereco,
        @NotBlank(message = "O tipo de cozinha é obrigatório") String tipoCozinha,
        @NotBlank(message = "O horário de funcionamento é obrigatório") String horarioFuncionamento,
        @NotNull(message = "O ID do dono é obrigatório") Long donoId
) {
}
