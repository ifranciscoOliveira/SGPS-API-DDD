package br.com.sgps.infrastructure.assembler;

import br.com.sgps.domain.entity.Inscricao;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.InscricaoId;
import br.com.sgps.domain.valueobject.VagaId;
import br.com.sgps.infrastructure.entity.CandidatoPersistenteEntity;
import br.com.sgps.infrastructure.entity.InscricaoPersistenceEntity;
import br.com.sgps.infrastructure.entity.VagaPersistenceEntity;

public class InscricaoPersistenceEntityAssembler {

    public InscricaoPersistenceEntity fromDomain(Inscricao inscricao) {
        return merge(InscricaoPersistenceEntity.builder().build(), inscricao);
    }

    public InscricaoPersistenceEntity merge(InscricaoPersistenceEntity inscricaoPersistenceEntity,
                                            Inscricao inscricao) {
        inscricaoPersistenceEntity.setId(inscricao.id().value());
        inscricaoPersistenceEntity.setCandidato(CandidatoPersistenteEntity.builder()
                .id(inscricao.candidatoId().value())
                .build());
        inscricaoPersistenceEntity.setVaga(VagaPersistenceEntity.builder()
                .id(inscricao.candidatoId().value())
                .build());
        inscricaoPersistenceEntity.setDataInscricao(inscricao.dataInscricao());
        inscricaoPersistenceEntity.setAprovado(inscricao.aprovado());
        inscricaoPersistenceEntity.setEtapaAtual(inscricao.etapaAtual());

        return inscricaoPersistenceEntity;
    }

    public Inscricao fromPersistenceEntity(InscricaoPersistenceEntity inscricaoPersistenceEntity) {
        return Inscricao.criarExistente()
                .id(new InscricaoId(inscricaoPersistenceEntity.getId()))
                .candidatoId(new CandidatoId(
                        inscricaoPersistenceEntity.getCandidato().getId()))
                .vagaId(new VagaId(
                        inscricaoPersistenceEntity.getVaga().getId()))
                .dataInscricao(inscricaoPersistenceEntity.getDataInscricao())
                .aprovado(inscricaoPersistenceEntity.getAprovado())
                .etapaAtual(inscricaoPersistenceEntity.getEtapaAtual())
                .build();
    }
}
