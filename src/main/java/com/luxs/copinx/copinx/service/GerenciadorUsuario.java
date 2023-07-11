package com.luxs.copinx.copinx.service;

import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.reviewException;
import com.luxs.copinx.copinx.service.Exceptions.usuarioInvalidoException;
import com.luxs.copinx.copinx.service.Usuario.Usuario;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GerenciadorUsuario implements Serializable {

    private List<Usuario> usuarios;

    public GerenciadorUsuario(){
        usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void adicionarUsuario(String nome, int idade, String senha) throws usuarioInvalidoException {
        try{
            getUsuario(nome);
        } catch (usuarioInvalidoException e) {
            try {
                senha = hash(senha);
            } catch (NoSuchAlgorithmException ex) {
                throw new usuarioInvalidoException("houve um problema ao armazenar a senha");
            }
            usuarios.add(new Usuario(nome, idade, senha));
            return;
        }
        throw new usuarioInvalidoException("O nome informado ja existe: "+nome);
    }

    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void removeUsuario(Usuario usuario){
        usuarios.remove(usuario);
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
        usuario.addReview(review);
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

    public Usuario getUsuario(String nome) throws usuarioInvalidoException {
        for(Usuario u : usuarios){
            if(u.getNome()!=null && u.getNome().equals(nome))
                return u;
        }
        throw new usuarioInvalidoException("Não é possivel encontrar um usuario com este nome");
    }

    public Usuario getUsuarioByToken(String token) throws usuarioInvalidoException {
        for(Usuario u : usuarios){
            if(u.getToken()!=null && u.getToken().equals(token))
                return u;
        }

        throw new usuarioInvalidoException("Não é possivel encontrar um usuario com este token");
    }

    public boolean temUsuario(String nome) throws usuarioInvalidoException {
        return getUsuario(nome) != null;
    }

    public String toString(){
        String out="";
        for(Usuario u : usuarios){
            out+=u.getNome()+" "+u.getIdade()+"\n";
        }
        return out;
    }

    public String login(String nome, String senha) throws usuarioInvalidoException {
        try {
            if(getUsuario(nome).getSenha().equals(hash(senha))){
                getUsuario(nome).setToken(generateToken());
                return getUsuario(nome).getToken();
            }
            throw new usuarioInvalidoException("A senha informada é invalida");
        } catch (usuarioInvalidoException | NoSuchAlgorithmException e) {
            throw new usuarioInvalidoException(e.getMessage());
        }
    }

    public boolean logout(String token){
        try {
            if(token!=null) {
                getUsuarioByToken(token).setToken(null);
                return true;
            }
            return false;
        } catch (usuarioInvalidoException e) {
            return false;
        }
    }

    private String hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        String out = "";
        for(byte b : hash){
            out+= String.format("%02x", b);
        }
        return out;
    }

    private String generateToken(){
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        for(Usuario u : usuarios){
            if(u.getToken()!=null && u.getToken().equals(uuidString))
                uuidString = generateToken();
        }
        return uuidString;
    }

}
