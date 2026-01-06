package br.com.sgps.application.vaga;

import br.com.sgps.domain.valueobject.InstituicaoId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VagaInput {

    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer limiteInscricoes;
    private String status;
    private String observacao;
    private UUID instituicaoId;
}
