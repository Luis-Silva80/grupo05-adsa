package b.com.tothlibs.apitothlib.services;

import b.com.tothlibs.apitothlib.entity.Livros;

import java.util.List;

public interface Usuario {

    abstract public Livros buscarLivro(Integer id);
    abstract public List<Livros> consultaListaLivros();
    abstract public Integer reservar(Integer idLivro, Integer idUsuario);
    abstract public Integer locarLivro(Integer idRegistro, Integer idUsuario);
    abstract public Integer renovarAlocacao(Integer id, Integer idUsuario);
    abstract public Integer devolverLivro(Integer idRegistro, Integer idUsuario);

}
