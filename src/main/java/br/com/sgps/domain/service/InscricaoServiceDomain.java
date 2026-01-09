package br.com.sgps.domain.service;

import br.com.sgps.domain.entity.Inscricao;
import br.com.sgps.domain.exception.VagaNaoEncontradaException;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.repository.InscricaoRepositoryDomain;
import br.com.sgps.domain.repository.VagaRepositoryDomain;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.VagaId;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class InscricaoServiceDomain {

    private final InscricaoRepositoryDomain inscricaoRepositoryDomain;
    private final VagaRepositoryDomain vagaRepositoryDomain;
    private final CandidatoRepositoryDomain candidatoRepositoryDomain;

    public Inscricao realizarInscricao(UUID idVaga, UUID idCandidato) {
        Objects.requireNonNull(idVaga);
        Objects.requireNonNull(idCandidato);
        Inscricao inscricao = Inscricao.criarNovaInscricao(new CandidatoId(idCandidato)
                , new VagaId(idVaga));

        if(candidatoRepositoryDomain.existe(inscricao.candidatoId())){
            throw new VagaNaoEncontradaException("Candidato não encontrado.");
        }
        var vaga = vagaRepositoryDomain.conusltarPorId(inscricao.vagaId())
                .orElseThrow(() -> new VagaNaoEncontradaException("Vaga não encontrada."));

        vaga.validarPeriodoDeInscricaoParaVaga();
        validarCandidatoJaInscritoParaVaga(inscricao);

        inscricaoRepositoryDomain.persistir(inscricao);

        return null;
    }

    private void validarCandidatoJaInscritoParaVaga(Inscricao inscricao) {
        if(inscricaoRepositoryDomain.existeInscricaoPorCandidatoEPorVaga(inscricao.candidatoId().value()
                , inscricao.vagaId().value())) {
            throw new VagaNaoEncontradaException("Candidato já inscrito para essa vaga.");
        }
    }
}
