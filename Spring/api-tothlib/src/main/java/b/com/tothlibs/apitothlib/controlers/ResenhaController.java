package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.Resenha;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.ResenhaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resenha")
public class ResenhaController {

    @Autowired
    private ResenhaRepository repository;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de resenhas publicadas")
    public ResponseEntity verificarResenhas() {

        List<Resenha> listaResenhas = repository.findAll();
        return ResponseEntity.status(200).body(listaResenhas);

    }

    @PostMapping
    @ApiOperation(value = "Realiza a publicação de uma nova resenha")
    public ResponseEntity postAluno(@RequestBody Resenha resenha) {

        repository.save(resenha);
        return ResponseEntity.status(201).build();

    }
}
