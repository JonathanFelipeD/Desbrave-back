package com.Desbrave.Desbrave.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Titulo {
    ANUNCIO("Anuncio"),
    DUVIDA("Duvida"),
    DISCUSSAO("Discussão"),
    SUGESTAO("Sugestão");

    private final String description;

    Titulo(String description) {
        this.description = description;
    }

    @Override
    @JsonValue
    public String toString(){return description;};

}
      