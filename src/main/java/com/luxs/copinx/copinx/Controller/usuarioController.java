package com.luxs.copinx.copinx.Controller;

import com.luxs.copinx.copinx.service.Exceptions.usuarioInvalidoException;
import com.luxs.copinx.copinx.service.GerenciadorUsuario;
import com.luxs.copinx.copinx.service.Usuario.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class usuarioController {

    GerenciadorUsuario gerenciador = new GerenciadorUsuario();

    @GetMapping("/api/usuario")
    public String listar(){
        return gerenciador.toString();
    }

    @PostMapping("/api/usuario/add")
    public String addUsuario(@RequestParam("nome") String nome, @RequestParam("senha") String senha, @RequestParam("idade") int idade){
        try {
            gerenciador.adicionarUsuario(nome, idade, senha);
            return "Usuario adicionado com sucesso";
        } catch (usuarioInvalidoException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/api/usuario/adicionarSeguindo")
    public String addSeguidor(@RequestParam("nome") String nome, @RequestParam("nomeSeguir") String nomeSeguir){
        try {
            gerenciador.seguirUsuario(gerenciador.getUsuario(nome), gerenciador.getUsuario(nomeSeguir));
            return "Usuario "+nomeSeguir+" seguido com sucesso";
        } catch (usuarioInvalidoException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/api/usuario/removerSeguindo")
    public String removerSeguidor(@RequestParam("nome") String nome, @RequestParam("nomeRemover") String nomeRemover){
        try {
            gerenciador.deixarDeSeguir(gerenciador.getUsuario(nome), gerenciador.getUsuario(nomeRemover));
            return "Você deixou de seguir "+nomeRemover+" com sucesso!";
        } catch (usuarioInvalidoException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/api/usuario/listarSeguindo")
    public String listarSeguindo(@RequestParam("nome") String nome){
        try {
            List<Usuario> seguindo = gerenciador.getUsuario(nome).getSeguindo();
            String out = "";
            for(Usuario u : seguindo){
                out+=u.getNome()+"\n";
            }
            return out;
        } catch (usuarioInvalidoException e) {
            return e.getMessage();
        }
    }
    @PostMapping("/api/usuario/listarSeguidores")
    public String listarSeguidores(@RequestParam("nome") String nome) {
        try {
            List<Usuario> seguidores = gerenciador.getUsuario(nome).getSeguidores();
            String out = "";
            for (Usuario u : seguidores) {
                out += u.getNome() + "\n";
            }
            return out;
        } catch (usuarioInvalidoException e) {
            return e.getMessage();
        }
    }


    }
