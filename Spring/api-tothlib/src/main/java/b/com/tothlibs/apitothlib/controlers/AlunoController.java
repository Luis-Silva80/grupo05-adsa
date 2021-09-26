package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.Exceptions.UsuarioNaoAdminException;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(AlunoController.class));

    @Autowired
    private PerfilUsuarioRepository repository;

    @GetMapping
    public @ResponseBody
    Iterable<PerfilUsuario> getAluno() {
        return repository.findAll();
    }

    @PostMapping("/{idAdmin}")
    public @ResponseBody Boolean postAluno(@PathVariable Integer idAdmin,@RequestBody PerfilUsuario aluno) throws UsuarioNaoAdminException {

        Integer admin = repository.findAdmin(idAdmin);

        String nome = aluno.getNome();

        if(admin.equals(1)){
            aluno.setUsuarioAdmin(0);
            aluno.setQtdLivrosLidos(0);
            aluno.setPontos(0L);
            aluno.setQtdResenhas(0);

            repository.save(aluno);

            LOGGER.info("Aluno " + aluno.getNome() + " cadastrado com sucesso!!");

            return true;

        }

        return false;
    }
}
