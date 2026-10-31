package fiap.restaurantefase2.core.domain.entity;

import fiap.restaurantefase2.core.domain.exception.TipoUsuarioInvalidoException;

public class TipoUsuario {
    private Long id;
    private String nome;
    private CategoriaUsuario categoria;

    private TipoUsuario(Long id, String nome, CategoriaUsuario categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

    public static TipoUsuario criar(String nome, CategoriaUsuario categoria) {
        validar(nome, categoria);
        return new TipoUsuario(null, nome, categoria);
    }

    public static TipoUsuario reconstituir(Long id, String nome, CategoriaUsuario categoria) {
        validar(nome, categoria);
        return new TipoUsuario(id, nome, categoria);
    }

    private static void validar(String nome, CategoriaUsuario categoria) {
        if (nome == null || nome.isBlank()) {
            throw new TipoUsuarioInvalidoException("O nome do tipo de usuário é obrigatório.");
        }
        if (categoria == null) {
            throw new TipoUsuarioInvalidoException("A categoria do tipo de usuário é obrigatória.");
        }
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public CategoriaUsuario getCategoria() { return categoria; }
}