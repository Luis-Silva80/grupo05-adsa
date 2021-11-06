package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.Exceptions.UsuarioNaoEncontradoException;
import b.com.tothlibs.apitothlib.dto.UsuarioInfo;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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

    @Autowired
    private HistoricoRepository repositoryHistorico;

    @Autowired
    private LivrosRepository repositoryLivro;

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
    public ResponseEntity postAluno(@RequestBody PerfilUsuario novoAluno) throws UsuarioNaoEncontradoException {

        novoAluno.setUsuarioAdmin(0);
        novoAluno.setQtdLivrosLidos(0);
        novoAluno.setPontos(0L);
        novoAluno.setQtdResenhas(0);
        novoAluno.setLivrosReservados(0);

        repository.save(novoAluno);

        LOGGER.info("Aluno " + novoAluno.getNome() + " cadastrado");
        return ResponseEntity.status(201).build();

    }

    @GetMapping("/{idUsuario}")
    @ApiOperation(value = "Retorna um usuario com ID especifico")
    public ResponseEntity exibeUsuarioAdmin(@PathVariable Integer idUsuario) {

        PerfilUsuario usuario = repository.findById(idUsuario).get();

        List<Integer> listId = repositoryHistorico.findFkLivrosByIdUsuario(idUsuario);
        UsuarioInfo usuarioInfo = new UsuarioInfo(usuario);

        for (Integer i : listId){
            Livros livro = repositoryLivro.findById(i).get();
            if(!containsName(usuarioInfo.getLivrosLidos(), livro.getId())){
                usuarioInfo.getLivrosLidos().add(livro);
            }
        }
        LOGGER.info("Retornando usuario desejado...");

        if(usuarioInfo != null){
            return ResponseEntity.status(200).body(usuarioInfo);
        }else {
            return ResponseEntity.status(404).build();
        }

    }

    @PutMapping("/{idAluno}")
    public ResponseEntity putAluno(@PathVariable Integer idAluno, @RequestBody PerfilUsuario usuarioAlterado){

        if(repository.existsById(idAluno)){

            usuarioAlterado.setId(idAluno);

            repository.save(usuarioAlterado);

            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();

    }

    public boolean containsName(final List<Livros> list, final Integer id){
        return list.stream().map(Livros::getId).filter(id::equals).findFirst().isPresent();
    }
}
