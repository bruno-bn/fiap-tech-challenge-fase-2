package fiap.restaurantefase2.core.application.usecase.restaurante;

import fiap.restaurantefase2.core.application.gateway.RestauranteGateway;
import fiap.restaurantefase2.core.application.gateway.UsuarioGateway;
import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.core.domain.entity.Usuario;
import fiap.restaurantefase2.core.domain.exception.UsuarioNaoEncontradoException;

public class CriarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;
    private final UsuarioGateway usuarioGateway;

    public CriarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public Restaurante executar(String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Long donoId) {
        // 1. Procura o usuário. Se não existir, lança uma exceção.
        Usuario dono = usuarioGateway.buscarPorId(donoId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado para o ID: " + donoId));

        // 2. Cria o restaurante. Se o usuário existir mas não tiver a categoria correta,
        // o método criar() vai lançar automaticamente a UsuarioNaoAutorizadoException.
        Restaurante novoRestaurante = Restaurante.criar(nome, endereco, tipoCozinha, horarioFuncionamento, dono);

        // 3. Salva e retorna
        return restauranteGateway.salvar(novoRestaurante);
    }
}
