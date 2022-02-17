package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Livros;
import b.com.tothlibs.apitothlib.repository.CategoriaRepository;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.repository.LivrosRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import b.com.tothlibs.apitothlib.services.UsuarioAdmin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BibliotecaController.class,
        LivrosRepository.class,
        PerfilUsuarioRepository.class,
        CategoriaRepository.class,
        HistoricoRepository.class})
class BibliotecaControllerTest {

    @Autowired
    BibliotecaController controller;

    @MockBean
    private LivrosRepository repository;

    @MockBean
    private PerfilUsuarioRepository repositoryUsuario;

    @MockBean
    private CategoriaRepository repositoryCategoria;

    @MockBean
    private HistoricoRepository repositoryHistorico;

    @MockBean
    UsuarioAdmin usuario;

    @Test
    void deveRetornarUmaLista(){
        List<Livros> listaFrutasMock = List.of(mock(Livros.class), mock(Livros.class));

        when(usuario.consultaListaLivros()).thenReturn(listaFrutasMock);


        ResponseEntity resposta = controller.listaLivros();

        // Verificações

        // é 200 o código de status de resposta do endpoint?
        assertEquals(200, resposta.getStatusCodeValue());

        // O corpo da resposta do endpoint é exatamente a lista que a repository entregou?
        assertEquals(listaFrutasMock, resposta.getBody());
    }

}