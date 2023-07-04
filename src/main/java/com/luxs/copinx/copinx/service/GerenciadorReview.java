package com.luxs.copinx.copinx.service;

import com.luxs.copinx.copinx.service.Agua.Agua;
import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.notaInvalidaException;
import com.luxs.copinx.copinx.service.Usuario.Usuario;

import java.util.List;

public class GerenciadorReview {

    public List<Review> reviews;

    public void addReview(Usuario autor, String descricao, int nota, Agua agua) throws notaInvalidaException {
        reviews.add(new Review(autor, descricao, nota, agua));
    }

    public void addReview(Review review){
        reviews.add(review);
    }

}
