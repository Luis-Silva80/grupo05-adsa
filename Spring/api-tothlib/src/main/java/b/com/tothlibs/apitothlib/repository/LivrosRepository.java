package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.entity.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivrosRepository extends JpaRepository<Livros, Integer> {

}