package fiap.restaurantefase2.infra.database.gateway;

import fiap.restaurantefase2.core.application.gateway.TipoUsuarioGateway;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import fiap.restaurantefase2.infra.database.mapper.TipoUsuarioMapper;
import fiap.restaurantefase2.infra.database.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TipoUsuarioGatewayImpl implements TipoUsuarioGateway {

    private final TipoUsuarioRepository repository;

    // Injeção de dependência do Spring Data
    public TipoUsuarioGatewayImpl(TipoUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {
        TipoUsuarioEntity entity = TipoUsuarioMapper.toEntity(tipoUsuario);
        TipoUsuarioEntity savedEntity = repository.save(entity);
        return TipoUsuarioMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<TipoUsuario> buscarPorId(Long id) {
        return repository.findById(id)
                .map(TipoUsuarioMapper::toDomain);
    }

    @Override
    public List<TipoUsuario> listarTodos() {
        return repository.findAll().stream()
                .map(TipoUsuarioMapper::toDomain)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
