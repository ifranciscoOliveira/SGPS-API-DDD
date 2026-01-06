package br.com.sgps.domain.exception;

import br.com.sgps.domain.valueobject.Email;

public class EmailEmUsoException extends RuntimeException {

    public EmailEmUsoException(){super();}

    public EmailEmUsoException(String msg){
        super(msg);
    }
}
