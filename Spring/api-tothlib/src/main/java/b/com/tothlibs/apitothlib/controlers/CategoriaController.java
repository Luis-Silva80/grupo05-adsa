package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Categoria;
import b.com.tothlibs.apitothlib.entity.Instituicao;
import b.com.tothlibs.apitothlib.repository.CategoriaRepository;
import b.com.tothlibs.apitothlib.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public @ResponseBody
    Iterable<Categoria> getInstituicao() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody String postInstituicao(@RequestBody Categoria categoria) {
        repository.save(categoria);
        return "Categoria cadastrada";
    }
}
