package com.ifce.loja.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record ProdutoRequestDTO (
        @NotBlank
        String nome,
        @DecimalMin(value = "0.0")
        double preco,
        @NotBlank
        String categoria
) {
}
