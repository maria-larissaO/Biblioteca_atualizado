package com.biblioteca.biblioteca_atividade_jesiel.domain.usuario;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository; //Uma interface do Spring Data JPA que fornece métodos prontos para operações de banco de dados, como salvar, atualizar, deletar e consultar entidades.

//definição da interface
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

}
/*
 * public interface UsuarioRepository: Define uma interface pública chamada UsuarioRepository.
 
 * extends JpaRepository<Usuario, UUID>: Indica que a interface UsuarioRepository estende (herda) 
 * a interface JpaRepository. O JpaRepository é uma interface genérica que fornece métodos prontos 
 * para operações de banco de dados.
 */

 //Usuario: É o tipo da entidade que o repositório gerencia (neste caso, a entidade Usuario).
 //UUID: É o tipo da chave primária da entidade Usuario (neste caso, o id da entidade é do tipo UUID)

 /* {} -  O corpo da interface está vazio porque o JpaRepository já fornece todos os métodos necessários para operações de banco de dados, como:
    save(): Salva ou atualiza uma entidade.
    findById(): Busca uma entidade pelo seu ID.
    findAll(): Retorna todas as entidades.
    delete(): Remove uma entidade.
  */
