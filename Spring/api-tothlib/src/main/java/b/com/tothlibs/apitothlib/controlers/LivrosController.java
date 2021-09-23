package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivrosController {

    @Autowired
    private LivrosRepository repository;

    @GetMapping
    public @ResponseBody
    Iterable<Livros> getAluno() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody String postAluno(@RequestBody Livros livros) {
        repository.save(livros);
        return "Livro cadastrado";
    }
}
