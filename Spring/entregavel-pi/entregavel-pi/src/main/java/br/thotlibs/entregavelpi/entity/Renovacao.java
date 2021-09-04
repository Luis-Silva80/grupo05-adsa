package br.thotlibs.entregavelpi.entity;

import java.time.LocalDate;

public class Renovacao extends Acao{

    private LocalDate dataDevolucao;

    public Renovacao(Integer idUsuario, Integer idLivro, String nomeUsuario, String tituloLivro, LocalDate dataDevolucao) {
        super(idUsuario, idLivro, nomeUsuario, tituloLivro);
        this.dataDevolucao = dataDevolucao;
    }
}
