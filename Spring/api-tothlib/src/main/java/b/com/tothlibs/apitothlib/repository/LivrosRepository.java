package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.listas.FilaObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivrosRepository extends JpaRepository<Livros, Integer> {


    @Query("Select l from Livros l where l.id = :id")
    public Livros findLivroById(@Param("id") Integer id);

    @Query("Select l from Livros l order by qtdReservas desc")
    public List<Livros> findLivrosOrderByQtdReservas();

}