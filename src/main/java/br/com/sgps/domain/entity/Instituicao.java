package br.com.sgps.domain.entity;

import br.com.sgps.domain.validator.FieldValidations;
import br.com.sgps.domain.valueobject.Documento;
import br.com.sgps.domain.valueobject.Email;
import br.com.sgps.domain.valueobject.InstituicaoId;

import java.util.Objects;

public class Instituicao {

    private InstituicaoId id;
    private String nome;
    private Documento cnpjCpf;
    private String telefone;
    private Email email;


    public Instituicao(InstituicaoId id, String nome, Documento cnpjCpf,
                       String telefone, Email email) {
        setId(id);
        setNome(nome);
        setCnpjCpf(cnpjCpf);
        setTelefone(telefone);
        setEmail(email);
    }

    public static Instituicao criarNovaInstituicao(String nome, Documento cnpjCpf,
                                                   String telefone, Email email){
        return new Instituicao(new InstituicaoId(),
                nome,
                cnpjCpf,
                telefone,
                email);
    }

    public InstituicaoId id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public Documento cnpjCpf() {
        return cnpjCpf;
    }

    public String telefone() {
        return telefone;
    }

    public Email email() {
        return email;
    }

    private void setId(InstituicaoId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setNome(String nome) {
        FieldValidations.requiresNonBlank(nome);
        this.nome = nome;
    }

    private void setCnpjCpf(Documento cnpjCpf) {
        Objects.requireNonNull(cnpjCpf);
        this.cnpjCpf = cnpjCpf;
    }

    private void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    private void setEmail(Email email) {
        this.email = email;
    }


}
