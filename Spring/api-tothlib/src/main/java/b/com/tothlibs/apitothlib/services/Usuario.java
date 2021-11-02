package b.com.tothlibs.apitothlib.services;

import b.com.tothlibs.apitothlib.entity.Livros;

import java.util.List;

public interface Usuario {

    abstract public Livros buscarLivro(Integer id);
    abstract public List<Livros> consultaListaLivros();
    abstract public Boolean reservar(Integer idLivro, Integer idUsuario);
    abstract public Boolean locarLivro(Integer idRegistro, Integer idUsuario);
    abstract public Boolean renovarAlocacao(Integer id, Integer idUsuario);
    abstract public Boolean devolverLivro(Integer id, Integer idUsuario);

}
