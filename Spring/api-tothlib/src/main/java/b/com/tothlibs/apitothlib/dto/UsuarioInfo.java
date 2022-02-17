package b.com.tothlibs.apitothlib.dto;

import b.com.tothlibs.apitothlib.entity.Historico;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioInfo {

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private Integer usuarioAdmin;
    private Long pontos;
    private Integer qtdLivrosLidos;
    private Integer qtdResenhas;
    private Integer fkTbInstituicao;
    private Integer livrosReservados;
    private List<Livros> livrosLidos;
    private Historico dadosLivros;

    public UsuarioInfo(PerfilUsuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.senha = usuario.retornaSenha();
        this.usuarioAdmin = usuario.getUsuarioAdmin();
        this.pontos = usuario.getPontos();
        this.qtdLivrosLidos = usuario.getQtdLivrosLidos();
        this.qtdResenhas = usuario.getQtdResenhas();
        this.fkTbInstituicao = usuario.getFkTbInstituicao();
        this.livrosReservados = usuario.getLivrosReservados();

        this.livrosLidos = new ArrayList<>();
    }

    public Historico getDadosLivros() {
        return dadosLivros;
    }

    public void setDadosLivros(Historico dadosLivros) {
        this.dadosLivros = dadosLivros;
    }

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

    public Integer getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(Integer usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public Long getPontos() {
        return pontos;
    }

    public void setPontos(Long pontos) {
        this.pontos = pontos;
    }

    public Integer getQtdLivrosLidos() {
        return qtdLivrosLidos;
    }

    public void setQtdLivrosLidos(Integer qtdLivrosLidos) {
        this.qtdLivrosLidos = qtdLivrosLidos;
    }

    public Integer getQtdResenhas() {
        return qtdResenhas;
    }

    public void setQtdResenhas(Integer qtdResenhas) {
        this.qtdResenhas = qtdResenhas;
    }

    public Integer getFkTbInstituicao() {
        return fkTbInstituicao;
    }

    public void setFkTbInstituicao(Integer fkTbInstituicao) {
        this.fkTbInstituicao = fkTbInstituicao;
    }

    public Integer getLivrosReservados() {
        return livrosReservados;
    }

    public void setLivrosReservados(Integer livrosReservados) {
        this.livrosReservados = livrosReservados;
    }

    public List<Livros> getLivrosLidos() {
        return livrosLidos;
    }

    public void setLivrosLidos(List<Livros> livrosLidos) {
        this.livrosLidos = livrosLidos;
    }
}
