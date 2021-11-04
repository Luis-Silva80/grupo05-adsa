package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.dto.UsuarioInfo;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
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

    @Autowired
    private HistoricoRepository repositoryHistorico;

    @Autowired
    private LivrosRepository repositoryLivro;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de usuarios administradores")
    public ResponseEntity getAluno() {

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
    public ResponseEntity postAluno(@RequestBody PerfilUsuario admin) {

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

        PerfilUsuario usuario = repository.findById(idAdmin).get();

        List<Integer> listId = repositoryHistorico.findFkLivrosByIdUsuario(idAdmin);
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

    public boolean containsName(final List<Livros> list, final Integer id){
        return list.stream().map(Livros::getId).filter(id::equals).findFirst().isPresent();
    }
}
