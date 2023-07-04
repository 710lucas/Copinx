package com.luxs.copinx.copinx.service;

import com.luxs.copinx.copinx.service.Agua.Agua;
import com.luxs.copinx.copinx.service.Agua.Review;
import com.luxs.copinx.copinx.service.Exceptions.aguaInvalidaException;
import com.luxs.copinx.copinx.service.Exceptions.reviewException;

import java.util.Collections;
import java.util.List;

public class GerenciadorAgua {

    private List<Agua> aguas;

    public List<Agua> getAguas() {
        return aguas;
    }

    public void setAguas(List<Agua> aguas) {
        this.aguas = aguas;
    }

    public void adicionarAgua(String nome, String descricao){
        aguas.add(new Agua(nome, descricao));
    }

    public void adicionarAgua(Agua agua) throws aguaInvalidaException {
        if(temAgua(agua.getNome()))
            throw new aguaInvalidaException("Já existe uma agua com este mesmo nome, tente mudá-lo");
        aguas.add(agua);
    }

    public void removerAgua(Agua agua){
        aguas.remove(agua);
    }

    public void editarAgua(Agua agua, String descricao, String nome) throws aguaInvalidaException {
        if(temAgua(nome))
            throw new aguaInvalidaException("Já existe uma agua com este mesmo nome, tente mudá-lo");
        agua.setDescricao(descricao);
        agua.setNome(nome);
    }

    public void addReview(Agua agua, Review review) throws reviewException {
        agua.addReview(review);
    }

    public void removeReview(Agua agua, Review review) throws reviewException {
        agua.removeReview(review);
    }

    public boolean temAgua(String nome){
        for(Agua a : aguas){
            if(a.getNome().equals(nome))
                return true;
        }
        return false;
    }


    public String toString(){
        String out = "";

        for(Agua a : aguas){
            out+=a.getNome()+" "+a.getDescricao()+" "+a.calculaNotaGeral()+"\n";
        }

        return out;
    }

    public GerenciadorAgua ordenarAguas(){

        List<Agua> a = getAguas();
        Collections.sort(a);
        GerenciadorAgua gA = new GerenciadorAgua();
        gA.setAguas(a);
        return gA;

    }

}
