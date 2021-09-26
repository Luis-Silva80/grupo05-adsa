package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {

    @Query("SELECT u.usuarioAdmin FROM PerfilUsuario u WHERE u.id = :id")
    public Integer findAdmin(@Param("id") Integer id);

}