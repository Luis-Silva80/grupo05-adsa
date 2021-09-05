package br.thotlibs.entregavelpi.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

abstract public class Usuario {

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private Boolean admin;
    private Date dataNascimento;
    private List<Livro> listLivros;

    public Usuario(Integer id, String nome, String cpf, String email, String telefone, String senha, Boolean admin, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.admin = admin;
        this.dataNascimento = dataNascimento;
        this.listLivros= new ArrayList<Livro>();

    }


    abstract public Optional buscarLivro(Integer id);
    abstract public List<Livro> consultarLista();
    abstract public String AlocarLivro(Integer id);
    abstract public String devolverLivro(Integer id);
    abstract public String renovarAlocacao(Integer id);


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Livro> getListLivros() {
        return listLivros;
    }

    public void setListLivros(Livro livros) {
        this.listLivros.add(livros);
    }
}
