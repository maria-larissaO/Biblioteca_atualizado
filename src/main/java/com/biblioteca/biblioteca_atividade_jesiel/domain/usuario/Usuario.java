package com.biblioteca.biblioteca_atividade_jesiel.domain.usuario;

//importam classes e anotações para o fucionamento do código
import java.util.UUID; //classe que representa um identificadir único universal UUID
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// jakarta.persistence é uma API que é usada para mapear a classe usuario para uma tabela no banco de dados

//Definição da classe
@Entity // esta anotação indica que a classe Usuario é uma entidade JPA, ou seja, ela será mapeada para uma tabela no BD
public class Usuario { //Define a classe Usuario como pública, ou seja, pode ser acessada de qualquer outro lugar no código
    
    //Atributos da classe
    @Id //indica que o atributo id é a chave primária da entidade 
    @GeneratedValue(strategy = GenerationType.AUTO) //especifica que o valor do id será gerado automaticamente pelo BD. A estratégia GenerationType.AUTO permite que o provedor de persistência escolha a melhor estratégia para gerar o valor. 
    private UUID id; //declara um atributo privado do tipo UUID para armazenar o identificador único do usuario

    @Column(nullable = false) //indica que a coluna nome no BD não pode ser nula.
    private String nome; //declara um atributo privado do tipo String p/  armazenar o nome do usuário

    @Column(nullable = false, unique = true) //indica que a coluna email no BD não pode ser nula e deve ser única(não pode haver dois usuarios com o mesmo email).
    private String email; //declara um atributo privado do tipo String p/ armazenar o email do usuário.


    //Métodos Getters e Setters
    /*
     Esses métodos são usados para acessar (getters) e modificar (setters)
      os atributos privados da classe Usuario. Eles são parte do conceito 
      de encapsulamento em Java, que permite controlar o acesso aos dados
       de uma classe.
     */
    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    
    //Construtores
    public Usuario(){ //construtor padrão(sem parametros) - necessario p/ que o JPA possa instanciar objetos dessa classe
    }

    public Usuario(UUID id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(UsuarioDto usuarioDto){
        this.nome = usuarioDto.nome();
        this.email = usuarioDto.email();
    }
}
