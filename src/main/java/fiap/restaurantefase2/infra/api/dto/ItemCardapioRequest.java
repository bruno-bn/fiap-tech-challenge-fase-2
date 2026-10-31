package fiap.restaurantefase2.infra.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemCardapioRequest(
        @NotBlank(message = "O nome do item é obrigatório") String nome,
        String descricao,
        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero") BigDecimal preco,
        boolean apenasNoLocal,
        String caminhoFoto
) {
}