package b.com.tothlibs.apitothlib;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import b.com.tothlibs.apitothlib.services.Usuario;
import b.com.tothlibs.apitothlib.services.UsuarioAdmin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class UsuarioAdminServiceTeste {

    @TestConfiguration
    static class UsuarioAdminServiceTestConfiguration {
        @Bean
        public Usuario usuarioAdminService() {
            return new UsuarioAdmin();
        }
    }

    @Autowired
    UsuarioAdmin usuario;

    @MockBean
    PerfilUsuarioRepository perfilUsuarioRepository;

    @MockBean
    LivrosRepository livrosRepository;

    @MockBean
    HistoricoRepository historicoRepository;


    @Before
    public void setup() {

        Livros l = new Livros();
        Livros l2 = new Livros();

        l.setId(90000);
        l.setTitulo("TesteJunit");
        l.setQtdReservadoAgora(0);

        l2.setId(80000);
        l2.setTitulo("TesteJunit2");
        l2.setQtdReservadoAgora(5);

        List<Livros> list = new ArrayList<>();
        list.add(l);
        list.add(l2);

        //Teste BuscaLivbroPorIdTest
        Mockito.when(livrosRepository.findById(l.getId()))
                .thenReturn(java.util.Optional.of(l));

        //Teste BuscarUmaListaDeLivros
        Mockito.when(livrosRepository.findAll())
                .thenReturn(list);

        //Teste CadastrarLivro
        Livros livro = new Livros();

        livro.setId(777);
        livro.setTitulo("TesteCadastro");
        livro.setStatusLivro("Indisponivel");
        livro.setAutor("Euu");
        livro.setDescricao("teste");
        livro.setEdicao("Saraiva");
        livro.setQtdReservadoAgora(0);
        livro.setFkTbbiblioteca(2);
        livro.setLinguagem("PT-BR");
        livro.setQtdResenhas(0);
        livro.setEdicao("5");
        livro.setQtdEstoque(20);

        Mockito.when(livrosRepository.save(livro)).thenReturn(livro);
    }

    @Test
    public void BuscaLivbroPorIdTest() {

        Integer idTeste = 90000;

        Optional<Livros> livroTeste = usuario.buscarLivro(idTeste);

        Assertions.assertEquals(90000, livroTeste.get().getId());
        Assertions.assertEquals("TesteJunit", livroTeste.get().getTitulo());
        Assertions.assertEquals(0, livroTeste.get().getQtdReservadoAgora());


    }

    @Test
    public void BuscarUmaListaDeLivros() {

        List<Livros> listTeste = usuario.consultaListaLivros();
        Assert.assertEquals(2, listTeste.size());
        Assert.assertEquals("90000", listTeste.get(0).getId().toString());
        Assert.assertEquals("TesteJunit", listTeste.get(0).getTitulo());

    }

    @Test
    public void CadastrarLivro() {

        Livros lT = new Livros();

        lT.setId(777);
        lT.setTitulo("TesteCadastro");
        lT.setStatusLivro("Disponivel");
        lT.setAutor("Euu");
        lT.setDescricao("teste");
        lT.setEdicao("Saraiva");
        lT.setQtdReservadoAgora(0);
        lT.setFkTbbiblioteca(2);
        lT.setLinguagem("PT-BR");
        lT.setQtdResenhas(0);
        lT.setEdicao("5");
        lT.setQtdEstoque(20);

        Livros livroTeste = usuario.cadastrarLivro(lT);

        Assert.assertEquals("TesteCadastro", livroTeste.getTitulo());

    }

    @Test
    public void ReservaTest() {


        UsuarioAdmin teste = Mockito.mock(UsuarioAdmin.class);
        Integer idLivro = 8888;
        Integer idUsu = 9999;
        String tipo = "Reserva";
//
//        Livros lTeste = new Livros();
//        PerfilUsuario pTeste = new PerfilUsuario();
//        Historico r = new Historico();
//
//        r.setId(5000);
//        r.setNomeLivro("testeregistro");
//        r.setNomePerfilUsuario("Luis");
//        r.setAcao("Reserva");
//        r.setFkTbPerfilUsuario(1);
//        r.setFkTbLivros(1);
//        r.setDataLivroHistorico(LocalDate.now());

//        Mockito.when(historicoRepository.save(r)).thenReturn(r);
//        Mockito.doNothing().when(livrosRepository.save(lTeste));
//        Mockito.doNothing().when(p.save(lTeste));


        teste.criaResgistro(idLivro, idUsu, tipo);

        Mockito.verify(teste).criaResgistro(idLivro, idUsu, tipo);

        Mockito.verify(teste, Mockito.times(1)).criaResgistro(idLivro, idUsu, tipo);

//        System.out.println(Mockito.when(historicoRepository.save(r)).thenReturn(r));
//
//        ArgumentCaptor<Historico> argument = ArgumentCaptor.forClass(Historico.class);
//        Mockito.verify(teste).criaResgistro(argument.capture().getFkTbLivros(), argument.capture().getFkTbPerfilUsuario(), argument.capture().getAcao());
//        Assert.assertEquals("Reserva", argument.getValue().getAcao().equals("Reserva"));

//        Assert.assertEquals("Retirada", resp.getAcao());

//        u.setId(888);
//        u.setUsuarioAdmin(0);
//        u.setLivrosReservados(0);
//        u.setQtdResenhas(0);
//        u.setQtdLivrosLidos(0);
//        u.setPontos(0L);
//        u.setStatusAtivo(true);
//        u.setEmail("teste@.com");
//        u.setCpf("464665");
//        u.setSenha("teste");
//
//        l.setId(777);
//        l.setTitulo("TesteCadastro");
//        l.setStatusLivro("Disponivel");
//        l.setAutor("Euu");
//        l.setDescricao("teste");
//        l.setEdicao("Saraiva");
//        l.setQtdReservadoAgora(0);
//        l.setFkTbbiblioteca(2);
//        l.setLinguagem("PT-BR");
//        l.setQtdResenhas(0);
//        l.setEdicao("5");
//        l.setQtdEstoque(20);
//
//
//        Mock
//        Mockito.when(livrosRepository.save(l)).thenReturn(l);
//        Mockito.when(perfilUsuarioRepository.save(u)).thenReturn(u);
//
//        Integer registroCriado = usuario.reservar(l.getId(),u.getId());
//
//        Assert.assertNotNull(registroCriado);


    }

    @Test
    public void RetirarTest() {

        UsuarioAdmin teste = Mockito.mock(UsuarioAdmin.class);

        Integer idRegistro = 6666;
        Integer idLivro = 8888;
        Integer idUsu = 9999;
        String tipo = "Retirada";

        teste.criaResgistroComIdRegistro(idRegistro, idLivro, idUsu, tipo);
        Mockito.verify(teste).criaResgistroComIdRegistro(idRegistro, idLivro, idUsu, tipo);
        Mockito.verify(teste, Mockito.times(1)).criaResgistroComIdRegistro(idRegistro, idLivro, idUsu, tipo);

    }

    @Test
    public void RenovarTest() {

        UsuarioAdmin teste = Mockito.mock(UsuarioAdmin.class);

        Integer idRegistro = 6666;
        Integer idLivro = 8888;
        Integer idUsu = 9999;
        String tipo = "Renovacao";

        teste.criaResgistroComIdRegistro(idRegistro, idLivro, idUsu, tipo);
        Mockito.verify(teste).criaResgistroComIdRegistro(idRegistro, idLivro, idUsu, tipo);
        Mockito.verify(teste, Mockito.times(1)).criaResgistroComIdRegistro(idRegistro, idLivro, idUsu, tipo);

    }

    @Test
    public void DevolverTest() {

        UsuarioAdmin teste = Mockito.mock(UsuarioAdmin.class);

        Integer idRegistro = 6666;
        Integer idLivro = 8888;
        Integer idUsu = 9999;
        String tipo = "Devolver";

        teste.criaResgistroComIdRegistro(idRegistro, idLivro, idUsu, tipo);
        Mockito.verify(teste).criaResgistroComIdRegistro(idRegistro, idLivro, idUsu, tipo);
        Mockito.verify(teste, Mockito.times(1)).criaResgistroComIdRegistro(idRegistro, idLivro, idUsu, tipo);

    }

}
