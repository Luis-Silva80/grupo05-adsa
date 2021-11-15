package b.com.tothlibs.apitothlib;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.services.Usuario;
import b.com.tothlibs.apitothlib.services.UsuarioAdmin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

public class UsuarioAdminServiceTeste {

    @TestConfiguration
    static class UsuarioAdminServiceTestConfiguration{
        @Bean
        public Usuario usuarioAdminService(){
            return new UsuarioAdmin();
        }
    }
   @Autowired
    UsuarioAdmin usuarioAdmin;

    @MockBean
    LivrosRepository livrosRepository;

    Livros livros = new Livros(1,"a","b","C","d",
            "e","f",1,2,3,4,5,"pt/br");

//    @Test
//    public void UsuarioAdminTesteServiceInsertUsuario(){
//          usuarioAdmin.cadastrarLivro(livros);
//
//          Assertions.assertEquals(livros,);
//    }

}
