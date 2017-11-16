package com.ts

import com.ts.dtos.TeamDto

class MainController {

    def loginService
    def mainService

    def index() {
        if (!loginService.getUserLogged()) {
            redirect uri: "/login"
            return
        }
        List<PersonalityType> personalityTypeList = PersonalityType.getAll();
        render(view: "index", model: [
                personalities: personalityTypeList*.name
        ])
    }

    def analize() {
//        if (!loginService.getUserLogged()) {
//            redirect uri: "/login"
//            return
//        }
        List<List<TeamDto>> teams = mainService.analizeWithOptions(params.peoples, Integer.valueOf(params.teams), Integer.valueOf(params.optimals));
        //List<List<TeamDto>> teams = [mainService.analize(params.peoples, Integer.valueOf(params.teams), Integer.valueOf(params.optimals))];
        params.options = teams
        result();
    }

    def result() {
        if (!loginService.getUserLogged()) {
            redirect uri: "/login"
            return
        }
        render(view: "testResult", model: [options: params.options])
    }
}
