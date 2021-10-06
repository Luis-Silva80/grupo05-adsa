package com.conexao.testebancoduastabelas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class InstituicaoController {

        @Autowired
        private TbinstituicaoRepository repository;

        @GetMapping
        public @ResponseBody
        Iterable<Tbinstituicao> getInstituicao() {
            return repository.findAll();
        }

        @PostMapping
        public @ResponseBody String postNome(@RequestBody Tbinstituicao instituicao) {
                repository.save(instituicao);
                return "Instituição cadastrada";
        }
}
