package br.com.sgps.domain.valueobject;

import br.com.sgps.domain.entity.Candidato;

import java.util.Objects;
import java.util.UUID;

public record CandidatoId(UUID value) {

    public CandidatoId(){
        this(UUID.randomUUID());
    }

    public CandidatoId(UUID value){
        Objects.requireNonNull(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
