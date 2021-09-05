package br.thotlibs.entregavelpi.entity;

import br.thotlibs.entregavelpi.interfaces.Utilizavel;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UsuarioAluno extends Usuario implements Utilizavel {

    public UsuarioAluno(Integer id, String nome, String cpf, String email, String telefone, String senha, Boolean admin, Date dataNascimento) {
        super(id, nome, cpf, email, telefone, senha, admin, dataNascimento);
    }

    @Override
    public Optional buscarLivro(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Livro> consultarLista() {
        return null;
    }

    @Override
    public String AlocarLivro(Integer id) {
        return null;
    }

    @Override
    public String devolverLivro(Integer id) {
        return null;
    }

    @Override
    public String renovarAlocacao(Integer id) {
        return null;
    }
}
