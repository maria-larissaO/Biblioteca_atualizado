package com.biblioteca.biblioteca_atividade_jesiel.domain.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    
}