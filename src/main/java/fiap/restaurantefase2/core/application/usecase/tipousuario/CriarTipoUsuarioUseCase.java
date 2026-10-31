package fiap.restaurantefase2.core.application.usecase.tipousuario;

import fiap.restaurantefase2.core.application.gateway.TipoUsuarioGateway;
import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;

public class CriarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public CriarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public TipoUsuario executar(String nome, CategoriaUsuario categoria) {
        // 1. Cria a entidade pura (as regras de negócio e validações já rodam aqui)
        TipoUsuario novoTipo = TipoUsuario.criar(nome, categoria);

        // 2. Manda salvar através da porta (Gateway)
        return tipoUsuarioGateway.salvar(novoTipo);
    }
}
