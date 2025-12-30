package br.com.sgps.domain.repository;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.infrastructure.assembler.CandidatoPersistenceEntityAssembler;
import br.com.sgps.infrastructure.provider.CandidatoPersistenceProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.UUID;

@DataJpaTest
@Import({
        CandidatoPersistenceProvider.class,
        CandidatoPersistenceEntityAssembler.class
})
class CandidatoRepositoryDomainTestIT {

    private final CandidatoRepositoryDomain candidatoRepositoryDomain;

    @Autowired
    public CandidatoRepositoryDomainTestIT(CandidatoRepositoryDomain candidatoRepositoryDomain){
        this.candidatoRepositoryDomain = candidatoRepositoryDomain;
    }

    private Candidato criarCandidato(){
        return Candidato.criarNovoCandidato(
                "01474712345",
                "Fulano de tal",
                new Email("teste@teste.com"),
                "9182234943",
                LocalDate.of(1986,5,5));
    }

    @Test
    void deveCadastrarComSucesso(){

        Candidato candidato = criarCandidato();
        UUID id = candidato.id().value();

        candidatoRepositoryDomain.persistir(candidato);

        Candidato candidatoCadastrado = candidatoRepositoryDomain.conusltarPorId(new CandidatoId(id)).get();
        Assertions.assertThat(candidatoCadastrado.id().value()).isEqualTo(id);

    }

    @Test
    void deveAlterarComSucesso(){
        Candidato candidato = criarCandidato();
        UUID id = candidato.id().value();

        candidatoRepositoryDomain.persistir(candidato);

        Candidato candidatoCadastrado = candidatoRepositoryDomain.conusltarPorId(new CandidatoId(id)).get();

        candidatoCadastrado.alterarNome("novo nome");
        candidatoCadastrado.alterarTelefone("111111111");
        candidatoCadastrado.alterarEmail(new Email("teste.novo@teste.com"));
        candidatoCadastrado.alterarDataNascimento(LocalDate.of(1986,10,10));

        candidatoRepositoryDomain.persistir(candidatoCadastrado);
        Candidato candidatoAlterado = candidatoRepositoryDomain.conusltarPorId(new CandidatoId(id)).get();

        Assertions.assertThat(id).isEqualTo(candidatoAlterado.id().value());
        Assertions.assertThat("novo nome").isEqualTo(candidatoAlterado.nome());
        Assertions.assertThat("111111111").isEqualTo(candidatoAlterado.telefone());
        Assertions.assertThat(new Email("teste.novo@teste.com")).isEqualTo(candidatoAlterado.email());
        Assertions.assertThat(LocalDate.of(1986,10,10)).isEqualTo(candidatoAlterado.dataNascimento());


    }

    @Test
    void deveRetornarValoresValidosParaCandidatoExistente(){

        Candidato candidato = criarCandidato();

        candidatoRepositoryDomain.persistir(candidato);

        Assertions.assertThat(candidatoRepositoryDomain.existe(candidato.id())).isTrue();
        Assertions.assertThat(candidatoRepositoryDomain.existe(new CandidatoId())).isFalse();

    }

    @Test
    void deveRetornarValoresValidosParaEmailEmUso(){
        Candidato candidato = criarCandidato();
        UUID id = candidato.id().value();

        candidatoRepositoryDomain.persistir(candidato);

        Candidato novoCandidato = criarCandidato();

        Assertions.assertThat(candidatoRepositoryDomain.existeEmailCadastrado(novoCandidato.email(),candidato.id())).isFalse();
        Assertions.assertThat(candidatoRepositoryDomain.existeEmailCadastrado(novoCandidato.email(),new CandidatoId())).isTrue();

    }


}