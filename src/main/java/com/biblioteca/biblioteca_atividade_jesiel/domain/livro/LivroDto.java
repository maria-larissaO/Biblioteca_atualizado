package com.biblioteca.biblioteca_atividade_jesiel.domain.livro;

import jakarta.validation.constraints.NotBlank;

public record LivroDto(@NotBlank String titulo, @NotBlank String autor, @NotBlank String edicao) {
    
}