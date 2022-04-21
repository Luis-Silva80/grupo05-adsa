package b.com.tothlibs.apitothlib.dto;

import b.com.tothlibs.apitothlib.entity.Livros;

import javax.persistence.Column;
import java.util.List;

public class ResponseLivroMobile {

    private Integer id;
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
    private List<String> tombosDisponiveis;

    public ResponseLivroMobile(Livros livros, List<String> tombosDisponiveis) {
        this.id = livros.getId();
        this.titulo = livros.getTitulo();
        this.descricao = livros.getDescricao();
        this.autor = livros.getAutor();
        this.edicao = livros.getEdicao();
        this.editora = livros.getEditora();
        this.infoPrateleira = livros.getInfoPrateleira();
        this.qtdResenhas = livros.getQtdResenhas();
        this.qtdReservadosAgora = livros.getQtdReservadosAgora();
        this.qtdDisponiveis = livros.getQtdDisponiveis();
        this.qtdEstoque = livros.getQtdEstoque();
        this.fkTbBiblioteca = livros.getFkTbBiblioteca();
        this.linguagem = livros.getLinguagem();
        this.corEtiqueta = livros.getCorEtiqueta();
        this.qtdReservadosTotal = livros.getQtdReservadosTotal();
        this.tombosDisponiveis = tombosDisponiveis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<String> getTombosDisponiveis() {
        return tombosDisponiveis;
    }

    public void setTombosDisponiveis(List<String> tombosDisponiveis) {
        this.tombosDisponiveis = tombosDisponiveis;
    }
}
