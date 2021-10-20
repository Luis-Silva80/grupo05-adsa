package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(AlunoController.class));

    @Autowired
    private PerfilUsuarioRepository repository;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de usuarios administradores")
    public ResponseEntity adminAluno() {

        List<PerfilUsuario> usuarios = repository.findAll();

        if (usuarios.isEmpty()) {
            LOGGER.info("Nenhum usuario encontrado");
            return ResponseEntity.status(204).build();
        } else {
            LOGGER.info("Retornando lista de usuarios");
            return ResponseEntity.status(200).body(usuarios);
        }
    }

    @PostMapping()
    @ApiOperation(value = "Realiza o cadastro de um administrador")
    public ResponseEntity adminAluno(@RequestBody PerfilUsuario admin) {

        admin.setUsuarioAdmin(1);
        admin.setQtdLivrosLidos(0);
        admin.setPontos(0L);
        admin.setQtdResenhas(0);

        repository.save(admin);

        LOGGER.info("Aluno " + admin.getNome() + " cadastrado");
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{idAdmin}")
    @ApiOperation(value = "Retorna um administrador com ID especifico")
    public ResponseEntity exibeUsuarioAdmin(@PathVariable Integer idAdmin) {

        LOGGER.info("Retornando usuario desejado...");
        return ResponseEntity.of(repository.findById(idAdmin));

    }
}
