package br.com.sgps.infrastructure.repository;

import br.com.sgps.domain.entity.Inscricao;
import br.com.sgps.infrastructure.entity.InscricaoPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InscricaoPersistenceRepository extends JpaRepository<InscricaoPersistenceEntity, UUID> {


    boolean existsByCandidatoIdAndVagaId(UUID candidatoId, UUID vagaId);


}
