//UsuarioDto, é usado para transferir dados (DTO - Data Transfer Object) com validações básicas

package com.biblioteca.biblioteca_atividade_jesiel.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//NotBlank: Uma anotação que valida se uma String não é nula e não está em branco(ou seja, não é vazia e não contém apenas espaços em branco);
//Email: Anotação que valida se uma String está no formato de um email válido.
public record UsuarioDto(@NotBlank String nome, @NotBlank @Email String email) {

}
//nome: Um campo do tipo String que armazena o nome do usuário. A anotação @NotBlank garante que esse campo não pode ser nulo ou vazio.
//email: Um campo do tipo String que armazena o email do usuário. As anotações @NotBlank e @Email garantem que o campo não pode ser nulo, vazio, e deve estar no formato de um email válido

/*
{} - O corpo do record está vazio porque o record gera automaticamente todos os métodos necessários 
 (como construtores, getters, equals(), hashCode(), etc.) com base nos componentes declarados (nome e email).
 */