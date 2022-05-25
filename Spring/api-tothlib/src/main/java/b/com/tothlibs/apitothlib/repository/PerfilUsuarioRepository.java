package b.com.tothlibs.apitothlib.repository;

import java.util.List;

import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {

    @Query("SELECT u.usuarioAdmin FROM PerfilUsuario u WHERE u.id = :id")
    public Integer findAdminById(@Param("id") Integer id);

    @Query("SELECT u.id, u.email FROM PerfilUsuario u WHERE u.id = :id and u.usuarioAdmin = 0")
    public List<Object> findEmailAndId();

    public PerfilUsuario findByEmailAndSenha(String email, String senha);

    @Query("SELECT u FROM PerfilUsuario u WHERE u.usuarioAdmin = 1")
    public List<PerfilUsuario> findAdmin();

    @Query("SELECT u FROM PerfilUsuario u WHERE u.usuarioAdmin = 0")
    public List<PerfilUsuario> findAlunos();


    @Query("SELECT u FROM PerfilUsuario u WHERE u.usuarioAdmin = 0 and u.statusAtivo = false ")
    public List<PerfilUsuario> findAlunosInativos();

    @Query("SELECT u FROM PerfilUsuario u WHERE u.usuarioAdmin = 0 and u.id = :id")
    public PerfilUsuario findByIdOnNotAdmin(@Param("id") Integer id);

    public PerfilUsuario findByEmail(String email);

    @Query("SELECT u.foto FROM PerfilUsuario u WHERE u.id = :id")
    public byte[] findFotoById(@Param("id") Integer id);

}