package br.com.sgps.domain.service;


import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.exception.CandidatoNaoEncontratoException;
import br.com.sgps.domain.exception.DocumentoEmUsoException;
import br.com.sgps.domain.exception.EmailEmUsoException;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CandidatoServiceTest {

    @Mock
    private CandidatoRepositoryDomain candidatoRepositoryDomain;

    @InjectMocks
    private CandidatoService candidatoService;

    private Candidato criarCandidato(){
        return Candidato.criarNovoCandidato(
                new Documento("573.049.640-06"),
                "Fulano de tal",
                new Email("teste@teste.com"),
                "9182234943",
                LocalDate.of(1986,5,5));
    }


    @Test
    void deveSalvarComSucesso(){

        Mockito.when(candidatoRepositoryDomain.existeEmailCadastrado(Mockito.any(),Mockito.any())).thenReturn(false);
        Mockito.when(candidatoRepositoryDomain.existeCpfCadastrado(Mockito.any(), Mockito.any())).thenReturn(false);

        Candidato candidato = candidatoService.salvar( new Documento("573.049.640-06"),"fulano de tal",
                new Email("teste@teste.com"),"8192234564",
                LocalDate.of(1900,5,5));


        Assertions.assertThat(candidato).isNotNull();
        Assertions.assertThat(candidato.id().value()).isNotNull();


    }
    @Test
    void deveValidarEmailExistente(){

        Mockito.when(candidatoRepositoryDomain.existeEmailCadastrado(Mockito.any(),Mockito.any())).thenReturn(true);

        Exception exception = assertThrows(EmailEmUsoException.class, ()->{
            candidatoService.salvar( new Documento("573.049.640-06"),"fulano de tal",
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
            candidatoService.salvar( new Documento("573.049.640-06"),"fulano de tal",
                    new Email("teste@teste.com"),"8192234564",
                    LocalDate.of(1900,5,5));
        });

        Assertions.assertThat(exception).isNotNull();
    }

    @Test
    void deveAlterarComSucesso(){

        Candidato candidato = criarCandidato();
        Mockito.when(candidatoRepositoryDomain.consultarPorId(candidato.id())).thenReturn(Optional.of(candidato));
        Mockito.when(candidatoRepositoryDomain.existeEmailCadastrado(Mockito.any(),Mockito.any())).thenReturn(false);
        Mockito.when(candidatoRepositoryDomain.existeCpfCadastrado(Mockito.any(), Mockito.any())).thenReturn(false);


        Candidato candidatoAlterado = candidatoService.alterar(candidato.id(),"fulano de tal novo",
                new Email("teste22@teste.com"),"8192234564",
                LocalDate.of(1900,2,2));


        Assertions.assertThat(candidato).isNotNull();
        Assertions.assertThat(candidato.id().value()).isNotNull();
        Assertions.assertThat(candidato.email()).isEqualTo(new Email("teste22@teste.com"));
        assertEquals("fulano de tal novo", candidato.nome());
        assertEquals("8192234564", candidato.telefone());
        assertEquals(LocalDate.of(1900,2,2), candidato.dataNascimento());


    }

    @Test
    void deveLancarErroNaoEncontratoNaAlteracao(){

        Candidato candidato = criarCandidato();
        Mockito.when(candidatoRepositoryDomain.consultarPorId(candidato.id())).thenReturn(Optional.empty());

        assertThrows(CandidatoNaoEncontratoException.class,
                ()->candidatoService.alterar(candidato.id(),"fulano de tal novo",
                new Email("teste22@teste.com"),"8192234564",
                LocalDate.of(1900,2,2)));


    }


}