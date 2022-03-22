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

    @Column(name = "qtd_reservados_agora")
    private Integer qtdReservadosAgora;

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

    @Column(name = "qtd_reservados_total")
    private Integer qtdReservadosTotal;

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

    public Livros(Integer id,
                  String titulo,
                  String descricao,
                  String autor,
                  String edicao,
                  String editora,
                  String infoPrateleira,
                  Integer qtdResenhas,
                  Integer qtdReservadosAgora,
                  Integer qtdDisponiveis,
                  Integer qtdEstoque,
                  Integer fkTbBiblioteca,
                  String linguagem,
                  String corEtiqueta,
                  Integer qtdReservadosTotal) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.edicao = edicao;
        this.editora = editora;
        this.infoPrateleira = infoPrateleira;
        this.qtdResenhas = qtdResenhas;
        this.qtdReservadosAgora = qtdReservadosAgora;
        this.qtdDisponiveis = qtdDisponiveis;
        this.qtdEstoque = qtdEstoque;
        this.fkTbBiblioteca = fkTbBiblioteca;
        this.linguagem = linguagem;
        this.corEtiqueta = corEtiqueta;
        this.qtdReservadosTotal = qtdReservadosTotal;

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
                ", infoPrateleira='" + infoPrateleira + '\'' +
                ", qtdResenhas=" + qtdResenhas +
                ", qtdReservadosAgora=" + qtdReservadosAgora +
                ", qtdDisponiveis=" + qtdDisponiveis +
                ", qtdEstoque=" + qtdEstoque +
                ", fkTbBiblioteca=" + fkTbBiblioteca +
                ", linguagem='" + linguagem + '\'' +
                ", corEtiqueta='" + corEtiqueta + '\'' +
                ", qtdReservadosTotal=" + qtdReservadosTotal +
                '}';
    }
}