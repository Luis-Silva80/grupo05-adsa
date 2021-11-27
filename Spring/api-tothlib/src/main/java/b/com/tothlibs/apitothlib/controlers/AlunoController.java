package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.Exceptions.UsuarioNaoEncontradoException;
import b.com.tothlibs.apitothlib.dto.UsuarioInfo;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.listas.PilhaObj;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(AlunoController.class));

    PilhaObj<PerfilUsuario> pilhaUsuariosDeletados;
    List<PerfilUsuario> listaTemporariaDeletados = new ArrayList<>();
    Integer qtdDeletados = 0;


    @Autowired
    private PerfilUsuarioRepository repository;

    @Autowired
    private HistoricoRepository repositoryHistorico;

    @Autowired
    private LivrosRepository repositoryLivro;

    @GetMapping
    @ApiOperation(value = "Retorna a lista de alunos cadastrados")
    public ResponseEntity getAluno() {
        List<PerfilUsuario> alunos = repository.findAlunos()
                .stream()
                .filter(usuario -> usuario.getUsuarioAdmin().equals(0))
                .collect(Collectors.toList());

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
        novoAluno.setFkTbInstituicao(2);
        novoAluno.setStatusAtivo(true);

        repository.save(novoAluno);

        LOGGER.info("Aluno " + novoAluno.getNome() + " cadastrado");
        return ResponseEntity.status(201).build();

    }

    @GetMapping("/{idUsuario}")
    @ApiOperation(value = "Retorna um usuario com ID especifico")
    public ResponseEntity exibeUsuarioAdmin(@PathVariable Integer idUsuario) {

        PerfilUsuario usuario = repository.findById(idUsuario).get();

        List<Integer> listId = repositoryHistorico.findLivrosByUser(idUsuario);
        UsuarioInfo usuarioInfo = new UsuarioInfo(usuario);

        for (Integer l : listId) {
            Livros livro = repositoryLivro.findById(l).get();
            if (!usuarioInfo.getLivrosLidos().contains(livro)) {
                usuarioInfo.getLivrosLidos().add(repositoryLivro.findById(l).get());
            }
        }

        LOGGER.info("Retornando usuario desejado...");

        if (usuarioInfo != null) {
            return ResponseEntity.status(200).body(usuarioInfo);
        } else {
            return ResponseEntity.status(404).build();
        }

    }

    @PutMapping("/{idAluno}")
    public ResponseEntity putAluno(@PathVariable Integer idAluno, @RequestBody PerfilUsuario usuarioAlterado) {

        if (repository.existsById(idAluno)) {

            usuarioAlterado.setId(idAluno);

            repository.save(usuarioAlterado);

            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();

    }

    @PatchMapping("/{idAluno}")
    @ApiOperation(value = "Remove um aluno pelo ID")
    public ResponseEntity deletaPorId(@PathVariable Integer idAluno) {

        PerfilUsuario alunoInativo = repository.findById(idAluno).get();


        if (alunoInativo != null) {

            alteraStatus(alunoInativo, "deletar");

            return ResponseEntity.status(200).body(alunoInativo);

        } else {

            return ResponseEntity.status(404).build();
        }

    }

    @PostMapping("/desfazer")
    @ApiOperation(value = "Desfaz a exclusão do ultimo aluno deletado")
    public ResponseEntity desfazerDelete() {

        System.out.println("-------------------");
        pilhaUsuariosDeletados = new PilhaObj(listaTemporariaDeletados.size());

        if (pilhaUsuariosDeletados.isEmpty()) {
            listaTemporariaDeletados
                    .stream()
                    .forEach(perfilUsuario -> pilhaUsuariosDeletados.push(perfilUsuario));
        }

        pilhaUsuariosDeletados.exibe();

        if (listaTemporariaDeletados.size() == 0) {

            return ResponseEntity.status(204).build();

        } else {

            System.out.println("removendo o topo da pilha" + pilhaUsuariosDeletados.peek());

            alteraStatus(pilhaUsuariosDeletados.peek(), "desfazer");

            listaTemporariaDeletados.remove(pilhaUsuariosDeletados.peek());

            pilhaUsuariosDeletados.pop();
            pilhaUsuariosDeletados.exibe();

            return ResponseEntity.status(200).build();

        }

    }


    @GetMapping("/deletaInativos")
    public ResponseEntity deletaInativos() {

        List<PerfilUsuario> listaDeUsuariosInativos = repository.findAlunosInativos();
        List<PerfilUsuario> listaDeDeletados = new ArrayList<>();


        listaDeUsuariosInativos
                .stream()
                .forEach(perfilUsuario -> verificarDataInativacao(perfilUsuario, listaDeDeletados));

        if (qtdDeletados == 0) {

            LOGGER.info("Nenhum usuário foi deletado esse dia!");

        } else {

            LOGGER.info("Quantidade de usuários deletados: " + qtdDeletados);

        }

        qtdDeletados = 0;

        return ResponseEntity.status(200).body(listaDeDeletados);

    }

    @PatchMapping("/alterar-nome/{idAluno}/{novoNome}")
    public ResponseEntity patchNome(@PathVariable Integer idAluno, @PathVariable String novoNome){

        PerfilUsuario alunoComNome = repository.findById(idAluno).get();

        alunoComNome.setNome(novoNome);

        repository.save(alunoComNome);

        return ResponseEntity.status(200).build();

    }

    public void verificarDataInativacao(PerfilUsuario p,
                                        List<PerfilUsuario> listaDeDeletados) {


        if (LocalDate.now().isAfter(p.getDataInativacao().plusDays(30))) {

            listaDeDeletados.add(p);
            qtdDeletados++;
            //                repository.deleteById(p.getId());
        }

    }


    public void alteraStatus(PerfilUsuario p, String status) {

        if (status.equals("deletar")) {

            p.setStatusAtivo(false);
            p.setDataAtivacao(LocalDate.now());
            listaTemporariaDeletados.add(p);

        } else {
            p.setStatusAtivo(true);
            p.setDataAtivacao(null);
        }

        p.setFkTbInstituicao(1);
        repository.save(p);
    }

    public boolean containsName(final List<Livros> list, final Integer id) {
        return list.stream().map(Livros::getId).filter(id::equals).findFirst().isPresent();
    }
}
