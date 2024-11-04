package udea.edu.co.calidad.automation_project.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RetrieveOrderById implements Task {
    private final String orderId;

    public RetrieveOrderById(String orderId) {
        this.orderId = orderId; // Inicializa el ID de la orden
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource("/order/" + orderId) // Llama al endpoint correspondiente
        );
    }

    public static RetrieveOrderById forOrder(String orderId) {
        return instrumented(RetrieveOrderById.class, orderId); // Método estático para facilitar la creación de instancias
    }
}
