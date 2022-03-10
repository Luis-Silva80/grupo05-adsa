package b.com.tothlibs.apitothlib.entity;

import javax.persistence.*;

@Table(name = "tbExemplar")
@Entity
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nr_exemplar")
    private Integer nrExemplar;

    @Column(name = "tombo", length = 60)
    private String tombo;

    @Column(name = "reservado")
    private Boolean reservado;

    @Column(name = "retirado")
    private Boolean retirado;

    @Column(name = "devolvido")
    private Boolean devolvido;

    @Column(name = "fk_tb_livro")
    private Integer fkTbLivro;

    public Integer getFkTbLivro() {
        return fkTbLivro;
    }

    public void setFkTbLivro(Integer fkTbLivro) {
        this.fkTbLivro = fkTbLivro;
    }

    public Boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Boolean devolvido) {
        this.devolvido = devolvido;
    }

    public Boolean getRetirado() {
        return retirado;
    }

    public void setRetirado(Boolean retirado) {
        this.retirado = retirado;
    }

    public Boolean getReservado() {
        return reservado;
    }

    public void setReservado(Boolean reservado) {
        this.reservado = reservado;
    }

    public String getTombo() {
        return tombo;
    }

    public void setTombo(String tombo) {
        this.tombo = tombo;
    }

    public Integer getNrExemplar() {
        return nrExemplar;
    }

    public void setNrExemplar(Integer nrExemplar) {
        this.nrExemplar = nrExemplar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}