package com.ts

import grails.converters.JSON

class MainService2 {

    def analize(String peoples, Integer teams, Integer optimals) {
        List<String> peopleList = peoples.split('\\|');
        List<Map> teamsTmp = this.getMatches(peopleList);
        List<Map> teamsTmpSorted = teamsTmp.sort { -it.weight };
        println "--------------"
        println teamsTmpSorted as JSON
        println peopleList.size() / teams
        println "--------------"
        def resu1 = this.process(teamsTmpSorted, teamsTmpSorted.size() / teams);

        teamsTmpSorted = teamsTmpSorted.findAll {
            it.people1 != resu1.people1 && it.people1 != resu1.people2 && it.people1 != resu1.people3 &&
                    it.people2 != resu1.people1 && it.people2 != resu1.people2 && it.people2 != resu1.people3
        }
        def resu2 = this.process(teamsTmpSorted, teamsTmpSorted.size() / teams);
        println "********"
        println resu1
        println resu2
        println "********"
        return resu1;
    }

    private process(List<Map> teams, BigDecimal peopleQuantity) {
        Integer index = 0;

        def teamsResult = [];
        teams.each { Map team ->
            def temp = teams.findAll { it.people1 != team.people1 || it.people2 != team.people2 }
            println "-------------------------------"
            println "------------ TEAM -------------"
            println "-------------------------------"

            println team
            println "-------------------------------"
            //team1
            def univ1 = temp.findAll { it.people1 == team.people1 || it.people2 == team.people1 }
            println univ1
            //team3
            def univ2 = temp.findAll { it.people1 == team.people2 || it.people2 == team.people2 }
            println univ2

            def selected = null;
            univ1.each { univ ->
                if (univ.people1 != team.people1) {
                    println "- primero "
                    def tmp = univ2.findAll { it.people1 == univ.people1 || it.people2 == univ.people1 }
                    if (tmp.size() && (!selected || selected.weight < tmp[0].weight)) {
                        selected = tmp[0];
                    }
                    println tmp
                }
                if (univ.people2 != team.people1) {
                    println "- segundo"
                    def tmp = univ2.findAll { it.people1 == univ.people2 || it.people2 == univ.people2 }
                    if (tmp.size() && (!selected || selected.weight < tmp[0].weight)) {
                        selected = tmp[0];
                    }
                    println tmp
                }
            }
            println "- selected"
            println selected
            String sel = selected.people1
            if (team.people1 != selected.people2 && team.people2 != selected.people2) {
                sel = selected.people2
            }
            println "+++++++++++++++++++++++++"
            println "+ " + team.people1 + " " + team.people2 + " " + sel + " (" + (team.weight + selected.weight) + ") +"
            println "+++++++++++++++++++++++++"

            teamsResult += [people1: team.people1, people2: team.people2, people3: sel, weight: (team.weight + selected.weight)]
        }

        teamsResult = teamsResult.sort { -it.weight }
        return teamsResult[0]
    }

    def getMatch(List result, List<Map> teams, Map team, Integer index) {
        index++;
        println index
        println teams[index]
        if (index < teams.size()) {
            Map match = teams[index];
            if (match.people1 == team.people1 || match.people2 == team.people1) {
                println " -> Next: " + match
            }
        }
        List teamsTmp = teams.findAll {
            it.people1 != team.people1 &&
                    it.people2 != team.people1 &&
                    (it.people1 == team.people2 || it.people2 == team.people2)
        }
        if (teamsTmp.size()) {
            println " -> Otro: " + teamsTmp[0];
        }
    }

    private List<Map> getMatches(List<String> peopleList) {
        List<Map> teamsTmp = [];
        peopleList.each { String people1 ->
            List<String> peopleInfo1 = people1.split(",");
            peopleList.each { String people2 ->
                List<String> peopleInfo2 = people2.split(",");
                if (peopleInfo1[0] != peopleInfo2[0]) {
                    PersonalityType personalityType1 = PersonalityType.findByName(peopleInfo1[1]);
                    PersonalityType personalityType2 = PersonalityType.findByName(peopleInfo2[1]);
                    PersonalityTypeMatch match = this.getPersonalityMaths(personalityType1, personalityType2);
                    def itemTmp = teamsTmp.find {
                        (it.people1 == peopleInfo1[0] || it.people2 == peopleInfo1[0]) && (it.people1 == peopleInfo2[0] || it.people2 == peopleInfo2[0])
                    };
                    if (!itemTmp) {
                        teamsTmp << [people1: peopleInfo1[0], people2: peopleInfo2[0], weight: match.weight];
                    }
                }
            }
        }
        return teamsTmp;
    }

    private PersonalityTypeMatch getPersonalityMaths(PersonalityType personalityType1, PersonalityType personalityType2) {
        PersonalityTypeMatch match = PersonalityTypeMatch.findByPersonalityType1AndPersonalityType2(personalityType1, personalityType2, [sort: "weight", order: "desc"]);
        if (!match) {
            match = PersonalityTypeMatch.findByPersonalityType1AndPersonalityType2(personalityType2, personalityType1, [sort: "weight", order: "desc"]);
        }
        return match;
    }
}
