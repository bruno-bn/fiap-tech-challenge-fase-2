package fiap.restaurantefase2.core.domain.exception;

public class UsuarioNaoAutorizadoException extends DomainException {
    public UsuarioNaoAutorizadoException(String mensagem) {
        super(mensagem);
    }
}
