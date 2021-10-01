package b.com.tothlibs.apitothlib.services;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAdmin implements Administravel {



    private LivrosRepository repository;

    private PerfilUsuarioRepository repositoryUsuario;

    @Override
    public Boolean cadastrarLivro(Livros livro) {


        livro.setQtdResenhas(0);
        livro.setQtdReservas(0);
        livro.setQtdReservadoAgora(0);
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

        repository.deleteById(id);

        if (repository.existsById(id)) {
            return false;
        } else {
            return true;
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
}
