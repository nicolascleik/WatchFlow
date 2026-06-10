package com.watchflow.WatchFlow.core.exceptions;

public class NaoEncontradoException extends RuntimeException {

    public NaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public NaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}