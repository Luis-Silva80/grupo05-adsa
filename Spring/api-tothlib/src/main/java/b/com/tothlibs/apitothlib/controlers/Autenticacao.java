package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.dto.UsuarioDto;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autenticacao")
public class Autenticacao {

    List<UsuarioDto> listAutenticados = new ArrayList<UsuarioDto>();

    @Autowired
    private PerfilUsuarioRepository repository;

    @PostMapping("/{email}/{senha}")
    @ApiOperation(value = "Realiza a autenticação na plataforma")
    public ResponseEntity autenticar(@PathVariable String email, @PathVariable String senha) {


        PerfilUsuario user = repository.findByEmail(email);

        if(user != null){
            UsuarioDto usuarioDto = null;

            List<PerfilUsuario> listUsuarios = repository.findAll();

            for (PerfilUsuario u : listUsuarios) {
                if (u.getEmail().equals(email)) {
                    if (u.retornaSenha().equals(senha)) {
                        usuarioDto = new UsuarioDto(u);
                        listAutenticados.add(usuarioDto);
                    }

                }
            }
            return ResponseEntity.status(200).body(user.getId());
        }else {
            return ResponseEntity.status(404).build();
        }

       

    }

    @GetMapping("/usuariosLogados")
    @ApiOperation(value = "Verificar os usuaris autenticados")
    public ResponseEntity consultarAutenticados() {

        List<UsuarioDto> autenticados = listAutenticados.stream()
                .sorted(Comparator.comparing(UsuarioDto::getNome))
                .collect(Collectors.toList());

        if (autenticados.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(autenticados);
        }

    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Realiza o logout na plataforma")
    public ResponseEntity logoff(@PathVariable String id) {
        String retorno = "";
        Boolean isAutentic = false;
        PerfilUsuario usuario = repository.findById(id).get();

        for (UsuarioDto u : listAutenticados) {
            if (u.pegarId().equals(id)) {

                if (u.getAutenticado()) {

                    retorno = String.format("Usuário %s saiu do sistema", u.getNome());

                    u.setAutenticado(false);
                    u.setLogoff();

                    isAutentic = true;

                } else {
                    retorno = String.format("Usuário %s não autenticado", u.getNome());
                }
            }
        }

        if(!isAutentic){

            return ResponseEntity.status(200).body(retorno);
        }else {

            return ResponseEntity.status(200).body(retorno);
        }

    }


}
