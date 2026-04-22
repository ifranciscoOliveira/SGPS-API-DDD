package br.com.sgps.domain.exception;

public class InstituicaoNaoEncontradoException extends RuntimeException{

    public InstituicaoNaoEncontradoException() {
    }

    public InstituicaoNaoEncontradoException(String message) {
        super(message);
    }
}
