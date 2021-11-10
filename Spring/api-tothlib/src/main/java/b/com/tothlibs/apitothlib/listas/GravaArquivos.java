package b.com.tothlibs.apitothlib.listas;

import b.com.tothlibs.apitothlib.dto.UsuariosPendentesDto;
import b.com.tothlibs.apitothlib.entity.Livros;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GravaArquivos<T> {

    private List<T> lista;
    private String nomeArquivo;

    private LocalDateTime dataHoje = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String dataFormatada = dataHoje.format(formatter);

    public GravaArquivos(List<T> lista, String nomeArquivo) {

        this.lista = lista;
        this.nomeArquivo = nomeArquivo;

    }

    public void verificaTipoArquivo() {


        if (this.lista.size() > 0) {

            if (this.lista.get(0) instanceof Livros) {

                nomeArquivo += "-" +  dataFormatada.toString();
                nomeArquivo += ".txt";
                System.out.println("CHEGUEI AQUI");
                List<Livros> listaDeLivros = (List<Livros>) this.lista;

                gravaArquivoTxtLivros(listaDeLivros, nomeArquivo);

            } else if (this.lista.get(0) instanceof UsuariosPendentesDto) {
                System.out.println("É DO TIPO ALUNO PENDENTE");
            }

        } else {
            System.out.println("Esta é uma lista vazia.");
        }

    }


    public static void gravaRegistro(String nomeDeArquivo, String registro) {

        BufferedWriter saida = null;

        // Abre o arquivo
        try {

            saida = new BufferedWriter(new FileWriter(nomeDeArquivo, true));

        } catch (IOException error) {

            System.out.println("Erro de open do arquivo: " + error.getMessage());

        }

        //Grava o registro e finaliza
        try {

            saida.append(registro + "\n");
            saida.close();

        } catch (IOException error) {

            System.out.println("Erro na gravação do arquivo: " + error.getMessage());

        }


    }


    public static void gravaArquivoTxtLivros(List<Livros> listaDeLivros, String nomeDeAqruivo) {


        LocalDateTime dataHoje = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = dataHoje.format(formatador);

        Integer contaRegistroTipo1 = 0;
        Integer contaRegistroTipo2 = 0;

        // Monta o registro de header
        String header = "00LIVROS";
        header += dataFormatada;
        header += "01";

        //Grava registro do header
        gravaRegistro(nomeDeAqruivo, header);

        //Grava o corpo

        String corpo = "";

        for (Livros l : listaDeLivros) {

            corpo = "01";
            corpo += String.format("%-6.6S", l.getId());
            corpo += String.format("%-45.45S", l.getTitulo());
            corpo += String.format("%-45.45S", l.getDescricao());
            corpo += String.format("%-45.45S", l.getAutor());
            corpo += String.format("%-45.45s", l.getEdicao()); // 99,99
            corpo += String.format("%-45.45S", l.getEditora());
            corpo += String.format("%-20.20S", l.getStatusLivro());
            corpo += String.format("%06d", l.getQtdResenhas());
            corpo += String.format("%03d", l.getQtdReservas());
            corpo += String.format("%03d", l.getQtdEstoque());
            corpo += String.format("%03d", l.getQtdReservadoAgora());

            gravaRegistro(nomeDeAqruivo, corpo);

            contaRegistroTipo1++;

        }

        //Monta e grava o trailer;

        String trailer = "09";
        trailer += String.format("%05d", contaRegistroTipo1);
        trailer += String.format("%05d", contaRegistroTipo2);
        gravaRegistro(nomeDeAqruivo, trailer);


    }

    public static void leArquivoTxt(String nomeDeArquivo) {

        BufferedReader entrada = null;
        String registroLido;
        String tipoRegistro;

        try {

            entrada = new BufferedReader(new FileReader(nomeDeArquivo));

        } catch (IOException error) {

            System.out.println("Erro na abertura no arquivo: " + error.getMessage());


        }

        try {

            registroLido = entrada.readLine();
            while (registroLido != null) {

                tipoRegistro = registroLido.substring(0, 2);

                if (tipoRegistro.equalsIgnoreCase("00")) {

                    System.out.println("Eh um reader");
                }

            }

        } catch (IOException error) {

            System.out.println("Erro na leitura do arquivo: " + error.getMessage());

        }

    }

    public String formataRegistroLivroTipo1(String corpo, Livros l) {

        corpo = "01";
        corpo += String.format("%-6.6S", l.getId());
        corpo += String.format("%-45.45S", l.getTitulo());
        corpo += String.format("%-45.45S", l.getDescricao());
        corpo += String.format("%-45.45S", l.getAutor());
        corpo += String.format("%-45.45s", l.getEdicao()); // 99,99
        corpo += String.format("%-45.45S", l.getEditora());
        corpo += String.format("%-20.20S", l.getStatusLivro());
        corpo += String.format("%06d", l.getQtdResenhas());
        corpo += String.format("%03d", l.getQtdReservas());
        corpo += String.format("%03d", l.getQtdEstoque());
        corpo += String.format("%03d", l.getQtdReservadoAgora());

        return corpo;
    }


}
