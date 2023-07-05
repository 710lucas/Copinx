package com.luxs.copinx.copinx.service;

import com.luxs.copinx.copinx.service.Agua.Agua;
import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.notaInvalidaException;
import com.luxs.copinx.copinx.service.Usuario.Usuario;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class GerenciadorReview {

    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Usuario autor, String descricao, int nota, Agua agua) throws notaInvalidaException {
        reviews.add(new Review(autor, descricao, nota, agua));
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
            out+=r.getAutor().getNome()+" "+r.getDescricao()+" "+r.getNota()+"\n";
        }

        return out;
    }

    public GerenciadorReview ordenarReviews(){
        List<Review> r = this.getReviews();
        Collections.sort(r);
        GerenciadorReview g = new GerenciadorReview();
        return g;
    }



}
