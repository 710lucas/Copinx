package com.luxs.copinx.copinx.service.Agua;

import com.luxs.copinx.copinx.service.Exceptions.reviewException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Agua implements Comparable<Agua>, Serializable {


    private String nome;
    private List<Review> reviews = new ArrayList<>();
    private String descricao;

    //data adicionada

    public Agua(String nome, String descricao){
        setNome(nome);
        setDescricao(descricao);
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void addReview(Review review) throws reviewException {
        if(temReview(review))
            throw new reviewException("Essa review ja existe");
        reviews.add(review);
    }

    public void removeReview(Review review) throws reviewException {
        if(!temReview(review))
            throw new reviewException("Essa review não existe");
        reviews.remove(review);
    }

    public boolean temReview(Review review){
        return reviews.contains(review);
    }

    public String getNome(){
        return nome;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getDescricao() {
        return descricao;
    }

    public float calculaNotaGeral(){
        float nota = 0;
        for(Review r : reviews){
            nota+=r.getNota();
        }
        System.out.println(nota);
        System.out.println(reviews.size());
        return (nota/reviews.size());
    }

    @Override
    public int compareTo(Agua o) {
        if(this.calculaNotaGeral() > o.calculaNotaGeral())
            return 1;
        else if(this.calculaNotaGeral() < o.calculaNotaGeral())
            return -1;
        return 0;
    }
}
