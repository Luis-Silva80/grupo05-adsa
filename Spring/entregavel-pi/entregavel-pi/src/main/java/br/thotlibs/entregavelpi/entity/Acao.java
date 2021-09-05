package br.thotlibs.entregavelpi.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

abstract public class Acao {

    private Integer idUsuario;
    private Integer idLivro;
    private String nomeUsuario;
    private String tituloLivro;
    private LocalDateTime data;


    public Acao(Integer idUsuario, Integer idLivro, String nomeUsuario, String tituloLivro) {
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.nomeUsuario = nomeUsuario;
        this.tituloLivro = tituloLivro;
        this.data = LocalDateTime.now();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
