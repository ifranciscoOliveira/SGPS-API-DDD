package br.com.sgps.application.vaga;

import br.com.sgps.domain.repository.VagaRepositoryDomain;
import br.com.sgps.domain.service.VagaServiceDomain;
import br.com.sgps.domain.valueobject.InstituicaoId;
import br.com.sgps.domain.valueobject.VagaId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VagaApplicationService {

    private final VagaServiceDomain vagaServiceDomain;
    private final VagaRepositoryDomain vagaRepositoryDomain;


    @Transactional
    public void criar(VagaInput vagaInput){
        var vaga = vagaServiceDomain.salvar(vagaInput.getTitulo(),
                vagaInput.getDescricao(),
                vagaInput.getDataInicio(),
                vagaInput.getDataFim(),
                vagaInput.getLimiteInscricoes(),
                vagaInput.getStatus(),
                vagaInput.getObservacao(),
                new InstituicaoId(vagaInput.getInstituicaoId())
        );

        vagaRepositoryDomain.persistir(vaga);
    }

    @Transactional
    public void alterar(VagaId id,VagaAlterarInput vagaAlterarInput){
        var vagaAlterar = vagaServiceDomain.alterar(
                id,
                vagaAlterarInput.getTitulo(),
                vagaAlterarInput.getDescricao(),
                vagaAlterarInput.getDataInicio(),
                vagaAlterarInput.getDataFim(),
                vagaAlterarInput.getLimiteInscricoes(),
                vagaAlterarInput.getStatus(),
                vagaAlterarInput.getObservacao(),
                new InstituicaoId(vagaAlterarInput.getInstituicaoId()));
        vagaRepositoryDomain.persistir(vagaAlterar);
    }
}
