package fiap.restaurantefase2.infra.database.gateway;

import fiap.restaurantefase2.core.application.gateway.UsuarioGateway;
import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.infra.database.mapper.UsuarioMapper;
import fiap.restaurantefase2.infra.database.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository repository;

    public UsuarioGatewayImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id).map(UsuarioMapper::toDomain);
    }
}
