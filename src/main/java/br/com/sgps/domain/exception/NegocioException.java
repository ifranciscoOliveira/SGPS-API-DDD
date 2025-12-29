package br.com.sgps.domain.exception;


public class NegocioException extends RuntimeException {

    public NegocioException(){
        super();
    }
    public NegocioException(String msg){
        super(msg);
    }
}
