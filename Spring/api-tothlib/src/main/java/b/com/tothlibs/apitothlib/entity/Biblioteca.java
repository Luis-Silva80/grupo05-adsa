package b.com.tothlibs.apitothlib.entity;

import javax.persistence.*;

@Table(name = "tbbiblioteca", indexes = {
        @Index(name = "fk_tb_perfil_usuario", columnList = "fk_tb_perfil_usuario")
})
@Entity
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "qtd_livros")
    private Integer qtdLivros;

    @Column(name = "fk_tb_perfil_usuario")
    private Integer fkTbPerfilUsuario;

    public Integer getFkTbPerfilUsuario() {
        return fkTbPerfilUsuario;
    }

    public void setFkTbPerfilUsuario(Integer fkTbPerfilUsuario) {
        this.fkTbPerfilUsuario = fkTbPerfilUsuario;
    }

    public Integer getQtdLivros() {
        return qtdLivros;
    }

    public void setQtdLivros(Integer qtdLivros) {
        this.qtdLivros = qtdLivros;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}