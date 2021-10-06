package br.thotlibs.entregavelpi;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Table(name = "tblivros", indexes = {
        @Index(name = "fk_tbLivros_tbBiblioteca", columnList = "fk_tbLivros_tbBiblioteca")
})
@Entity
public class LivroBanco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tbLivros_id", nullable = false)
    private Integer id;

    @Column(name = "tbLivros_titulo", length = 45)
    private String tblivrosTitulo;

    @Column(name = "tbLivros_descricao", length = 120)
    private String tblivrosDescricao;

    @Column(name = "tbLivros_autor", length = 45)
    private String tblivrosAutor;

    @Column(name = "tbLivros_edicao", length = 45)
    private String tblivrosEdicao;

    @Column(name = "tbLivros_editora", length = 45)
    private String tblivrosEditora;

    @Column(name = "tbLivros_status", length = 15)
    private String tblivrosStatus;

    @ColumnDefault("'0'")
    @Column(name = "tbLivros_qtdResenhas")
    private Integer tblivrosQtdresenhas;

    @Column(name = "tbLivros_qtdReservas")
    private Integer tblivrosQtdreservas;

    @Column(name = "tbLivros_qtdEstoque")
    private Integer tblivrosQtdestoque;

    @Column(name = "tbLivros_qtdReservadoAgora")
    private Integer tblivrosQtdreservadoagora;

    @ManyToOne
    @JoinColumn(name = "fk_tbLivros_tbBiblioteca")
    private Tbbiblioteca fkTblivrosTbbiblioteca;

    public Tbbiblioteca getFkTblivrosTbbiblioteca() {
        return fkTblivrosTbbiblioteca;
    }

    public void setFkTblivrosTbbiblioteca(Tbbiblioteca fkTblivrosTbbiblioteca) {
        this.fkTblivrosTbbiblioteca = fkTblivrosTbbiblioteca;
    }

    public Integer getTblivrosQtdreservadoagora() {
        return tblivrosQtdreservadoagora;
    }

    public void setTblivrosQtdreservadoagora(Integer tblivrosQtdreservadoagora) {
        this.tblivrosQtdreservadoagora = tblivrosQtdreservadoagora;
    }

    public Integer getTblivrosQtdestoque() {
        return tblivrosQtdestoque;
    }

    public void setTblivrosQtdestoque(Integer tblivrosQtdestoque) {
        this.tblivrosQtdestoque = tblivrosQtdestoque;
    }

    public Integer getTblivrosQtdreservas() {
        return tblivrosQtdreservas;
    }

    public void setTblivrosQtdreservas(Integer tblivrosQtdreservas) {
        this.tblivrosQtdreservas = tblivrosQtdreservas;
    }

    public Integer getTblivrosQtdresenhas() {
        return tblivrosQtdresenhas;
    }

    public void setTblivrosQtdresenhas(Integer tblivrosQtdresenhas) {
        this.tblivrosQtdresenhas = tblivrosQtdresenhas;
    }

    public String getTblivrosStatus() {
        return tblivrosStatus;
    }

    public void setTblivrosStatus(String tblivrosStatus) {
        this.tblivrosStatus = tblivrosStatus;
    }

    public String getTblivrosEditora() {
        return tblivrosEditora;
    }

    public void setTblivrosEditora(String tblivrosEditora) {
        this.tblivrosEditora = tblivrosEditora;
    }

    public String getTblivrosEdicao() {
        return tblivrosEdicao;
    }

    public void setTblivrosEdicao(String tblivrosEdicao) {
        this.tblivrosEdicao = tblivrosEdicao;
    }

    public String getTblivrosAutor() {
        return tblivrosAutor;
    }

    public void setTblivrosAutor(String tblivrosAutor) {
        this.tblivrosAutor = tblivrosAutor;
    }

    public String getTblivrosDescricao() {
        return tblivrosDescricao;
    }

    public void setTblivrosDescricao(String tblivrosDescricao) {
        this.tblivrosDescricao = tblivrosDescricao;
    }

    public String getTblivrosTitulo() {
        return tblivrosTitulo;
    }

    public void setTblivrosTitulo(String tblivrosTitulo) {
        this.tblivrosTitulo = tblivrosTitulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}