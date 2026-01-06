package br.com.sgps.domain.exception;

public class IntituicaoNaoEncontradoException extends RuntimeException{

    public IntituicaoNaoEncontradoException() {
    }

    public IntituicaoNaoEncontradoException(String message) {
        super(message);
    }
}
