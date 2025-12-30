package br.com.sgps.domain.service;

import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.exception.DocumentoEmUsoException;
import br.com.sgps.domain.exception.EmailEmUsoException;
import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.domain.repository.CandidatoRepositoryDomain;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@DomainService
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
        verificarCpfExistente(candidato.cpf(), candidato.id());
        return candidato;

    }

    private void verificarEmailExistente(Email email, CandidatoId id) {
        if(candidatoRepositoryDomain.existeEmailCadastrado(email,id)){
            throw new EmailEmUsoException();
        }
    }
    private void verificarCpfExistente(String cpf, CandidatoId id) {
        if(candidatoRepositoryDomain.existeCpfCadastrado(cpf,id)){
            throw new DocumentoEmUsoException();
        }
    }



}
