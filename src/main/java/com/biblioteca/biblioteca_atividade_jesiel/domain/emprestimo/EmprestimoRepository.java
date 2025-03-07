package com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID> {
    
}