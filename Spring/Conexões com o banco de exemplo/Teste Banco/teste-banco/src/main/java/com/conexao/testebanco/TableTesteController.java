package com.conexao.testebanco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nomes")
public class TableTesteController {

    @Autowired
    private TabletesteRepository repository;

    @GetMapping
    public @ResponseBody Iterable<Tableteste> getNome() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody
    String postNome(@RequestBody Tableteste usuario) {
        repository.save(usuario);
        return "Usuário cadastrado";
    }
}
