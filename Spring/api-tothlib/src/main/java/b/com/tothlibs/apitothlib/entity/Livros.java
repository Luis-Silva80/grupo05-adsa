package b.com.tothlibs.apitothlib.entity;

import javax.persistence.*;

@Table(name = "tblivros", indexes = {
        @Index(name = "fk_tb_biblioteca", columnList = "fk_tb_biblioteca")
})
@Entity
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo", length = 45)
    private String titulo;

    @Column(name = "descricao", length = 120)
    private String descricao;

    @Column(name = "autor", length = 45)
    private String autor;

    @Column(name = "edicao", length = 45)
    private String edicao;

    @Column(name = "editora", length = 45)
    private String editora;

    @Column(name = "info_prateleira", length = 50)
    private String infoPrateleira;

    @Column(name = "qtd_resenhas")
    private Integer qtdResenhas;

    @Column(name = "qtd_reservas")
    private Integer qtdReservas;

    @Column(name = "qtd_disponiveis")
    private Integer qtdDisponiveis;

    @Column(name = "qtd_estoque")
    private Integer qtdEstoque;

    @Column(name = "fk_tb_biblioteca")
    private Integer fkTbBiblioteca;

    @Column(name = "linguagem")
    private String linguagem;

    @Column( name = "cor_etiqueta" )
    private String corEtiqueta;

    public String getInfoPrateleira() {
        return infoPrateleira;
    }

    public void setInfoPrateleira(String infoPrateleira) {
        this.infoPrateleira = infoPrateleira;
    }

    public Integer getQtdDisponiveis() {
        return qtdDisponiveis;
    }

    public void setQtdDisponiveis(Integer qtdDisponiveis) {
        this.qtdDisponiveis = qtdDisponiveis;
    }

    public Integer getQtdReservadosAgora() {
        return qtdReservadosAgora;
    }

    public void setQtdReservadosAgora(Integer qtdReservadosAgora) {
        this.qtdReservadosAgora = qtdReservadosAgora;
    }

    public Integer getFkTbBiblioteca() {
        return fkTbBiblioteca;
    }

    public void setFkTbBiblioteca(Integer fkTbBiblioteca) {
        this.fkTbBiblioteca = fkTbBiblioteca;
    }

    public String getCorEtiqueta() {
        return corEtiqueta;
    }

    public void setCorEtiqueta(String corEtiqueta) {
        this.corEtiqueta = corEtiqueta;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public Integer getFkTbbiblioteca() {
        return fkTbBiblioteca;
    }

    public void setFkTbbiblioteca(Integer fkTbbiblioteca) {
        this.fkTbBiblioteca = fkTbbiblioteca;
    }

    public Integer getQtdReservadoAgora() {
        return qtdReservadosAgora;
    }

    public void setQtdReservadoAgora(Integer qtdReservadoAgora) {
        this.qtdReservadosAgora = qtdReservadoAgora;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getQtdReservas() {
        return qtdReservas;
    }

    public void setQtdReservas(Integer qtdReservas) {
        this.qtdReservas = qtdReservas;
    }

    public Integer getQtdResenhas() {
        return qtdResenhas;
    }

    public void setQtdResenhas(Integer qtdResenhas) {
        this.qtdResenhas = qtdResenhas;
    }

    public String getStatusLivro() {
        return statusLivro;
    }

    public void setStatusLivro(String statusLivro) {
        this.statusLivro = statusLivro;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Livros() {
    }

    public Livros(Integer id, String titulo, String descricao, String autor, String edicao, String editora,
                  String statusLivro, Integer qtdResenhas, Integer qtdReservas, Integer qtdEstoque,
                  Integer qtdReservadosAgora, Integer fkTbBiblioteca, String linguagem) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.edicao = edicao;
        this.editora = editora;
        this.statusLivro = statusLivro;
        this.qtdResenhas = qtdResenhas;
        this.qtdReservas = qtdReservas;
        this.qtdEstoque = qtdEstoque;
        this.qtdReservadosAgora = qtdReservadosAgora;
        this.fkTbBiblioteca = fkTbBiblioteca;
        this.linguagem = linguagem;
    }

    @Override
    public String toString() {
        return "Livros{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", autor='" + autor + '\'' +
                ", edicao='" + edicao + '\'' +
                ", editora='" + editora + '\'' +
                ", statusLivro='" + statusLivro + '\'' +
                ", qtdResenhas=" + qtdResenhas +
                ", qtdReservas=" + qtdReservas +
                ", qtdEstoque=" + qtdEstoque +
                ", qtdReservadosAgora=" + qtdReservadosAgora +
                ", fkTbBiblioteca=" + fkTbBiblioteca +
                ", linguagem='" + linguagem + '\'' +
                '}';
    }
}