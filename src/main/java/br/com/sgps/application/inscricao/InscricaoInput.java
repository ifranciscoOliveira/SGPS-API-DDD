package br.com.sgps.application.inscricao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InscricaoInput {

    private String idCandidato;
    private String idVaga;
    
}
