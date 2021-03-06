package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.dto.UsuariosPendentesDto;
import b.com.tothlibs.apitothlib.entity.Historico;
import b.com.tothlibs.apitothlib.listas.ListaObj;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.utils.Mensagem;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    private Mensagem mensagem;

    @Autowired
    private HistoricoRepository repository;

    ListaObj listaObj = null;

    @GetMapping
    public ResponseEntity getHistorico(){

        List<Historico> historico = repository.findAll();

        if(!historico.isEmpty()){
            return ResponseEntity.status(200).body(historico);
        }else {
            mensagem = new Mensagem(01,"Registros não encontrados");
            return ResponseEntity.status(404).build();
        }

    }

    @GetMapping("/pendentes")
    @ApiOperation(value = "Retorna uma lista com o historico usuarios pendentes na plataforma")
    public ResponseEntity pendencia() throws IOException {

        List<UsuariosPendentesDto> listaDeHistorico = repository.findUserPendencia().stream()
                .filter(usuariosPendentesDto -> usuariosPendentesDto.getLivrosReservados() > 0)
                .collect(Collectors.toList());                ;

        listaObj = new ListaObj(listaDeHistorico.size());


//        listaDeHistorico.stream()
//                .filter(usuariosPendentesDto -> usuariosPendentesDto.getLivrosReservados() > 0)
//                .collect(Collectors.toList());

        for (int i = 0;i < listaDeHistorico.size();i++){
            listaObj.adicionaElemento(listaDeHistorico.get(i));
        }

        gravaArquivoCsv(listaObj);



//        byte[] arquivo = Files.readAllBytes(Paths.get("Pendentes.csv") );
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        httpHeaders.add("Content-Disposition", "attachment;filename=\"Pendentes.csv\"");
//
//        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);
//
//        return entity;


//        List<UsuariosPendentesDto> listaDeHistorico = repository.findUserPendencia();

        return ResponseEntity.status(200).body(listaDeHistorico);
    }

    public static void gravaArquivoCsv(ListaObj<UsuariosPendentesDto> lista) {

        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        String nomeArq = "Pendentes.csv";       // acrescenta a extensão .csv ao nome do arquivo

        // Bloco try-catch para abrir o arquivo
        try {
            // a criação do objeto FileWriter faz com que o arquivo seja aberto ou criado
            arq = new FileWriter(nomeArq, true); // true é para acrescentar ao arquivo
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }


//        private Integer id;
//        private Integer fkTbLivros;
//        private Integer fkTbPerfilUsuario;
//        private LocalDate dataLivroHistorico;
//        private String nomeLivro;
//        private String nomePerfilUsuario;
//        private String acao;
//        private LocalDate dataDevolucao;
//        private Integer livrosReservados;

        // Bloco try-catch para gravar no arquivo
        try {
            // Percorro a lista de cachorros
            for (int i = 0; i < lista.getTamanho(); i++) {
                // Obtenho um objeto dog da lista por vez (o do índice i)
                UsuariosPendentesDto pendentes = lista.getElemento(i);
                // Gravo os dados desse objeto no arquivo
                // Separando cada campo por um ;
                saida.format("%d;%d;%d;%s;%s;%s;%s;%s;%d\n", pendentes.getId(),
                        pendentes.getFkTbLivros(),
                        pendentes.getFkTbPerfilUsuario(),
                        pendentes.getDataLivroHistorico().toString(),
                        pendentes.getNomeLivro(),
                        pendentes.getNomePerfilUsuario(),
                        pendentes.getAcao(),
                        pendentes.getDataDevolucao(),
                        pendentes.getLivrosReservados());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }

    }

}
