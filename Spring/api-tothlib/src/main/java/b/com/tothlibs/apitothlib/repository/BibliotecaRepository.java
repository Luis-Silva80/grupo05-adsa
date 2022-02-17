package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.entity.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Integer> {
}