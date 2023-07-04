package com.luxs.copinx.copinx.service;

import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.reviewException;
import com.luxs.copinx.copinx.service.Exceptions.usuarioInvalidoException;
import com.luxs.copinx.copinx.service.Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuario {

    public List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void adicionarUsuario(String nome, int idade){
        usuarios.add(new Usuario(nome, idade));
    }

    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public String pesquisaUsuarios(String nome){
        String out = "";
        for(Usuario u : usuarios){
            if(u.getNome().contains(nome))
                out+=u.getNome()+"\n";
        }
        return out;
    }

    public void seguirUsuario(Usuario usuarioQueVaiSeguir, Usuario usuarioASerSeguido) throws usuarioInvalidoException {
        usuarioQueVaiSeguir.addSeguindo(usuarioASerSeguido);
        usuarioASerSeguido.addSeguidor(usuarioQueVaiSeguir);
    }

    public void deixarDeSeguir(Usuario usuarioQueVaiDeixar, Usuario usuarioASerDeixado) throws usuarioInvalidoException {
        usuarioQueVaiDeixar.removeSeguindo(usuarioASerDeixado);
        usuarioASerDeixado.removeSeguidor(usuarioQueVaiDeixar);
    }

    public void adicionarReview(Review review, Usuario usuario) throws reviewException {
        usuario.adicionaReview(review);
    }

    public void removerReview(Review review, Usuario usuario) throws reviewException {
        usuario.removeReview(review);
    }

    public String pesquisaReviews(String conteudo, Usuario usuario){
        String out="";
        for(Review r : usuario.getReviews()){
            if(r.getDescricao().contains(conteudo))
                out+=r.getDescricao()+"\n";
        }
        return out;
    }

    public String pesquisaSeguindo(String nome, Usuario usuario){
        String out="";
        for(Usuario u : usuario.getSeguindo()){
            if(u.getNome().contains(nome))
                out+=u.getNome()+"\n";
        }

        return out;
    }

    public String pesquisaSeguidores(String nome, Usuario usuario){
        String out="";
        for(Usuario u : usuario.getSeguidores()){
            if(u.getNome().contains(nome))
                out+=u.getNome()+'\n';
        }
        return out;
    }

}
