package br.com.sgps.domain.repository;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.infrastructure.repository.CandidatoPersistenceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@DataJpaTest
class CandidatoRepositoryDomainTestIT {
    @Autowired
    CandidatoRepositoryDomain candidatoRepositoryDomain;

    @Test
    void deveCadastrarComSucesso(){

        Candidato candidato = Candidato.criarNovoCandidato(
                "01474712345",
                "Fulano de tal",
                new Email("teste@teste.com"),
                "9182234943",
                LocalDate.of(1986,5,5)

        );

        candidatoRepositoryDomain.persistir(candidato);






    }
}