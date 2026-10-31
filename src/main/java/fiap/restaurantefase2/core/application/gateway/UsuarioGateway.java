package fiap.restaurantefase2.core.application.gateway;

import fiap.restaurantefase2.core.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioGateway {
    Optional<Usuario> buscarPorId(Long id);
}
