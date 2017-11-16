package com.ts

import grails.converters.JSON

class User {

    Long id
    String firstName
    String lastName
    String username
    String password

    static constraints = {
    }

    static mapping = {
        version(false)
    }

    @Override
    public String toString() {
        return [
                id       : id,
                firstName: firstName,
                lastName : lastName,
                username : username,
        ] as JSON;
    }
}
