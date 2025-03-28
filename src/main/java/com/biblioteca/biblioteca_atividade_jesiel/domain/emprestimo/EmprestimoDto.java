// package com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo;

// import jakarta.validation.constraints.NotNull;

// import java.time.LocalDate;
// import java.util.UUID;

// public record EmprestimoDto(@NotNull Usuario usuario, @NotNull Livro livro, @NotNull LocalDate dataEmprestimo, LocalDate dataDevolucao) {
    
//}

package com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo;

import com.biblioteca.biblioteca_atividade_jesiel.domain.livro.Livro;
import com.biblioteca.biblioteca_atividade_jesiel.domain.usuario.Usuario;

import java.time.LocalDate;

public record EmprestimoDto(
    Usuario usuario,
    Livro livro,
    LocalDate dataEmprestimo,
    LocalDate dataDevolucao
) {}

