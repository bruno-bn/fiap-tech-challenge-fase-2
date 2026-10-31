package fiap.restaurantefase2.infra.api.dto;

import fiap.restaurantefase2.core.domain.entity.Restaurante;

public record RestauranteResponse(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId,
        String nomeDono
) {
    public static RestauranteResponse de(Restaurante restaurante) {
        return new RestauranteResponse(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getDono().getId(),
                restaurante.getDono().getNome()
        );
    }
}
