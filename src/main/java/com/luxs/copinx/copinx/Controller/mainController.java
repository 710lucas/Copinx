package com.luxs.copinx.copinx.Controller;

import com.luxs.copinx.copinx.service.Exceptions.aguaInvalidaException;
import com.luxs.copinx.copinx.service.Exceptions.usuarioInvalidoException;
import com.luxs.copinx.copinx.service.GerenciadorAgua;
import com.luxs.copinx.copinx.service.GerenciadorReview;
import com.luxs.copinx.copinx.service.GerenciadorUsuario;
import com.luxs.copinx.copinx.service.Usuario.Usuario;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class mainController {

    GerenciadorUsuario gerenciador;
    GerenciadorAgua gerenciadorA;



    @Autowired
    public mainController(GerenciadorUsuario u, GerenciadorAgua a){
        gerenciador = u;
        gerenciadorA = a;
    }

    @GetMapping("/")
    public String mainPage(@CookieValue(value = "token", required = false) String token){
        if(token == null)
            return "login";
        try {
            gerenciador.getUsuarioByToken(token);
            return "redirect:/home";
        } catch (usuarioInvalidoException e) {
            return "login";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/home")
    public String homePage(@CookieValue("token") String token, Model model){

        try {
            model.addAttribute("nome", gerenciador.getUsuarioByToken(token).getNome());
            return "home";
        } catch (usuarioInvalidoException e) {
           return "login" ;
        }

    }

    @GetMapping("/profile/{username}")
    public String profile(@CookieValue(value = "token", required = false) String token, Model model, @PathVariable("username") String username){
        Usuario usuario = null;
        try {
            usuario = gerenciador.getUsuarioByToken(token);
        } catch (usuarioInvalidoException e) {
            System.out.println("leel");
            System.out.println(e.getMessage()+": "+token);
        }
        try {
            Usuario usuario1 = gerenciador.getUsuario(username);
            System.out.println(usuario1.getToken());
            if(usuario == usuario1) {
                model.addAttribute("ehUsuario", true);
            }
            else {
                model.addAttribute("ehUsuario", false);
            }
            model.addAttribute("nome", username);
            model.addAttribute("descricao", "WIP!");
            model.addAttribute("seguindo", usuario1.getSeguindo().size());
            model.addAttribute("seguidores", usuario1.getSeguidores().size());
        } catch (usuarioInvalidoException e) {
            System.out.println("laal");
            return "home";
        }

        return "profile";
    }

    @GetMapping("/waters")
    public String getWaters(){
        return "waters";
    }

    @GetMapping("/review/{user}/{index}")
    public String getReview(){
        return null;
    }


    @GetMapping("/water/{name}")
    public String getWater(@PathVariable("name") String nome, Model model){
        try {
            model.addAttribute("waterName", gerenciadorA.getAgua(nome).getNome());
            model.addAttribute("waterRating", gerenciadorA.getAgua(nome).calculaNotaGeral());
            model.addAttribute("waterDescription", gerenciadorA.getAgua(nome).getDescricao());
            return "water";
        } catch (aguaInvalidaException e) {
            System.out.println(e.getMessage());
            System.out.println(nome);
            return "redirect:/home";
        }
    }

}
