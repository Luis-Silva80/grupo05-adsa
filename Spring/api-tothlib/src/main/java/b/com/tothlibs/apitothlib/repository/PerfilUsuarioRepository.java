package b.com.tothlibs.apitothlib.repository;

import java.util.List;

import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {

    @Query("SELECT u.usuarioAdmin FROM PerfilUsuario u WHERE u.id = :id")
    public Integer findAdminById(@Param("id") Integer id);


    @Query("SELECT u FROM PerfilUsuario u WHERE u.usuarioAdmin = 1")
    public List<PerfilUsuario> findAdmin();

    @Query("SELECT u FROM PerfilUsuario u WHERE u.usuarioAdmin = 0")
    public List<PerfilUsuario> findAlunos();

    @Query("SELECT u FROM PerfilUsuario u WHERE u.usuarioAdmin = 0 and u.statusAtivo = false ")
    public List<PerfilUsuario> findAlunosInativos();

    @Query("SELECT u FROM PerfilUsuario u WHERE u.usuarioAdmin = 0 and u.id = :id")
    public PerfilUsuario findByIdOnNotAdmin(@Param("id") Integer id);

    public PerfilUsuario findByNome(String nome);

    public PerfilUsuario findByEmail(String email);

    @Query("SELECT a FROM PerfilUsuario a WHERE a.usuarioAdmin = 1 and a.id = :id")
    public PerfilUsuario findAlunoById(@Param("id") Integer id);

    @Query("SELECT p FROM PerfilUsuario p WHERE p.id = :id")
    public PerfilUsuario findPerfilUsuarioById(@Param("id") Integer id);

}