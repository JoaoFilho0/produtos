package com.ifce.loja.controller;

import com.ifce.loja.model.Produto;
import com.ifce.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public Produto addProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto pegarProdutoPorID(@PathVariable Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            Produto produtoAtual = produtoOptional.get();

            produtoAtual.setNome(produtoAtualizado.getNome());
            produtoAtual.setPreco(produtoAtualizado.getPreco());
            produtoAtual.setCategoria(produtoAtualizado.getCategoria());

            return produtoRepository.save(produtoAtual);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "Produto deletado com sucesso";
    }
}
