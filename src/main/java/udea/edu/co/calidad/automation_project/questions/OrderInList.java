package udea.edu.co.calidad.automation_project.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import udea.edu.co.calidad.automation_project.models.OrderModel;


import java.util.List;
import java.util.Optional;

public class OrderInList implements Question<Boolean> {

    private final OrderModel expectedOrder;

    public OrderInList(OrderModel expectedOrder) {
        this.expectedOrder = expectedOrder;
    }

    // Método estático para construir la pregunta de manera fluida
    public static OrderInList contains(OrderModel order) {
        return new OrderInList(order);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String getOrdersEndpoint = "/order";

        // Get to retrieve the list of orders
        SerenityRest.given()
            .get(getOrdersEndpoint)
            .then()
            .statusCode(200);

        // Get Json response as a list of OrderModel objects
        List<OrderModel> orders = SerenityRest.lastResponse()
            .jsonPath()
            .getList(".", OrderModel.class);

        // Search for the expected order in the list of orders
        Optional<OrderModel> matchingOrder = orders.stream()
                .filter(order -> 
                    order.getProductName().equals(expectedOrder.getProductName()) &&
                    order.getDate().equals(expectedOrder.getDate()) &&
                    order.getPrice().equals(expectedOrder.getPrice()) &&
                    order.getDescription().equals(expectedOrder.getDescription()) &&
                    order.getQuantity().equals(expectedOrder.getQuantity()) &&
                    order.getCustomer().equals(expectedOrder.getCustomer())                    
                )
                .findFirst();

        // Return true if the order was found in the list
        return matchingOrder.isPresent();
    }
}
