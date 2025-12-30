package br.com.sgps.application.candidato;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.exception.CandidatoNaoEncontratoException;
import br.com.sgps.domain.exception.EmailEmUsoException;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.service.ManterCandidatoService;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidatoManagementApplicationService {

    private final ManterCandidatoService candidatoServiceDomain;
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

        Candidato candidato = candidatoRepositoryDomain.conusltarPorId(id)
                .orElseThrow(()-> new CandidatoNaoEncontratoException());

        candidato.alterarEmail(new Email(candidatoAlterarInput.getEmail()));
        candidato.alterarNome(candidatoAlterarInput.getNome());
        candidato.alterarTelefone(candidatoAlterarInput.getTelefone());
        candidato.alterarDataNascimento(candidatoAlterarInput.getDataNascimento());

        candidatoRepositoryDomain.persistir(candidato);

    }


}
