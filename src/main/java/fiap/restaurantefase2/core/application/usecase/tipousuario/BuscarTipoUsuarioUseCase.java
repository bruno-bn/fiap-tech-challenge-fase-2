package fiap.restaurantefase2.core.application.usecase.tipousuario;

import fiap.restaurantefase2.core.application.gateway.TipoUsuarioGateway;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.core.domain.exception.TipoUsuarioInvalidoException;

import java.util.List;

public class BuscarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public BuscarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public TipoUsuario porId(Long id) {
        return tipoUsuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new TipoUsuarioInvalidoException("Tipo de usuário não encontrado para o ID: " + id));
    }

    public List<TipoUsuario> todos() {
        return tipoUsuarioGateway.listarTodos();
    }
}
