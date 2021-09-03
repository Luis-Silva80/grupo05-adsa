package br.thotlibs.entregavelpi.interfaces;

import br.thotlibs.entregavelpi.entity.Livro;

import java.util.List;
import java.util.Optional;

public interface Utilizavel {

    public Optional buscarLivro(Integer id);
    public List<Livro> consultarLista();
    public String AlocarLivro(Integer id);
    public String devolverLivro(Integer id);
    public String renovarAlocacao(Integer id);


}
