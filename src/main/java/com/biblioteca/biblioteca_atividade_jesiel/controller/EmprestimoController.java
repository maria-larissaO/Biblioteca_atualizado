package com.biblioteca.biblioteca_atividade_jesiel.controller;

import com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo.Emprestimo;
import com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.biblioteca.biblioteca_atividade_jesiel.service.NotificationService;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private NotificationService notificationService;

    
    @PostMapping
    public ResponseEntity<?> criarEmprestimo(@RequestBody Emprestimo emprestimo) {
        if (emprestimo.getLivro() == null || emprestimo.getLivro().getId() == null) {
            return ResponseEntity.badRequest().body("Livro é obrigatório.");
        }

        if (emprestimo.getUsuario() == null || emprestimo.getUsuario().getId() == null) {
            return ResponseEntity.badRequest().body("Usuário é obrigatório.");
        }

        if (emprestimo.getDataDevolucao() == null || emprestimo.getDataDevolucao().isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Data de devolução inválida. Deve ser uma data futura.");
        }

        emprestimo.setDataEmprestimo(LocalDate.now());

        Emprestimo emprestimoCriado = this.emprestimoRepository.save(emprestimo);
        notificationService.notificarEmprestimoCriado(emprestimoCriado);

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

    //@GetMapping("/usuario/{usuarioId}")
    //public ResponseEntity<List<Emprestimo>> listarEmprestimosPorUsuario(@PathVariable UUID usuarioId) {
     //   List<Emprestimo> emprestimos = this.emprestimoRepository.findByUsuarioId(usuarioId);

       // return ResponseEntity.ok(emprestimos);
    //}

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

/*
 * - **Criar um empréstimo** → `POST /emprestimos`
- **Listar todos os empréstimos** → `GET /emprestimos`
- **Buscar empréstimo por ID** → `GET /emprestimos/{id}`
- **Listar empréstimos de um usuário específico** → `GET /emprestimos/usuario/{usuarioId}`
- **Atualizar empréstimo** → `PUT /emprestimos/{id}`
- **Deletar empréstimo** → `DELETE /emprestimos/{id}`
 */