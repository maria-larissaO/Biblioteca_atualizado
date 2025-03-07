package com.biblioteca.biblioteca_atividade_jesiel.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.biblioteca_atividade_jesiel.domain.usuario.UsuarioRepository;
import com.biblioteca.biblioteca_atividade_jesiel.domain.usuario.Usuario;
import com.biblioteca.biblioteca_atividade_jesiel.domain.usuario.UsuarioDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //CRUD - create. read, update, delete
    //criação de usuario
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioDto dados){
        Usuario usuarioCriado = new Usuario(dados);

        this.usuarioRepository.save(usuarioCriado);

        return ResponseEntity.ok(usuarioCriado);
    }

    //seleção de usuario
    @GetMapping
    public ResponseEntity<List<Usuario>> selecionaTodosOsUsuarios(){
        List<Usuario> usuarios = this.usuarioRepository.findAll();

        return ResponseEntity.ok(usuarios);
    }

    //seleção por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> selecionaUsuarioPorId(@PathVariable UUID id){
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }
    //atualização de usuario
    // put -> atualiza o usuario por inteiro
    // patch -> atualizar campo especifico do usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioDto dados){
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    //deleção de usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable UUID id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        this.usuarioRepository.delete(usuario);

        return ResponseEntity.ok("Usuario" + usuario.getNome() + "Deletado com sucesso");
    }
}
