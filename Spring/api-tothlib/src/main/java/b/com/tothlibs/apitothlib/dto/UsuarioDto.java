package b.com.tothlibs.apitothlib.dto;

import b.com.tothlibs.apitothlib.entity.PerfilUsuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsuarioDto {

    private String nome;
    private Boolean autenticado;
    private String autenticadoEm;
    private String logoffRealizadoEm;


    String hojeFormatado = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

    public UsuarioDto(PerfilUsuario usuario) {

        this.nome = usuario.getNome();
        this.autenticado = true;
        this.autenticadoEm = hojeFormatado;

    }

    public String getNome() {
        return nome;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public String getAutenticadoEm() {
        return autenticadoEm;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public String getLogoffRealizadoEm() {
        return logoffRealizadoEm;
    }

    public void setLogoff() {
        this.logoffRealizadoEm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
