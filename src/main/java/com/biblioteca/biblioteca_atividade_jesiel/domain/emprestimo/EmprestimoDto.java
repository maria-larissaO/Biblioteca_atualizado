package com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record EmprestimoDto(@NotNull UUID usuarioId, @NotNull UUID livroId, @NotNull LocalDate dataEmprestimo, LocalDate dataDevolucao) {
    
}