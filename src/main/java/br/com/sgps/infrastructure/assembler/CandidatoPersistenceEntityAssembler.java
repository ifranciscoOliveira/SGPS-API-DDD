package br.com.sgps.infrastructure.assembler;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.infrastructure.entity.CandidatoPersistenteEntity;

public class CandidatoPersistenceEntityAssembler {


    public CandidatoPersistenteEntity fromDomain(Candidato candidato){
        return merge(new CandidatoPersistenteEntity(),candidato);
    }


    public CandidatoPersistenteEntity merge(CandidatoPersistenteEntity candidatoPersistenteEntity,
                                            Candidato candidato){
        candidatoPersistenteEntity.setCpf(candidato.cpf());
        candidatoPersistenteEntity.setNome(candidato.nome());
        candidatoPersistenteEntity.setEmail(candidato.email().value());
        candidatoPersistenteEntity.setTelefone(candidato.telefone());
        candidatoPersistenteEntity.setDataNascimento(candidato.dataNascimento());
        return candidatoPersistenteEntity;
    }

    public Candidato persistenceEntityToDoman(CandidatoPersistenteEntity candidatoPersistenteEntity){
        Candidato candidato = new Candidato(candidatoPersistenteEntity.getId())
        return null;
    }


}
