package com.luxs.copinx.copinx.Controller;

import com.luxs.copinx.copinx.service.Agua.Agua;
import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.aguaInvalidaException;
import com.luxs.copinx.copinx.service.Exceptions.notaInvalidaException;
import com.luxs.copinx.copinx.service.Exceptions.reviewException;
import com.luxs.copinx.copinx.service.Exceptions.usuarioInvalidoException;
import com.luxs.copinx.copinx.service.GerenciadorAgua;
import com.luxs.copinx.copinx.service.GerenciadorReview;
import com.luxs.copinx.copinx.service.GerenciadorUsuario;
import com.luxs.copinx.copinx.service.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class reviewController {

    GerenciadorUsuario gerenciadorU;
    GerenciadorAgua gerenciadorA;
    GerenciadorReview gerenciadorR;

    @Autowired
    public reviewController(GerenciadorUsuario u, GerenciadorAgua a, GerenciadorReview r){
        gerenciadorU = u;
        gerenciadorR = r;
        gerenciadorA = a;
    }

    @GetMapping("/api/reviews")
    public String listar(){
        return gerenciadorR.toString();
    }

    @PostMapping("/api/review/add")
    public String add(@RequestParam("agua") String aguaNome, @RequestParam("nota") int nota, @RequestParam("usuario") String usuario, @RequestParam("descricao") String descricao){
        try {
            Agua a = gerenciadorA.getAgua(aguaNome);
            Usuario u = gerenciadorU.getUsuario(usuario);
            Review r = gerenciadorR.addReview(u, descricao, nota, a);
            a.addReview(r);
            u.addReview(r);
            return  "Review adicionada com sucesso";

        } catch (aguaInvalidaException | usuarioInvalidoException | notaInvalidaException e) {
            return e.getMessage();
        } catch (reviewException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/api/review/sort-agua")
    public String sortA(@RequestParam("nome") String aguaNome){
        try {
            return gerenciadorR.ordenarReviews(gerenciadorA.getAgua(aguaNome).getReviews()).toString();
        } catch (aguaInvalidaException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/api/review/sort-user")
    public String sortU(@RequestParam("nome") String userNome){
        try {
            return gerenciadorR.ordenarReviews(gerenciadorU.getUsuario(userNome).getReviews()).toString();
        } catch (usuarioInvalidoException e) {
            return e.getMessage();
        }
    }



}
