package b.com.tothlibs.apitothlib.listas;

import b.com.tothlibs.apitothlib.dto.UsuariosPendentesDto;
import b.com.tothlibs.apitothlib.entity.Categoria;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.CategoriaRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class LayoutArquivos<T> {


    private List<T> lista;
    private List<T> listaSecundaria;
    private String nomeArquivo;




    public LayoutArquivos(List<T> lista, String nomeArquivo, List<T> listaSecundaria) {

        this.lista = lista;
        this.nomeArquivo = nomeArquivo;
        this.listaSecundaria = listaSecundaria;

    }

    public LayoutArquivos() {


    }


    public void verificaTipoArquivo() {


        if (this.lista.size() > 0 && this.listaSecundaria.size() > 0) {

            if (this.lista.get(0) instanceof Livros && this.listaSecundaria.get(0) instanceof Categoria) {


                List<Livros> listaDeLivros = (List<Livros>) this.lista;
                List<Categoria> listaDeCategoria = (List<Categoria>) this.listaSecundaria;

                gravaArquivoTxtLivros(listaDeLivros, nomeArquivo, listaDeCategoria);

            } else if (this.lista.get(0) instanceof UsuariosPendentesDto) {
                System.out.println("É DO TIPO ALUNO PENDENTE");
            } else {
                System.out.println("Algo de errado não está certo");
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


    public static void gravaArquivoTxtLivros(List<Livros> listaDeLivros, String nomeDeAqruivo, List<Categoria> listaDeCategoria) {


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

        String corpo1 = "";
        String corpo2 = "";

        for (Livros l : listaDeLivros) {


            gravaRegistro(nomeDeAqruivo, formataRegistroLivroTipo1(corpo1, l));

            gravaRegistro(nomeDeAqruivo, formataRegistroLivroTipo2(corpo2, l, listaDeCategoria));

            contaRegistroTipo1++;
            contaRegistroTipo2++;

        }

        //Monta e grava o trailer;

        String trailer = "09";
        trailer += String.format("%05d", contaRegistroTipo1);
        trailer += String.format("%05d", contaRegistroTipo2);
        gravaRegistro(nomeDeAqruivo, trailer);


    }

    public List<List<T>> leArquivoTxt(String nomeArq) {

        List<List<T>> listasLidas = new ArrayList<>();

        BufferedReader entrada = null;

        // generico
        String registro;
        String tipoRegistro;
        int id;
        LocalDate dataHoraGeracaoDoArquivo;
        String versaoLayout;

        // PARA TIPO REGISTRO 01 --> LIVRO

        String titulo;
        String descricao;
        String autor;
        String edicao;
        String editora;
        String statusLivro;
        int qtdResenhas;
        int qtdReservas;
        int qtdEstoque;
        int qtdReservados;
        int fkTbInstituicao;
        String linguagem;

        // PARA TIPO REGISTRO 02 --CATEGORIA
        String nome;
        int    fkTbLivros;

        // PARA TRAILLER
        int qtdRegistroTipo1 = 0;
        int qtdRegistroTipo2 = 0;
        int qtdRegistroHeader = 0;
        int qtdRegistroTrailer = 0;

        List<Livros>    listaLidaDeLivros = new ArrayList();
        List<Categoria> listaLidaDeCategorias = new ArrayList<>();

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo: " +
                    erro.getMessage());
        }

        try {
            registro = entrada.readLine();  // Lê o primeiro registro do arquivo

            while (registro != null) {      // Enquanto não chegou ao final do arquivo
                // obtém o tipo do registro - primeiros 2 caracteres do registro
                // substring devolve um "pedaço da String",
                // que começa na posição 0 e termina na posição 1 (como num vetor)
                //    0123456
                //    00NOTA
                tipoRegistro = registro.substring(0, 2);

                // Verifica se o tipoRegistro é "00" (header), ou "01" (trailer) ou "02" (corpo)
                if (tipoRegistro.equals("00")) {
                    System.out.println("--> Eh um header");
                    System.out.println("--> Tipo de registro          : " + registro.substring(0, 2));
                    System.out.println("--> Tipo de arquivo           : " + registro.substring(2, 8));
                    System.out.println("--> Data de gravação          : " + registro.substring(8, 18));
                    System.out.println("--> Versão do layout          : " + registro.substring(18, 20));
                    System.out.println("-------------------------------------------------------------");

                    qtdRegistroHeader++;

                } else if (tipoRegistro.equals("09")) {
                    System.out.println("Eh um trailer");
                    qtdRegistroTrailer++;

                    System.out.println("* * *   C O N T A B I L I Z A C A O   * * *");
                    System.out.println("*                                          ");
                    System.out.println("*  Quantidade de header         : " + qtdRegistroHeader);
                    System.out.println("*  Quantidade de trailer        : " + qtdRegistroTrailer);
                    System.out.println("*  Quantidade de registro tipo 1: " + qtdRegistroTipo1);
                    System.out.println("*  Quantidade de registro tipo 2: " + qtdRegistroTipo2);
                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * ");

                    if (qtdRegistroTipo1 == qtdRegistroTipo2) {
                        System.out.println("Quantidade de registros do tipo 1 é compatível com quantidade de registros" +
                                " do tipo 2");
                    } else {
                        System.out.println("Quantidade de registros do tipo 1 é incompatível com quantidade de registros" +
                                " do tipo 2");
                    }
                } else if (tipoRegistro.equals("01")) {

                    System.out.println("Eh um registro de livro");
                    id = 0;
                    titulo = registro.substring(8, 53).trim();
                    descricao = registro.substring(53, 453).trim();
                    autor = registro.substring(453, 498).trim();
                    edicao = registro.substring(498, 543).trim();
                    editora = registro.substring(543, 588).trim();
                    statusLivro = registro.substring(588, 608).trim();
                    qtdResenhas = Integer.valueOf(registro.substring(608, 614));
                    qtdReservas = Integer.valueOf(registro.substring(614, 617));
                    qtdEstoque = Integer.valueOf(registro.substring(617, 620));
                    qtdReservados = Integer.valueOf(registro.substring(620, 623));
                    fkTbInstituicao = Integer.valueOf(registro.substring(623, 629));
                    linguagem = registro.substring(629, 659).trim();

                    Livros l = new Livros(id,
                            titulo,
                            descricao,
                            autor,
                            edicao,
                            editora,
                            statusLivro,
                            qtdResenhas,
                            qtdReservas,
                            qtdEstoque,
                            qtdReservados, fkTbInstituicao, linguagem);

                    qtdRegistroTipo1++;

                    listaLidaDeLivros.add(l);

                } else if (tipoRegistro.equals("02")) {

                    id   = 0;
                    nome = registro.substring(8, 53);
                    fkTbLivros = Integer.valueOf(registro.substring(53,59));

                    Categoria c = new Categoria(id,nome,fkTbLivros);

                    listaLidaDeCategorias.add(c);

                    qtdRegistroTipo2++;

                }

                // lê o próximo registro
                registro = entrada.readLine();
            }

            entrada.close();
        } catch (IOException erro) {
            System.out.println("Erro ao ler arquivo: " + erro.getMessage());
        }



        listasLidas.add((List<T>) listaLidaDeLivros);
        listasLidas.add((List<T>) listaLidaDeCategorias);

        return listasLidas;
    }

    public FilaObj<List<T>> leArquivoTxt2(String nomeArq) {

        FilaObj<List<T>> listasLidas = new FilaObj<>(2);

        BufferedReader entrada = null;

        // generico
        String registro;
        String tipoRegistro;
        int id;
        LocalDate dataHoraGeracaoDoArquivo;
        String versaoLayout;

        // PARA TIPO REGISTRO 01 --> LIVRO

        String titulo;
        String descricao;
        String autor;
        String edicao;
        String editora;
        String statusLivro;
        int qtdResenhas;
        int qtdReservas;
        int qtdEstoque;
        int qtdReservados;
        int fkTbInstituicao;
        String linguagem;

        // PARA TIPO REGISTRO 02 --CATEGORIA
        String nome;
        int    fkTbLivros;

        // PARA TRAILLER
        int qtdRegistroTipo1 = 0;
        int qtdRegistroTipo2 = 0;
        int qtdRegistroHeader = 0;
        int qtdRegistroTrailer = 0;

        List<Livros>    listaLidaDeLivros = new ArrayList();
        List<Categoria> listaLidaDeCategorias = new ArrayList<>();

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo: " +
                    erro.getMessage());
        }

        try {
            registro = entrada.readLine();  // Lê o primeiro registro do arquivo

            while (registro != null) {      // Enquanto não chegou ao final do arquivo
                // obtém o tipo do registro - primeiros 2 caracteres do registro
                // substring devolve um "pedaço da String",
                // que começa na posição 0 e termina na posição 1 (como num vetor)
                //    0123456
                //    00NOTA
                tipoRegistro = registro.substring(0, 2);

                // Verifica se o tipoRegistro é "00" (header), ou "01" (trailer) ou "02" (corpo)
                if (tipoRegistro.equals("00")) {
                    System.out.println("--> Eh um header");
                    System.out.println("--> Tipo de registro          : " + registro.substring(0, 2));
                    System.out.println("--> Tipo de arquivo           : " + registro.substring(2, 8));
                    System.out.println("--> Data de gravação          : " + registro.substring(8, 18));
                    System.out.println("--> Versão do layout          : " + registro.substring(18, 20));
                    System.out.println("-------------------------------------------------------------");

                    qtdRegistroHeader++;

                } else if (tipoRegistro.equals("09")) {
                    System.out.println("Eh um trailer");
                    qtdRegistroTrailer++;

                    System.out.println("* * *   C O N T A B I L I Z A C A O   * * *");
                    System.out.println("*                                          ");
                    System.out.println("*  Quantidade de header         : " + qtdRegistroHeader);
                    System.out.println("*  Quantidade de trailer        : " + qtdRegistroTrailer);
                    System.out.println("*  Quantidade de registro tipo 1: " + qtdRegistroTipo1);
                    System.out.println("*  Quantidade de registro tipo 2: " + qtdRegistroTipo2);
                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * ");

                    if (qtdRegistroTipo1 == qtdRegistroTipo2) {
                        System.out.println("Quantidade de registros do tipo 1 é compatível com quantidade de registros" +
                                " do tipo 2");
                    } else {
                        System.out.println("Quantidade de registros do tipo 1 é incompatível com quantidade de registros" +
                                " do tipo 2");
                    }
                } else if (tipoRegistro.equals("01")) {

                    System.out.println("Eh um registro de livro");
                    id = 0;
                    titulo = registro.substring(8, 53).trim();
                    descricao = registro.substring(53, 453).trim();
                    autor = registro.substring(453, 498).trim();
                    edicao = registro.substring(498, 543).trim();
                    editora = registro.substring(543, 588);
                    statusLivro = registro.substring(588, 608);
                    qtdResenhas = Integer.valueOf(registro.substring(608, 614));
                    qtdReservas = Integer.valueOf(registro.substring(614, 617));
                    qtdEstoque = Integer.valueOf(registro.substring(617, 620));
                    qtdReservados = Integer.valueOf(registro.substring(620, 623));
                    fkTbInstituicao = Integer.valueOf(registro.substring(623, 629));
                    linguagem = registro.substring(629, 659).trim();

                    Livros l = new Livros(id,
                            titulo,
                            descricao,
                            autor,
                            edicao,
                            editora,
                            statusLivro,
                            qtdResenhas,
                            qtdReservas,
                            qtdEstoque,
                            qtdReservados, fkTbInstituicao, linguagem);

                    qtdRegistroTipo1++;

                    listaLidaDeLivros.add(l);

                } else if (tipoRegistro.equals("02")) {

                    id   = 0;
                    nome = registro.substring(8, 53);
                    fkTbLivros = Integer.valueOf(registro.substring(53,59));

                    Categoria c = new Categoria(id,nome,fkTbLivros);

                    listaLidaDeCategorias.add(c);

                    qtdRegistroTipo2++;

                }

                // lê o próximo registro
                registro = entrada.readLine();
            }

            entrada.close();
        } catch (IOException erro) {
            System.out.println("Erro ao ler arquivo: " + erro.getMessage());
        }



        listasLidas.insert((List<T>) listaLidaDeLivros);
        listasLidas.insert((List<T>) listaLidaDeCategorias);

        return listasLidas;
    }

    public static String formataRegistroLivroTipo1(String corpo, Livros l) {

        corpo = "01";
        corpo += String.format("%06d", l.getId());
        corpo += String.format("%-45.45S", l.getTitulo());
        corpo += String.format("%-400.400S", l.getDescricao().replace("\n", " ").replace("  ", " "));
        corpo += String.format("%-45.45S", l.getAutor());
        corpo += String.format("%-45.45s", l.getEdicao()); // 99,99
        corpo += String.format("%-45.45S", l.getEditora());
        corpo += String.format("%-20.20S", l.getStatusLivro());
        corpo += String.format("%06d", l.getQtdResenhas());
        corpo += String.format("%03d", l.getQtdReservas());
        corpo += String.format("%03d", l.getQtdEstoque());
        corpo += String.format("%03d", l.getQtdReservadoAgora());
        corpo += String.format("%06d", l.getFkTbbiblioteca());
        corpo += String.format("%-30.30S", l.getLinguagem());

        return corpo;
    }

    public static String formataRegistroLivroTipo2(String corpo2, Livros l, List<Categoria> listaDeCategoria) {

        corpo2 = "02";

        if (listaDeCategoria.isEmpty()) {


            corpo2 += String.format("%06d", 0);
            corpo2 += String.format("%-45.45s", "ESSE LIVRO NÃO POSSUI CATEGORIA");
            corpo2 += String.format("%06d", 0);

            return corpo2;

        } else {

            for (Categoria c : listaDeCategoria) {

                if (c.getFkTblivros().equals(l.getId())) {


                    corpo2 += String.format("%06d", c.getId());
                    corpo2 += String.format("%-45.45S", c.getNome());
                    corpo2 += String.format("%06d", c.getFkTblivros());

                    return corpo2;
                }

            }
        }

        corpo2 += String.format("%06d", 0);
        corpo2 += String.format("%-45.45s", "ESSE LIVRO NÃO POSSUI CATEGORIA");
        corpo2 += String.format("%06d", 0);

        return corpo2;
    }


}
