package com.luxs.copinx.copinx.service.Exceptions;

public class usuarioInvalidoException extends Exception{
    usuarioInvalidoException(){
        super();
    }

    public usuarioInvalidoException(String msg){
        super(msg);
    }
}
