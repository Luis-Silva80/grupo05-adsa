package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.Exceptions.UsuarioNaoEncontradoException;
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
@RequestMapping("/aluno")
public class AlunoController {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(AlunoController.class));

    @Autowired
    private PerfilUsuarioRepository repository;

    @GetMapping
    @ApiOperation(value = "Retorna a lista de alunos cadastrados")
    public ResponseEntity getAluno() {
        List<PerfilUsuario> alunos = repository.findAlunos();

        if (alunos.isEmpty()) {

            LOGGER.info("Nenhum aluno encontrado");
            return ResponseEntity.status(204).build();

        } else {

            LOGGER.info("Retornando lista de usuarios");
            return ResponseEntity.status(200).body(alunos);
        }
    }

    @PostMapping()
    @ApiOperation(value = "Realiza o cadastro de um novo aluno")
    public ResponseEntity postAluno(@RequestBody PerfilUsuario aluno) throws UsuarioNaoEncontradoException {

        aluno.setUsuarioAdmin(0);
        aluno.setQtdLivrosLidos(0);
        aluno.setPontos(0L);
        aluno.setQtdResenhas(0);

        repository.save(aluno);

        LOGGER.info("Aluno " + aluno.getNome() + " cadastrado");
        return ResponseEntity.status(201).build();

    }

    @GetMapping("/{idUsuario}")
    @ApiOperation(value = "Retorna um usuario com ID especifico")
    public ResponseEntity exibeUsuarioAdmin(@PathVariable Integer idUsuario) {

        LOGGER.info("Retornando usuario desejado...");
        return ResponseEntity.of(repository.findById(idUsuario));

    }
}
