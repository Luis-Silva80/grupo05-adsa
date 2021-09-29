package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Categoria;
import b.com.tothlibs.apitothlib.entity.Historico;
import b.com.tothlibs.apitothlib.repository.CategoriaRepository;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/historico")
public class HistoricoController {
    @Autowired
    private HistoricoRepository repository;

    @GetMapping
    public @ResponseBody
    Iterable<Historico> getInstituicao() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody String postInstituicao(@RequestBody Historico historico) {
        repository.save(historico);
        return "Histórico cadastrado";
    }
}
