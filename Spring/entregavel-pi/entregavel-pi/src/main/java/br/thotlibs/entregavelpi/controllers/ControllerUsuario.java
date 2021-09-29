package br.thotlibs.entregavelpi.controllers;

import br.thotlibs.entregavelpi.dto.UsuarioDto;
import br.thotlibs.entregavelpi.entity.Livro;
import br.thotlibs.entregavelpi.entity.Usuario;
import br.thotlibs.entregavelpi.entity.UsuarioAdmin;
import org.springframework.web.bind.annotation.*;

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
                    u.setLogoff();


                } else {
                    retorno = String.format("Usuário %s não autenticado", u.getNome());
                }
            }
        }

        return retorno;

    }

    @PostMapping("/cadastrarLivro/{id}")
    public String cadastrarLivro(@PathVariable Integer id, @RequestBody Livro livro) {

        String retorno = "";

        for (Usuario u : listUsuarios) {
            if (u.getId().equals(id)) {
                if (u.getAdmin()) {
                    listLivros.add(livro);
                    livro.setDisponivel(true);
                    retorno = String.format("Livro: %s cadastrado com sucesso!!", livro.getTitulo());

                } else {
                    retorno = "Somente os administradores podem cadastrar livros!!";
                }
            }
        }

        return retorno;

    }

    @GetMapping("/consultarListaLivros")
    public List<Livro> cadastrarLivro() {

        return listLivros;

    }

    @PostMapping("/reservarLivro/{idUsuario}/{idLivro}")
    public String reservarLivro(@PathVariable Integer idUsuario,@PathVariable Integer idLivro) {

        String retorno = "";

        Livro alugado = buscarLivro(idLivro);

        for (Usuario usuario : listUsuarios) {
            if (usuario.getId().equals(idUsuario)) {

                for(Livro livroAlugado : listLivros){
                    if(livroAlugado.getId().equals(idLivro)){
                        livroAlugado.setDisponivel(false);
                    }
                }

                alugado.setDisponivel(false);
                usuario.setListLivros(alugado);
                usuario.LocarLivro(idLivro, idUsuario);
                retorno = "Livro reservado com sucesso!!";
            }else {
                retorno = "Não foi possivel reservar o livro!!";
            }
        }

        return retorno;

    }

    @GetMapping("buscarLivro/{id}")
    public Livro buscarLivro(@PathVariable Integer id) {

        Livro livro = new Livro();


//        return listLivros.stream().filter(livro1 -> livro1.getId().equals(id)).collect(Collectors.toList());


        for (Livro l : listLivros) {
            if (l.getId().equals(id)) {
                livro.setId(l.getId());
                livro.setAutor(l.getAutor());
                livro.setTitulo(l.getTitulo());
                livro.setDisponivel(l.getDisponivel());
            }
        }

        return livro;

    }


}
