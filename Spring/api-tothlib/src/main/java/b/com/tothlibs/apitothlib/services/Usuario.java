package b.com.tothlibs.apitothlib.services;

import b.com.tothlibs.apitothlib.entity.Livros;

import java.util.List;
import java.util.Optional;

public interface Usuario {

    abstract public Optional<Livros> buscarLivro(Integer id);
    abstract public List<Livros> consultaListaLivros();
    abstract public Integer reservar(String tombo, Integer idUsuario);
    abstract public Integer locarLivro(String tombo, Integer idUsuario);
    abstract public Integer renovarAlocacao(String tombo, Integer idUsuario);
    abstract public Integer devolverLivro(String tombo, Integer idUsuario);

}
