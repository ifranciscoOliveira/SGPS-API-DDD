package br.com.sgps.infrastructure.provider;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.infrastructure.assembler.CandidatoPersistenceEntityAssembler;
import br.com.sgps.infrastructure.entity.CandidatoPersistenteEntity;
import br.com.sgps.infrastructure.repository.CandidatoPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CandidatoPersistenceProvider implements CandidatoRepositoryDomain {

    private final CandidatoPersistenceRepository candidatoPersistenceRepository;
    private final CandidatoPersistenceEntityAssembler assembleCandidado;

    @Override
    public boolean existeEmailCadastrado(Email email, CandidatoId id) {
        return candidatoPersistenceRepository.existEmailCadastrado(email.value(), id.value());
    }

    @Override
    public boolean existeCpfCadastrado(String cpf, CandidatoId id) {
        return candidatoPersistenceRepository.existCpfCadastrado(cpf, id.value());
    }

    @Override
    public boolean existe(CandidatoId id) {
        return candidatoPersistenceRepository.existsById(id.value());
    }

    @Override
    public void persistir(Candidato candidato) {
        UUID candidatoId = candidato.id().value();

        candidatoPersistenceRepository.findById(candidatoId)
                .ifPresentOrElse((candidadoEncontrado) ->
                        alterar(candidato,candidadoEncontrado),
                        ()->salvar(candidato));


    }

    @Override
    public Optional<Candidato> conusltarPorId(CandidatoId id) {
        Optional<CandidatoPersistenteEntity> optonalCandidatoPersisteceRep = candidatoPersistenceRepository.findById(id.value());
        return optonalCandidatoPersisteceRep.map(assembleCandidado::persistenceEntityToDoman);
    }

    private void salvar(Candidato candidato){

        CandidatoPersistenteEntity persistenteEntity = assembleCandidado.fromDomain(candidato);
        candidatoPersistenceRepository.saveAndFlush(persistenteEntity);
    }

    private void alterar(Candidato candidato,CandidatoPersistenteEntity candidatoPersistenteEntity){
        candidatoPersistenteEntity = assembleCandidado.merge(candidatoPersistenteEntity,candidato);
        candidatoPersistenceRepository.saveAndFlush(candidatoPersistenteEntity);
    }





}
