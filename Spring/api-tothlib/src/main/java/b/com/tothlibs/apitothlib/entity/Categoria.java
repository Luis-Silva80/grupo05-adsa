package b.com.tothlibs.apitothlib.entity;

import javax.persistence.*;

@Table(name = "tbcategoria", indexes = {
        @Index(name = "fk_tbLivros", columnList = "fk_tbLivros")
})
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 70)
    private String nome;

    @Column(name = "fk_tbLivros")
    private Integer fkTblivros;

    public Integer getFkTblivros() {
        return fkTblivros;
    }

    public void setFkTblivros(Integer fkTblivros) {
        this.fkTblivros = fkTblivros;
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

    public Categoria(){

    }

    public Categoria(Integer id, String nome, Integer fkTblivros) {
        this.id = id;
        this.nome = nome;
        this.fkTblivros = fkTblivros;
    }
}