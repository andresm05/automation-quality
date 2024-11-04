package udea.edu.co.calidad.automation_project.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class RetrieveOrders implements Task {

    public static RetrieveOrders list() {
        return instrumented(RetrieveOrders.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
         actor.attemptsTo(
            Get.resource("/order")
        );
    }

   
    
}
