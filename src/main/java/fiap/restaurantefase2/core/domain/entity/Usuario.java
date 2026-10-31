package fiap.restaurantefase2.core.domain.entity;

import fiap.restaurantefase2.core.domain.exception.UsuarioInvalidoException;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;

    private Usuario(Long id, String nome, String email, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public static Usuario criar(String nome, String email, TipoUsuario tipoUsuario) {
        validar(nome, email, tipoUsuario);
        return new Usuario(null, nome, email, tipoUsuario);
    }

    public static Usuario reconstituir(Long id, String nome, String email, TipoUsuario tipoUsuario) {
        validar(nome, email, tipoUsuario);
        return new Usuario(id, nome, email, tipoUsuario);
    }

    private static void validar(String nome, String email, TipoUsuario tipoUsuario) {
        if (nome == null || nome.isBlank()) {
            throw new UsuarioInvalidoException("O nome do usuário é obrigatório.");
        }
        if (email == null || email.isBlank()) {
            throw new UsuarioInvalidoException("O e-mail do usuário é obrigatório.");
        }
        if (tipoUsuario == null) {
            throw new UsuarioInvalidoException("O tipo de usuário é obrigatório.");
        }
    }

    public boolean isDonoDeRestaurante() {
        return this.tipoUsuario != null &&
                CategoriaUsuario.DONO_RESTAURANTE.equals(this.tipoUsuario.getCategoria());
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
}
