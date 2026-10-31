package fiap.restaurantefase2.infra.api.controller;

import fiap.restaurantefase2.core.application.usecase.itemcardapio.BuscarItemCardapioUseCase;
import fiap.restaurantefase2.core.application.usecase.itemcardapio.CriarItemCardapioUseCase;
import fiap.restaurantefase2.core.domain.entity.ItemCardapio;
import fiap.restaurantefase2.infra.api.dto.ItemCardapioRequest;
import fiap.restaurantefase2.infra.api.dto.ItemCardapioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes/{restauranteId}/itens")
@RequiredArgsConstructor
public class ItemCardapioController {

    private final CriarItemCardapioUseCase criarItemCardapioUseCase;
    private final BuscarItemCardapioUseCase buscarItemCardapioUseCase;

    @PostMapping
    public ResponseEntity<ItemCardapioResponse> adicionarItem(
            @PathVariable Long restauranteId,
            @Valid @RequestBody ItemCardapioRequest request) {

        ItemCardapio novoItem = criarItemCardapioUseCase.executar(
                restauranteId,
                request.nome(),
                request.descricao(),
                request.preco(),
                request.apenasNoLocal(),
                request.caminhoFoto()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(ItemCardapioResponse.de(novoItem));
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapioResponse>> listarItensDoRestaurante(@PathVariable Long restauranteId) {
        List<ItemCardapioResponse> itens = buscarItemCardapioUseCase.porRestaurante(restauranteId).stream()
                .map(ItemCardapioResponse::de)
                .toList();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemCardapioResponse> buscarItemPorId(
            @PathVariable Long restauranteId,
            @PathVariable Long itemId) {

        ItemCardapio item = buscarItemCardapioUseCase.porIdERestaurante(itemId, restauranteId);
        return ResponseEntity.ok(ItemCardapioResponse.de(item));
    }
}
