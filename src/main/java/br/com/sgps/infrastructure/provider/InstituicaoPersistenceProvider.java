package br.com.sgps.infrastructure.provider;


import br.com.sgps.domain.entity.Instituicao;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.repository.InstituicaoRepositoryDomain;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.domain.valueobject.InstituicaoId;
import br.com.sgps.infrastructure.assembler.InstituicaoPersistenceEntityAssembler;
import br.com.sgps.infrastructure.entity.InstituicaoPersistenceEntity;
import br.com.sgps.infrastructure.repository.InstituicaoPersistenceRporitoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InstituicaoPersistenceProvider implements InstituicaoRepositoryDomain {

    private final InstituicaoPersistenceRporitoy instituicaoPersistenceRporitoy;
    private final InstituicaoPersistenceEntityAssembler instituicaoPersistenceEntityAssembler;


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
        InstituicaoPersistenceEntity instituicaoRepository =
                instituicaoPersistenceRporitoy.findById(id.value()).orElse(null);
        if (instituicaoRepository != null) {
            return Optional.of(instituicaoPersistenceEntityAssembler.toDomain(instituicaoRepository));
        }
        return Optional.empty();
    }

    @Override
    public List<Instituicao> listarTdos() {
        List<InstituicaoPersistenceEntity>  lista = instituicaoPersistenceRporitoy.findAll();
        return instituicaoPersistenceEntityAssembler.persistenceEntityToDomain(lista);
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
