package br.com.sgps.domain.repository;

import br.com.sgps.domain.entity.Instituicao;
import br.com.sgps.domain.entity.Vaga;
import br.com.sgps.domain.valueobject.VagaId;

import java.util.Optional;

public interface VagaRepositoryDomain {

    boolean existeTituloCadastrado(String titulo, VagaId id);

    boolean existe(VagaId id);

    void persistir(Vaga vaga);

    Optional<Vaga> conusltarPorId(VagaId id);

}
