package udea.edu.co.calidad.automation_project.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class HasAccess implements Task {

    private final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    private final String url;

    public HasAccess() {
        this.url = environmentVariables.getProperty("serenity.base.url", "https://calidadappapi.azurewebsites.net");
    }

    public static HasAccess toTheApi() {
        return new HasAccess();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.whoCan(CallAnApi.at(url));
    }

}
