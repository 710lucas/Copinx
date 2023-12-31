package com.luxs.copinx.copinx.service.Usuario;

import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.reviewException;
import com.luxs.copinx.copinx.service.Exceptions.usuarioInvalidoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

    private String nome;
    private String senha;
    private String token;
    private int idade;
    private List<Usuario> seguidores =  new ArrayList<>();
    private List<Usuario> seguindo = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    public Usuario(String nome, int idade, String senha){
        this.nome = nome;
        this.idade = idade;
        this.senha = senha;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void addSeguidor(Usuario seguidor) throws usuarioInvalidoException {
        if(temSeguidor(seguidor))
            throw new usuarioInvalidoException("O usuario "+seguidor.getNome()+" já existe na lista de seguidores");
        seguidores.add(seguidor);
    }

    public void removeSeguidor(Usuario seguidor) throws usuarioInvalidoException {
        if(!temSeguidor(seguidor))
            throw new usuarioInvalidoException("É necessário que o usuário "+seguidor.getNome()+" siga você para que você possa removê-lo da sua lista de seguidores");
        seguidores.remove(seguidor);
    }

    public boolean temSeguidor(Usuario seguidor){
        return seguidores.contains(seguidor);
    }

    public void addSeguindo(Usuario seguir) throws  usuarioInvalidoException{
        if(temSeguindo(seguir))
            throw new usuarioInvalidoException("Você já segue o usuario "+seguir.getNome());
        seguindo.add(seguir);
    }

    public void removeSeguindo(Usuario seguir) throws usuarioInvalidoException {
        if(!temSeguindo(seguir))
            throw new usuarioInvalidoException("Você precisa ser amigo deste usuário para que você possa removê-lo");
        seguindo.remove(seguir);
    }

    public boolean temSeguindo(Usuario usuario){
        return seguindo.contains(usuario);
    }

    public void addReview(Review review) throws reviewException {
        if(reviews.contains(review))
            throw new reviewException("Review ja existe");
        reviews.add(review);
    }

    public void removeReview(Review review) throws reviewException {
        if(!reviews.contains(review))
            throw new reviewException("Review não existe");
        reviews.remove(review);
    }

    public boolean temReview(Review review){
        return reviews.contains(review);
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome(){
        return nome;
    }

    public String getSenha(){
        return senha;
    }

    public String getToken() {
        return token;
    }

    public List<Usuario> getSeguidores(){
        return seguidores;
    }

    public List<Usuario> getSeguindo() {
        return seguindo;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
