package com.biblioteca.biblioteca_atividade_jesiel.domain.livro;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String edicao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Livro(){
    }

    public Livro(UUID id, String titulo, String autor, String edicao){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.edicao = edicao;
    }

    public Livro(LivroDto usuariodto){
        this.titulo = usuariodto.titulo();
        this.autor = usuariodto.autor();
        this.edicao = usuariodto.edicao();
    }
}
