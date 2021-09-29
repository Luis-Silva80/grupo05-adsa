package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Instituicao;
import b.com.tothlibs.apitothlib.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoRepository repository;

    @GetMapping
    public @ResponseBody
    Iterable<Instituicao> getInstituicao() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody String postInstituicao(@RequestBody Instituicao instituicao) {
        repository.save(instituicao);
        return "Instituição cadastrada";
    }
}
