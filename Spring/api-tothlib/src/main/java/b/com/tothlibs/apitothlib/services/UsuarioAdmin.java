package b.com.tothlibs.apitothlib.services;

import b.com.tothlibs.apitothlib.dto.Response;
import b.com.tothlibs.apitothlib.entity.Exemplar;
import b.com.tothlibs.apitothlib.entity.Historico;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.ExemplarRepository;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioAdmin implements Administravel, Usuario {

    Livros livro = null;
    Exemplar exemplar = null;
    Historico registro = null;
    PerfilUsuario usuario = null;

    @Autowired
    private LivrosRepository repositoryLivros;

    @Autowired
    private ExemplarRepository repositoryExemplar;

    @Autowired
    private PerfilUsuarioRepository repositoryUsuario;

    @Autowired
    private HistoricoRepository repositoryHistorico;

    @Override
    public Response excluirLivro(Integer id) {


        if (repositoryLivros.existsById(id)) {
            repositoryLivros.deleteById(id);

            return new Response(00, "Livro Deletado com sucesso");

        } else {
            return new Response(02, "Livro não existe na base");
        }

    }

    @Override
    public Response alterarLivro(Integer id, Livros livroAtualizado) {

        if (repositoryLivros.existsById(id)) {
            livroAtualizado.setId(id);
            livroAtualizado.setFkTbBiblioteca(1);
            repositoryLivros.save(livroAtualizado);
            return new Response(00, "Livro atualizado");
        } else {
            return new Response(02, "Livro não encontrado");
        }
    }


    //Metodos de usuario


    @Override
    public Optional<Livros> buscarLivro(Integer id) {

        Optional<Livros> livro = repositoryLivros.findById(id);

        return livro;
    }

    @Override
    public List<Livros> consultaListaLivros() {

        List<Livros> livros = repositoryLivros.findAll();
        return livros;

    }

    @Override
    public Integer reservar(String tombo, Integer idUsuario) {

        Exemplar exemplar = repositoryExemplar.findByTombo(tombo);
        PerfilUsuario usuario = repositoryUsuario.findById(idUsuario).get();
        Livros livro;

        if (usuario.getStatusAtivo()) {

            if (!exemplar.getId().equals(null) && !exemplar.getId().equals(0)) {
                livro = repositoryLivros.findLivroById(exemplar.getFkTbLivro());
            } else {
                return 0;
            }


            if (livro.getQtdDisponiveis() > 0) {
                if (livro.getQtdReservadosAgora().equals(livro.getQtdEstoque())) {
                    if (exemplar.getReservado().equals(false) && exemplar.getRetirado().equals(false) && exemplar.getRenovado().equals(false)) {

                        if (usuario.getQtdReservadosAgora() <= 2) {
                            exemplar.setReservado(true);
                            livro.setQtdReservadosAgora(livro.getQtdReservadosAgora() + 1);
                            livro.setQtdReservadosTotal(livro.getQtdReservadosTotal() + 1);
                            livro.setQtdDisponiveis(livro.getQtdDisponiveis() - 1);

                            repositoryLivros.save(livro);
                            repositoryExemplar.save(exemplar);

                            criaResgistroDeReserva(exemplar, usuario, livro, "RESERVA");

                        } else {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }


            } else {
                return 0;
            }


        } else {
            return 0;
        }

        Historico u = repositoryHistorico.
                findTopByFkTbPerfilUsuarioAndTomboOrderByIdDesc(idUsuario, tombo);

        return u.getId();


    }

    @Override
    public Integer locarLivro(String tombo, Integer idUsuario) {

        usuario = repositoryUsuario.findById(idUsuario).get();
        exemplar = repositoryExemplar.findByTombo(tombo);
        livro = repositoryLivros.findLivroById(exemplar.getFkTbLivro());
        registro = repositoryHistorico.findTopByFkTbPerfilUsuarioAndTomboOrderByIdDesc(idUsuario, tombo);

        if (usuario == null || exemplar == null || livro == null || registro == null) {
            return 0;
        }


        if (usuario.getStatusAtivo()) {

            //alterar verificação para se o registro existe
            // ao invéz de se o registro com aquele ID existe pois a segunda opção está redundante
            if (registro.getAcao().equalsIgnoreCase("RESERVA")) {

                exemplar.setId(exemplar.getId());

                exemplar.setReservado(false);
                exemplar.setRetirado(true);


                criaResgistroDeReserva(exemplar, usuario, livro, "RETIRADA");

                Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();
                return ultimoRegistro.getId();


            } else {
                return 0;
            }


        } else {

            return 0;

        }
    }

    @Override
    public Integer renovarAlocacao(String tombo, Integer idUsuario) {

        usuario = repositoryUsuario.findById(idUsuario).get();
        exemplar = repositoryExemplar.findByTombo(tombo);
        livro = repositoryLivros.findLivroById(exemplar.getFkTbLivro());
        registro = repositoryHistorico.findTopByFkTbPerfilUsuarioAndTomboOrderByIdDesc(idUsuario, tombo);

        if (usuario == null || exemplar == null || livro == null || registro == null) {
            return 0;
        }

        if (usuario.getStatusAtivo()) {
            if (registro.getAcao().equalsIgnoreCase("RETIRADA")) {

                exemplar.setRetirado(false);
                exemplar.setRenovado(true);

                criaResgistroDeReserva(exemplar, usuario, livro, "RENOVACAO");

                Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();
                return ultimoRegistro.getId();
            } else {
                return null;
            }

        } else {
            return null;
        }

    }

    @Override
    public Integer devolverLivro(String tombo, Integer idUsuario) {


        usuario = repositoryUsuario.findById(idUsuario).get();
        exemplar = repositoryExemplar.findByTombo(tombo);
        livro = repositoryLivros.findLivroById(exemplar.getFkTbLivro());
        registro = repositoryHistorico.findTopByFkTbPerfilUsuarioAndTomboOrderByIdDesc(idUsuario, tombo);

        if (usuario == null || exemplar == null || livro == null || registro == null) {
            return 0;
        }


        if (usuario.getStatusAtivo()) {

            Historico registroAntigo = repositoryHistorico.findTopByFkTbPerfilUsuarioAndTomboOrderByIdDesc(idUsuario, tombo);

            if (registroAntigo.getAcao().equalsIgnoreCase("RETIRADA") || registroAntigo.getAcao().equalsIgnoreCase("RENOVACAO")) {

                livro.setQtdReservadosAgora(livro.getQtdReservadosAgora() - 1);
                livro.setQtdDisponiveis(livro.getQtdDisponiveis() + 1);
                usuario.setQtdLivrosLidos(usuario.getQtdLivrosLidos() + 1);
                usuario.setPontos(usuario.getPontos() + 5);
                usuario.setQtdReservadosAgora(usuario.getQtdReservadosAgora() - 1);

                exemplar.setRenovado(false);
                exemplar.setRetirado(false);
                exemplar.setReservado(false);
                exemplar.setDevolvido(false);

                repositoryUsuario.save(usuario);
                repositoryLivros.save(livro);
                repositoryExemplar.save(exemplar);

                criaResgistroDeReserva(exemplar, usuario, livro, "DEVOLUCAO");

                Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();


                return ultimoRegistro.getId();
            } else {
                return 0;
            }

        } else {
            return 0;
        }
    }


    public void criaResgistroDeReserva(Exemplar exemplar, PerfilUsuario usuario, Livros livro, String tipoRegistro) {

        if (tipoRegistro.equalsIgnoreCase("RESERVA")) {

            registro = new Historico();

            registro.setFkTbPerfilUsuario(usuario.getId());
            registro.setTombo(exemplar.getTombo());
            registro.setNrExemplar(exemplar.getNrExemplar());
            registro.setAcao(tipoRegistro);
            registro.setNomePerfilUsuario(usuario.getNome());
            registro.setNomeLivro(livro.getTitulo());
            registro.setFkTbExemplar(exemplar.getId());
            registro.setDataRegistro(LocalDate.now());


        } else if (tipoRegistro.equalsIgnoreCase("RETIRADA")) {

            registro = new Historico();
//
            registro.setFkTbPerfilUsuario(usuario.getId());
            registro.setTombo(exemplar.getTombo());
            registro.setNrExemplar(exemplar.getNrExemplar());
            registro.setAcao(tipoRegistro);
            registro.setNomePerfilUsuario(usuario.getNome());
            registro.setNomeLivro(livro.getTitulo());
            registro.setFkTbExemplar(exemplar.getId());
            registro.setDataRegistro(LocalDate.now());
            registro.setDataPrevDevolucao(LocalDate.now().plusDays(10));

        } else if (tipoRegistro.equalsIgnoreCase("RENOVACAO")) {

            LocalDate dataPrevDevolucaoAntiga = repositoryHistorico.findTopByFkTbPerfilUsuarioAndTomboOrderByIdDesc(usuario.getId(), exemplar.getTombo()).getDataPrevDevolucao();

            registro = new Historico();

            registro.setFkTbPerfilUsuario(usuario.getId());
            registro.setTombo(exemplar.getTombo());
            registro.setNrExemplar(exemplar.getNrExemplar());
            registro.setAcao(tipoRegistro);
            registro.setNomePerfilUsuario(usuario.getNome());
            registro.setNomeLivro(livro.getTitulo());
            registro.setFkTbExemplar(exemplar.getId());
            registro.setDataRegistro(LocalDate.now());
            registro.setDataPrevDevolucao(dataPrevDevolucaoAntiga.plusDays(10));

        }else {

            registro = new Historico();

            registro.setFkTbPerfilUsuario(usuario.getId());
            registro.setTombo(exemplar.getTombo());
            registro.setNrExemplar(exemplar.getNrExemplar());
            registro.setAcao(tipoRegistro);
            registro.setNomePerfilUsuario(usuario.getNome());
            registro.setNomeLivro(livro.getTitulo());
            registro.setFkTbExemplar(exemplar.getId());
            registro.setDataRegistro(LocalDate.now());
            registro.setDataDevolvido(LocalDate.now());

        }

        repositoryHistorico.save(registro);


    }

}