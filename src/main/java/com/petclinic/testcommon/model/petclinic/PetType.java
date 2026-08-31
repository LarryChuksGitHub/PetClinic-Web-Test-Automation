package com.petclinic.testcommon.model.petclinic;

import lombok.Getter;

public enum PetType {
    DOG ("dog"),
    BIRD("bird"),
    HAMSTER("hamster"),
    LIZARD("lizard"),
    SNAKE("snake"),
    CAT("cat");

    @Getter
    private final String type;
    PetType( String type) {
       this.type = type;
    }
}
