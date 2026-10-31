package fiap.restaurantefase2.infra.config.usecase;

import fiap.restaurantefase2.core.application.gateway.TipoUsuarioGateway;
import fiap.restaurantefase2.core.application.usecase.tipousuario.BuscarTipoUsuarioUseCase;
import fiap.restaurantefase2.core.application.usecase.tipousuario.CriarTipoUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TipoUsuarioConfig {

    @Bean
    public CriarTipoUsuarioUseCase criarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        return new CriarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    @Bean
    public BuscarTipoUsuarioUseCase buscarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        return new BuscarTipoUsuarioUseCase(tipoUsuarioGateway);
    }
}
