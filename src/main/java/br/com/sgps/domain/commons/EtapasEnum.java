package br.com.sgps.domain.commons;

import lombok.Getter;

@Getter
public enum EtapasEnum {
    INSCRITO(1, "Inscrito"),
    ANALISE(2, "Análise"),
    TESTE(3, "Testes"),
    ENTREVISTA(4, "Entrevista"),
    AVALICACAO_FINAL(5, "Avaliação Final");


    private final int ordem;
    private final String descricao;

    EtapasEnum(int ordem, String descricao) {
        this.ordem = ordem;
        this.descricao = descricao;
    }

    public boolean isFinal() {
        return this == AVALICACAO_FINAL;
    }
}
