package com.luxs.copinx.copinx.service.Usuario;

import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.usuarioInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private int idade;
    private List<Usuario> seguidores;
    private List<Usuario> seguindo;
    private List<Review> reviews;

    public Usuario(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void addSeguidor(Usuario seguidor){
        if(temSeguidor(seguidor))
            throw new usuarioInvalidoException("O usuario "+seguidor.getNome()+" já existe na lista de seguidores");
        seguidores.add(seguidor);
    }

    public void removeSeguidor(Usuario seguidor){
        if(!temSeguidor(seguidor))
            throw new usuarioInvalidoException("É necessário que o usuário "+seguidor.getNome()+" siga você para que você possa removê-lo da sua lista de seguidores");
        seguidores.remove(seguidor);
    }

    public boolean temSeguidor(Usuario seguidor){
        return seguidores.contains(seguidor);
    }

    public void addSeguindo(Usuario seguir){
        if(temSeguindo(seguir))
            throw new usuarioInvalidoException("Você já segue o usuario "+seguir.getNome());
        seguindo.add(seguir);
    }

    public void removeSeguindo(Usuario seguir){
        if(!temSeguindo(seguir))
            throw new usuarioInvalidoException("Você precisa ser amigo deste usuário para que você possa removê-lo");
        seguindo.remove(seguir);
    }

    public boolean temSeguindo(Usuario usuario){
        return seguindo.contains(usuario);
    }

    public void adicionaReview(Review review){
        reviews.add(review);
    }

    public void setNome(String nome){
        this.nome = nome;
    }


    public int getIdade() {
        return idade;
    }
}
