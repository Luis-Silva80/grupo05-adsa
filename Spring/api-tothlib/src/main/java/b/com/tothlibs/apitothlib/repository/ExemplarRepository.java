package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.entity.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {

    public Exemplar getExemplarById();

    @Query("select e from Exemplar e where " +
           " e.reservado = false and "       +
           " e.retirado  = false and "       +
           " (e.devolvido = false or e.devolvido = true)")
    public List<Exemplar> getExemplaresReservadosAgora();

    @Query("select count e from Exemplar e where "  +
           " e.reservado = false and  "             +
           " e.retirado  = false and "              +
           " (e.devolvido = false or e.devolvido = true) ")
    public Integer getQtdExemplaresReservadosAgora();
}
