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

    @Column(name = "renovado")
    private Boolean renovado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNrExemplar() {
        return nrExemplar;
    }

    public void setNrExemplar(Integer nrExemplar) {
        this.nrExemplar = nrExemplar;
    }

    public String getTombo() {
        return tombo;
    }

    public void setTombo(String tombo) {
        this.tombo = tombo;
    }

    public Boolean getReservado() {
        return reservado;
    }

    public void setReservado(Boolean reservado) {
        this.reservado = reservado;
    }

    public Boolean getRetirado() {
        return retirado;
    }

    public void setRetirado(Boolean retirado) {
        this.retirado = retirado;
    }

    public Boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Boolean devolvido) {
        this.devolvido = devolvido;
    }

    public Integer getFkTbLivro() {
        return fkTbLivro;
    }

    public void setFkTbLivro(Integer fkTbLivro) {
        this.fkTbLivro = fkTbLivro;
    }

    public Boolean getRenovado() {
        return renovado;
    }

    public void setRenovado(Boolean renovado) {
        this.renovado = renovado;
    }

    public Exemplar() {
    }

    public Exemplar(Integer id,
                    Integer nrExemplar,
                    String tombo,
                    Boolean reservado,
                    Boolean retirado,
                    Boolean devolvido,
                    Integer fkTbLivro,
                    Boolean renovado) {
        this.id = id;
        this.nrExemplar = nrExemplar;
        this.tombo = tombo;
        this.reservado = reservado;
        this.retirado = retirado;
        this.devolvido = devolvido;
        this.fkTbLivro = fkTbLivro;
        this.renovado = renovado;
    }

    @Override
    public String toString() {
        return "Exemplar{" +
                "id=" + id +
                ", nrExemplar=" + nrExemplar +
                ", tombo='" + tombo + '\'' +
                ", reservado=" + reservado +
                ", retirado=" + retirado +
                ", devolvido=" + devolvido +
                ", fkTbLivro=" + fkTbLivro +
                ", renovado=" + renovado +
                '}';
    }
}