package b.com.tothlibs.apitothlib.controlers;

import b.com.tothlibs.apitothlib.entity.Historico;
import b.com.tothlibs.apitothlib.entity.PerfilUsuario;
import b.com.tothlibs.apitothlib.repository.HistoricoRepository;
import b.com.tothlibs.apitothlib.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoRepository repository;

    @GetMapping
    public ResponseEntity pendencia() {

        List<Historico> listaDeHistorico = repository.findUserPendencia().stream()
                .filter(historico -> historico.getAcao().equals("Renovacao") ||
                        historico.getAcao().equals("Locacao")).collect(Collectors.toList());



        return ResponseEntity.status(200).body(listaDeHistorico);
    }

}
