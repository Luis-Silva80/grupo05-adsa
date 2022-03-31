package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.dto.UsuariosPendentesDto;
import b.com.tothlibs.apitothlib.entity.Historico;
import b.com.tothlibs.apitothlib.entity.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {

    @Query("SELECT NEW b.com.tothlibs.apitothlib.dto.UsuariosPendentesDto(h, p.livrosReservados)" +
            " from Historico h" +
            " inner join PerfilUsuario p on h.fkTbPerfilUsuario = p.id " +
            " where h.acao = 'Retirada' or h.acao = 'Renovacao' and p.livrosReservados > 0 order by h.id desc")
    public List<UsuariosPendentesDto> findUserPendencia();

    @Query("SELECT h.dataRegistro FROM Historico h where h.id = :id")
    public LocalDate findDataRegistro(@Param("id") Integer id);

    @Query("SELECT h.dataPrevDevolucao FROM Historico h where h.id = :id")
    public LocalDate findDataDevolucao(@Param("id") Integer id);

    @Query("SELECT h.fkTbPerfilUsuario FROM Historico h where h.fkTbPerfilUsuario = :id")
    public List<Integer> findLivrosByUser(@Param("id") Integer id);

    public Historico findTopByOrderByIdDesc();

//    @Query("select h from Historico h ")
    public Historico findTopByFkTbPerfilUsuarioAndTomboOrderByIdDesc(Integer idUsuario, String tombo);

    public List<Historico> findAllByOrderByIdDesc();

    public Historico findFirstByFkTbPerfilUsuarioOrderByIdDesc(Integer idUsuario);

//    @Query("SELECT h FROM Historico h where h.id = :id"
//    public List<UsuariosPendentesDto> findUserPendenciaById(@Param("id") Integer id);

    @Query("SELECT h.fkTbExemplar FROM Historico h where h.fkTbPerfilUsuario = :idUsuario")
    public List<Integer> findFkExemplarByIdUsuario(@Param("idUsuario") Integer idUsuario);

    public Historico findTopByTomboAndFkTbPerfilUsuarioOOrderByIdDesc(String tombo, Integer idUsuario);
}