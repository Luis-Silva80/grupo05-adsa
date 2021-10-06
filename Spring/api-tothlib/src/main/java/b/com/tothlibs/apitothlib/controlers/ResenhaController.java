package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.Resenha;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.ResenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/resenha")
public class ResenhaController {

    @Autowired
    private ResenhaRepository repository;

    @GetMapping
    public @ResponseBody
    Iterable<Resenha> getAluno() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody String postAluno(@RequestBody Resenha resenha) {
        repository.save(resenha);
        return "Resenha compartilhada";
    }
}
