package fiap.restaurantefase2.core.domain.entity;

import fiap.restaurantefase2.core.domain.exception.RestauranteInvalidoException;
import fiap.restaurantefase2.core.domain.exception.UsuarioNaoAutorizadoException;

public class Restaurante {
    private Long id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Usuario dono;

    private Restaurante(Long id, String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Usuario dono) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.dono = dono;
    }

    public static Restaurante criar(String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Usuario dono) {
        validar(nome, dono);
        return new Restaurante(null, nome, endereco, tipoCozinha, horarioFuncionamento, dono);
    }

    public static Restaurante reconstituir(Long id, String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Usuario dono) {
        validar(nome, dono);
        return new Restaurante(id, nome, endereco, tipoCozinha, horarioFuncionamento, dono);
    }

    private static void validar(String nome, Usuario dono) {
        if (nome == null || nome.isBlank()) {
            throw new RestauranteInvalidoException("O nome do restaurante é obrigatório.");
        }
        if (dono == null || !dono.isDonoDeRestaurante()) {
            throw new UsuarioNaoAutorizadoException("O usuário atribuído como dono deve ter a categoria DONO_RESTAURANTE.");
        }
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTipoCozinha() { return tipoCozinha; }
    public String getHorarioFuncionamento() { return horarioFuncionamento; }
    public Usuario getDono() { return dono; }
}
