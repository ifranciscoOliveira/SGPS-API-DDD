package br.com.sgps.domain.repository;

import br.com.sgps.domain.entity.Instituicao;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.domain.valueobject.InstituicaoId;

import java.util.Optional;

public interface InstituicaoRepositoryDomain {

    public boolean exiteDocumentoCadastrado(Documento documento, InstituicaoId id);

    boolean existeEmailCadastrado(Email email, InstituicaoId id);

    boolean existe(InstituicaoId id);

    void persistir(Instituicao instituicao);

    Optional<Instituicao> conusltarPorId(InstituicaoId id);

}
