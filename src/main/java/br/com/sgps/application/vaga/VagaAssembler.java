package br.com.sgps.application.vaga;

import br.com.sgps.domain.entity.Vaga;

public class VagaAssembler {

    public VagaOutPut domainToOutPut(Vaga vaga){
        return VagaOutPut.builder()
                .id(vaga.id().value().toString())
                .titulo(vaga.titulo())
                .descricao(vaga.descricao())
                .dataInicio(vaga.dataInicio())
                .dataFim(vaga.dataFim())
                .limiteInscricoes(vaga.limiteInscricoes())
                .status(vaga.status())
                .observacao(vaga.observacao())
                .instituicaoId(vaga.instituicaoId().value())
                .build();
    }
}
