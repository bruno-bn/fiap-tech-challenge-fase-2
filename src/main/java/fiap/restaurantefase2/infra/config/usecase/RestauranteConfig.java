package fiap.restaurantefase2.infra.config.usecase;

import fiap.restaurantefase2.core.application.gateway.RestauranteGateway;
import fiap.restaurantefase2.core.application.gateway.UsuarioGateway;
import fiap.restaurantefase2.core.application.usecase.restaurante.BuscarRestauranteUseCase;
import fiap.restaurantefase2.core.application.usecase.restaurante.CriarRestauranteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {

    @Bean
    public CriarRestauranteUseCase criarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        return new CriarRestauranteUseCase(restauranteGateway, usuarioGateway);
    }

    @Bean
    public BuscarRestauranteUseCase buscarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new BuscarRestauranteUseCase(restauranteGateway);
    }
}
