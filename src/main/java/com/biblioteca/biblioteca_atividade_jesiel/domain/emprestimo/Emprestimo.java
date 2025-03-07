package com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo;

import com.biblioteca.biblioteca_atividade_jesiel.domain.livro.Livro;
import com.biblioteca.biblioteca_atividade_jesiel.domain.usuario.Usuario;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Livro livro;

    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @Column
    private LocalDate dataDevolucao;

   
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(){
    }

    public Emprestimo(UUID id, Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao){
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(EmprestimoDto emprestimoDto){
        this.usuario = emprestimoDto.usuario();
        this.livro = emprestimoDto.livro();
        this.dataEmprestimo = emprestimoDto.dataEmprestimo();
        this.dataDevolucao = emprestimoDto.dataDevolucao();
    }
}