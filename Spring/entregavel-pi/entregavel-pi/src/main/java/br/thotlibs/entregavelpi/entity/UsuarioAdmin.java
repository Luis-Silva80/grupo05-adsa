package br.thotlibs.entregavelpi.entity;

import br.thotlibs.entregavelpi.interfaces.Administravel;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UsuarioAdmin extends Usuario implements Administravel {

    public UsuarioAdmin(Integer id, String nome, String cpf, String email, String telefone, String senha, Boolean admin, Date    dataNascimento) {
        super(id, nome, cpf, email, telefone, senha, admin, dataNascimento);
    }

    @Override
    public String cadastrarLivro(Livro livro) {

        String retorno = "";
        super.setListLivros(livro);

        retorno = String.format("Livro com id: %d cadastrado com sucesso!!", livro.getId());

        return retorno;
    }

    @Override
    public String excluirLivro(Integer id) {

        String retorno = "";

        try {
            Livro livroExcluir = new Livro();

            Optional<Livro> livroBusca = super.getListLivros().stream().filter(livro -> livro.getId() == id).findFirst();

            super.getListLivros().remove(livroBusca);
            retorno = String.format("Livro com id: %d excluido com sucesso!!", id);

            return retorno;

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Livro alterarLivro(Livro livroAlterar, Integer id) {

        Livro livroAlterado = new Livro();

        for (Livro livro : super.getListLivros()) {
            if (livro.getId() == id) {
                livro.setTitulo(livroAlterar.getTitulo());
                livro.setDisponivel(livroAlterar.getDisponivel());
                livro.setTitulo(livroAlterar.getTitulo());

                livroAlterado = new Livro(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getDisponivel());
            }
        }

        return livroAlterado;
    }

    @Override
    public Optional buscarLivro(Integer id) {

        Optional<Livro> livroBusca = super.getListLivros().stream().filter(livro -> livro.getId() == id).findFirst();

        return livroBusca;
    }

    @Override
    public List<Livro> consultarLista() {
        return super.getListLivros();
    }

    @Override
    public String LocarLivro(Integer idLivro, Integer idUsuario) {
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
