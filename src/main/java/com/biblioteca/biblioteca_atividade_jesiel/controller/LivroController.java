package com.biblioteca.biblioteca_atividade_jesiel.controller;

import com.biblioteca.biblioteca_atividade_jesiel.domain.livro.Livro;
import com.biblioteca.biblioteca_atividade_jesiel.domain.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody Livro livro) {
        Livro livroCriado = livroRepository.save(livro);

        return ResponseEntity.ok(livroCriado);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = this.livroRepository.findAll();

        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable UUID id) {
        Livro livro = this.livroRepository.findById(id).orElse(null);

        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(livro);
    }

    //@GetMapping("/disponiveis")
    //@GetMapping("/indisponiveis")
    

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable UUID id, @RequestBody Livro livroAtualizado) {
        Livro livro = this.livroRepository.findById(id).orElse(null);

        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setEdicao(livroAtualizado.getEdicao());

        this.livroRepository.save(livro);

        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable UUID id) {
        Livro livro = this.livroRepository.findById(id).orElse(null);

        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        this.livroRepository.delete(livro);
        
        return ResponseEntity.ok("Livro deletado com sucesso");
    }
}

/*
 * - **Criar um livro** → `POST /livros`
- **Listar todos os livros** → `GET /livros`
- **Buscar livro por ID** → `GET /livros/{id}`
- **Listar apenas livros disponíveis** → `GET /livros/disponiveis`
- **Listar apenas livros indisponíveis** → `GET /livros/indisponiveis`
- **Atualizar livro** → `PUT /livros/{id}`
- **Deletar livro** → `DELETE /livros/{id}`
 */