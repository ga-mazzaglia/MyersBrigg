package com.ts

import grails.converters.JSON

class PersonalityTypeMatch {

    Long id
    PersonalityType personalityType1
    PersonalityType personalityType2
    Integer weight

    static constraints = {
    }

    static mapping = {
        version(false)
    }

    @Override
    public String toString() {
        return [
                id              : id,
                personalityType1: personalityType1,
                personalityType2: personalityType2,
                weight          : weight,
        ] as JSON;
    }
}
