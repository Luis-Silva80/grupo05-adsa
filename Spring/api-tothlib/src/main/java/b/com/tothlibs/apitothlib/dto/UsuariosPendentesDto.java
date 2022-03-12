package b.com.tothlibs.apitothlib.dto;

import b.com.tothlibs.apitothlib.entity.Historico;

import javax.persistence.Column;
import java.time.LocalDate;

public class UsuariosPendentesDto {


    private Integer id;
    private Integer fkTbExemplar;
    private Integer fkTbPerfilUsuario;
    private LocalDate dataRegistro;
    private String nomeLivro;
    private String nomePerfilUsuario;
    private String acao;
    private LocalDate dataPrevDevolucao;
    private Integer livrosReservados;

    public UsuariosPendentesDto(Historico historico,
                                Integer livrosReservados) {
        this.id = historico.getId();
        this.fkTbExemplar = historico.getFkTbExemplar();
        this.fkTbPerfilUsuario = historico.getFkTbPerfilUsuario();
        this.dataRegistro = historico.getDataRegistro();
        this.nomeLivro = historico.getNomeLivro();
        this.nomePerfilUsuario = historico.getNomePerfilUsuario();
        this.acao = historico.getAcao();
        this.dataPrevDevolucao = historico.getDataPrevDevolucao();
        this.livrosReservados = livrosReservados;
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

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
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

    public LocalDate getDataPrevDevolucao() {
        return dataPrevDevolucao;
    }

    public void setDataPrevDevolucao(LocalDate dataPrevDevolucao) {
        this.dataPrevDevolucao = dataPrevDevolucao;
    }

    public Integer getLivrosReservados() {
        return livrosReservados;
    }

    public void setLivrosReservados(Integer livrosReservados) {
        this.livrosReservados = livrosReservados;
    }

    @Override
    public String toString() {
        return "UsuariosPendentesDto{" +
                "id=" + id +
                ", fkTbExemplar=" + fkTbExemplar +
                ", fkTbPerfilUsuario=" + fkTbPerfilUsuario +
                ", dataRegistro=" + dataRegistro +
                ", nomeLivro='" + nomeLivro + '\'' +
                ", nomePerfilUsuario='" + nomePerfilUsuario + '\'' +
                ", acao='" + acao + '\'' +
                ", dataPrevDevolucao=" + dataPrevDevolucao +
                ", livrosReservados=" + livrosReservados +
                '}';
    }
}
