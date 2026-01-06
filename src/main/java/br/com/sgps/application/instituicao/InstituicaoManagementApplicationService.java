package br.com.sgps.application.instituicao;

import br.com.sgps.domain.repository.InstituicaoRepositoryDomain;
import br.com.sgps.domain.service.InstituicaoService;
import br.com.sgps.domain.valueobject.InstituicaoId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InstituicaoManagementApplicationService {

    public final InstituicaoService instituicaoServiceDomain;
    public final InstituicaoRepositoryDomain instituicaoRepositoryDomain;

    @Transactional
    public void criar(InstituicaoInput instituicaoInput){
        Objects.requireNonNull(instituicaoInput);

        var instituicao = instituicaoServiceDomain.salvar(instituicaoInput.getNome(),
                instituicaoInput.getCnpjCpf(),
                instituicaoInput.getTelefone(),
                instituicaoInput.getEmail());

        instituicaoRepositoryDomain.persistir(instituicao);
    }

    @Transactional
    public void alterar(InstituicaoId id, InstituicaoInput instituicaoInput){

        Objects.requireNonNull(id);
        Objects.requireNonNull(instituicaoInput);
        var instituicaoAlterar = instituicaoServiceDomain.alterar(id,
                instituicaoInput.getNome(),
                instituicaoInput.getCnpjCpf(),
                instituicaoInput.getTelefone(),
                instituicaoInput.getEmail());

        instituicaoRepositoryDomain.persistir(instituicaoAlterar);
    }
}
