package b.com.tothlibs.apitothlib.services;

import b.com.tothlibs.apitothlib.entity.Livros;

public interface Administravel {

    abstract public Boolean cadastrarLivro(Livros livro);
    abstract public Boolean excluirLivro(Integer id);
    abstract public Boolean alterarLivro(Integer id, Livros livro);

}
