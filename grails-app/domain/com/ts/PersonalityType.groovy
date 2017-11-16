package com.ts

import grails.converters.JSON

class PersonalityType {

    Long id
    String name

    static constraints = {
    }

    static mapping = {
        version(false)
    }

    @Override
    public String toString() {
        return [
                id  : id,
                name: name,
        ] as JSON;
    }
}
