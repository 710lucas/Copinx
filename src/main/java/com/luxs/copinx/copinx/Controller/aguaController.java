package com.luxs.copinx.copinx.Controller;

import com.luxs.copinx.copinx.service.Agua.Agua;
import com.luxs.copinx.copinx.service.Exceptions.aguaInvalidaException;
import com.luxs.copinx.copinx.service.GerenciadorAgua;
import com.luxs.copinx.copinx.service.GerenciadorReview;
import com.luxs.copinx.copinx.service.arquivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            gerenciador.adicionarAgua(new Agua("Indaiá","Água Indaiá"));
            gerenciador.adicionarAgua(new Agua("Minalba", "Água Minalba"));
            gerenciador.adicionarAgua(new Agua("Crystal", "Água Crystal"));
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
            arquivo.salvar(arquivo.GERENCIADOR_AGUA, gerenciador);
            return "Água adicionada com sucesso";
        } catch (aguaInvalidaException e) {
            return e.getMessage();
        }
    }
    @PostMapping("/api/agua/remover")
    public String remover(@RequestParam("nome") String nome){
        try {
            gerenciador.removerAgua(gerenciador.getAgua(nome));
            arquivo.salvar(arquivo.GERENCIADOR_AGUA, gerenciador);
            return "Água adicionada com sucesso";
        } catch (aguaInvalidaException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/api/{agua}/getReviews")
    public String getReviews(@PathVariable("agua") String agua){
        GerenciadorReview r = new GerenciadorReview();
        try {
            r.setReviews(gerenciador.getAgua(agua).getReviews());
            return r.toString();
        } catch (aguaInvalidaException e) {
            return "redirect:/waters";
        }
    }

}
