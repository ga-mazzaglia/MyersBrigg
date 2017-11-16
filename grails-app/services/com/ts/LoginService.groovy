package com.ts

class LoginService {

    def sessionService

    static private String USER_LOGGED = "userLogged"

    public User getUserLogged() {
        return (User) sessionService.getValue(USER_LOGGED);
    }

    public logout() {
        sessionService.saveValue(USER_LOGGED, null);
    }

    public User check(String username, String pass) {
        User user = User.findByUsername(username);
        sessionService.saveValue(USER_LOGGED, user);
        return user;
    }

}
