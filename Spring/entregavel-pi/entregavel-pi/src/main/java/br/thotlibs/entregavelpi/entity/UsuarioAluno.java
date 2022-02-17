package br.thotlibs.entregavelpi.entity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UsuarioAluno extends Usuario {

    public UsuarioAluno(Integer id, String nome, String cpf, String email, String telefone, String senha, Boolean admin, Date dataNascimento) {
        super(id, nome, cpf, email, telefone, senha, admin, dataNascimento);
    }

    @Override
    public Optional buscarLivro(Integer id) {

        Optional<Livro> livroBusca = super.getListLivros().stream().filter(livro -> livro.getId() == id).findFirst();

        return livroBusca;

    }

    @Override
    public List<Livro> consultarLista() {
        return null;
    }


    @Override
    public String LocarLivro(Integer idLivro, Integer idUsuario) {
        try {

            Livro livroLocado = (Livro) super.getListLivros().stream().filter(livro -> livro.getId().equals(idLivro));
            livroLocado.setDisponivel(false);
            return "Livro: " + livroLocado.getTitulo() + " locado com sucesso!";

        }catch (Exception e){
            return e.getMessage();
        }
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
