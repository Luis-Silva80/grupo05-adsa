package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.entity.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRpository<Livros, Integer> {
}