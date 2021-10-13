package b.com.tothlibs.apitothlib.repository;

import b.com.tothlibs.apitothlib.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {

    @Query("SELECT h FROM Historico h order by h.id desc")
    public List<Historico> findUserPendencia();

    @Query("SELECT h.dataLivroHistorico FROM Historico h where h.id = :id")
    public LocalDate findDataRegistro(@Param("id") Integer id);

//    SELECT TOP (2) * FROM [dbo].[tbHistorico]
//    where fk_tb_perfil_usuario = 3
//    and
//    acao = 'Renovacao'
//    or
//            acao = 'Locacao'
//    order by id desc;
//WHERE h.fk_tb_perfil_usuario = :fk_tb_perfil_usuario" +
//            " and h.acao = 'Renovacao' or acao = 'Locacao' order by h.id desc
}