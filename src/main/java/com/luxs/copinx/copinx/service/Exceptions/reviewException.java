package com.luxs.copinx.copinx.service.Exceptions;

public class reviewException extends Exception {
    public reviewException(String essaReviewJaExiste) {
        super(essaReviewJaExiste);
    }
}
