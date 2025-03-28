//(UsuarioController) em uma aplicação Spring Boot, responsável por gerenciar as operações CRUD (Create, Read, Update, Delete) para a entidade Usuario

package com.biblioteca.biblioteca_atividade_jesiel.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity; //Usado para retornar respostas HTTP personalizadas.
import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.biblioteca_atividade_jesiel.domain.usuario.UsuarioRepository;
import com.biblioteca.biblioteca_atividade_jesiel.domain.usuario.Usuario;
import com.biblioteca.biblioteca_atividade_jesiel.domain.usuario.UsuarioDto;
import jakarta.validation.Valid; //Usado para validar os dados recebidos no corpo da requisição.
import java.util.List; //Para retornar listas de usuários.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; //CAPTURA VALORES DA URL (por exemplo, /usuarios/123).
import java.util.UUID; //Para trabalhar com identificadores únicos.
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

    //Definição do Controlador
@RestController //indica que esta classe é um controlador REST, ou seja, ela lida com requisições HTTP e retorna dados no formato JSON ou XML.
@RequestMapping("/usuarios") //define o caminho base(/usuarios) para todas as rotas definidas nesta classe.
public class UsuarioController {
    @Autowired //Usado para injetar dependências automaticamente (neste caso, o UsuarioRepository).
    private UsuarioRepository usuarioRepository; //declara um campo p acessar o repositorio de usuarios, que é usado p interagir cm o BD.

    //CRUD - create. read, update, delete
    //criação de usuario
    @PostMapping //mapeia requisições HTTP POST p este método
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioDto dados){ //recebe os dados do usuario no corpo da requisição @RequestBody e valida esses dados usando a anotação @Valid.
        Usuario usuarioCriado = new Usuario(dados); //cria um novo objeto Usuario a partir dos dados recebidos.

        this.usuarioRepository.save(usuarioCriado); //salva o novo usuario no BD.

        return ResponseEntity.ok(usuarioCriado); //retorna o usuario criado com o status HTTP 200 OK.
    }

    //seleção de usuario
    @GetMapping //mapeia requisições HTTP GET p este método
    public ResponseEntity<List<Usuario>> selecionaTodosOsUsuarios(){
        List<Usuario> usuarios = this.usuarioRepository.findAll(); //this.usuarioRepository.findAll() - busca todos os usuarios no BD

        return ResponseEntity.ok(usuarios); //retorna a lista de usuarios com o status HTTP 200 OK.
    }

    //seleção por id
    @GetMapping("/{id}") //Mapeia requisições HTTP GET com um ID na URL (por exemplo, /usuarios/123).
    public ResponseEntity<Usuario> selecionaUsuarioPorId(@PathVariable UUID id){ //@PathVariable UUID id - captura o ID da URL e converte para o tipo UUID.
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null); //busca o usuario pelo ID. Se não encontrar, retorna null.

        if (usuario == null) { //Verifica se o usuario foi encontrado.
            return ResponseEntity.notFound().build(); //retorna o status HTTP 404 (Not Found) se o usuario não existir.
        }

        return ResponseEntity.ok(usuario); //retorna o usuario encontrado com o status HTTP 200 OK.
    }
    //atualização de usuario
    // put -> atualiza o usuario por inteiro
    // patch -> atualizar campo especifico do usuario
    @PutMapping("/{id}") //mapeia requisições HTTP PUT com ID na URL
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioDto dados){ //@PathVariable UUID id: captura o ID da URL. Já @RequestBody UsuarioDto dados: captura os novos dados do usuario non corpo da requisição
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null); //busca o usuario pelo ID.

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuario.setNome(dados.nome()); //atualiza os campos do usuario.
        usuario.setEmail(dados.email()); //atualiza os campos do usuario.

        this.usuarioRepository.save(usuario); //salva as alterações no BD.

        return ResponseEntity.ok(usuario); //retorna o usuario atualizado com o status HTTP 200 OK.
    }

    //deleção de usuario
    @DeleteMapping("/{id}") //mapeia as requisições HTTP DELETE com ID na URL
    public ResponseEntity<String> deletarUsuario(@PathVariable UUID id) { //@PathVariable UUID id: captura o ID da URL.
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null); //busca o usuario pelo ID.

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        this.usuarioRepository.delete(usuario); //remove o usuario do BD.

        return ResponseEntity.ok("Usuario" + usuario.getNome() + "Deletado com sucesso"); //retorna uma mensagem de sucesso com o status HTTP 200 OK.
    }
}
