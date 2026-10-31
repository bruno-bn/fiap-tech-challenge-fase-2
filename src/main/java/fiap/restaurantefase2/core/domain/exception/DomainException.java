package fiap.restaurantefase2.core.domain.exception;

public abstract class DomainException extends RuntimeException {
    public DomainException(String mensagem) {
        super(mensagem);
    }
}
