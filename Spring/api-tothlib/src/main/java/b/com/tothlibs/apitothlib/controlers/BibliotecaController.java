package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.dto.Response;
import b.com.tothlibs.apitothlib.dto.UsuariosPendentesDto;
import b.com.tothlibs.apitothlib.entity.Categoria;
import b.com.tothlibs.apitothlib.entity.Historico;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.listas.LayoutArquivos;
import b.com.tothlibs.apitothlib.repository.*;
import b.com.tothlibs.apitothlib.services.UsuarioAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/biblioteca")
@Api(value = "API REST")
@CrossOrigin(origins = "*")
public class BibliotecaController {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(BibliotecaController.class));

    @Autowired
    private LivrosRepository repository;

    @Autowired
    private PerfilUsuarioRepository repositoryUsuario;

    @Autowired
    private CategoriaRepository repositoryCategoria;

    @Autowired
    private HistoricoRepository repositoryHistorico;

    @Autowired
    UsuarioAdmin admin;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de livros")
    public ResponseEntity listaLivros() {

        List<Livros> livros = admin.consultaListaLivros();

        if(!livros.isEmpty()){
            return ResponseEntity.status(200).body(livros);
        }else {
            return ResponseEntity.status(204).build();
        }

    }

    @PostMapping("/{idAdmin}")
    @ApiOperation(value = "Realiza um cadastro de um livro")
    public ResponseEntity cadastrarLivro(@PathVariable Integer idAdmin, @RequestBody Livros idLivro) {

        Integer admin = repositoryUsuario.findAdminById(idAdmin);
        Integer admin = repositoryUsuario.findAdminById(idAdmin);

        if (admin.equals(1)) {

            idLivro.setQtdResenhas(0);
            idLivro.setQtdReservas(0);
            idLivro.setQtdReservadoAgora(0);
            idLivro.setStatusLivro("disponivel");
            idLivro.setFkTbbiblioteca(2);

            repository.save(idLivro);
            return ResponseEntity.status(201).build();

        }

        return ResponseEntity.status(400).build();

    }

    @GetMapping("/{idLivro}")
    @ApiOperation(value = "Retorna um livro por um ID especifico")
    public ResponseEntity exibeLivroById(@PathVariable Integer idLivro) {

        LOGGER.info("Retornando usuario desejado...");
        return ResponseEntity.of(admin.buscarLivro(idLivro));

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Realiza uma alteração no cadastro de livros")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Alteração bem sucedida"),
            @ApiResponse(code = 404, message = "Livro não encontrado")})
    public ResponseEntity alteraLivro(@PathVariable Integer id, @RequestBody Livros livroAtualizado) {


        Response resp = admin.alterarLivro(id, livroAtualizado);

        if (resp.getCodigo().equals(00)) {
            return ResponseEntity.status(200).body(resp);
        } else {
            return ResponseEntity.status(404).body(resp);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove um livro pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro removido com sucesso"),
            @ApiResponse(code = 404, message = "Livro não encontrado")})
    public ResponseEntity deletaPorId(@PathVariable Integer id) {

        Response resp = admin.excluirLivro(id);

        if (resp.getCodigo().equals(00)) {
            return ResponseEntity.status(200).body(resp);
        } else {
            return ResponseEntity.status(404).body(resp);
        }


    }

    @PutMapping("reservar/{idUsuario}/{idLivro}")
    @ApiOperation(value = "Realiza a reserva de um livro que esteja disponivel na biblioteca")
    public ResponseEntity reservarLivro(@PathVariable Integer idUsuario, @PathVariable Integer idLivro) {

        try {

            Historico u = repositoryHistorico.
                    findTopByFkTbPerfilUsuarioAndFkTbLivrosOrderByIdDesc(idUsuario, idLivro);
            if (u.getAcao().equals("Retirada") || u.getAcao().equals("Renovacao")) {
                return ResponseEntity.status(400).body("Não pode reservar o mesmo livro sem devolver antes!");

            } else if (u.getAcao().equals("Reserva")) {
                return ResponseEntity.status(400).body("Você ja está com o livro reservado!!!");
            } else {

                Integer idRegistro = admin.reservar(idLivro, idUsuario);

                if (idRegistro != null) {

                    return ResponseEntity.status(200).body(idRegistro);
                } else {

                    return ResponseEntity.status(400).build();
                }
            }
        } catch (NullPointerException e) {

            Integer idRegistro = admin.reservar(idLivro, idUsuario);

            if (idRegistro != null) {

                return ResponseEntity.status(200).body(idRegistro);
            } else {

                return ResponseEntity.status(400).build();
            }

        }
    }

    @GetMapping("/buscarLivrosReservados/{idUsuario}")
    @ApiOperation(value = "Busca os livros que estão reservados agora para o usuário")
    public ResponseEntity buscarLivrosLidos(@PathVariable Integer idUsuario) {

        try {

            PerfilUsuario p = repositoryUsuario.findById(idUsuario).get();

        } catch (NullPointerException erro) {

            return ResponseEntity.status(404).body("Usuário não encontrado!");

        }

        PerfilUsuario p = repositoryUsuario.findById(idUsuario).get();

        List<Integer> idLivrosAssociadosAoUsuario = new ArrayList<>();

        idLivrosAssociadosAoUsuario = repositoryHistorico.findFkLivrosByIdUsuario(idUsuario);

        List<Integer> idRepetidos = new ArrayList<>();

        List<Livros> listaDeLivrosEncontrados = new ArrayList<>();

        Historico h;

        Livros l;

        for (Integer c : idLivrosAssociadosAoUsuario) {

            idRepetidos.add(c);

            if (!verificaIdRepetido(idRepetidos, c)) {

                h = repositoryHistorico.findTopByFkTbPerfilUsuarioAndFkTbLivrosOrderByIdDesc(idUsuario, c);

                if (!h.getAcao().equals("Devolucao")) {

                    l = repository.findLivroById(c);

                    listaDeLivrosEncontrados.add(l);

                }
            }
        }

        return ResponseEntity.status(200).body(listaDeLivrosEncontrados);
    }

    @PutMapping("retirar/{idRegistro}/{idUsuario}")
    @ApiOperation(value = "Realiza a retirada de um livro que esteja reservado pelo usuario")
    public ResponseEntity retirarLivro(@PathVariable Integer idRegistro, @PathVariable Integer idUsuario) {


        Integer novoRegistro = admin.locarLivro(idRegistro, idUsuario);

        if (novoRegistro != null) {
            return ResponseEntity.status(200).body(novoRegistro);
        } else {
            return ResponseEntity.status(400).body("Retirada não concluida");
        }

    }

    @PutMapping("renovar/{idRegistro}/{idUsuario}")
    @ApiOperation(value = "Realiza a renovação do tempo de alocação do livro por 10 dias")
    public ResponseEntity renovarLivro(@PathVariable Integer idRegistro, @PathVariable Integer idUsuario) {

        Integer novoCodRegistro = admin.renovarAlocacao(idRegistro, idUsuario);

        if (idRegistro != null) {
            return ResponseEntity.status(200).body(novoCodRegistro);
        } else {
            return ResponseEntity.status(400).body("Renovação não concluida");
        }

    }

    @PutMapping("devolver/{idRegistro}/{idUsuario}")
    @ApiOperation(value = "Realiza a devolução do livro que está com o usuario")
    public ResponseEntity devolverLivro(@PathVariable Integer idRegistro, @PathVariable Integer idUsuario) {

        Integer novoCodRegistro = admin.devolverLivro(idRegistro, idUsuario);

        if (novoCodRegistro != null) {
            return ResponseEntity.status(200).body(novoCodRegistro);
        } else {
            return ResponseEntity.status(404).body("Registro de retirada não encontrado!!");
        }

    }

    @GetMapping("/gravarArqTxt/livros")
    @ApiOperation(value = "Retorna um arquivo com os livros da biblioteca")
    public ResponseEntity gravaTxt() {

        List<Livros> listaDeLivros = repository.findAll();
        List<Categoria> listaDeCategorias = repositoryCategoria.findAll();
        LocalDateTime dataHoje = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = dataHoje.format(formatter);

        String nomeArquivo = "Livros-";
        nomeArquivo += dataFormatada + ".txt";

        if (listaDeLivros.isEmpty()) {

            return ResponseEntity.status(204).body("A lista de Livros está vazia!");

        }

        LayoutArquivos managerLayoutArquivos = new LayoutArquivos(listaDeLivros, nomeArquivo, listaDeCategorias);

        managerLayoutArquivos.verificaTipoArquivo();

        managerLayoutArquivos.leArquivoTxt(nomeArquivo);

        return ResponseEntity.status(200).body("Arquivo gerado com sucesso");

    }

    @PostMapping("/upload")
    public void upload(@RequestParam MultipartFile file){

//        LayoutArquivos lerArq = new LayoutArquivos();
//
//        System.out.println(file.getName());
//
//        lerArq.leArquivoTxt(file.getName());

    }


    @GetMapping("/lerArquivo")
    @ApiOperation(value = "Realiza a leitura de um arquivo com as informções dentro arquivo")
    public ResponseEntity leTxt() {

        String nomeArquivo = "Livros-2021-11-17.txt";

        LayoutArquivos trataArquivo = new LayoutArquivos();

        trataArquivo.leArquivoTxt(nomeArquivo);

        return ResponseEntity.status(200).build();
    }

    public Integer efetuarReserva(Integer idUsuario, Integer idLivro) {
        Integer idRegistro = admin.reservar(idLivro, idUsuario);

        if (idRegistro != null) {
            return idRegistro;
        } else {
            return null;
        }
    }

    public Boolean verificaIdRepetido(List<Integer> listaDeId, Integer id) {

        Integer contadorDeRepeticao = 0;

        for (Integer c = 0; c < listaDeId.size(); c++) {

            if (listaDeId.get(c).equals(id)) {

                contadorDeRepeticao++;

            }
        }

        if (contadorDeRepeticao > 1) {

            return true;
        }
        return false;
    }

}
