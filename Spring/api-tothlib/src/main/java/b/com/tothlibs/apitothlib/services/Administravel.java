package b.com.tothlibs.apitothlib.services;

import b.com.tothlibs.apitothlib.dto.Response;
import b.com.tothlibs.apitothlib.entity.Livros;

public interface Administravel {

    abstract public Livros cadastrarLivro(Livros livro);
    abstract public Response excluirLivro(Integer id);
    abstract public Response alterarLivro(Integer id, Livros livro);

}
