package fiap.restaurantefase2.core.application.gateway;

import fiap.restaurantefase2.core.domain.entity.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioGateway {

    TipoUsuario salvar(TipoUsuario tipoUsuario);

    Optional<TipoUsuario> buscarPorId(Long id);

    List<TipoUsuario> listarTodos();

    void deletar(Long id);
}
