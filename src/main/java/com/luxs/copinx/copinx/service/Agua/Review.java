package com.luxs.copinx.copinx.service.Agua;

import com.luxs.copinx.copinx.service.Exceptions.notaInvalidaException;
import com.luxs.copinx.copinx.service.Usuario.Usuario;

import java.io.Serializable;

public class Review implements Comparable<Review>, Serializable {

    private int nota; //1 - 10, depois dividir por 2
    private String descricao;
    private Usuario autor;

    private Agua agua;

    public Review(Usuario autor, String descricao, int nota, Agua agua) throws notaInvalidaException {
        if(nota<1 || nota > 10){
            throw new notaInvalidaException("A nota informada deve ser entre 1 e 10");
        }

        this.autor = autor;
        this.descricao = descricao;
        this.nota = nota;
        this.agua = agua;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNota(int nota) throws notaInvalidaException {
        if(nota<1 || nota > 10){
            throw new notaInvalidaException("A nota informada deve ser entre 1 e 10");
        }
        this.nota = nota;
    }

    public void setAgua(Agua agua) {
        this.agua = agua;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getNota() {
        return nota;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Agua getAgua() {
        return agua;
    }

    public String toString(){
        return agua.getNome()+";"+autor.getNome()+";"+descricao;
    }

    @Override
    public int compareTo(Review o) {
        if(this.getNota() > o.getNota())
            return 1;
        else if(this.getNota() < o.getNota())
            return -1;
        return 0;
    }
}
