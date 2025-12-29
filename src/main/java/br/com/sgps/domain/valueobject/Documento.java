package br.com.sgps.domain.valueobject;

import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.domain.validator.FieldValidations;

public record Documento(String value) {

    public enum Tipo {

        CPF(11),
        CNPJ(14);

        private final int tamanho;

        Tipo(int tamanho) {
            this.tamanho = tamanho;
        }

        public int getTamanho() {
            return tamanho;
        }
    }

    public Documento(String value){
        FieldValidations.requiresNonBlank(value);
        String documentoSemFormatacao = removerFormatacao(value);
        validar(documentoSemFormatacao);
        this.value = documentoSemFormatacao;
    }

    private void validar(String valor) throws NegocioException {
        if (valor.length() != Tipo.CPF.getTamanho() && valor.length() != Tipo.CNPJ.getTamanho() ){
            throw new NegocioException("CPF inválido");
        }

    }
    private String removerFormatacao(String value) {
        return value.replaceAll("\\D", "");
    }




}
