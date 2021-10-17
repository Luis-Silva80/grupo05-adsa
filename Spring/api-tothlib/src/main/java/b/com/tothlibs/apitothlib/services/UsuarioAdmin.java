package b.com.tothlibs.apitothlib.services;

import b.com.tothlibs.apitothlib.entity.Historico;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioAdmin implements Administravel, Usuario {


    Livros livro = null;
    Historico registro = null;
    PerfilUsuario usuario = null;

    @Autowired
    private LivrosRepository repository;

    @Autowired
    private PerfilUsuarioRepository repositoryUsuario;

    @Autowired
    private HistoricoRepository repositoryHistorico;

    @Override
    public Boolean cadastrarLivro(Livros livro) {

        livro.setQtdResenhas(0);
        livro.setQtdReservas(0);
        livro.setQtdReservadoAgora(0);
        livro.setFkTbbiblioteca(1);
        livro.setStatusLivro("disponivel");

        repository.save(livro);

        if (repository.findAll().contains(livro)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Boolean excluirLivro(Integer id) {


        if (repository.existsById(id)) {
            repository.deleteById(id);


            if (repository.existsById(id)) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }

    }

    @Override
    public Boolean alterarLivro(Integer id, Livros livroAtualizado) {

        if (repository.existsById(id)) {
            livroAtualizado.setId(id);
            repository.save(livroAtualizado);
            return true;
        } else {
            return false;
        }
    }


    //Metodos de usuario


    @Override
    public Livros buscarLivro(Integer id) {

        Livros livro = repository.findById(id).get();

        return livro;
    }

    @Override
    public List<Livros> consultaListaLivros() {

        List<Livros> livros = repository.findAll();
        return livros;

    }

    @Override
    public Boolean reservar(Integer idLivro, Integer idUsuario) {
        livro = new Livros();
        usuario = new PerfilUsuario();

        livro = repository.findById(idLivro).get();

        Integer qtdReservas = livro.getQtdReservas();

        usuario = repositoryUsuario.findById(idUsuario).get();

        if (livro != null) {
            if (livro.getQtdReservadoAgora() >= livro.getQtdEstoque()) {
                return false;
            } else {
                if (qtdReservas < livro.getQtdEstoque()) {
                    livro.setId(idLivro);
                    livro.setQtdReservas(qtdReservas + 1);
                    repository.save(livro);

                    usuario.setLivrosReservados(usuario.getLivrosReservados()+1);
                    repositoryUsuario.save(usuario);
                    criaResgistro(livro.getId(), usuario.getId(), "Reserva");

                    return true;

                } else {
                    return false;
                }
            }
        } else {

            return false;
        }

    }

    @Override
    public Boolean locarLivro(Integer idRegistro, Integer idUsuario) {

        usuario = new PerfilUsuario();
        registro = repositoryHistorico.findById(idRegistro).get();

        livro = repository.findById(registro.getFkTbLivros()).get();
        Integer qtdReservadosAgora = livro.getQtdReservadoAgora();

        if (livro != null) {

            if (livro.getQtdReservas().equals(0)) {
                return false;
            } else {
                if (livro.getQtdReservas() >= livro.getQtdEstoque()) {
                    livro.setId(registro.getFkTbLivros());
                    livro.setStatusLivro("Indisponivel");
                    return false;
                } else {
                    if (qtdReservadosAgora.equals(livro.getQtdReservas())) {
                        livro.setId(registro.getFkTbLivros());
                        livro.setStatusLivro("Indisponivel");
                        return false;
                    } else {

                        if (livro.getQtdReservadoAgora() >= livro.getQtdEstoque()) {
                            livro.setId(registro.getFkTbLivros());
                            livro.setStatusLivro("Indisponivel");
                            return false;
                        }

                        if (registro.getId().equals(idRegistro)) {

                            livro.setId(registro.getFkTbLivros());
                            livro.setQtdReservadoAgora(qtdReservadosAgora + 1);
                            livro.setQtdReservas(livro.getQtdReservas() - 1);

                            repository.save(livro);
                            criaResgistroComIdRegistro(idRegistro, registro.getFkTbLivros(), idUsuario, "Retirada");
                            return true;
                        } else {
                            return false;
                        }

                    }
                }
            }

        } else {
            return false;
        }


    }

    @Override
    public Boolean renovarAlocacao(Integer idLivro, Integer idUsuario) {
        return null;
    }

    @Override
    public Boolean devolverLivro(Integer idLivro, Integer idUsuario) {
        return null;
    }


    public void criaResgistro(Integer idLivro, Integer idUsuario, String tipoRegistro) {

        registro = new Historico();
        livro = repository.findById(idLivro).get();

        registro.setFkTbPerfilUsuario(usuario.getId());
        registro.setFkTbLivros(idLivro);
        registro.setAcao(tipoRegistro);
        registro.setNomePerfilUsuario(usuario.getNome());
        registro.setNomeLivro(livro.getTitulo());
        registro.setDataLivroHistorico(LocalDate.now());

        repositoryHistorico.save(registro);

    }

    public void criaResgistroComIdRegistro(Integer idRegistro, Integer idLivro, Integer idUsuario, String tipoRegistro) {

        usuario = repositoryUsuario.findById(idUsuario).get();
        registro = new Historico();
        livro = repository.findById(idLivro).get();

        registro.setFkTbPerfilUsuario(usuario.getId());
        registro.setFkTbLivros(idLivro);
        registro.setAcao(tipoRegistro);
        registro.setNomePerfilUsuario(usuario.getNome());
        registro.setNomeLivro(livro.getTitulo());
        registro.setDataLivroHistorico(LocalDate.now());
        registro.setDataDevolucao(LocalDate.now().plusDays(10));

        repositoryHistorico.save(registro);

    }
}
