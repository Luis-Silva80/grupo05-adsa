package b.com.tothlibs.apitothlib.entity;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "tbresenha", indexes = {
        @Index(name = "fk_tb_perfil_usuario", columnList = "fk_tb_perfil_usuario"),
        @Index(name = "fk_tb_livros", columnList = "fk_tb_livros")
})
@Entity
public class Resenha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "data_publicacao")
    private Instant dataPublicacao;

    @Column(name = "conteudo_publicacao", length = 120)
    private String conteudoPublicacao;

    @Column(name = "fk_tb_livros")
    private Integer fkTbLivros;

    @Column(name = "fk_tb_perfil_usuario")
    private Integer fkTbPerfilUsuario;

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

    public String getConteudoPublicacao() {
        return conteudoPublicacao;
    }

    public void setConteudoPublicacao(String conteudoPublicacao) {
        this.conteudoPublicacao = conteudoPublicacao;
    }

    public Instant getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Instant dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}