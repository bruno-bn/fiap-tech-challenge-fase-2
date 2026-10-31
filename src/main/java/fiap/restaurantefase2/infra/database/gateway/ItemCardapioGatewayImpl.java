package fiap.restaurantefase2.infra.database.gateway;

import fiap.restaurantefase2.core.application.gateway.ItemCardapioGateway;
import fiap.restaurantefase2.core.domain.entity.ItemCardapio;
import fiap.restaurantefase2.infra.database.entity.ItemCardapioEntity;
import fiap.restaurantefase2.infra.database.mapper.ItemCardapioMapper;
import fiap.restaurantefase2.infra.database.repository.ItemCardapioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemCardapioGatewayImpl implements ItemCardapioGateway {

    private final ItemCardapioRepository repository;

    public ItemCardapioGatewayImpl(ItemCardapioRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemCardapio salvar(ItemCardapio item) {
        ItemCardapioEntity entity = ItemCardapioMapper.toEntity(item);
        ItemCardapioEntity savedEntity = repository.save(entity);
        return ItemCardapioMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(Long id) {
        return repository.findById(id).map(ItemCardapioMapper::toDomain);
    }

    @Override
    public List<ItemCardapio> buscarPorRestaurante(Long restauranteId) {
        return repository.findByRestauranteId(restauranteId).stream()
                .map(ItemCardapioMapper::toDomain)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
