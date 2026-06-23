package br.com.sgps.apresentation.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErroResponse(
        int status,
        String mensagem,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,
        List<CampoErro> campos
) {

    public ErroResponse(int status, String mensagem) {
        this(status, mensagem, LocalDateTime.now(), null);
    }

    public ErroResponse(int status, String mensagem, List<CampoErro> campos) {
        this(status, mensagem, LocalDateTime.now(), campos);
    }

    public record CampoErro(String campo, String mensagem) {}
}
