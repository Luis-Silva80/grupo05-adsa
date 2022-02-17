package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoRepository2 extends JpaRepository<Historico, Integer> {

    @Query("SELECT h FROM Historico h order by h.id desc")
    public List<Historico> findUserPendencia();

}