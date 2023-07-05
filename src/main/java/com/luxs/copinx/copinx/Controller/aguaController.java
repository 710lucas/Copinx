package com.luxs.copinx.copinx.Controller;

import com.luxs.copinx.copinx.service.Agua.Agua;
import com.luxs.copinx.copinx.service.Exceptions.aguaInvalidaException;
import com.luxs.copinx.copinx.service.GerenciadorAgua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class aguaController {

    GerenciadorAgua gerenciador;

    @Autowired
    public aguaController(GerenciadorAgua gerenciador){
        this.gerenciador = gerenciador;
    }

    @GetMapping("/api/povoar")
    public String teste(){
        try {
            gerenciador.adicionarAgua(new Agua("Indaia","A melhor água do Brasil!"));
            gerenciador.adicionarAgua(new Agua("Oraci13","A melhor água do mundo!"));
            gerenciador.adicionarAgua(new Agua("AguasClaras","Criada em 1918, a Águas Claras te proporciona uma agua com um gosto inesquecível!"));
        } catch (aguaInvalidaException e) {
            return e.getMessage();
        }
        return "povoado com sucesso";

    }

    @GetMapping("/api/aguas")
    public String listarAguas(){
        return gerenciador.toString();
    }

    @PostMapping("/api/agua/adicionar")
    public String adicionar(@RequestParam("nome") String nome, @RequestParam("descricao") String descricao){
        try {
            gerenciador.adicionarAgua(nome, descricao);
            return "Água adicionada com sucesso";
        } catch (aguaInvalidaException e) {
            return e.getMessage();
        }
    }

}
