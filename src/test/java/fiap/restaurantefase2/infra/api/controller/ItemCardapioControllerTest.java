package fiap.restaurantefase2.infra.api.controller;

import fiap.restaurantefase2.core.domain.entity.CategoriaUsuario;
import fiap.restaurantefase2.infra.database.entity.ItemCardapioEntity;
import fiap.restaurantefase2.infra.database.entity.RestauranteEntity;
import fiap.restaurantefase2.infra.database.entity.TipoUsuarioEntity;
import fiap.restaurantefase2.infra.database.entity.UsuarioEntity;
import fiap.restaurantefase2.infra.database.repository.ItemCardapioRepository;
import fiap.restaurantefase2.infra.database.repository.RestauranteRepository;
import fiap.restaurantefase2.infra.database.repository.TipoUsuarioRepository;
import fiap.restaurantefase2.infra.database.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ItemCardapioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    private UsuarioEntity donoSalvo;
    private RestauranteEntity restauranteSalvo;
    private ItemCardapioEntity itemSalvo;

    @BeforeEach
    void setUp() {
        TipoUsuarioEntity tipo = tipoUsuarioRepository.save(new TipoUsuarioEntity(null, "Dono", CategoriaUsuario.DONO_RESTAURANTE));
        donoSalvo = usuarioRepository.save(new UsuarioEntity(null, "Carlos", "carlos@email.com", tipo));

        RestauranteEntity restaurante = new RestauranteEntity(null, "Pizzaria do Carlos", "Rua X", "Italiana", "18h-23h", donoSalvo);
        restauranteSalvo = restauranteRepository.save(restaurante);

        // Criando um item inicial para podermos testar o GET
        ItemCardapioEntity item = new ItemCardapioEntity();
        item.setRestaurante(restauranteSalvo);
        item.setNome("Pizza de Calabresa");
        item.setDescricao("Com cebola");
        item.setPreco(new BigDecimal("40.00"));
        item.setApenasNoLocal(false);
        itemSalvo = itemCardapioRepository.save(item);
    }

    @Test
    void deveCriarItemCardapioRetornar201Created() throws Exception {
        String jsonRequest = """
                {
                    "nome": "Pizza Margherita",
                    "descricao": "Molho, muçarela e manjericão",
                    "preco": 45.90,
                    "apenasNoLocal": false
                }
                """;

        mockMvc.perform(post("/api/restaurantes/" + restauranteSalvo.getId() + "/itens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Pizza Margherita"))
                .andExpect(jsonPath("$.preco").value(45.9));
    }

    @Test
    void deveRetornarErroQuandoRestauranteNaoExistirAoCriarItem() throws Exception {
        String jsonRequest = """
                {
                    "nome": "Pizza Fantasma",
                    "descricao": "Não existe",
                    "preco": 10.0,
                    "apenasNoLocal": false
                }
                """;

        mockMvc.perform(post("/api/restaurantes/9999/itens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isUnprocessableEntity()); // Pode ser isNotFound() dependendo do seu Handler
    }

    @Test
    void deveBuscarItemPorIdRetornar200Ok() throws Exception {
        mockMvc.perform(get("/api/restaurantes/" + restauranteSalvo.getId() + "/itens/" + itemSalvo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Pizza de Calabresa"));
    }

    @Test
    void deveRetornarErroAoBuscarItemDeOutroRestaurante() throws Exception {
        // Criamos um segundo restaurante
        RestauranteEntity outroRestaurante = restauranteRepository.save(
                new RestauranteEntity(null, "Sushi Bar", "Rua Y", "Japonesa", "18h-23h", donoSalvo)
        );

        // Tentamos buscar o item (que é da Pizzaria) passando o ID do Sushi Bar
        mockMvc.perform(get("/api/restaurantes/" + outroRestaurante.getId() + "/itens/" + itemSalvo.getId()))
                .andExpect(status().isUnprocessableEntity()); // Altere para isNotFound() ou isBadRequest() consoante o seu GlobalExceptionHandler
    }
}