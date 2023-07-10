package com.luxs.copinx.copinx.service;

import com.luxs.copinx.copinx.service.Agua.Agua;
import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.notaInvalidaException;
import com.luxs.copinx.copinx.service.Usuario.Usuario;
import org.springframework.javapoet.ClassName;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class GerenciadorReview implements Serializable {

    private List<Review> reviews = new ArrayList<>();

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Review addReview(Usuario autor, String descricao, int nota, Agua agua) throws notaInvalidaException {
        Review r = new Review(autor, descricao, nota, agua);
        reviews.add(r);
        return r;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public void removeReview(Review review){
        reviews.remove(review);
    }

    public void editReview(Review review, String novaDescricao){
        review.setDescricao(novaDescricao);
    }

    public String toString(){
        String out="";

        for(Review r : getReviews()){
            out+=r.getAutor().getNome()+";"+r.getAgua().getNome()+";"+r.getDescricao()+";"+r.getNota()+"\n";
        }

        return out;
    }

    public GerenciadorReview ordenarReviews(List<Review> r){
        Collections.sort(r);
        GerenciadorReview g = new GerenciadorReview();
        g.setReviews(r);
        return g;
    }



}
