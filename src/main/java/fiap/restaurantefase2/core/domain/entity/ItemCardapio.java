package fiap.restaurantefase2.core.domain.entity;

import fiap.restaurantefase2.core.domain.exception.ItemCardapioInvalidoException;

import java.math.BigDecimal;

public class ItemCardapio {
    private Long id;
    private Long restauranteId;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean apenasNoLocal;
    private String caminhoFoto;

    private ItemCardapio(Long id, Long restauranteId, String nome, String descricao, BigDecimal preco, boolean apenasNoLocal, String caminhoFoto) {
        this.id = id;
        this.restauranteId = restauranteId;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.apenasNoLocal = apenasNoLocal;
        this.caminhoFoto = caminhoFoto;
    }

    public static ItemCardapio criar(Long restauranteId, String nome, String descricao, BigDecimal preco, boolean apenasNoLocal, String caminhoFoto) {
        validar(restauranteId, nome, preco);
        return new ItemCardapio(null, restauranteId, nome, descricao, preco, apenasNoLocal, caminhoFoto);
    }

    public static ItemCardapio reconstituir(Long id, Long restauranteId, String nome, String descricao, BigDecimal preco, boolean apenasNoLocal, String caminhoFoto) {
        validar(restauranteId, nome, preco);
        return new ItemCardapio(id, restauranteId, nome, descricao, preco, apenasNoLocal, caminhoFoto);
    }

    private static void validar(Long restauranteId, String nome, BigDecimal preco) {
        if (restauranteId == null) {
            throw new ItemCardapioInvalidoException("O ID do restaurante é obrigatório.");
        }
        if (nome == null || nome.isBlank()) {
            throw new ItemCardapioInvalidoException("O nome do item é obrigatório.");
        }
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ItemCardapioInvalidoException("O preço deve ser maior que zero.");
        }
    }

    public Long getId() { return id; }
    public Long getRestauranteId() { return restauranteId; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPreco() { return preco; }
    public boolean isApenasNoLocal() { return apenasNoLocal; }
    public String getCaminhoFoto() { return caminhoFoto; }
}
