package fiap.restaurantefase2.infra.database.entity;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tipo_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CategoriaUsuario categoria;
}
