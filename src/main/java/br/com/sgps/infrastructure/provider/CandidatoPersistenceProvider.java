package br.com.sgps.infrastructure.provider;

import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.infrastructure.repository.CandidatoPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CandidatoPersistenceProvider implements CandidatoRepositoryDomain {

    private CandidatoPersistenceRepository candidatoPersistenceRepository;

    @Override
    public boolean existeEmailCadastrado(Email email, CandidatoId id) {
        return candidatoPersistenceRepository.existEmailCadastrado(email.value(), id.value().toString());
    }
}
