class UrlMappings {

	static mappings = {

        "/"(controller: "Main") {
            action = [GET:"index"]
        }

        "/login"(controller: "Login") {
            action = [GET:"signin", POST:"login"]
        }

        "/logout"(controller: "Login") {
            action = [GET:"logout"]
        }

        "/analize"(controller: "Main") {
            action = [GET: "analize", POST: "analize"]
        }

        "/result/$id"(controller: "Main") {
            action = [POST: "result"]
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "500"(view:'/error')
	}
}
