package br.com.sgps.domain.repository;

import br.com.sgps.domain.entity.Inscricao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InscricaoRepositoryDomain {

    Optional<Inscricao> consultarPorId(UUID id);

    boolean existeInscricaoPorCandidatoEPorVaga(UUID candidatoId, UUID vagaId);

    void persistir(Inscricao inscricao);

    List<Inscricao> consultarInscricoesPorCandidatoId(UUID candidatoId);

    List<Inscricao> consultarInscricoesPorVagaId(UUID vagaId);



}
