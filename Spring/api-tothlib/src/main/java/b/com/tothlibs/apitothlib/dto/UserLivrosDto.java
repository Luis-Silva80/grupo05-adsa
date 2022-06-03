package b.com.tothlibs.apitothlib.dto;

import b.com.tothlibs.apitothlib.entity.Livros;

public class UserLivrosDto {

    private String tombo;
    private String status;

    private String titulo;
    private String descricao;
    private String autor;
    private String edicao;
    private String editora;
    private String infoPrateleira;
    private Integer qtdResenhas;
    private Integer qtdReservadosAgora;
    private Integer qtdDisponiveis;
    private Integer qtdEstoque;
    private Integer fkTbBiblioteca;
    private String linguagem;
    private String corEtiqueta;
    private Integer qtdReservadosTotal;


    public UserLivrosDto(String tombo, String status, Livros livro) {
        this.tombo = tombo;
        this.status = status;
        this.titulo = livro.getTitulo();
        this.descricao = livro.getDescricao();
        this.autor = livro.getAutor();
        this.edicao = livro.getEdicao();
        this.editora = livro.getEditora();
        this.infoPrateleira = livro.getInfoPrateleira();
        this.qtdResenhas = livro.getQtdResenhas();
        this.qtdReservadosAgora = livro.getQtdReservadosAgora();
        this.qtdDisponiveis = livro.getQtdDisponiveis();
        this.qtdEstoque = livro.getQtdEstoque();
        this.fkTbBiblioteca = livro.getFkTbBiblioteca();
        this.linguagem = livro.getLinguagem();
        this.corEtiqueta = livro.getCorEtiqueta();
        this.qtdReservadosTotal = livro.getQtdReservadosTotal();
    }

    public UserLivrosDto() {
    }

    public String getTombo() {
        return tombo;
    }

    public void setTombo(String tombo) {
        this.tombo = tombo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getInfoPrateleira() {
        return infoPrateleira;
    }

    public void setInfoPrateleira(String infoPrateleira) {
        this.infoPrateleira = infoPrateleira;
    }

    public Integer getQtdResenhas() {
        return qtdResenhas;
    }

    public void setQtdResenhas(Integer qtdResenhas) {
        this.qtdResenhas = qtdResenhas;
    }

    public Integer getQtdReservadosAgora() {
        return qtdReservadosAgora;
    }

    public void setQtdReservadosAgora(Integer qtdReservadosAgora) {
        this.qtdReservadosAgora = qtdReservadosAgora;
    }

    public Integer getQtdDisponiveis() {
        return qtdDisponiveis;
    }

    public void setQtdDisponiveis(Integer qtdDisponiveis) {
        this.qtdDisponiveis = qtdDisponiveis;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getFkTbBiblioteca() {
        return fkTbBiblioteca;
    }

    public void setFkTbBiblioteca(Integer fkTbBiblioteca) {
        this.fkTbBiblioteca = fkTbBiblioteca;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public String getCorEtiqueta() {
        return corEtiqueta;
    }

    public void setCorEtiqueta(String corEtiqueta) {
        this.corEtiqueta = corEtiqueta;
    }

    public Integer getQtdReservadosTotal() {
        return qtdReservadosTotal;
    }

    public void setQtdReservadosTotal(Integer qtdReservadosTotal) {
        this.qtdReservadosTotal = qtdReservadosTotal;
    }
}
