package com.ifce.loja.controller;

import com.ifce.loja.dto.ProdutoRequestDTO;
import com.ifce.loja.dto.ProdutoResponseDTO;
import com.ifce.loja.model.Produto;
import com.ifce.loja.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> addProduto(@RequestBody @Valid ProdutoRequestDTO produtoDTO) {
        Produto produto = this.produtoService.add(produtoDTO);

        URI uri = UriComponentsBuilder.fromPath("/{id}").build(produto);

        return ResponseEntity.created(uri).body(new ProdutoResponseDTO(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodosProdutos() {
        return ResponseEntity.ok().body(
                this.produtoService.listarTodosProdutos().stream().map(ProdutoResponseDTO::new).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> pegarProdutoPorID(@PathVariable Long id) {
        Optional<Produto> produto = this.produtoService.pegarProdutoPorID(id);

        return produto
                .map(value -> ResponseEntity.ok(new ProdutoResponseDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO produtoAtualizado) {
        Produto produto = this.produtoService.update(id, produtoAtualizado);

        if (produto != null) {
            return ResponseEntity.ok(new ProdutoResponseDTO(produto));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        Optional<Produto> produto = this.produtoService.pegarProdutoPorID(id);

        if (produto.isPresent()) {
            this.produtoService.delete(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
