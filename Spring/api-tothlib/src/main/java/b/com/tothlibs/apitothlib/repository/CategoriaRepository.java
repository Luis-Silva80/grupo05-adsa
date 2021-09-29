package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}