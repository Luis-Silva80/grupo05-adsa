package b.com.tothlibs.apitothlib.entity;

import javax.persistence.*;

@Table(name = "tbperfilusuario", indexes = {
        @Index(name = "fk_tb_instituicao", columnList = "fk_tb_instituicao")
})
@Entity
public class PerfilUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 70)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "email", length = 70)
    private String email;

    @Column(name = "telefone", length = 14)
    private String telefone;

    @Column(name = "senha", length = 35)
    private String senha;

    @Column(name = "usuario_admin")
    private Integer usuarioAdmin;

    @Column(name = "pontos")
    private Long pontos;

    @Column(name = "qtd_livros_lidos")
    private Integer qtdLivrosLidos;

    @Column(name = "qtd_resenhas")
    private Integer qtdResenhas;

    @Column(name = "fk_tb_instituicao")
    private Integer fkTbInstituicao;

    public Integer getFkTbInstituicao() {
        return fkTbInstituicao;
    }

    public void setFkTbInstituicao(Integer fkTbInstituicao) {
        this.fkTbInstituicao = fkTbInstituicao;
    }

    public Integer getQtdResenhas() {
        return qtdResenhas;
    }

    public void setQtdResenhas(Integer qtdResenhas) {
        this.qtdResenhas = qtdResenhas;
    }

    public Integer getQtdLivrosLidos() {
        return qtdLivrosLidos;
    }

    public void setQtdLivrosLidos(Integer qtdLivrosLidos) {
        this.qtdLivrosLidos = qtdLivrosLidos;
    }

    public Long getPontos() {
        return pontos;
    }

    public void setPontos(Long pontos) {
        this.pontos = pontos;
    }

    public Integer getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(Integer usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "PerfilUsuario" +
                "\nid=" + id +
                "\n, nome='" + nome + '\'' +
                "\n, cpf='" + cpf + '\'' +
                "\n, email='" + email + '\'' +
                "\n, telefone='" + telefone + '\'' +
                "\n, senha='" + senha + '\'' +
                "\n, usuarioAdmin=" + usuarioAdmin +
                "\n, pontos=" + pontos +
                "\n, qtdLivrosLidos=" + qtdLivrosLidos +
                "\n, qtdResenhas=" + qtdResenhas +
                "\n, fkTbInstituicao=" + fkTbInstituicao;
    }
}