package b.com.tothlibs.apitothlib.dto;

import b.com.tothlibs.apitothlib.entity.Historico;

import java.time.LocalDate;

public class UsuariosPendentesDto {


    private Integer id;
    private Integer fkTbLivros;
    private Integer fkTbPerfilUsuario;
    private LocalDate dataLivroHistorico;
    private String nomeLivro;
    private String nomePerfilUsuario;
    private String acao;
    private LocalDate dataDevolucao;
    private Integer livrosReservados;

    public UsuariosPendentesDto(Historico historico,
                                Integer livrosReservados) {
        this.id = historico.getId();
        this.fkTbLivros = historico.getFkTbLivros();
        this.fkTbPerfilUsuario = historico.getFkTbPerfilUsuario();
        this.dataLivroHistorico = historico.getDataLivroHistorico();
        this.nomeLivro = historico.getNomeLivro();
        this.nomePerfilUsuario = historico.getNomePerfilUsuario();
        this.acao = historico.getAcao();
        this.dataDevolucao = historico.getDataDevolucao();
        this.livrosReservados = livrosReservados;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkTbLivros() {
        return fkTbLivros;
    }

    public void setFkTbLivros(Integer fkTbLivros) {
        this.fkTbLivros = fkTbLivros;
    }

    public Integer getFkTbPerfilUsuario() {
        return fkTbPerfilUsuario;
    }

    public void setFkTbPerfilUsuario(Integer fkTbPerfilUsuario) {
        this.fkTbPerfilUsuario = fkTbPerfilUsuario;
    }

    public LocalDate getDataLivroHistorico() {
        return dataLivroHistorico;
    }

    public void setDataLivroHistorico(LocalDate dataLivroHistorico) {
        this.dataLivroHistorico = dataLivroHistorico;
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

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
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
                ", fkTbLivros=" + fkTbLivros +
                ", fkTbPerfilUsuario=" + fkTbPerfilUsuario +
                ", dataLivroHistorico=" + dataLivroHistorico +
                ", nomeLivro='" + nomeLivro + '\'' +
                ", nomePerfilUsuario='" + nomePerfilUsuario + '\'' +
                ", acao='" + acao + '\'' +
                ", dataDevolucao=" + dataDevolucao +
                ", livrosReservados=" + livrosReservados +
                '}';
    }
}
