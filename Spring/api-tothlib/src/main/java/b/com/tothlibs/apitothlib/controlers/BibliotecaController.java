package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import b.com.tothlibs.apitothlib.services.UsuarioAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/biblioteca")
public class BibliotecaController {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(BibliotecaController.class));

    @Autowired
    private UsuarioAdmin admin;

    @Autowired
    private LivrosRepository repository;

    @Autowired
    private PerfilUsuarioRepository repositoryUsuario;

    @GetMapping
    public ResponseEntity getLivros() {

        List<Livros> livros = admin.consultaListaLivros();


        if (livros.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(livros);
        }

    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity postLivros(@PathVariable Integer idUsuario, @RequestBody Livros livros) {

        Integer isAdmin = repositoryUsuario.findAdminById(idUsuario);

        if (isAdmin.equals(1)) {

            if (admin.cadastrarLivro(livros)) {
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.status(400).build();
            }

        } else {
            return ResponseEntity.status(404).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity putLivro(@PathVariable int id,
                                   @RequestBody Livros livroAtualizado) {

        if (admin.alterarLivro(id, livroAtualizado)) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteLivro(@PathVariable Integer id) {

        if (admin.excluirLivro(id)) {
            return ResponseEntity.status(205).build();
        } else {
            return ResponseEntity.status(400).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity buscarLivro(@PathVariable Integer id){

        Livros livro = admin.buscarLivro(id);

        if(livro != null){
            return ResponseEntity.status(200).body(livro);
        }else {
            return ResponseEntity.status(404).build();
        }

    }

    @PutMapping("reservar/{idLivro}/{idUsuario}")
    public ResponseEntity reservarLivro(@PathVariable Integer idLivro,@PathVariable Integer idUsuario){

        if(admin.reservar(idLivro,idUsuario)){

            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(400).build();
        }


    }


}
