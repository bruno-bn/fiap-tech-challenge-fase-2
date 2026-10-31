package fiap.restaurantefase2.infra.api.controller;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.infra.api.dto.RestauranteRequest;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import fiap.restaurantefase2.infra.database.entity.UsuarioEntity;
import fiap.restaurantefase2.infra.database.repository.TipoUsuarioRepository;
import fiap.restaurantefase2.infra.database.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RestauranteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    private UsuarioEntity donoSalvo;

    @BeforeEach
    void setUp() {
        TipoUsuarioEntity tipoDono = new TipoUsuarioEntity(null, "Dono", CategoriaUsuario.DONO_RESTAURANTE);
        TipoUsuarioEntity tipoSalvo = tipoUsuarioRepository.save(tipoDono);

        UsuarioEntity dono = new UsuarioEntity(null, "João Dono", "joao@email.com", tipoSalvo);
        donoSalvo = usuarioRepository.save(dono);
    }

    @Test
    void deveCriarRestauranteRetornar201Created() throws Exception {
        RestauranteRequest request = new RestauranteRequest(
                "Restaurante do João",
                "Rua Principal, 123",
                "Italiana",
                "18:00 as 23:00",
                donoSalvo.getId()
        );

        mockMvc.perform(post("/api/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Restaurante do João"))
                .andExpect(jsonPath("$.nomeDono").value("João Dono"));
    }

    @Test
    void deveRetornar422QuandoUsuarioNaoForDono() throws Exception {
        TipoUsuarioEntity tipoCliente = new TipoUsuarioEntity(null, "Cliente", CategoriaUsuario.CLIENTE);
        TipoUsuarioEntity tipoSalvo = tipoUsuarioRepository.save(tipoCliente);

        UsuarioEntity cliente = new UsuarioEntity(null, "Maria Cliente", "maria@email.com", tipoSalvo);
        UsuarioEntity clienteSalvo = usuarioRepository.save(cliente);

        RestauranteRequest request = new RestauranteRequest(
                "Restaurante da Maria",
                "Rua Secundária",
                "Brasileira",
                "10:00 as 15:00",
                clienteSalvo.getId()
        );

        mockMvc.perform(post("/api/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.erro").value("Erro de Validação de Negócio"))
                .andExpect(jsonPath("$.mensagem").value("O usuário atribuído como dono deve ter a categoria DONO_RESTAURANTE."));
    }
}