package b.com.tothlibs.apitothlib.entity;

import javax.persistence.*;

@Table(name = "tblivros", indexes = {
        @Index(name = "fk_tbBiblioteca", columnList = "fk_tbBiblioteca")
})
@Entity
public class    Livros {
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

    @Column(name = "status_livro", length = 15)
    private String statusLivro;

    @Column(name = "qtd_resenhas")
    private Integer qtdResenhas;

    @Column(name = "qtd_reservas")
    private Integer qtdReservas;

    @Column(name = "qtd_estoque")
    private Integer qtdEstoque;

    @Column(name = "qtd_reservado_agora")
    private Integer qtdReservadoAgora;

    @Column(name = "fk_tbBiblioteca")
    private Integer fkTbbiblioteca;

    public Integer getFkTbbiblioteca() {
        return fkTbbiblioteca;
    }

    public void setFkTbbiblioteca(Integer fkTbbiblioteca) {
        this.fkTbbiblioteca = fkTbbiblioteca;
    }

    public Integer getQtdReservadoAgora() {
        return qtdReservadoAgora;
    }

    public void setQtdReservadoAgora(Integer qtdReservadoAgora) {
        this.qtdReservadoAgora = qtdReservadoAgora;
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
}