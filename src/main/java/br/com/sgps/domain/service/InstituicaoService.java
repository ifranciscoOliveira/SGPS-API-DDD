package br.com.sgps.domain.service;

import br.com.sgps.domain.entity.Instituicao;
import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.domain.repository.InstituicaoRepositoryDomain;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InstituicaoService {

    private final InstituicaoRepositoryDomain instituicaoRepositoryDomain;

    public Instituicao salvar(String nome, Documento cnpjCpf,
                              String telefone, Email email){

        Instituicao instituicao = Instituicao.criarNovaInstituicao(nome,cnpjCpf,telefone,email);

        if(instituicaoRepositoryDomain.exiteDocumentoCadastrado(instituicao.cnpjCpf())){
            throw new NegocioException("Já existe uma instituição com o CNPJ/CPF informado.");
        }
        return instituicao;

    }


}
