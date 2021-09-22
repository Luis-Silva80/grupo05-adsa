package br.thotlibs.entregavelpi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class ControllerLivro {

    @Autowired
    private LivroRepository repository;

    @PostMapping
    public String cadastrarLivro(@RequestBody LivroBanco novoLivro){

        repository.save(novoLivro);

        return String.format("Livro %s cadastrado com sucesso!!", novoLivro.getTitulo());

    }

    @GetMapping
    public List<LivroBanco> exibirLivros(){
        return repository.findAll();
    }

}
