package fiap.restaurantefase2.infra.api.controller;

import fiap.restaurantefase2.core.application.usecase.tipousuario.BuscarTipoUsuarioUseCase;
import fiap.restaurantefase2.core.application.usecase.tipousuario.CriarTipoUsuarioUseCase;
import fiap.restaurantefase2.core.domain.entity.TipoUsuario;
import fiap.restaurantefase2.infra.api.dto.TipoUsuarioRequest;
import fiap.restaurantefase2.infra.api.dto.TipoUsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-usuario")
public class TipoUsuarioController {

    private final CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;
    private final BuscarTipoUsuarioUseCase buscarTipoUsuarioUseCase;

    public TipoUsuarioController(CriarTipoUsuarioUseCase criarTipoUsuarioUseCase, BuscarTipoUsuarioUseCase buscarTipoUsuarioUseCase) {
        this.criarTipoUsuarioUseCase = criarTipoUsuarioUseCase;
        this.buscarTipoUsuarioUseCase = buscarTipoUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioResponse> criar(@Valid @RequestBody TipoUsuarioRequest request) {
        TipoUsuario novoTipo = criarTipoUsuarioUseCase.executar(request.nome(), request.categoria());
        return ResponseEntity.status(HttpStatus.CREATED).body(TipoUsuarioResponse.de(novoTipo));
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuarioResponse>> listarTodos() {
        List<TipoUsuarioResponse> tipos = buscarTipoUsuarioUseCase.todos().stream()
                .map(TipoUsuarioResponse::de)
                .toList();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponse> buscarPorId(@PathVariable Long id) {
        TipoUsuario tipoUsuario = buscarTipoUsuarioUseCase.porId(id);
        return ResponseEntity.ok(TipoUsuarioResponse.de(tipoUsuario));
    }
}