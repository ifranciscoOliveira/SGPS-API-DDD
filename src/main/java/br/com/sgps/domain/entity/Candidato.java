package br.com.sgps.domain.entity;

import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.Email;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Candidato {

    private CandidatoId id;
    private String cpf;
    private String nome;
    private Email email;
    private String telefone;
    private LocalDate dataNascimento;

    public Candidato(CandidatoId id, String cpf, String nome,
                     Email email, String telefone, LocalDate dataNascimento) throws NegocioException {
       this.setId(id);
       this.setCpf(cpf);
       this.setNome(nome);
       this.setEmail(email);
       this.setTelefone(telefone);
       this.setDataNascimento(dataNascimento);
    }

    public static Candidato criarNovoCandidato(String cpf, String nome,
                                                Email email, String telefone, LocalDate dataNascimento) throws NegocioException {
        return new Candidato(
                new CandidatoId(),
                cpf,
                nome,
                email,
                telefone,
                dataNascimento);

    }

    public CandidatoId id() {
        return id;
    }

    public String cpf() {
        return cpf;
    }

    public String nome() {
        return nome;
    }

    public Email email() {
        return email;
    }

    public String telefone() {
        return telefone;
    }

    public LocalDate dataNascimento() {
        return dataNascimento;
    }

    private void setId(CandidatoId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setCpf(String cpf) {
        Objects.requireNonNull(cpf);
        this.cpf = cpf;
    }

    private void setNome(String nome) {
        Objects.requireNonNull(nome);
        this.nome = nome;
    }

    private void setEmail(Email email) {
        Objects.requireNonNull(email);
        this.email = email;
    }

    private void setTelefone(String telefone) {
        Objects.requireNonNull(telefone);
        this.telefone = telefone;
    }

    private void setDataNascimento(LocalDate dataNascimento) throws NegocioException {
        Objects.requireNonNull(dataNascimento);
        validarMaiorDeIdade(dataNascimento);
        this.dataNascimento = dataNascimento;
    }

    private void validarMaiorDeIdade(LocalDate dataNascimento) throws NegocioException {
        LocalDate hoje = LocalDate.now();
        long idade = ChronoUnit.YEARS.between(dataNascimento, hoje);

        if(idade < 18){
            throw new NegocioException("Condidato deve ser maior de idade");
        }
    }
}
