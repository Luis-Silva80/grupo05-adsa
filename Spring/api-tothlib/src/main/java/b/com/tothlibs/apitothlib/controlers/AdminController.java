package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(AlunoController.class));

    @Autowired
    private PerfilUsuarioRepository repository;

    @GetMapping
    public @ResponseBody
    Iterable<PerfilUsuario> getAluno() {
        return repository.findAll();
    }

    @PostMapping()
    public @ResponseBody boolean postAluno(@RequestBody PerfilUsuario admin) {

        admin.setUsuarioAdmin(1);
        admin.setQtdLivrosLidos(0);
        admin.setPontos(0L);
        admin.setQtdResenhas(0);

        repository.save(admin);

        LOGGER.info("Admin " + admin.getNome() + " cadastrado com sucesso!!");

        return true;
    }

    @GetMapping("/{idUsuario}")
    public @ResponseBody Optional<PerfilUsuario> exibeUsuario(@PathVariable Integer idUsuario){

        return repository.findById(idUsuario);

    }
}
