package com.ts

import com.ts.dtos.PeopleDto
import com.ts.dtos.TeamDto

class MainService {

    def List<List<TeamDto>> analizeWithOptions(String peoples, Integer teamQuantity, Integer optimals) {
        List<List<TeamDto>> result = [];
        Integer retry = 10;
        while (result.size() < 3) {
            List<TeamDto> tmp = analize(peoples, teamQuantity, optimals);
            Boolean apply = false;
            result.each { List<TeamDto> listTmp ->
                if (listTmp.first().weight < tmp.first().weight) {
                    apply = true;
                }
            }
            if (result.size() == 0 || apply) {
                result << tmp
            }
            sleep(100);
            println "-----"
            println "Cantidad: " + result.size()
            println "-----"
            if (result.size() == 1) {
                retry--;
                println "-----"
                println "Retry: " + retry
                println "-----"
                if (retry == 0) {
                    retry = 5;
                    result = [];
                }
            }
        }
        return result.sort { -it*.weight };
    }

    def List<TeamDto> analize(String peoples, Integer teamQuantity, Integer optimals) {
        List<TeamDto> teams = [];

        List<PeopleDto> peopleList = getPeopleList(peoples);
        List<PeopleDto> seeds = this.getSeeds(peopleList, teamQuantity);
        seeds.each { PeopleDto info ->
            TeamDto team = new TeamDto();
            team.id = teams.size() + 1;
            team.peopleDtoList.push(info)
            teams << team
            peopleList = peopleList.findAll { it.name != info.name }
        }
        Integer peoplesPerTeam = Math.ceil(peopleList.size() / teamQuantity);
        println "-- seeds"
        println seeds
        println "-- peoplesPerTeam"
        println peoplesPerTeam
        println "--"

        peopleList.each { PeopleDto people ->
            println "--- peopleList"
            println peopleList
            println "---"
            def matchTmp = null;
            //Vamos quitando equipos completos
            def temp = teams.findAll { it.peopleDtoList.size() < peoplesPerTeam };
            //Si ya todos están completos y sobran personas analizamos esas personas con todos los equipos
            if (temp.size() == 0) {
                temp = teams
            }
            temp.each { TeamDto team ->
                PeopleDto peopleTeam = team.peopleDtoList.last()
                PersonalityType personalityType1 = PersonalityType.findByName(peopleTeam.personality);
                PersonalityType personalityType2 = PersonalityType.findByName(people.personality);
                PersonalityTypeMatch match = this.getPersonalityMaths(personalityType1, personalityType2);
                if (matchTmp) {
                    if (matchTmp.weight < match.weight) {
                        matchTmp = [team: team, people: people, weight: match.weight]
                    }
                } else {
                    matchTmp = [team: team, people: people, weight: match.weight]
                }
            }
            if (matchTmp) {
                peopleList = peopleList.findAll { it.name != matchTmp.people.name }
                matchTmp.team.peopleDtoList.push(matchTmp.people);
                matchTmp.team.weight += matchTmp.weight;
            }
        }
        if (teams.find { it.weight == 1 } && peopleList.size() != 0) {
            println "---"
            println " - Recalculando: " + teams.find { it.weight == 1 }
            println " - Size: " + peopleList.size()
            println "---"
            teams = analize(peoples, teamQuantity, optimals)
        }
        return teams.sort { -it.weight };
    }

    private List getSeeds(List<PeopleDto> peopleList, Integer teamQuantity) {
        List<PersonalityType> seeds = [];
        Integer lastRandom = -1;
        while (teamQuantity) {
            teamQuantity--;
            Random random = new Random()
            Integer rand = random.nextInt(peopleList.size());
            while (rand == lastRandom || seeds.find { it.name == peopleList[rand].name }) {
                rand = random.nextInt(peopleList.size());
            }
            lastRandom = rand;
            seeds << peopleList[rand];
            sleep(100)
        }
        return seeds;
    }

    private PersonalityTypeMatch getPersonalityMaths(PersonalityType personalityType1, PersonalityType personalityType2) {
        PersonalityTypeMatch match = PersonalityTypeMatch.findByPersonalityType1AndPersonalityType2(personalityType1, personalityType2, [sort: "weight", order: "desc"]);
        if (!match) {
            match = PersonalityTypeMatch.findByPersonalityType1AndPersonalityType2(personalityType2, personalityType1, [sort: "weight", order: "desc"]);
        }
        return match;
    }

    /**
     * Retorna un listado de Perdonas
     *
     * @param people
     * @return
     */
    private List<PeopleDto> getPeopleList(String people) {
        List<PeopleDto> peopleDtoList = [];
        people.split('\\|').each { info ->
            info = info.split(",");
            peopleDtoList << new PeopleDto(name: info[0], personality: info[1])
        }
        return peopleDtoList;
    }
}
