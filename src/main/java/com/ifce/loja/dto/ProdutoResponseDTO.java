package com.ifce.loja.dto;

import com.ifce.loja.model.Produto;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        double preco,
        String categoria
) {
    public ProdutoResponseDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria()
        );
    }
}
