package com.ts.dtos

import grails.converters.JSON

/**
 * Created by gmazzaglia on 9/11/17.
 */
class TeamDto {
    Integer id = 0;
    List<PeopleDto> peopleDtoList = [];
    Integer weight = 0;

    @Override
    public String toString() {
        return [
                id           : id,
                peopleDtoList: peopleDtoList,
                weight       : weight
        ] as JSON;
    }
}
