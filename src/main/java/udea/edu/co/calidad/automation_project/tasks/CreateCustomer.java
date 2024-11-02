package udea.edu.co.calidad.automation_project.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import udea.edu.co.calidad.automation_project.models.CustomerModel;

public class CreateCustomer implements Task {

    private final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    private final String url = environmentVariables.getProperty("webdriver.base.url");

    private final CustomerModel customerModel;

    public CreateCustomer(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public static CreateCustomer withDetails(CustomerModel customer) {
        return instrumented(CreateCustomer.class, customer);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(url + "/customer")
                .with(request -> request.header("Content-Type", "application/json")
                .body(customerModel)));
    };

}
