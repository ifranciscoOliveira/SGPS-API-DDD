package br.com.sgps.domain.entity;


import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.domain.valueobject.Email;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CandidatoTest {



    @Test
    void deveValidarMaioridadeDoCandidato(){
        Assertions.assertThatExceptionOfType(NegocioException.class)
                .isThrownBy(() ->
                    Candidato.criarNovoCandidato(
                            "00000000000",
                            "FULANO DE TAL",
                            new Email("teste@teste.com"),
                            "81991102392",
                            LocalDate.of(2009,01,01)));


    }



}