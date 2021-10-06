package b.com.tothlibs.apitothlib.listas;


import b.com.tothlibs.apitothlib.controlers.AlunoController;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ListaDeObjetos<T> {

    private PerfilUsuario[] vetorUsuarios;
    private int nroElem;

    public ListaDeObjetos(List<PerfilUsuario> listaPerfilUsuario) {
        this.vetorUsuarios = new PerfilUsuario[listaPerfilUsuario.size()];
        this.nroElem = 0;
    }

    public ResponseEntity filtrarNomeDecrescente(List<T> listaDeUsuarios) {


        listaDeUsuarios = listaDeUsuarios.stream()
                .sorted((Comparator<? super T>) Comparator.comparing(PerfilUsuario::getNome)
                        .reversed()).collect(Collectors.toList());


        T[] vetorDeUsuariosDecrescente = (T[]) (new Object[listaDeUsuarios.size()]);

        for (Integer c = 0; c < vetorDeUsuariosDecrescente.length; c++) {

            vetorDeUsuariosDecrescente[c] = (T) listaDeUsuarios.get(c);

        }

        return ResponseEntity.status(200).body(vetorDeUsuariosDecrescente);
    }

    public ResponseEntity filtrarNomeCrescente(List<PerfilUsuario> listaDeUsuarios) {


        listaDeUsuarios = listaDeUsuarios.stream()
                .sorted(Comparator.comparing(PerfilUsuario::getNome)).collect(Collectors.toList());

        T[] vetorDeUsuariosCrescente = (T[]) new Object[listaDeUsuarios.size()];

        for (Integer c = 0; c < vetorDeUsuariosCrescente.length; c++) {

            vetorDeUsuariosCrescente[c] = (T) listaDeUsuarios.get(c);

        }

        return ResponseEntity.status(200).body(vetorDeUsuariosCrescente);
    }
}


//  public ResponseEntity exportarCsv(List<PerfilUsuario> listaDeUsuarios){
//
//
//
//
//        return ResponseEntity.status(200).build();
//   }

//    public ResponseEntity gravaArquivoCsv(List<PerfilUsuario> lista, String nomeArquivo) {
//        FileWriter arq = null;  // Objeto que representa o arquivo para escrita
//        Formatter saida = null; // objeto que será usado para escrita no arquivo
//        Boolean error = false;
//        nomeArquivo += ".csv";  // acrescenta a extensão .csv ao nome do arquivo
//
//        // Bloco try-catch para abrir o arquivo
//        try {
//            // a criação do objeto fileWriter faz com que o arquivo aberto ou criado
//            arq = new FileWriter(nomeArquivo, true); // append = acrescentar ao arquivo
//            saida = new Formatter(arq);
//        } catch (IOException e) {
//            System.out.println("Erro ao abrir o arquivo");
//            System.exit(1);
//        }
//
//        // Bloco try-catch para gravar no arquivo
//        try {
//            // Percorro a lista de cachorros
//            for (int i = 0; i < lista.size(); i++) {
//                PerfilUsuario perfil = lista.get(i);
//                // Gravo os dados desse objeto no arquivo
//                // Separando cada campo por um ;
//                saida.format("%d;%s;%s;%.2f\n", perfil., dog.getNome(), dog.getPorte(), dog.getPeso());
//            }
//        } catch (FormatterClosedException e) {
//            System.out.println("Erro ao gravar o arquivo");
//            error = true;
//        } finally {
//            saida.close();
//            try {
//                arq.close();
//            } catch (IOException e) {
//                System.out.println("Erro ao fechar o arquivo");
//                error = true;
//            }
//            if (error) {
//                System.exit(1);
//            }
//        }
//    }
//
//    public static void leExibeArquivoCsv(String nomeArquivo) {
//        FileReader arq = null;  // Objeto que representa o arquivo para leitura
//        Scanner entrada = null; // objeto usado para ler do arquivo
//        Boolean deuRuim = false;
//        nomeArquivo += ".csv";  // acrescenta a extensão .csv ao nome do arquivo
//
//        try {
//            arq = new FileReader(nomeArquivo);
//            entrada = new Scanner(arq).useDelimiter(";|\\n");
//        } catch (FileNotFoundException e) {
//            System.out.println("Arquivo não encontrado");
//            System.exit(1);
//        }
//
//        // Bloco try-catch para ler do arquivo
//        try {
//
//            System.out.printf("%4s %-15s %-9s %s\n",
//                    "ID",
//                    "NOME",
//                    "PORTE",
//                    "PESO");
//
//            while (entrada.hasNext()) {
//                Integer id = entrada.nextInt();
//                String nome = entrada.next();
//                String porte = entrada.next();
//                Double peso = entrada.nextDouble();
//
//                System.out.printf("%4d %-15s %-9s %4.1f\n", id, nome, porte, peso);
//
//            }
//        } catch (NoSuchElementException erro) {
//
//            System.out.println("Arquivo com problemas");
//
//        } catch (IllegalStateException erro){
//
//            System.out.println("Erro na leitura do arquivo");
//
//        } finally {
//
//            entrada.close();
//
//            try {
//                arq.close();
//            } catch (IOException erro){
//
//                System.out.println("Erro ao fechar arquivo");
//                deuRuim = true;
//
//            }
//
//            if(deuRuim){
//
//                System.exit(1);
//            }
//
//        }
//}
//




