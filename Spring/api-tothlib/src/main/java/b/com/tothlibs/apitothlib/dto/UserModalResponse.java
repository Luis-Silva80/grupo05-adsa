package b.com.tothlibs.apitothlib.dto;

import b.com.tothlibs.apitothlib.entity.Historico;

import java.time.LocalDate;

public class UserModalResponse {

    private LocalDate reservadoEm;
    private LocalDate devolverEm;
    private String nome;

    public UserModalResponse(LocalDate reservadoEm, LocalDate devolverEm, String nome) {
        this.reservadoEm = reservadoEm;
        this.devolverEm = devolverEm;
        this.nome = nome;
    }

    public UserModalResponse(Historico registro) {
        this.reservadoEm = registro.getDataRegistro();
        this.devolverEm = registro.getDataPrevDevolucao();
        this.nome = registro.getNomeLivro();
    }

    public UserModalResponse() {
    }

    public LocalDate getReservadoEm() {
        return reservadoEm;
    }

    public void setReservadoEm(LocalDate reservadoEm) {
        this.reservadoEm = reservadoEm;
    }

    public LocalDate getDevolverEm() {
        return devolverEm;
    }

    public void setDevolverEm(LocalDate devolverEm) {
        this.devolverEm = devolverEm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
