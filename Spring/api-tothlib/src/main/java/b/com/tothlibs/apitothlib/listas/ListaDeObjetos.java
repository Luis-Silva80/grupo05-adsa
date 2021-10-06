package b.com.tothlibs.apitothlib.listas;


import b.com.tothlibs.apitothlib.controlers.AlunoController;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDeObjetos {

    private PerfilUsuario[] vetorUsuarios;
    private int nroElem;

    public ListaDeObjetos(List<PerfilUsuario> listaPerfilUsuario) {
        this.vetorUsuarios = new PerfilUsuario[listaPerfilUsuario.size()];
        this.nroElem = 0;
    }

    public ResponseEntity filtrarNomeDecrescente(List<PerfilUsuario> listaDeUsuarios) {

        List<PerfilUsuario> listaDeUsuariosDecrescente;


        listaDeUsuariosDecrescente = listaDeUsuarios.stream()
                .sorted(Comparator.comparing(PerfilUsuario::getNome)
                .reversed()).collect(Collectors.toList());


        return ResponseEntity.status(200).body(listaDeUsuariosDecrescente);
    }

    public  ResponseEntity filtrarNomeCrescente(List<PerfilUsuario> listaDeUsuarios){

        List<PerfilUsuario> listaDeUsuariosCrescente;


        listaDeUsuariosCrescente = listaDeUsuarios.stream()
                .sorted(Comparator.comparing(PerfilUsuario::getNome)).collect(Collectors.toList());

        return ResponseEntity.status(200).body(listaDeUsuariosCrescente);
    }

   public ResponseEntity exportarCsv(List<PerfilUsuario> listaDeUsuarios){

        
        return ResponseEntity.status(200).build();
   }

}





