package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/livros")
@Api(value = "API REST")
@CrossOrigin(origins = "*")
public class BibliotecaController {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(BibliotecaController.class));

    @Autowired
    private LivrosRepository repository;

    @Autowired
    private PerfilUsuarioRepository repositoryUsuario;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de livros")
    public @ResponseBody
    Iterable<Livros> getLivros() {
        return repository.findAll();
    }

    @PostMapping("/{idLivro}")
    public @ResponseBody
    @ApiOperation(value = "Realiza um cadastro de um livro")
    Boolean postLivros(@PathVariable Integer idUsuario, @RequestBody Livros idLivro) {

        Integer admin = repositoryUsuario.findAdminById(idUsuario);

        if (admin.equals(1)) {

            idLivro.setQtdResenhas(0);
            idLivro.setQtdReservas(0);
            idLivro.setQtdReservadoAgora(0);
            idLivro.setStatusLivro("disponivel");

            repository.save(idLivro);
            return true;

        }

        return false;

    }

    @GetMapping("/{idLivro}")
    @ApiOperation(value = "Retorna um usuario por um ID especifico")
    public ResponseEntity exibeUsuarioAdmin(@PathVariable Integer idLivro){

        LOGGER.info("Retornando usuario desejado...");
        return ResponseEntity.of(repository.findById(idLivro));

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Realiza uma alteração no cadastro de livros")
    public ResponseEntity alteraLivro(@PathVariable Integer id, @RequestBody Livros livroAtualizado){
        if (repository.existsById(id)) {
            livroAtualizado.setId(id);
            repository.save(livroAtualizado);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove um livro pelo indice")
    public ResponseEntity deletaPorId(@PathVariable Integer id){

        repository.deleteById(id);

        if(repository.existsById(id)){
            return ResponseEntity.status(400).build();
        }else {
            return ResponseEntity.status(200).build();
        }

    }
}
