package com.biblioteca.biblioteca_atividade_jesiel.controller;

import com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo.Emprestimo;
import com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @PostMapping
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody Emprestimo emprestimo) {
        Emprestimo emprestimoCriado = this.emprestimoRepository.save(emprestimo);

        return ResponseEntity.ok(emprestimoCriado);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarEmprestimos() {
        List<Emprestimo> emprestimos = this.emprestimoRepository.findAll();

        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarEmprestimoPorId(@PathVariable UUID id) {
        Emprestimo emprestimo = this.emprestimoRepository.findById(id).orElse(null);

        if (emprestimo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(emprestimo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable UUID id, @RequestBody Emprestimo emprestimoAtualizado) {
        Emprestimo emprestimo = this.emprestimoRepository.findById(id).orElse(null);

        if (emprestimo == null) {
            return ResponseEntity.notFound().build();
        }

        emprestimo.setUsuario(emprestimoAtualizado.getUsuario());
        emprestimo.setLivro(emprestimoAtualizado.getLivro());
        emprestimo.setDataEmprestimo(emprestimoAtualizado.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoAtualizado.getDataDevolucao());

        this.emprestimoRepository.save(emprestimo);

        return ResponseEntity.ok(emprestimo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEmprestimo(@PathVariable UUID id) {
        Emprestimo emprestimo = this.emprestimoRepository.findById(id).orElse(null);

        if (emprestimo == null) {
            return ResponseEntity.notFound().build();
        }

        this.emprestimoRepository.delete(emprestimo);
        
        return ResponseEntity.ok("Empréstimo deletado com sucesso");
    }
}