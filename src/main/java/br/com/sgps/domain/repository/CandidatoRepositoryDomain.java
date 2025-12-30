package br.com.sgps.domain.repository;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;

import java.util.Optional;

public interface CandidatoRepositoryDomain {

    boolean existeEmailCadastrado(Email email, CandidatoId id);

    boolean existe(CandidatoId id);

    void persistir(Candidato candidato);

    Optional<Candidato> conusltarPorId(CandidatoId id);

}
