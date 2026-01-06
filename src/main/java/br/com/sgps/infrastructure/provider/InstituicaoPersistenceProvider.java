package br.com.sgps.infrastructure.provider;


import br.com.sgps.domain.entity.Instituicao;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.repository.InstituicaoRepositoryDomain;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.domain.valueobject.InstituicaoId;
import br.com.sgps.infrastructure.entity.InstituicaoPersistenceEntity;
import br.com.sgps.infrastructure.repository.InstituicaoPersistenceRporitoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InstituicaoPersistenceProvider implements InstituicaoRepositoryDomain {

    private final InstituicaoPersistenceRporitoy instituicaoPersistenceRporitoy;


    @Override
    public boolean exiteDocumentoCadastrado(Documento documento, InstituicaoId id) {
        return instituicaoPersistenceRporitoy.existCpfCadastrado(documento.value(), id.value());
    }

    @Override
    public boolean existeEmailCadastrado(Email email, InstituicaoId id) {
        return instituicaoPersistenceRporitoy.existEmailCadastrado(email.value(), id.value());
    }

    @Override
    public boolean existe(InstituicaoId id) {
        return instituicaoPersistenceRporitoy.existsById(id.value());
    }

    @Override
    public Optional<Instituicao> conusltarPorId(InstituicaoId id) {
        return Optional.empty();
    }

    @Override
    public void persistir(Instituicao instituicao) {
        UUID id = instituicao.id().value();

        instituicaoPersistenceRporitoy.findById(id)
                .ifPresentOrElse((instituicaoEncontrada) ->
                                alterar(instituicao, instituicaoEncontrada),
                        () -> salvar(instituicao));
    }

    private void salvar(Instituicao instituicao) {
        InstituicaoPersistenceEntity persistenteEntity = new InstituicaoPersistenceEntity();
        persistenteEntity.setId(instituicao.id().value());
        persistenteEntity.setNome(instituicao.nome());
        persistenteEntity.setCnpjCpf(instituicao.cnpjCpf().value());
        persistenteEntity.setTelefone(instituicao.telefone());
        persistenteEntity.setEmail(instituicao.email().value());

        instituicaoPersistenceRporitoy.saveAndFlush(persistenteEntity);
    }

    private void alterar(Instituicao instituicao, InstituicaoPersistenceEntity instituicaoEncontrada) {

        instituicaoEncontrada.setNome(instituicao.nome());
        instituicaoEncontrada.setCnpjCpf(instituicao.cnpjCpf().value());
        instituicaoEncontrada.setTelefone(instituicao.telefone());
        instituicaoEncontrada.setEmail(instituicao.email().value());

        instituicaoPersistenceRporitoy.saveAndFlush(instituicaoEncontrada);
    }
}
