package br.com.sgps.domain.valueobject;

import br.com.sgps.domain.validator.FieldValidations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Email(String value) {

    public Email {
        FieldValidations.requiresValidEmail(value);
    }



}
