package br.com.sgps.application.candidato;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateInput {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
}
