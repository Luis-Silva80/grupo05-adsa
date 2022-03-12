package b.com.tothlibs.apitothlib.services;

import b.com.tothlibs.apitothlib.dto.Response;
import b.com.tothlibs.apitothlib.entity.Historico;
import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioAdmin implements Administravel, Usuario {


    Livros livro = null;
    Historico registro = null;
    PerfilUsuario usuario = null;

    @Autowired
    private LivrosRepository repository;

    @Autowired
    private PerfilUsuarioRepository repositoryUsuario;

    @Autowired
    private HistoricoRepository repositoryHistorico;

    @Override
    public Response excluirLivro(Integer id) {


        if (repository.existsById(id)) {
            repository.deleteById(id);

            return new Response(00,"Livro Deletado com sucesso");

        } else {
            return new Response(02,"Livro não existe na base");
        }

    }

    @Override
    public Response alterarLivro(Integer id, Livros livroAtualizado) {

        if (repository.existsById(id)) {
            livroAtualizado.setId(id);
            livroAtualizado.setFkTbBiblioteca(1);
            repository.save(livroAtualizado);
            return new Response(00,"Livro atualizado");
        } else {
            return new Response(02,"Livro não encontrado");
        }
    }


    //Metodos de usuario


    @Override
    public Optional<Livros> buscarLivro(Integer id) {

        Optional<Livros> livro = repository.findById(id);

        return livro;
    }

    @Override
    public List<Livros> consultaListaLivros() {

        List<Livros> livros = repository.findAll();
        return livros;

    }

    @Override
    public Integer reservar(Integer idLivro, Integer idUsuario) {
        livro = new Livros();
        usuario = new PerfilUsuario();

        livro = repository.findById(idLivro).get();

        Integer qtdReservas = livro.getQtdReservas();

        usuario = repositoryUsuario.findById(idUsuario).get();


        Historico ultimoRegistroByUsuario = repositoryHistorico.findFirstByFkTbPerfilUsuarioOrderByIdDesc(usuario.getId());

        if(usuario.getStatusAtivo()){
            if (livro != null) {
                if (livro.getQtdReservadoAgora() >= livro.getQtdEstoque()) {
                    return null;
                } else {
                    if (qtdReservas < livro.getQtdEstoque()) {
                        livro.setId(idLivro);
                        livro.setQtdReservas(qtdReservas + 1);
                        repository.save(livro);

                        usuario.setLivrosReservados(usuario.getLivrosReservados() + 1);
                        repositoryUsuario.save(usuario);
                        criaResgistro(livro.getId(), usuario.getId(), "Reserva");

                        Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();
                        return ultimoRegistro.getId();

                    } else {
                        return null;
                    }
                }
            } else {

                return null;
            }
        }else {
            return null;
        }


    }

    @Override
    public Integer locarLivro(Integer idRegistro, Integer idUsuario) {

        usuario = repositoryUsuario.findById(idUsuario).get();
        registro = repositoryHistorico.findById(idRegistro).get();

        livro = repository.findById(registro.getFkTbLivros()).get();
        Integer qtdReservadosAgora = livro.getQtdReservadoAgora();

        if(usuario.getStatusAtivo()){
            if (livro != null) {

                if (livro.getQtdReservas().equals(0)) {
                    return null;
                } else {
                    if (livro.getQtdReservas() >= livro.getQtdEstoque()) {
                        livro.setId(registro.getFkTbLivros());
                        livro.setStatusLivro("Indisponivel");
                        repository.save(livro);
                        return null;
                    } else {
                        if (qtdReservadosAgora.equals(livro.getQtdReservas())) {
                            livro.setId(registro.getFkTbLivros());
                            livro.setStatusLivro("Indisponivel");
                            repository.save(livro);
                            return null;
                        } else {

                            if (livro.getQtdReservadoAgora() >= livro.getQtdEstoque()) {
                                livro.setId(registro.getFkTbLivros());
                                livro.setStatusLivro("Indisponivel");
                                repository.save(livro);
                                return null;
                            }

                            //alterar verificação para se o registro existe ao invéz de se o registro com aquele ID existe pois a segunda opção está redundante
                            if (registro.getId().equals(idRegistro)) {

                                livro.setId(registro.getFkTbLivros());
                                livro.setQtdReservadoAgora(qtdReservadosAgora + 1);
                                livro.setQtdReservas(livro.getQtdReservas() - 1);

                                if (livro.getQtdReservadoAgora().equals(livro.getQtdEstoque())) {
                                    livro.setStatusLivro("Indisponivel");
                                } else {
                                    livro.setStatusLivro("Disponivel");
                                }

                                repository.save(livro);
                                criaResgistroComIdRegistro(idRegistro, registro.getFkTbLivros(), idUsuario, "Retirada");

                                Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();
                                return ultimoRegistro.getId();
                            } else {
                                return null;
                            }

                        }
                    }
                }

            } else {
                return null;
            }
        }else {
            return null;
        }


    }

    @Override
    public Integer renovarAlocacao(Integer idRegistro, Integer idUsuario) {

        Historico registro = repositoryHistorico.findById(idRegistro).get();

        if(usuario.getStatusAtivo()){
            if (registro.getFkTbPerfilUsuario().equals(idUsuario)) {
                if (registro.getAcao().equals("Retirada")) {


                    criaResgistroComIdRegistro(idRegistro, registro.getFkTbExemplar(), idUsuario, "Renovacao");

                    Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();
                    return ultimoRegistro.getId();
                } else {
                    return null;
                }

            } else {
                return null;
            }
        }else {
            return null;
        }

    }

    @Override
    public Integer devolverLivro(Integer idRegistro, Integer idUsuario) {


        if(usuario.getStatusAtivo()){
            if (repositoryHistorico.existsById(idRegistro)) {

                Historico registroAntigo = repositoryHistorico.findById(idRegistro).get();
                Integer idLivroNoRegistro = registroAntigo.getFkTbExemplar();

                livro = repository.findById(idLivroNoRegistro).get();
                usuario = repositoryUsuario.findById(idUsuario).get();

                if (!livro.get().equals(0)) {
                    if (registroAntigo.getAcao().equals("Renovacao") || registroAntigo.getAcao().equals("Retirada")) {

                        livro.setId(idLivroNoRegistro);
                        livro.setQtdReservadoAgora(livro.getQtdReservadoAgora() - 1);

                        usuario.setId(idUsuario);
                        usuario.setQtdLivrosLidos(usuario.getQtdLivrosLidos() + 1);
                        usuario.setLivrosReservados(usuario.getLivrosReservados() - 1);
                        usuario.setPontos(usuario.getPontos()+5);

                        criaResgistro(idLivroNoRegistro, idUsuario, "Devolucao");
                        Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();

                        repositoryUsuario.save(usuario);
                        repository.save(livro);
                        return ultimoRegistro.getId();
                    } else {
                        return null;
                    }

                } else {
                    return null;
                }

            } else {
                return null;
            }
        }else {
            return null;
        }
    }


    public void criaResgistro(Integer idLivro, Integer idUsuario, String tipoRegistro) {

        registro = new Historico();
        livro = repository.findById(idLivro).get();

        usuario = repositoryUsuario.findById(idUsuario).get();

        registro.setFkTbPerfilUsuario(usuario.getId());
        registro.setFkTbExemplar(idLivro);
        registro.setAcao(tipoRegistro);
        registro.setNomePerfilUsuario(usuario.getNome());
        registro.setNomeLivro(livro.getTitulo());
        registro.setDataRegistro(LocalDate.now());

        repositoryHistorico.save(registro);

    }

    public void criaResgistroComIdRegistro(Integer idRegistro, Integer idLivro, Integer idUsuario, String tipoRegistro) {

        usuario = repositoryUsuario.findById(idUsuario).get();
        registro = new Historico();


        livro = repository.findById(idLivro).get();

        LocalDate dataAntiga = repositoryHistorico.findDataDevolucao(idRegistro);

        registro.setFkTbPerfilUsuario(usuario.getId());
        registro.setNrExemplar(idLivro.toString());
        registro.setAcao(tipoRegistro);
        registro.setNomePerfilUsuario(usuario.getNome());
        registro.setNomeLivro(livro.getTitulo());
        registro.setDataRegistro(LocalDate.now());

        if (tipoRegistro.equals("Retirada")) {
            registro.setDataPrevDevolucao(LocalDate.now().plusDays(10));
        } else {
            registro.setDataPrevDevolucao(dataAntiga.plusDays(10));
        }

        repositoryHistorico.save(registro);

    }
}
