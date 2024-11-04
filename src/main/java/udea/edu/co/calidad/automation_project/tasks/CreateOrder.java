package udea.edu.co.calidad.automation_project.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import udea.edu.co.calidad.automation_project.models.OrderModel;
import static net.serenitybdd.screenplay.Tasks.instrumented;



public class CreateOrder implements Task {

    private final OrderModel order;




    public CreateOrder(OrderModel order) {
        if (order == null) {
            throw new IllegalArgumentException("OrderModel cannot be null");
        }
        this.order = order;
    }

    // Método para inicializar la tarea con los detalles de la orden
    public static CreateOrder withDetails(OrderModel order) {
        return instrumented(CreateOrder.class, order);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // URL de la API para crear órdenes
        String createOrderEndpoint = "/order";

        // Enviar la solicitud POST con los detalles de la orden en el cuerpo de la solicitud
        actor.attemptsTo(
            Post.to(createOrderEndpoint)
                .with(request -> request
                    .header("Content-Type", "application/json")
                    .body(order)
                )
        );
    }
}
