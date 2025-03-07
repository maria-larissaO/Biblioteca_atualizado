package com.biblioteca.biblioteca_atividade_jesiel.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(@NotBlank String nome, @NotBlank @Email String email) {

}