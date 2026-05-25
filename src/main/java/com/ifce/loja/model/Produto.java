package com.ifce.loja.model;

import com.ifce.loja.dto.ProdutoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private double preco;

    private String categoria;

    public Produto(ProdutoRequestDTO produtoDTO) {
        this.nome = produtoDTO.nome();
        this.preco = produtoDTO.preco();
        this.categoria = produtoDTO.categoria();
    }
}
