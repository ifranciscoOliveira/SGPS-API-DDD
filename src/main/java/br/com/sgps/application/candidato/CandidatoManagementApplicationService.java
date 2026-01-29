package br.com.sgps.application.candidato;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.exception.CandidatoNaoEncontratoException;
import br.com.sgps.domain.exception.EmailEmUsoException;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.service.CandidatoService;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidatoManagementApplicationService {

    private final CandidatoService candidatoServiceDomain;
    private final CandidatoRepositoryDomain candidatoRepositoryDomain;

    @Transactional
    public UUID criar(CandidateInput candidatoInput) throws EmailEmUsoException {
        Objects.requireNonNull(candidatoInput);

        Candidato candidato = candidatoServiceDomain.salvar(candidatoInput.getCpf(),
                candidatoInput.getNome(),new Email(candidatoInput.getEmail()),
                candidatoInput.getTelefone(),candidatoInput.getDataNascimento());

        candidatoRepositoryDomain.persistir(candidato);
        return candidato.id().value();
    }

    @Transactional
    public void alterar(CandidatoId id,CandidatoAlterarInput candidatoAlterarInput ){

        Objects.requireNonNull(id);
        Objects.requireNonNull(candidatoAlterarInput);

        Candidato candidatoAlterar = candidatoServiceDomain.alterar(id,candidatoAlterarInput.getNome(),
                new Email(candidatoAlterarInput.getEmail()),
                candidatoAlterarInput.getTelefone(),candidatoAlterarInput.getDataNascimento());

        candidatoRepositoryDomain.persistir(candidatoAlterar);

    }

    @Transactional(readOnly = true)
    public Candidato consultarPorId(CandidatoId id) {
        Objects.requireNonNull(id);
        return candidatoRepositoryDomain.conusltarPorId(id)
                .orElseThrow(CandidatoNaoEncontratoException::new);
    }


}
