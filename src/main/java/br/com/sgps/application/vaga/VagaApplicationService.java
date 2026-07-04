package br.com.sgps.application.vaga;

import br.com.sgps.domain.commons.Pagina;
import br.com.sgps.domain.commons.Paginacao;
import br.com.sgps.domain.entity.Vaga;
import br.com.sgps.domain.repository.VagaRepositoryDomain;
import br.com.sgps.domain.service.VagaServiceDomain;
import br.com.sgps.domain.valueobject.InstituicaoId;
import br.com.sgps.domain.valueobject.VagaId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VagaApplicationService {

    private final VagaServiceDomain vagaServiceDomain;
    private final VagaRepositoryDomain vagaRepositoryDomain;


    @Transactional
    public Vaga criar(VagaInput vagaInput){
        var vaga = vagaServiceDomain.salvar(vagaInput.getTitulo(),
                vagaInput.getDescricao(),
                vagaInput.getDataInicio(),
                vagaInput.getDataFim(),
                vagaInput.getLimiteInscricoes(),
                vagaInput.getStatus(),
                vagaInput.getObservacao(),
                new InstituicaoId(vagaInput.getInstituicaoId())
        );

        return vagaRepositoryDomain.persistir(vaga);
    }

    @Transactional
    public Vaga alterar(VagaId id,VagaAlterarInput vagaAlterarInput){
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
        return vagaRepositoryDomain.persistir(vagaAlterar);
    }

    public List<Vaga> consultarTodos(){
        return vagaRepositoryDomain.consultarTodos();
    }

    public Pagina<Vaga> listar(VagaFiltro vagaFiltro, Paginacao paginacao){
        return vagaRepositoryDomain.listar(vagaFiltro, paginacao);
    }
}
