package b.com.tothlibs.apitothlib.controlers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instituicao")
public class ControlerInstituicao {


    @PostMapping
    public String cadastroInst(@RequestBody String novaInsituicao){


        return "";

    }

    @GetMapping
    public String exibirInst(){

        return "";

    }

}
