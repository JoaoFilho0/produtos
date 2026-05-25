package com.ifce.loja.service;

import com.ifce.loja.dto.ProdutoRequestDTO;
import com.ifce.loja.model.Produto;
import com.ifce.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto add(ProdutoRequestDTO produtoDTO) {
        Produto produto = new Produto(produtoDTO);

        return this.produtoRepository.save(produto);
    }

    public List<Produto> listarTodosProdutos() {
        return this.produtoRepository.findAll();
    }

    public Optional<Produto> pegarProdutoPorID(Long id) {
        return this.produtoRepository.findById(id);
    }

    public Produto update(Long id, ProdutoRequestDTO produtoAtualizado) {
        Optional<Produto> produtoOptional = this.pegarProdutoPorID(id);

        if (produtoOptional.isPresent()) {
            Produto produtoAtual = produtoOptional.get();

            produtoAtual.setNome(produtoAtualizado.nome());
            produtoAtual.setPreco(produtoAtualizado.preco());
            produtoAtual.setCategoria(produtoAtualizado.categoria());

            return produtoRepository.save(produtoAtual);
        }

        return null;
    }

    public void delete(Long id) {
        this.produtoRepository.deleteById(id);
    }
}
