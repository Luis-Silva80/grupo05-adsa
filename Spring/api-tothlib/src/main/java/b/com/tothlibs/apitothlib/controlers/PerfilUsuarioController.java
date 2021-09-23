package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/aluno")
public class PerfilUsuarioController {

    @Autowired
    private PerfilUsuarioRepository repository;

    @GetMapping
    public @ResponseBody
    Iterable<PerfilUsuario> getAluno() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody String postAluno(@RequestBody PerfilUsuario aluno) {
        repository.save(aluno);
        return "Aluno cadastrado";
    }
}
