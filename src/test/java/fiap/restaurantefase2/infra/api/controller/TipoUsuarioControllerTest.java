package fiap.restaurantefase2.infra.api.controller;


import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import fiap.restaurantefase2.infra.database.repository.TipoUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TipoUsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    private TipoUsuarioEntity tipoSalvo;

    @BeforeEach
    void setUp() {
        TipoUsuarioEntity tipo = new TipoUsuarioEntity(null, "Administrador", CategoriaUsuario.DONO_RESTAURANTE);
        tipoSalvo = tipoUsuarioRepository.save(tipo);
    }

    @Test
    void deveCriarTipoUsuarioRetornar201Created() throws Exception {
        String jsonRequest = """
                {
                    "nome": "Cliente VIP",
                    "categoria": "CLIENTE"
                }
                """;

        mockMvc.perform(post("/api/tipos-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Cliente VIP"))
                .andExpect(jsonPath("$.categoria").value("CLIENTE"));
    }

    @Test
    void deveBuscarTodosOsTiposUsuarioRetornar200Ok() throws Exception {
        mockMvc.perform(get("/api/tipos-usuario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].nome").value(hasItem("Administrador")));
    }
}