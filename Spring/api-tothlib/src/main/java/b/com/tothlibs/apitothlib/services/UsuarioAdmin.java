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

        System.out.println(idLivro);
        System.out.println(idUsuario);

        livro = new Livros();
        usuario = new PerfilUsuario();

        livro = repository.findById(idLivro).get();

        Integer qtdReservas = livro.getQtdReservas();


        System.out.println(livro);

        usuario = repositoryUsuario.findById(idUsuario).get();

        if (livro != null) {
            if (livro.getQtdReservadoAgora() >= livro.getQtdEstoque()) {
                return false;
            } else {
                if (qtdReservas < livro.getQtdEstoque()) {
                    livro.setId(idLivro);
                    livro.setQtdReservas(qtdReservas + 1);

                    repository.save(livro);

                    criaResgistro(livro.getId(), usuario.getId(), "Reserva");

                    return true;

                }else {
                    return false;
                }
            }
        } else {

            return false;
        }

    }

    @Override
    public Boolean locarLivro(Integer idLivro, Integer idUsuario) {
        return null;
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

        livro = repository.findById(idLivro).get();
        usuario = repositoryUsuario.findById(idUsuario).get();
        registro = new Historico();


        System.out.println(usuario.getId());
        System.out.println(livro.getId());

        registro.setFkTbPerfilUsuario(usuario.getId());
        registro.setFkTbLivros(idLivro);
        registro.setAcao(tipoRegistro);
        registro.setNomePerfilUsuario(usuario.getNome());
        registro.setNomeLivro(livro.getTitulo());
        registro.setDataLivroHistorico(Instant.now());

        repositoryHistorico.save(registro);

    }
}
