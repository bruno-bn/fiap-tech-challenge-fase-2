package fiap.restaurantefase2.infra.database.gateway;

import fiap.restaurantefase2.core.application.gateway.RestauranteGateway;
import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.infra.database.entity.RestauranteEntity;
import fiap.restaurantefase2.infra.database.mapper.RestauranteMapper;
import fiap.restaurantefase2.infra.database.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RestauranteGatewayImpl implements RestauranteGateway {

    private final RestauranteRepository repository;

    public RestauranteGatewayImpl(RestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        RestauranteEntity entity = RestauranteMapper.toEntity(restaurante);
        RestauranteEntity savedEntity = repository.save(entity);
        return RestauranteMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return repository.findById(id).map(RestauranteMapper::toDomain);
    }

    @Override
    public List<Restaurante> listarTodos() {
        return repository.findAll().stream()
                .map(RestauranteMapper::toDomain)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
