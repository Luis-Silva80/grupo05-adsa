package br.thotlibs.entregavelpi.controllers;

import br.thotlibs.entregavelpi.dto.UsuarioDto;
import br.thotlibs.entregavelpi.entity.Livro;
import br.thotlibs.entregavelpi.entity.Usuario;
import br.thotlibs.entregavelpi.entity.UsuarioAdmin;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("usuarios")
public class ControllerUsuario {

    List<Livro> listLivros = new ArrayList<Livro>();
    List<Usuario> listUsuarios = new ArrayList<Usuario>();
    List<UsuarioDto> listAutenticados = new ArrayList<UsuarioDto>();


    @PostMapping("/cadastroAdmin")
    public Usuario cadastrarAdmin(@RequestBody UsuarioAdmin usuario) {

        usuario.setAdmin(true);
        listUsuarios.add(usuario);
        return usuario;

    }


    @PostMapping("/cadastroAluno")
    public Usuario cadastrarAluno(@RequestBody UsuarioAdmin usuario) {

        usuario.setAdmin(false);
        listUsuarios.add(usuario);
        return usuario;

    }

    @GetMapping("/consultarListaUsuarios")
    public List<Usuario> consultarListaUsuarios() {

        return listUsuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(Collectors.toList());

    }

    @PostMapping("/autenticacao/{nome}/{senha}")
    public UsuarioDto autenticar(@PathVariable String nome, @PathVariable String senha) {

        UsuarioDto usuarioDto = null;

        for (Usuario u : listUsuarios) {
            if (u.getNome().equals(nome)) {
                if (u.getSenha().equals(senha)) {
                    usuarioDto = new UsuarioDto(u);
                    listAutenticados.add(usuarioDto);
                }

            }
        }
        return usuarioDto;

    }

    @GetMapping("/consultarListaAutenticados")
    public List<UsuarioDto> consultarAutenticados() {

        return listAutenticados.stream()
                .sorted(Comparator.comparing(UsuarioDto::getNome))
                .collect(Collectors.toList());

    }


    @DeleteMapping("/autenticacao/{nome}")
    public String logoff(@PathVariable String nome) {

        String retorno = "";

        for (UsuarioDto u : listAutenticados) {
            if (u.getNome().equals(nome)) {
                if (u.getAutenticado()) {



                    retorno = String.format("Usuário %s saiu do sistema", u.getNome());

                    u.setAutenticado(false);



                } else {
                    retorno = String.format("Usuário %s não autenticado", u.getNome());
                }
            }
        }

        return retorno;

    }

    @PostMapping("/cadastrarLivro/{id}")
    public String cadastrarLivro(@PathVariable Integer id, @RequestBody Livro livro){

        String retorno ="";

        for (Usuario u : listUsuarios){
            if (u.getId().equals(id)){
                if (u.getAdmin()){
                    listLivros.add(livro);
                    livro.setDisponivel(true);
                    retorno = String.format("Livro: %s cadastrado com sucesso!!", livro.getTitulo());

                }else {
                    retorno = "Somente os administradores podem cadastrar livros!!";
                }
            }
        }

        return retorno;

    }

    @GetMapping("/consultarListaLivros")
    public List<Livro> cadastrarLivro(){

        return listLivros;

    }


}
