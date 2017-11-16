import com.ts.PersonalityType
import com.ts.PersonalityTypeMatch

class BootStrap {

    def init = { servletContext ->
        println "----"
        println "PersonalityType size: " + PersonalityType.getAll().size()
        println "PersonalityTypeMatch size: " + PersonalityTypeMatch.getAll().size()
        println "----"
    }
    def destroy = {
    }
}
