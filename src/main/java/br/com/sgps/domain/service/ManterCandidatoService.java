package br.com.sgps.domain.service;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.exception.EmailEmUsoException;
import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class ManterCandidatoService {

    private  final CandidatoRepositoryDomain candidatoRepositoryDomain;

    public Candidato salvar(String cpf, String nome,
                            Email email, String telefone, LocalDate dataNascimento) throws EmailEmUsoException, NegocioException {
        Candidato candidato = Candidato.criarNovoCandidato(cpf,
                nome,
                email,
                telefone,
                dataNascimento);

        verificarEmailExistente(email, candidato.id());
        return candidato;

    }

    private void verificarEmailExistente(Email email, CandidatoId id) throws EmailEmUsoException {
        if(candidatoRepositoryDomain.existeEmailCadastrado(email,id)){
            throw new EmailEmUsoException();
        }
    }



}
