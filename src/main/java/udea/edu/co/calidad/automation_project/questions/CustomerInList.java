package udea.edu.co.calidad.automation_project.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;
import udea.edu.co.calidad.automation_project.models.CustomerModel;
import java.util.List;

public class CustomerInList implements Question<Boolean> {

    private final CustomerModel customer;

    public CustomerInList(CustomerModel customer) {
        this.customer = customer;
    }

    public static CustomerInList matches(CustomerModel customer) {
        return new CustomerInList(customer);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        List<String> customerNames = SerenityRest.lastResponse().jsonPath().getList("name");
        return customerNames.contains(customer.getName());
    }
}
