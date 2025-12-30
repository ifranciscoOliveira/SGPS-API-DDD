package br.com.sgps.domain.service;


import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.exception.DocumentoEmUsoException;
import br.com.sgps.domain.exception.EmailEmUsoException;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.valueobject.Email;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ManterCandidatoServiceTest {

    @Mock
    private CandidatoRepositoryDomain candidatoRepositoryDomain;

    @InjectMocks
    private ManterCandidatoService candidatoService;

    private Candidato criarCandidato(){
        return Candidato.criarNovoCandidato(
                "01474712345",
                "Fulano de tal",
                new Email("teste@teste.com"),
                "9182234943",
                LocalDate.of(1986,5,5));
    }


    @Test
    void deveSalvarComSucesso(){

        Mockito.when(candidatoRepositoryDomain.existeEmailCadastrado(Mockito.any(),Mockito.any())).thenReturn(false);
        Mockito.when(candidatoRepositoryDomain.existeCpfCadastrado(Mockito.any(), Mockito.any())).thenReturn(false);

        Candidato candidato = candidatoService.salvar("000022233","fulano de tal",
                new Email("teste@teste.com"),"8192234564",
                LocalDate.of(1900,5,5));


        Assertions.assertThat(candidato).isNotNull();
        Assertions.assertThat(candidato.id().value()).isNotNull();


    }
    @Test
    void deveValidarEmailExistente(){

        Mockito.when(candidatoRepositoryDomain.existeEmailCadastrado(Mockito.any(),Mockito.any())).thenReturn(true);

        Exception exception = assertThrows(EmailEmUsoException.class, ()->{
            candidatoService.salvar("000022233","fulano de tal",
                    new Email("teste@teste.com"),"8192234564",
                    LocalDate.of(1900,5,5));
        });

        Assertions.assertThat(exception).isNotNull();
    }

    @Test
    void deveValidarCpfExistente(){

        Mockito.when(candidatoRepositoryDomain.existeEmailCadastrado(Mockito.any(),Mockito.any())).thenReturn(false);
        Mockito.when(candidatoRepositoryDomain.existeCpfCadastrado(Mockito.any(), Mockito.any())).thenReturn(true);

        DocumentoEmUsoException exception = assertThrows(DocumentoEmUsoException.class, ()->{
            candidatoService.salvar("000022233","fulano de tal",
                    new Email("teste@teste.com"),"8192234564",
                    LocalDate.of(1900,5,5));
        });

        Assertions.assertThat(exception).isNotNull();
    }



}