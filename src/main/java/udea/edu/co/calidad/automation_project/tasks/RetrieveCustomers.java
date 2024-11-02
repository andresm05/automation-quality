package udea.edu.co.calidad.automation_project.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RetrieveCustomers implements Task {

    public static RetrieveCustomers list() {
        return instrumented(RetrieveCustomers.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource("/customer")
        );
    }
}
