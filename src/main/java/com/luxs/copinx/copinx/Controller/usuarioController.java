package com.luxs.copinx.copinx.Controller;

import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.usuarioInvalidoException;
import com.luxs.copinx.copinx.service.GerenciadorUsuario;
import com.luxs.copinx.copinx.service.Usuario.Usuario;
import com.luxs.copinx.copinx.service.arquivo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
@RestController
public class usuarioController {

    GerenciadorUsuario gerenciador;

    @Autowired
    public usuarioController(GerenciadorUsuario gerenciador){
        this.gerenciador = gerenciador;
    }

    @GetMapping("/api/usuario")
    public String listar(){
        return gerenciador.toString();
    }

    @PostMapping("/api/usuario/add")
    public String addUsuario(@RequestParam("nome") String nome, @RequestParam("senha") String senha, @RequestParam("idade") int idade){
        try {
            gerenciador.adicionarUsuario(nome, idade, senha);
            arquivo.salvar(arquivo.GERENCIADOR_USUARIO, gerenciador);
            return "Usuario adicionado com sucesso";
        } catch (usuarioInvalidoException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/api/usuario/login")
    public String login(@RequestParam("nome") String nome, @RequestParam("senha") String senha){
        try{
            String token = gerenciador.login(nome, senha);
            System.out.println(token);
            return token;
        } catch (usuarioInvalidoException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/api/usuario/logout")
    public String logout(@CookieValue("token") String token){
        if(gerenciador.logout(token)){
            return "Logout realizado com sucesso";
        }
        return "Houve uma falha ao realizar o logout";
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

    @GetMapping("/api/usuario/listarSeguindo")
    public ResponseEntity<String> listarSeguindo(@RequestParam("nome") String nome){
        try {
            List<Usuario> seguindo = gerenciador.getUsuario(nome).getSeguindo();
            String out = "";
            for(Usuario u : seguindo){
                out+=u.getNome()+"\n";
            }
            return ResponseEntity.status(HttpStatus.OK).body(out);
        } catch (usuarioInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/api/getUsuarioByToken")
    public ResponseEntity<String> getByToken(@RequestParam("token") String token){
        if(token == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("É necessário fazer login");

        try {
            String usuario = gerenciador.getUsuarioByToken(token).getNome();
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (usuarioInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel encontrar este usuário");
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

    @GetMapping("/api/usuario/getReviews")
    public ResponseEntity<String> listarReviews(@RequestParam("nome") String nome){
        try {
            String out="";
            for(Review r : gerenciador.getUsuario(nome).getReviews()){
                out+=r.toString()+";"+gerenciador.getUsuario(nome).getReviews().indexOf(r)+"\n";
            }
            return ResponseEntity.status(HttpStatus.OK).body(out);
        } catch (usuarioInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/api/usuario/getAguas")
    public ResponseEntity<String> listarAguas(@RequestParam("nome") String nome){
        try {
            String out="";
            for(Review r : gerenciador.getUsuario(nome).getReviews()){
                out+=r.getAgua().getNome()+"\n";
            }
            return ResponseEntity.status(HttpStatus.OK).body(out);
        } catch (usuarioInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<String> getUserProfile(@PathParam("name") String name){
        return ResponseEntity.status(200).body("!");
    }



    }
