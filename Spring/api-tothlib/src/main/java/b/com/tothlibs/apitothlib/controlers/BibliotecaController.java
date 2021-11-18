package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Categoria;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.listas.LayoutArquivos;
import b.com.tothlibs.apitothlib.repository.CategoriaRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import b.com.tothlibs.apitothlib.services.UsuarioAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    UsuarioAdmin admin;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de livros")
    public ResponseEntity listaLivros() {

        List<Livros> livros = repository.findAll();
        return ResponseEntity.status(200).body(repository.findAll());

    }

    @PostMapping("/{idAdmin}")
    @ApiOperation(value = "Realiza um cadastro de um livro")
    public ResponseEntity cadastrarLivro(@PathVariable Integer idAdmin, @RequestBody Livros idLivro) {

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
        return ResponseEntity.of(repository.findById(idLivro));

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Realiza uma alteração no cadastro de livros")
    public ResponseEntity alteraLivro(@PathVariable Integer id, @RequestBody Livros livroAtualizado) {
        if (repository.existsById(id)) {
            livroAtualizado.setId(id);
            repository.save(livroAtualizado);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove um livro pelo ID")
    public ResponseEntity deletaPorId(@PathVariable Integer id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }

    }

    @PutMapping("reservar/{idLivro}/{idUsuario}")
    public ResponseEntity reservarLivro(@PathVariable Integer idLivro, @PathVariable Integer idUsuario) {

        Integer idRegistro = admin.reservar(idLivro, idUsuario);

        if (idRegistro != null) {
            return ResponseEntity.status(200).body("ID para retirada: " + idRegistro);
        } else {
            return ResponseEntity.status(400).build();
        }


    }

    @PutMapping("retirar/{idRegistro}/{idUsuario}")
    public ResponseEntity retirarLivro(@PathVariable Integer idRegistro, @PathVariable Integer idUsuario) {


        Integer novoRegistro = admin.locarLivro(idRegistro, idUsuario);

        if (novoRegistro != null) {
            return ResponseEntity.status(200).body("ID da retirada: " + novoRegistro);
        } else {
            return ResponseEntity.status(400).body("Retirada não concluida");
        }

    }

    @PutMapping("renovar/{idRegistro}/{idUsuario}")
    public ResponseEntity renovarLivro(@PathVariable Integer idRegistro, @PathVariable Integer idUsuario) {

        Integer novoCodRegistro = admin.renovarAlocacao(idRegistro, idUsuario);

        if (idRegistro != null) {
            return ResponseEntity.status(200).body("Novo ID de retirada: " + novoCodRegistro);
        } else {
            return ResponseEntity.status(400).body("Renovação não concluida");
        }

    }

    @PutMapping("devolver/{idRegistro}/{idUsuario}")
    public ResponseEntity devolverLivro(@PathVariable Integer idRegistro, @PathVariable Integer idUsuario) {

        Integer novoCodRegistro = admin.devolverLivro(idRegistro, idUsuario);

        if (novoCodRegistro != null) {
            return ResponseEntity.status(200).body("Novo ID de retirada: " + novoCodRegistro);
        } else {
            return ResponseEntity.status(404).body("Registro de retirada não encontrado!!");
        }

    }

    @GetMapping("/gravarArqTxt/livros")
    public ResponseEntity gravaTxt() {

        List<Livros> listaDeLivros = repository.findAll();
        List<Categoria> listaDeCategorias = repositoryCategoria.findAll();
        LocalDateTime dataHoje = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = dataHoje.format(formatter);

        String nomeArquivo =  "Livros-";
               nomeArquivo += dataFormatada + ".txt";

        if(listaDeLivros.isEmpty()){

            return ResponseEntity.status(204).body("A lista de Livros está vazia!");

        }

        LayoutArquivos managerLayoutArquivos = new LayoutArquivos(listaDeLivros, nomeArquivo, listaDeCategorias);

        managerLayoutArquivos.verificaTipoArquivo();

        managerLayoutArquivos.leArquivoTxt(nomeArquivo);

        return ResponseEntity.status(200).body("Arquivo gerado com sucesso");

    }

    @GetMapping("/lerArquivo/")
    public ResponseEntity leTxt(){

        String nomeArquivo = "Livros-2021-11-17.txt";

        LayoutArquivos trataArquivo = new LayoutArquivos();

        trataArquivo.leArquivoTxt(nomeArquivo);

        return ResponseEntity.status(200).build();
    }

}
