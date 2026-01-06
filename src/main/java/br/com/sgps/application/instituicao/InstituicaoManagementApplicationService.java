package br.com.sgps.application.instituicao;

import br.com.sgps.domain.repository.InstituicaoRepositoryDomain;
import br.com.sgps.domain.service.InstituicaoService;
import br.com.sgps.domain.valueobject.InstituicaoId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstituicaoManagementApplicationService {

    public final InstituicaoService instituicaoServiceDomain;
    public final InstituicaoRepositoryDomain instituicaoRepositoryDomain;

    public void criar(InstituicaoInput instituicaoInput){
        var instituicao = instituicaoServiceDomain.salvar(instituicaoInput.getNome(),
                instituicaoInput.getCnpjCpf(),
                instituicaoInput.getTelefone(),
                instituicaoInput.getEmail());

        instituicaoRepositoryDomain.persistir(instituicao);
    }

    public void alterar(InstituicaoId id, InstituicaoInput instituicaoInput){
        var instituicaoAlterar = instituicaoServiceDomain.alterar(id,
                instituicaoInput.getNome(),
                instituicaoInput.getCnpjCpf(),
                instituicaoInput.getTelefone(),
                instituicaoInput.getEmail());

        instituicaoRepositoryDomain.persistir(instituicaoAlterar);
    }
}
