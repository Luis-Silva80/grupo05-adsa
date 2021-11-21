package b.com.tothlibs.apitothlib;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
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
import org.mockito.Mock;
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
    static class UsuarioAdminServiceTestConfiguration{
        @Bean
        public Usuario usuarioAdminService(){
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
    public void setup(){

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

        Mockito.when(livrosRepository.findById(l.getId()))
                .thenReturn(java.util.Optional.of(l));

        Mockito.when(livrosRepository.findAll())
                .thenReturn(list);
    }
    
    @Test
    public void BuscaLivbroPorIdTest() {

        Integer idTeste = 90000;

        Optional<Livros> livroTeste = usuario.buscarLivro(idTeste);

        Assertions.assertEquals(90000,livroTeste.get().getId());
        Assertions.assertEquals("TesteJunit",livroTeste.get().getTitulo());
        Assertions.assertEquals(0,livroTeste.get().getQtdReservadoAgora());


    }

    @Test
    public void BuscarUmaListaDeLivros(){

        List<Livros> listTeste = usuario.consultaListaLivros();
        Assert.assertEquals(2,listTeste.size());
        Assert.assertEquals("90000",listTeste.get(0).getId().toString());
        Assert.assertEquals("TesteJunit",listTeste.get(0).getTitulo());

    }

    
}
