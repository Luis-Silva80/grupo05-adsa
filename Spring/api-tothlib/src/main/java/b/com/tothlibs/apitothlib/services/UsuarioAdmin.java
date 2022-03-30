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

    Livros   livro        = null;
    Exemplar exemplar     = null;
    Historico registro    = null;
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

            return new Response(00,"Livro Deletado com sucesso");

        } else {
            return new Response(02,"Livro não existe na base");
        }

    }

    @Override
    public Response alterarLivro(Integer id, Livros livroAtualizado) {

        if (repositoryLivros.existsById(id)) {
            livroAtualizado.setId(id);
            livroAtualizado.setFkTbBiblioteca(1);
            repositoryLivros.save(livroAtualizado);
            return new Response(00,"Livro atualizado");
        } else {
            return new Response(02,"Livro não encontrado");
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
    public Integer locarLivro(Integer idRegistro, Integer idUsuario) {
        return null;
    }

    @Override
    public Integer renovarAlocacao(Integer id, Integer idUsuario) {
        return null;
    }

    @Override
    public Integer devolverLivro(Integer idRegistro, Integer idUsuario) {
        return null;
    }

    @Override
    public Integer reservar(String tombo, Integer idUsuario) {

        Exemplar exemplar = repositoryExemplar.findByTombo(tombo);
        PerfilUsuario usuario = repositoryUsuario.findById(idUsuario).get();
        Livros livro;

        if(!exemplar.getId().equals(null)){
            livro = repositoryLivros.findLivroById(exemplar.getFkTbLivro());
        }else {
            return 0;
        }

        if(exemplar.getReservado().equals(0) && exemplar.getRetirado().equals(0)){

            exemplar.setReservado(true);
            livro.setQtdReservadosAgora(livro.getQtdReservadosAgora() + 1);
            livro.setQtdReservadosTotal(livro.getQtdReservadosTotal() + 1);

            repositoryLivros.save(livro);
            repositoryExemplar.save(exemplar);

            criaResgistroDeReserva(tombo, exemplar.getNrExemplar(), idUsuario, "RESERVA");

        }

        Historico u = repositoryHistorico.
                findTopByFkTbPerfilUsuarioAndFkTbExemplarOrderByIdDesc(idUsuario, tombo);

        System.out.println("teste");

        return u.getId();

    }

//    @Override
//    public Integer reservar(String tombo, Integer idUsuario) {
//
////        try {
//
//            Exemplar exemplar = repositoryExemplar.findByTombo(tombo);
//            PerfilUsuario usuario = repositoryUsuario.findById(idUsuario).get();
//            Livros livro;
//
//            if(!exemplar.getId().equals(null)){
//                livro = repositoryLivros.findLivroById(exemplar.getFkTbLivro());
//            }else {
//                return ResponseEntity.status(400).body("Exemplar não disponivel");
//            }
//
//            if(exemplar.getReservado().equals(0) && exemplar.getRetirado().equals(0)){
//
//                exemplar.setReservado(true);
//                livro.setQtdReservadosAgora(livro.getQtdReservadosAgora() + 1);
//                livro.setQtdReservadosTotal(livro.getQtdReservadosTotal() + 1);
//
//                repositoryLivros.save(livro);
//                repositoryExemplar.save(exemplar);
//
//                criaResgistroDeReserva(tombo, exemplar.getNrExemplar(), idUsuario, "RESERVA");
//
//
//
//            }
//
//            Historico u = repositoryHistorico.
//                    findTopByFkTbPerfilUsuarioAndFkTbExemplarOrderByIdDesc(idUsuario, tombo);
//
//            System.out.println("teste");
//
////            if (u.getAcao().equals("Retirada") || u.getAcao().equals("Renovacao")) {
////                return ResponseEntity.status(400).body("Não pode reservar o mesmo livro sem devolver antes!");
////
////            } else if (u.getAcao().equals("Reserva")) {
////                return ResponseEntity.status(400).body("Você ja está com o livro reservado!!!");
////            } else {
////
////                Integer idRegistro = admin.reservar(idLivro, idUsuario);
////
////                if (idRegistro != null) {
////
////                    return ResponseEntity.status(200).body(idRegistro);
////                } else {
////
////                    return ResponseEntity.status(400).build();
////                }
////            }
////        } catch (NullPointerException e) {
////
////            Integer idRegistro = admin.reservar(idLivro, idUsuario);
////
////            if (idRegistro != null) {
////
////                return ResponseEntity.status(200).body(idRegistro);
////            } else {
////
////                return ResponseEntity.status(400).build();
////            }
////
////

//    @Override
//    public Integer locarLivro(Integer idRegistro, Integer idUsuario) {
//
//        usuario = repositoryUsuario.findById(idUsuario).get();
//        registro = repositoryHistorico.findById(idRegistro).get();
//
//        livro = repository;
//        Integer qtdReservadosAgora = livro.getQtdReservadoAgora();
//
//        if(usuario.getStatusAtivo()){
//            if (livro != null) {
//
//                if (livro.getQtdReservas().equals(0)) {
//                    return null;
//                } else {
//                    if (livro.getQtdReservas() >= livro.getQtdEstoque()) {
//                        livro.setId(registro.getFkTbLivros());
//                        livro.setStatusLivro("Indisponivel");
//                        repository.save(livro);
//                        return null;
//                    } else {
//                        if (qtdReservadosAgora.equals(livro.getQtdReservas())) {
//                            livro.setId(registro.getFkTbLivros());
//                            livro.setStatusLivro("Indisponivel");
//                            repository.save(livro);
//                            return null;
//                        } else {
//
//                            if (livro.getQtdReservadoAgora() >= livro.getQtdEstoque()) {
//                                livro.setId(registro.getFkTbLivros());
//                                livro.setStatusLivro("Indisponivel");
//                                repository.save(livro);
//                                return null;
//                            }
//
//                            //alterar verificação para se o registro existe ao invéz de se o registro com aquele ID existe pois a segunda opção está redundante
//                            if (registro.getId().equals(idRegistro)) {
//
//                                livro.setId(registro.getFkTbLivros());
//                                livro.setQtdReservadoAgora(qtdReservadosAgora + 1);
//                                livro.setQtdReservas(livro.getQtdReservas() - 1);
//
//                                if (livro.getQtdReservadoAgora().equals(livro.getQtdEstoque())) {
//                                    livro.setStatusLivro("Indisponivel");
//                                } else {
//                                    livro.setStatusLivro("Disponivel");
//                                }
//
//                                repository.save(livro);
//                                criaResgistroComIdRegistro(idRegistro, registro.getFkTbLivros(), idUsuario, "Retirada");
//
//                                Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();
//                                return ultimoRegistro.getId();
//                            } else {
//                                return null;
//                            }
//
//                        }
//                    }
//                }
//
//            } else {
//                return null;
//            }
//        }else {
//            return null;
//        }
//
//
//    }
//
//    @Override
//    public Integer renovarAlocacao(Integer idRegistro, Integer idUsuario) {
//
//        Historico registro = repositoryHistorico.findById(idRegistro).get();
//
//        if(usuario.getStatusAtivo()){
//            if (registro.getFkTbPerfilUsuario().equals(idUsuario)) {
//                if (registro.getAcao().equals("Retirada")) {
//
//
//                    criaResgistroComIdRegistro(idRegistro, registro.getFkTbExemplar(), idUsuario, "Renovacao");
//
//                    Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();
//                    return ultimoRegistro.getId();
//                } else {
//                    return null;
//                }
//
//            } else {
//                return null;
//            }
//        }else {
//            return null;
//        }
//
//    }
//
//    @Override
//    public Integer devolverLivro(Integer idRegistro, Integer idUsuario) {
//
//
//        if(usuario.getStatusAtivo()){
//            if (repositoryHistorico.existsById(idRegistro)) {
//
//                Historico registroAntigo = repositoryHistorico.findById(idRegistro).get();
//                Integer idLivroNoRegistro = registroAntigo.getFkTbExemplar();
//
//                livro = repository.findById(idLivroNoRegistro).get();
//                usuario = repositoryUsuario.findById(idUsuario).get();
//
//                if (!livro.get().equals(0)) {
//                    if (registroAntigo.getAcao().equals("Renovacao") || registroAntigo.getAcao().equals("Retirada")) {
//
//                        livro.setId(idLivroNoRegistro);
//                        livro.setQtdReservadoAgora(livro.getQtdReservadoAgora() - 1);
//
//                        usuario.setId(idUsuario);
//                        usuario.setQtdLivrosLidos(usuario.getQtdLivrosLidos() + 1);
//                        usuario.setLivrosReservados(usuario.getLivrosReservados() - 1);
//                        usuario.setPontos(usuario.getPontos()+5);
//
//                        criaResgistro(idLivroNoRegistro, idUsuario, "Devolucao");
//                        Historico ultimoRegistro = repositoryHistorico.findTopByOrderByIdDesc();
//
//                        repositoryUsuario.save(usuario);
//                        repository.save(livro);
//                        return ultimoRegistro.getId();
//                    } else {
//                        return null;
//                    }
//
//                } else {
//                    return null;
//                }
//
//            } else {
//                return null;
//            }
//        }else {
//            return null;
//        }
//    }


    public void criaResgistroDeReserva(String tombo, Integer nrExemplar,  Integer idUsuario, String tipoRegistro) {

        registro = new Historico();


//        livro = repositoryLivros.findById(idLivro).get();

        exemplar = repositoryExemplar.findByTombo(tombo);

        usuario = repositoryUsuario.findById(idUsuario).get();

        registro.setFkTbPerfilUsuario(usuario.getId());
        registro.setTombo(tombo);
        registro.setNrExemplar(nrExemplar);
        registro.setAcao(tipoRegistro);
        registro.setNomePerfilUsuario(usuario.getNome());
        registro.setNomeLivro(livro.getTitulo());
        registro.setDataRegistro(LocalDate.now());

        repositoryHistorico.save(registro);

    }

//    public void criaResgistroComIdRegistro(Integer idRegistro, Integer nrExemplar, Integer idUsuario, String tipoRegistro) {
//
//        usuario = repositoryUsuario.findById(idUsuario).get();
//        registro = new Historico();
//
//
//        livro = repositoryLivros.findById(idLivro).get();
//
//        LocalDate dataAntiga = repositoryHistorico.findDataDevolucao(idRegistro);
//
//        registro.setFkTbPerfilUsuario(usuario.getId());
//        registro.setNrExemplar(nrExemplar);
//        registro.setAcao(tipoRegistro);
//        registro.setNomePerfilUsuario(usuario.getNome());
//        registro.setNomeLivro(livro.getTitulo());
//        registro.setDataRegistro(LocalDate.now());
//
//        if (tipoRegistro.equals("Retirada")) {
//            registro.setDataPrevDevolucao(LocalDate.now().plusDays(10));
//        } else {
//            registro.setDataPrevDevolucao(dataAntiga.plusDays(10));
//        }
//
//        repositoryHistorico.save(registro);
//
//    }
}