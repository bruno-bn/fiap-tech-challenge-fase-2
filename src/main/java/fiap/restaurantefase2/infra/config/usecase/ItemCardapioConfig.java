package fiap.restaurantefase2.infra.config.usecase;

import fiap.restaurantefase2.core.application.gateway.ItemCardapioGateway;
import fiap.restaurantefase2.core.application.gateway.RestauranteGateway;
import fiap.restaurantefase2.core.application.usecase.itemcardapio.BuscarItemCardapioUseCase;
import fiap.restaurantefase2.core.application.usecase.itemcardapio.CriarItemCardapioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCardapioConfig {

    @Bean
    public CriarItemCardapioUseCase criarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway, RestauranteGateway restauranteGateway) {
        return new CriarItemCardapioUseCase(itemCardapioGateway, restauranteGateway);
    }

    @Bean
    public BuscarItemCardapioUseCase buscarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        return new BuscarItemCardapioUseCase(itemCardapioGateway);
    }
}
