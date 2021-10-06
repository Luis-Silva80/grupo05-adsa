package com.conexao.testebancoduastabelas;

import javax.persistence.*;

@Table(name = "tbinstituicao")
@Entity
public class Tbinstituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tb_instituicao_razao_social", length = 70)
    private String tbinstituicaoRazaosocial;

    @Column(name = "tb_instituicao_cep", length = 8)
    private String tbinstituicaoCep;

    @Column(name = "tb_instituicao_email", length = 70)
    private String tbinstituicaoEmail;

    @Column(name = "tb_instituicao_telefone", length = 15)
    private String tbinstituicaoTelefone;

    public String getTbinstituicaoTelefone() {
        return tbinstituicaoTelefone;
    }

    public void setTbinstituicaoTelefone(String tbinstituicaoTelefone) {
        this.tbinstituicaoTelefone = tbinstituicaoTelefone;
    }

    public String getTbinstituicaoEmail() {
        return tbinstituicaoEmail;
    }

    public void setTbinstituicaoEmail(String tbinstituicaoEmail) {
        this.tbinstituicaoEmail = tbinstituicaoEmail;
    }

    public String getTbinstituicaoCep() {
        return tbinstituicaoCep;
    }

    public void setTbinstituicaoCep(String tbinstituicaoCep) {
        this.tbinstituicaoCep = tbinstituicaoCep;
    }

    public String getTbinstituicaoRazaosocial() {
        return tbinstituicaoRazaosocial;
    }

    public void setTbinstituicaoRazaosocial(String tbinstituicaoRazaosocial) {
        this.tbinstituicaoRazaosocial = tbinstituicaoRazaosocial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}