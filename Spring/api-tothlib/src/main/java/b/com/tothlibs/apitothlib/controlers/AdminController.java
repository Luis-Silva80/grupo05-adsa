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
import java.util.stream.Collectors;

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

        List<PerfilUsuario> usuariosAdmin = repository.findAdmin();

        if (usuariosAdmin.isEmpty()) {
            LOGGER.info("Nenhum usuario encontrado");
            return ResponseEntity.status(204).build();
        } else {
            LOGGER.info("Retornando lista de usuarios");
            return ResponseEntity.status(200).body(usuariosAdmin);
        }
    }

    @PostMapping()
    @ApiOperation(value = "Realiza o cadastro de um administrador")
    public ResponseEntity postAluno(@RequestBody PerfilUsuario novoAdmin) {

        novoAdmin.setUsuarioAdmin(1);
        novoAdmin.setQtdLivrosLidos(0);
        novoAdmin.setPontos(0L);
        novoAdmin.setQtdResenhas(0);
        novoAdmin.setLivrosReservados(0);
        novoAdmin.setFkTbInstituicao(2);
        novoAdmin.setStatusAtivo(true);

        repository.save(novoAdmin);

        LOGGER.info("Aluno " + novoAdmin.getNome() + " cadastrado");
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{idAdmin}")
    @ApiOperation(value = "Retorna um administrador com ID especifico")
    public ResponseEntity exibeUsuarioAdmin(@PathVariable Integer idAdmin) {

        PerfilUsuario usuario = repository.findById(idAdmin).get();

        List<Integer> listId = repositoryHistorico.findLivrosByUser(idAdmin);

        UsuarioInfo usuarioInfo = new UsuarioInfo(usuario);

        for(Integer l : listId){
            usuarioInfo.getLivrosLidos().add(repositoryLivro.findById(l).get());
        }

        LOGGER.info("Retornando usuario desejado...");

        if(usuarioInfo != null){
            if(usuario.getUsuarioAdmin().equals(1)){
                return ResponseEntity.status(200).body(usuarioInfo);
            }else {
                return ResponseEntity.status(204).build();
            }
        }else {
            return ResponseEntity.status(404).build();
        }

    }
}
