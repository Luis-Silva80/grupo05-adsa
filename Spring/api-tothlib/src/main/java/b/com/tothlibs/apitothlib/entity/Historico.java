package b.com.tothlibs.apitothlib.entity;

import javax.persistence.*;
import java.time.Instant;

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

    @Column(name = "fk_tb_livros")
    private Integer fkTbLivros;

    @Column(name = "fk_tb_perfil_usuario")
    private Integer fkTbPerfilUsuario;

    @Column(name = "data_livro_historico")
    private Instant dataLivroHistorico;

    @Column(name = "nome_livro", length = 70)
    private String nomeLivro;

    @Column(name = "nome_perfil_usuario", length = 70)
    private String nomePerfilUsuario;

    @Column(name = "acao", length = 15)
    private String acao;

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getNomePerfilUsuario() {
        return nomePerfilUsuario;
    }

    public void setNomePerfilUsuario(String nomePerfilUsuario) {
        this.nomePerfilUsuario = nomePerfilUsuario;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public Instant getDataLivroHistorico() {
        return dataLivroHistorico;
    }

    public void setDataLivroHistorico(Instant dataLivroHistorico) {
        this.dataLivroHistorico = dataLivroHistorico;
    }

    public Integer getFkTbPerfilUsuario() {
        return fkTbPerfilUsuario;
    }

    public void setFkTbPerfilUsuario(Integer fkTbPerfilUsuario) {
        this.fkTbPerfilUsuario = fkTbPerfilUsuario;
    }

    public Integer getFkTbLivros() {
        return fkTbLivros;
    }

    public void setFkTbLivros(Integer fkTbLivros) {
        this.fkTbLivros = fkTbLivros;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}