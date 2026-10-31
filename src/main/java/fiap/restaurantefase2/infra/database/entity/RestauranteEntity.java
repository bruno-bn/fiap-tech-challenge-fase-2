package fiap.restaurantefase2.infra.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_restaurante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 255)
    private String endereco;

    @Column(name = "tipo_cozinha", nullable = false, length = 100)
    private String tipoCozinha;

    @Column(name = "horario_funcionamento", nullable = false, length = 100)
    private String horarioFuncionamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dono_id", nullable = false)
    private UsuarioEntity dono;
}
