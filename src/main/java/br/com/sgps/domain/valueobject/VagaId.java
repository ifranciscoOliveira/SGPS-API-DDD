package br.com.sgps.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record VagaId(UUID value) {

    public VagaId(){
        this(UUID.randomUUID());
    }

    public VagaId(UUID value){
        Objects.requireNonNull(value);
        this.value = value;
    }

}
