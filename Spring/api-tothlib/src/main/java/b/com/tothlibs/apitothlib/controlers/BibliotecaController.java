package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Biblioteca;
import b.com.tothlibs.apitothlib.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaRepository repository;

    @GetMapping
    public @ResponseBody
    Iterable<Biblioteca> getInstituicao() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody String postInstituicao(@RequestBody Biblioteca biblioteca) {
        repository.save(biblioteca);
        return "Biblioteca cadastrada";
    }
}
