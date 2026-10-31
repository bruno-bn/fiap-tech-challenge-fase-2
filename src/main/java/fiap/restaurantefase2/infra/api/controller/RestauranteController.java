package fiap.restaurantefase2.infra.api.controller;

import fiap.restaurantefase2.core.application.usecase.restaurante.BuscarRestauranteUseCase;
import fiap.restaurantefase2.core.application.usecase.restaurante.CriarRestauranteUseCase;
import fiap.restaurantefase2.core.domain.entity.Restaurante;
import fiap.restaurantefase2.infra.api.dto.RestauranteRequest;
import fiap.restaurantefase2.infra.api.dto.RestauranteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final BuscarRestauranteUseCase buscarRestauranteUseCase;

    @PostMapping
    public ResponseEntity<RestauranteResponse> criar(@Valid @RequestBody RestauranteRequest request) {
        Restaurante novoRestaurante = criarRestauranteUseCase.executar(
                request.nome(),
                request.endereco(),
                request.tipoCozinha(),
                request.horarioFuncionamento(),
                request.donoId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(RestauranteResponse.de(novoRestaurante));
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponse>> listarTodos() {
        List<RestauranteResponse> restaurantes = buscarRestauranteUseCase.todos().stream()
                .map(RestauranteResponse::de)
                .toList();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> buscarPorId(@PathVariable Long id) {
        Restaurante restaurante = buscarRestauranteUseCase.porId(id);
        return ResponseEntity.ok(RestauranteResponse.de(restaurante));
    }
}
