package udea.edu.co.calidad.automation_project.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseOrderId implements Question<String> {

    public static Question<String> orderId() {
        return new ResponseOrderId();
    }

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getString("id");
    }
}

