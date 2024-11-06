package udea.edu.co.calidad.automation_project.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseEmail implements Question<String> {

    public static Question<String> email() {
        return new ResponseEmail();
    }

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getString("email");
    }
}
