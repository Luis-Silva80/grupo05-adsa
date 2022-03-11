package b.com.tothlibs.apitothlib.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "tbhistorico", indexes = {
        @Index(name = "fk_tb_perfil_usuario", columnList = "fk_tb_perfil_usuario"),
        @Index(name = "fk_tb_livros", columnList = "fk_tb_livros")
})
@Entity
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fk_tb_exemplar")
    private Integer fkTbExemplar;

    @Column(name = "fk_tb_perfil_usuario")
    private Integer fkTbPerfilUsuario;

    @Column(name = "nome_livro", length = 70)
    private String nomeLivro;

    @Column(name = "nome_aluno", length = 70)
    private String nomePerfilUsuario;

    @Column(name = "acao", length = 15)
    private String acao;

    @Column(name = "tombo")
    private String tombo;

    @Column(name = "nr_exemplar")
    private String nrExemplar;

    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    @Column(name = "data_prev_devolucao")
    private LocalDate dataPrevDevolucao;

    @Column(name = "data_devolvido")
    private LocalDate dataDevolvido;

    public Historico() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkTbExemplar() {
        return fkTbExemplar;
    }

    public void setFkTbExemplar(Integer fkTbExemplar) {
        this.fkTbExemplar = fkTbExemplar;
    }

    public Integer getFkTbPerfilUsuario() {
        return fkTbPerfilUsuario;
    }

    public void setFkTbPerfilUsuario(Integer fkTbPerfilUsuario) {
        this.fkTbPerfilUsuario = fkTbPerfilUsuario;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getNomePerfilUsuario() {
        return nomePerfilUsuario;
    }

    public void setNomePerfilUsuario(String nomePerfilUsuario) {
        this.nomePerfilUsuario = nomePerfilUsuario;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getTombo() {
        return tombo;
    }

    public void setTombo(String tombo) {
        this.tombo = tombo;
    }

    public String getNrExemplar() {
        return nrExemplar;
    }

    public void setNrExemplar(String nrExemplar) {
        this.nrExemplar = nrExemplar;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalDate getDataPrevDevolucao() {
        return dataPrevDevolucao;
    }

    public void setDataPrevDevolucao(LocalDate dataPrevDevolucao) {
        this.dataPrevDevolucao = dataPrevDevolucao;
    }

    public LocalDate getDataDevolvido() {
        return dataDevolvido;
    }

    public void setDataDevolvido(LocalDate dataDevolvido) {
        this.dataDevolvido = dataDevolvido;
    }

    public Historico(Integer id,
                     Integer fkTbExemplar,
                     Integer fkTbPerfilUsuario,
                     String nomeLivro,
                     String nomePerfilUsuario,
                     String acao, String tombo,
                     String nrExemplar,
                     LocalDate dataRegistro,
                     LocalDate dataPrevDevolucao,
                     LocalDate dataDevolvido) {
        this.id = id;
        this.fkTbExemplar = fkTbExemplar;
        this.fkTbPerfilUsuario = fkTbPerfilUsuario;
        this.nomeLivro = nomeLivro;
        this.nomePerfilUsuario = nomePerfilUsuario;
        this.acao = acao;
        this.tombo = tombo;
        this.nrExemplar = nrExemplar;
        this.dataRegistro = dataRegistro;
        this.dataPrevDevolucao = dataPrevDevolucao;
        this.dataDevolvido = dataDevolvido;
    }

    @Override
    public String toString() {
        return "Historico{" +
                "id=" + id +
                ", fkTbExemplar=" + fkTbExemplar +
                ", fkTbPerfilUsuario=" + fkTbPerfilUsuario +
                ", nomeLivro='" + nomeLivro + '\'' +
                ", nomePerfilUsuario='" + nomePerfilUsuario + '\'' +
                ", acao='" + acao + '\'' +
                ", tombo='" + tombo + '\'' +
                ", nrExemplar='" + nrExemplar + '\'' +
                ", dataRegistro=" + dataRegistro +
                ", dataPrevDevolucao=" + dataPrevDevolucao +
                ", dataDevolvido=" + dataDevolvido +
                '}';
    }
}