package com.ts

import com.ts.utils.Logger

class LoginController {

    def loginService

    def signin() {
        if (loginService.getUserLogged()) {
            redirect uri: "/"
            return
        }
        Logger.kibana(Logger.TRACE, [
                user: session.userLogued
        ], "signin - User logued");
        render(view: "signin");
    }

    def login() {
        String user = params.username;
        String pass = params.password;

        def result = loginService.check(user, pass);
        Logger.kibana(Logger.TRACE, [
                user: result
        ], "login - User logued");
        if (result != null) {
            session.userLogued = result
            redirect(uri: params.back ?: "/");
            return
        }

        def model = [
                back   : params.back,
                message: [
                        type: "error",
                        text: "Usuario y/o contraseña incorrectos!"
                ]
        ];

        render(view: "signin", model: model);
    }

    def logout() {
        Logger.kibana(Logger.TRACE, [
                user: session.userLogued
        ], "User logout");
        loginService.logout();
        session.userLogued = null
        signin();
    }
}
