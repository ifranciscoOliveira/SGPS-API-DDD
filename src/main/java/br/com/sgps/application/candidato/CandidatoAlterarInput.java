package br.com.sgps.application.candidato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoAlterarInput {

    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
}
