package br.thotlibs.entregavelpi.entity;

import java.time.LocalDate;

public class Alocacao extends Acao{

    private LocalDate dataDevolucao;

    public Alocacao(Integer idUsuario, Integer idLivro, String nomeUsuario, String tituloLivro, LocalDate dataDevolucao) {
        super(idUsuario, idLivro, nomeUsuario, tituloLivro);
        this.dataDevolucao = dataDevolucao;
    }


    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
