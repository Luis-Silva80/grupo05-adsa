package br.thotlibs.entregavelpi.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricoAcoes {

    List<Acao> listAcoes;

    public HistoricoAcoes() {
        this.listAcoes = new ArrayList<Acao>();
    }

    public String adicionarAcao(Acao acao){

        listAcoes.add(acao);

        return "Adicionado com sucesso!!";

    }


    public List<Acao> bucarListaDeAcoes(String aluno){

        return listAcoes.stream()
                .filter(acao -> acao.getNomeUsuario().equals(aluno))
                .filter(acao2 -> acao2 instanceof Alocacao)
                .collect(Collectors.toList());

    }
}
