package br.thotlibs.entregavelpi;

import javax.persistence.*;

@Table(name = "tbbiblioteca")
@Entity
public class Tbbiblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tbBiblioteca_id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}