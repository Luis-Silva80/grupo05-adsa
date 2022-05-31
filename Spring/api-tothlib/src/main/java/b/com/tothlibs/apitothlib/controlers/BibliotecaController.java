package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.dto.Response;
import b.com.tothlibs.apitothlib.dto.ResponseLivroMobile;
import b.com.tothlibs.apitothlib.dto.ResponseLivroModal;
import b.com.tothlibs.apitothlib.entity.Exemplar;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.*;
import b.com.tothlibs.apitothlib.services.UsuarioAdmin;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/biblioteca")
@Api(value = "API REST")
@CrossOrigin(origins = "*")
public class BibliotecaController<T> {

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
    private ExemplarRepository exemplarRepository;

    @Autowired
    UsuarioAdmin admin;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de livros")

    public ResponseEntity listaLivros() {

        List<Livros> livros = admin.consultaListaLivros();

        if (!livros.isEmpty()) {
            return ResponseEntity.status(200).body(livros);
        } else {
            return ResponseEntity.status(204).build();
        }

    }


    @GetMapping("/v2")
    @ApiOperation(value = "Retorna uma lista de livros para a api-v2 kotlin")
    public ResponseEntity exibeLivroByIdV2() {

//        RestTemplate restTemplate = new RestTemplate(); //1
//        String url = "http://www.algumacoisa.com.br/api"; //2
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(url, String.class); //3

        List<Livros> livros = admin.consultaListaLivros();

        if (!livros.isEmpty()) {
            return ResponseEntity.status(200).body(livros);
        } else {
            return ResponseEntity.status(204).build();
        }

    }

    @PostMapping("/{idAdmin}")
    @ApiOperation(value = "Realiza um cadastro de um livro")
    public ResponseEntity cadastrarLivro(@PathVariable Integer idAdmin, @RequestBody Livros idLivro) {

        Integer admin = repositoryUsuario.findAdminById(idAdmin);
        Integer qtdEstoque = repository.findQtdEstoqueById(idAdmin);

        if (admin.equals(1)) {

            idLivro.setQtdResenhas(0);
            idLivro.setQtdReservadosTotal(0);
            idLivro.setQtdReservadosAgora(0);
            idLivro.setQtdDisponiveis(0);
            idLivro.setQtdEstoque(0);
            idLivro.setFkTbBiblioteca(2);

            repository.save(idLivro);
            return ResponseEntity.status(201).build();

        }

        return ResponseEntity.status(400).build();

    }

    @GetMapping("/exemplar/{tombo}")
    @ApiOperation(value = "Retorna um exemplar pelo tombo")
    public ResponseEntity<Exemplar> getExemplarByTombo(@PathVariable String tombo) {

        Exemplar exemplar = exemplarRepository.findByTombo(tombo);

        if (exemplar == null) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(exemplar);
    }

    @GetMapping("/exemplar/listExemplar")
    @ApiOperation(value = "Retorna uma lista de exemplares pelo id do Livro")
    public ResponseEntity<List<Exemplar>> getListExemplarByIdLivro(){

        List<Exemplar> listExemplares = exemplarRepository.findAll();

        if(listExemplares.isEmpty() || listExemplares == null){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(listExemplares);

    }

    @GetMapping("/exemplares/{idLivro}")
    @ApiOperation(value = "Retorna uma lista de exemplares pelo id do Livro")
    public ResponseEntity getLivroExemplares(@PathVariable Integer idLivro){

        Livros livro = repository.findLivroById(idLivro);
        List<String> tombosDisponiveis = exemplarRepository.listExemplaresDisponiveisById(idLivro);

        if(livro != null && !tombosDisponiveis.isEmpty()){
            return ResponseEntity.status(200).body(new ResponseLivroMobile(livro, tombosDisponiveis));
        }else {
            return ResponseEntity.status(404).build();
        }
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
//
    @PutMapping("reservar/{idUsuario}/{tombo}")
    @ApiOperation(value = "Realiza a reserva de um livro que esteja disponivel na biblioteca")
    public ResponseEntity reservarLivro(@PathVariable Integer idUsuario, @PathVariable String tombo) {

        Integer codigoReserva = admin.reservar(tombo, idUsuario);

        if (codigoReserva.equals(0)) {
            return ResponseEntity.status(400).body(0);
        }

        return ResponseEntity.status(200).body(codigoReserva);

    }

    //
//    @GetMapping("/buscarLivrosReservados/{idUsuario}")
//    @ApiOperation(value = "Busca os livros que estão reservados agora para o usuário")
//    public ResponseEntity buscarLivrosLidos(@PathVariable Integer idUsuario) {
//
//        try {
//
//            PerfilUsuario p = repositoryUsuario.findById(idUsuario).get();
//
//        } catch (NullPointerException erro) {
//
//            return ResponseEntity.status(404).body("Usuário não encontrado!");
//
//        }
//
//        PerfilUsuario p = repositoryUsuario.findById(idUsuario).get();
//
//        List<Integer> idLivrosAssociadosAoUsuario = new ArrayList<>();
//
//        idLivrosAssociadosAoUsuario = repositoryHistorico.findFkLivrosByIdUsuario(idUsuario);
//
//        List<Integer> idRepetidos = new ArrayList<>();
//
//        List<Livros> listaDeLivrosEncontrados = new ArrayList<>();
//
//        Historico h;
//
//        Livros l;
//
//        for (Integer c : idLivrosAssociadosAoUsuario) {
//
//            idRepetidos.add(c);
//
//            if (!verificaIdRepetido(idRepetidos, c)) {
//
//                h = repositoryHistorico.findTopByFkTbPerfilUsuarioAndFkTbLivrosOrderByIdDesc(idUsuario, c);
//
//                if (!h.getAcao().equals("Devolucao")) {
//
//                    l = repository.findLivroById(c);
//
//                    listaDeLivrosEncontrados.add(l);
//
//                }
//            }
//        }
//
//        return ResponseEntity.status(200).body(listaDeLivrosEncontrados);
//    }
//
    @PutMapping("retirar/{tombo}/{idUsuario}")
    @ApiOperation(value = "Realiza a retirada de um livro que esteja reservado pelo usuario")
    public ResponseEntity retirarLivro(@PathVariable String tombo, @PathVariable Integer idUsuario) {


        Integer novoRegistro = admin.locarLivro(tombo, idUsuario);

        if (novoRegistro != null) {
            return ResponseEntity.status(200).body(novoRegistro);
        } else {
            return ResponseEntity.status(400).body("Retirada não concluida");
        }

    }

    @PutMapping("renovar/{tombo}/{idUsuario}")
    @ApiOperation(value = "Realiza a renovação do tempo de alocação do livro por 10 dias")
    public ResponseEntity renovarLivro(@PathVariable String tombo, @PathVariable Integer idUsuario) {

        Integer novoCodRegistro = admin.renovarAlocacao(tombo, idUsuario);

        if (tombo != null || tombo.equals(0)) {
            return ResponseEntity.status(200).body(novoCodRegistro);
        } else {
            return ResponseEntity.status(400).body("Renovação não concluida");
        }

    }

    @PutMapping("devolver/{tombo}/{idUsuario}")
    @ApiOperation(value = "Realiza a devolução do livro que está com o usuario")
    public ResponseEntity devolverLivro(@PathVariable String tombo, @PathVariable Integer idUsuario) {

        Integer novoCodRegistro = admin.devolverLivro(tombo, idUsuario);

        if (novoCodRegistro != null) {
            return ResponseEntity.status(200).body(novoCodRegistro);
        } else {
            return ResponseEntity.status(404).body("Registro de retirada não encontrado!!");
        }

    }

    @GetMapping("/listLivrosKotlin")
    @ApiOperation(value = "Retorna uma lista de livros para o padrão mobile")
    public ResponseEntity responseMobileList(@RequestParam(value = "name", required = true) String name) {

        Livros livro = repository.findByTitulo(name);

        if (livro == null) {
            return ResponseEntity.status(204).build();
        } else {

            List<ResponseLivroModal> listResponseLivroModal = new ArrayList<>();

            RestTemplate restTemplate = new RestTemplate();
            String url = "https://www.googleapis.com/books/v1/volumes?q=" + name;
            String response
                    = restTemplate.getForObject(url, String.class);

            JSONObject json = new JSONObject(response);
            JSONObject json2 = json.getJSONArray("items").getJSONObject(0);
            JSONArray arrayItems = json.getJSONArray("items");
            JSONObject volumeInfo = json2.getJSONObject("volumeInfo");
            JSONObject saleInfo = json2.getJSONObject("saleInfo");
            JSONObject accessInfo = json2.getJSONObject("accessInfo");
            JSONObject listPrice = saleInfo.getJSONObject("listPrice");
            JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");


            try {
                for (int i = 0; i < arrayItems.length(); i++) {
                    JSONObject jsonFinal = json.getJSONArray("items").getJSONObject(i);

                    System.out.println("obj: " + jsonFinal);

                    JSONObject volumeInfoFinal = jsonFinal.getJSONObject("volumeInfo");
                    JSONObject saleInfoFinal = jsonFinal.getJSONObject("saleInfo");
                    JSONObject accessInfoFinal = jsonFinal.getJSONObject("accessInfo");
                    System.out.println("listPrice = : " + saleInfoFinal.has("listPrice"));
                    System.out.println("saleability = : " + saleInfoFinal.has("saleability"));
                    JSONObject listPriceFinal;
                    String saleability = "FOR_SALE";
                    Double amountFinal = 0.00;
                    String publishedDateFinal = "";


                    if (saleInfoFinal.has("listPrice")) {
                        listPriceFinal = saleInfoFinal.getJSONObject("listPrice");
                        amountFinal = listPriceFinal.getDouble("amount");
                    } else {
                        System.out.println("saleabilityteste: " + saleInfoFinal.getString("saleability"));
                        saleability = saleInfoFinal.getString("saleability");
                    }

                    if (volumeInfoFinal.has("publishedDate")) {
                        publishedDateFinal = volumeInfoFinal.getString("publishedDate");
                    }


                    JSONObject imageLinksFinal = volumeInfo.getJSONObject("imageLinks");

                    String titleFinal = volumeInfoFinal.getString("title");
                    System.out.println("\n\nPUBLISHDATE: " + volumeInfoFinal.getString("publishedDate") + "\n\n");
                    String descriptionFinal = volumeInfoFinal.getString("description");
                    String capaFinal = accessInfoFinal.getString("webReaderLink");

                    String smallThumbnailFinal = imageLinksFinal.getString("smallThumbnail");
                    String thumbnailFinal = imageLinksFinal.getString("thumbnail");
                    String autorFinal = volumeInfoFinal.getJSONArray("authors").getString(0);

                    ResponseLivroModal t = new ResponseLivroModal(titleFinal,
                            publishedDateFinal,
                            descriptionFinal,
                            amountFinal,
                            capaFinal,
                            smallThumbnailFinal,
                            thumbnailFinal,
                            autorFinal,
                            "disponivel",
                            "10/10",
                            saleability);


                    System.out.println("------AQUI: " + t);
                    System.out.println("--------------------------------------");
                    listResponseLivroModal.add(t);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return ResponseEntity.status(200).body(listResponseLivroModal);
        }



    }

//
//    @GetMapping(value = "/gravarArqTxt/livros", produces = "text/text")
//    @ApiOperation(value = "Retorna um arquivo com os livros da biblioteca")
//    public ResponseEntity<?> export() throws IOException {
//
//        List<Livros> listaDeLivros = repository.findAll();
//        List<Categoria> listaDeCategorias = repositoryCategoria.findAll();
//        LocalDateTime dataHoje = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String dataFormatada = dataHoje.format(formatter);
//
//        String filename = String.format("Livros-" + dataFormatada + ".txt");
//
//        if (listaDeLivros.isEmpty()) {
//
//            return ResponseEntity.status(204).body("A lista de Livros está vazia!");
//
//        }
//
//        LayoutArquivos managerLayoutArquivos = new LayoutArquivos(listaDeLivros, filename, listaDeCategorias);
//
//        managerLayoutArquivos.verificaTipoArquivo();
//
//        try {
//
//            File file = new File(filename);
//            Path path = Paths.get(file.getAbsolutePath());
//            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//            HttpHeaders header = new HttpHeaders();
//
//            System.out.println(path);
//
//            header.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + filename);
//            header.add("Cache-Control" , "no-cache, no-store,must-revalidate");
//            header.add("Pragma","no-cache");
//            header.add("Expires","0");
//
//            return ResponseEntity
//                    .ok()
//                    .contentLength(file.length())
//                    .headers(header)
//                    .contentType(MediaType.parseMediaType("application/octet-stream"))
//                    .body(resource);
//        }catch (IOException e){
//
//            e.printStackTrace();
//            return ResponseEntity.notFound().build();
//
//        }
//
//    }
//
//
//    @PutMapping("/upload")
//    @ApiOperation(value = "Recebe um arquivo e grava suas informações no banco")
//    public ResponseEntity lerArquivo(@RequestParam MultipartFile file) throws IOException {
//
//        LayoutArquivos managerLayoutArquivos = new LayoutArquivos();
//
//        List<Livros> livrosAGravar = new ArrayList<>();
//        List<Categoria> categoriasAGravar = new ArrayList<>();
//
//        List<List<T>> retornoDoMetodoLerArquivo;
//
//        retornoDoMetodoLerArquivo = (managerLayoutArquivos.leArquivoTxt(file.getOriginalFilename()));
//
//        if (retornoDoMetodoLerArquivo.get(0).get(0) instanceof Livros) {
//
//            livrosAGravar = (List<Livros>) retornoDoMetodoLerArquivo.get(0);
//
//        }
//
//        if (retornoDoMetodoLerArquivo.get(1).get(0) instanceof Categoria) {
//
//            categoriasAGravar = (List<Categoria>) retornoDoMetodoLerArquivo.get(1);
//
//        }
//
//        for (Livros l : livrosAGravar) {
//
//            repository.save(l);
//
//        }
//
//        for (Categoria c : categoriasAGravar) {
//
//            repositoryCategoria.save(c);
//
//        }
//
//
//        return ResponseEntity.status(200).build();
//    }
//
//    @PutMapping("/upload2")
//    @ApiOperation(value = "Recebe um arquivo e grava suas informações no banco")
//    public ResponseEntity lerArquivo2(@RequestParam MultipartFile file) throws IOException {
//
//        LayoutArquivos managerLayoutArquivos = new LayoutArquivos();
//
//        List<Livros> livrosAGravar = new ArrayList<>();
//        List<Categoria> categoriasAGravar = new ArrayList<>();
//
//        FilaObj<List<T>> retornoDoMetodoLerArquivo;
//
//        retornoDoMetodoLerArquivo = (managerLayoutArquivos.leArquivoTxt2(file.getOriginalFilename()));
//
//        retornoDoMetodoLerArquivo.exibe();
//
//        if (retornoDoMetodoLerArquivo.peek().get(0) instanceof Livros) {
//
//            livrosAGravar = (List<Livros>) retornoDoMetodoLerArquivo.poll();
//
//        }
//
//        if (retornoDoMetodoLerArquivo.peek().get(0) instanceof Categoria) {
//
//            categoriasAGravar = (List<Categoria>) retornoDoMetodoLerArquivo.poll();
//
//        }
//
//        for (Livros l : livrosAGravar) {
//
//            repository.save(l);
//
//        }
//
//        for (Categoria c : categoriasAGravar) {
//
//            repositoryCategoria.save(c);
//
//        }
//
//
//        return ResponseEntity.status(200).build();
//    }
//
//    @GetMapping("/favoritos")
//    @ApiOperation(value = "Põe numa fila os livros mais lidos e conforme a pontuação ele vai saindo da fila")
//    public ResponseEntity livroFavorito() {
//
//        try {
//
//            List<Livros> listaLivrosRanking = repository.findLivrosOrderByQtdReservas();
//
//            FilaObj<Livros> filaLivrosRanking = new FilaObj<>(listaLivrosRanking.size());
//
//            for (Livros l : listaLivrosRanking) {
//
//                filaLivrosRanking.insert(l);
//
//            }
//
//            List<Livros> listOrganizados = new ArrayList<>();
//
//            for (int i = 0; i<=filaLivrosRanking.getTamanho();i++){
//                listOrganizados.add(filaLivrosRanking.poll());
//            }
//
//            return ResponseEntity.status(200).body(listOrganizados);
//
//        } catch (Exception e){
//
//            return ResponseEntity.status(400).build();
//
//        }
//    }
//
//
////    public ResponseEntity leTxt(String nomeArq) {
////
////        String nomeArquivo = nomeArq;
////
////        LayoutArquivos trataArquivo = new LayoutArquivos();
////
////        trataArquivo.leArquivoTxt(nomeArquivo);
////
////        return ResponseEntity.status(200).build();
////    }



    @PostMapping("/cadastraExemplar/{fkTbLivro}/{idUsuario}")
    @ApiOperation(value = "Realiza o cadastro de um ou muitos exemplares")
    public ResponseEntity postAluno(@RequestParam List<String> listaDeTombo,
                                    @PathVariable Integer fkTbLivro,
                                    @PathVariable Integer idUsuario) {

        Integer resultado = cadastraExemplar(listaDeTombo,fkTbLivro, idUsuario);

        if(!resultado.equals(0)){
            return  ResponseEntity.status(200).body(resultado);
        }
        return ResponseEntity.status(400).build();
    }


    public Integer cadastraExemplar(List<String> listaDeTombo, Integer fkTbLivro, Integer idUsuario){

        PerfilUsuario usuario = repositoryUsuario.findById(idUsuario).get();

        if(usuario.equals(null)){
            return 0;
        }

        if(listaDeTombo.isEmpty()){

            return 0;
        }

        if(usuario.getStatusAtivo()){
            if(!repository.existsById(fkTbLivro)){

                return 0;
            }


            Integer qtdLivrosCadastrados = 0;
            Exemplar e;


            for(Integer c = 0; c <= listaDeTombo.size() - 1; c++){

                System.out.println(listaDeTombo.get(c));

                Long ultimoNrExemplar     = exemplarRepository
                        .findTopByFkTbLivroOrderByIdDesc(fkTbLivro);

                Integer nrExemplar = ultimoNrExemplar.intValue() + 1;

                exemplarRepository.save(new Exemplar(nrExemplar,
                        listaDeTombo.get(c),
                        false,
                        false,
                        false,
                        fkTbLivro,
                        false));

                qtdLivrosCadastrados ++;
            }

            Integer somaQtdExemplares = exemplarRepository
                    .findTopByFkTbLivroOrderByIdDesc(fkTbLivro).intValue();

            Livros livroReferencia = repository.findLivroById(fkTbLivro);
            livroReferencia.setQtdDisponiveis(livroReferencia.getQtdDisponiveis() + somaQtdExemplares);
            livroReferencia.setQtdEstoque(livroReferencia.getQtdEstoque() + somaQtdExemplares);
            repository.save(livroReferencia);

            return qtdLivrosCadastrados;

        }

        return 0;
    }


//
//    public Integer efetuarReserva(Integer idUsuario, Integer idLivro) {
//        Integer idRegistro = admin.reservar(idLivro, idUsuario);
//
//        if (idRegistro != null) {
//            return idRegistro;
//        } else {
//            return null;
//        }
//    }
//
//    public Boolean verificaIdRepetido(List<Integer> listaDeId, Integer id) {
//
//        Integer contadorDeRepeticao = 0;
//
//        for (Integer c = 0; c < listaDeId.size(); c++) {
//
//            if (listaDeId.get(c).equals(id)) {
//
//                contadorDeRepeticao++;
//
//            }
//        }
//
//        if (contadorDeRepeticao > 1) {
//
//            return true;
//        }
//        return false;
//    }
//
}
