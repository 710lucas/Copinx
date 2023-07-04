package com.luxs.copinx.copinx.Controller;

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
    public void addUsuario(@RequestParam("nome") String nome, @RequestParam("senha") String senha, @RequestParam("idade") int idade){
        gerenciador.adicionarUsuario(nome, idade, senha);
    }

}
