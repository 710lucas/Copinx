package com.luxs.copinx.copinx.service;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class arquivo {


    public static final String GERENCIADOR_AGUA = "GA.s";
    public static final String GERENCIADOR_REVIEW = "GR.s";
    public static final String GERENCIADOR_USUARIO = "GU.s";

    public static <T> T recuperaGerenciador(String arquivo, Class<T> classe){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
            T recuperado = classe.cast(in.readObject());
            in.close();
            return recuperado;
        } catch (IOException e) {
            try {
                System.out.println("AAAAAAAAAAAAAAAA");
                System.out.println(e.getMessage());
                return classe.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void salvar(String arquivo, Object o){
        try {
            ObjectOutputStream out = new ObjectOutputStream((new FileOutputStream(arquivo)));
            out.writeObject(o);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
