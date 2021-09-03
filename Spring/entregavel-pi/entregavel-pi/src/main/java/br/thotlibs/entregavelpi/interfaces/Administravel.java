package br.thotlibs.entregavelpi.interfaces;

import br.thotlibs.entregavelpi.entity.Livro;

public interface Administravel {

        abstract public String cadastrarLivro(Livro livro);
        abstract public String excluirLivro(Integer id);
        abstract public Livro alterarLivro(Livro livro, Integer id);

}
