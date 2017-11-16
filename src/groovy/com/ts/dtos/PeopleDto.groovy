package com.ts.dtos

import grails.converters.JSON

/**
 * Created by gmazzaglia on 9/11/17.
 */
class PeopleDto {
    String name
    String personality

    @Override
    public String toString() {
        return [
                name       : name,
                personality: personality
        ] as JSON;
    }
}
