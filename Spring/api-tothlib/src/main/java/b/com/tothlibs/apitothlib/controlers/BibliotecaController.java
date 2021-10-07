package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/livros")
public class BibliotecaController {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(BibliotecaController.class));

    @Autowired
    private LivrosRepository repository;

    @Autowired
    private PerfilUsuarioRepository repositoryUsuario;

    @GetMapping
    public @ResponseBody
    Iterable<Livros> getLivros() {
        return repository.findAll();
    }

    @PostMapping("/{idUsuario}")
    public @ResponseBody
    Boolean postLivros(@PathVariable Integer idUsuario, @RequestBody Livros livros) {

        Integer admin = repositoryUsuario.findAdminById(idUsuario);

        if (admin.equals(1)) {

            livros.setQtdResenhas(0);
            livros.setQtdReservas(0);
            livros.setQtdReservadoAgora(0);
            livros.setStatusLivro("disponivel");

            repository.save(livros);
            return true;

        }

        return false;

    }
}
