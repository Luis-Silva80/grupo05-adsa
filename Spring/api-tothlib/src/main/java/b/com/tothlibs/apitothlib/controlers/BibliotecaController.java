package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity getLivros() {

        List<Livros> livros = repository.findAll();

        if(livros.isEmpty()){
            return ResponseEntity.status(204).build();
        }else {
            return ResponseEntity.status(200).body(livros);
        }

    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity postLivros(@PathVariable Integer idUsuario, @RequestBody Livros livros) {

        Integer admin = repositoryUsuario.findAdminById(idUsuario);

        if (admin.equals(1)) {

            livros.setQtdResenhas(0);
            livros.setQtdReservas(0);
            livros.setQtdReservadoAgora(0);
            livros.setStatusLivro("disponivel");

            repository.save(livros);

            return ResponseEntity.status(201).build();
        }else {
            return ResponseEntity.status(404).build();
        }


    }


}
