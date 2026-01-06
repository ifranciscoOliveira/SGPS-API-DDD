package br.com.sgps.domain.service;

import br.com.sgps.domain.entity.Vaga;
import br.com.sgps.domain.exception.VagaNaoEncontradaException;
import br.com.sgps.domain.repository.InstituicaoRepositoryDomain;
import br.com.sgps.domain.repository.VagaRepositoryDomain;
import br.com.sgps.domain.valueobject.InstituicaoId;
import br.com.sgps.domain.valueobject.VagaId;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@DomainService
@RequiredArgsConstructor
public class VagaServiceDomain {

    private final VagaRepositoryDomain vagaRepositoryDomain;
    private final InstituicaoRepositoryDomain instituicaoRepositoryDomain;

    public Vaga salvar(String titulo, String descricao, LocalDate dataInicio,
                       LocalDate dataFim,Integer limiteInscricoes, String status, String observacao,
                       InstituicaoId instituicaoId){

        validarInstituicaoExistente(instituicaoId);
        Vaga vaga = Vaga.criarNovaVaga(titulo, descricao, dataInicio,
                dataFim,limiteInscricoes , status, observacao, instituicaoId);
        validarTituloEmUso(vaga.id(),titulo);
        vagaRepositoryDomain.persistir(vaga);
        return vaga;
    }
    public Vaga alterar(VagaId id ,String titulo, String descricao, LocalDate dataInicio,
                        LocalDate dataFim,Integer limiteInscricoes, String status, String observacao,
                        InstituicaoId instituicaoId){

        validarInstituicaoExistente(instituicaoId);
        Vaga vaga = vagaRepositoryDomain.conusltarPorId(id).orElseThrow(()->
                new VagaNaoEncontradaException("Vaga não encontrada com o ID: " + id));

        validarTituloEmUso(id, titulo);
        vaga.alterarObservacao(observacao);
        vaga.alterarTitulo(titulo);
        vaga.alterarStatus(status);
        vaga.alterarDataFim(dataFim);
        vaga.alterarDataInicio(dataInicio);
        vaga.alterarLimiteInscricoes(limiteInscricoes);
        vaga.alterarDescricao(descricao);
        vaga.alterarInstituicaoId(instituicaoId);
        vagaRepositoryDomain.persistir(vaga);
        return vaga;
    }


    private void validarTituloEmUso(VagaId id, String titulo) {
        if(vagaRepositoryDomain.existeTituloCadastrado(titulo, id)){
            throw new RuntimeException("Já existe uma vaga com o título informado.");
        }
    }

    private void validarInstituicaoExistente(InstituicaoId instituicaoId){
        Objects.requireNonNull(instituicaoId);
        if(!instituicaoRepositoryDomain.existe(instituicaoId)){
            throw new RuntimeException("Instituição não encontrada com o ID: " + instituicaoId);
        }
    }
}
