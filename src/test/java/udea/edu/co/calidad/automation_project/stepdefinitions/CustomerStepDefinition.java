package udea.edu.co.calidad.automation_project.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import udea.edu.co.calidad.automation_project.models.CustomerModel;
import udea.edu.co.calidad.automation_project.tasks.CreateCustomer;
import udea.edu.co.calidad.automation_project.questions.ResponseCode;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;
import net.serenitybdd.screenplay.Actor;
import udea.edu.co.calidad.automation_project.tasks.HasAccess;

public class CustomerStepDefinition {

    Actor author = Actor.named("author");

    @Before
    public void setup() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("author");
    }

    @Given("I have access to the system")
    public void iHaveAccessToTheSystem() {
        author.attemptsTo(
                HasAccess.toTheApi()
        );
    }

    @When("I create a new customer")
    public void iCreateANewCustomer() {
        double number  = Math.random();
        CustomerModel customer = new CustomerModel("John Doe", "john.doe@example.com" + number, "1234567890", "123 Main St");
        author.remember("newCustomer", customer);
        author.attemptsTo(
                CreateCustomer.withDetails(customer)
        );
    }

    @Then("the customer should be saved in the system")
    public void theCustomerShouldBeSavedInTheSystem() {
        author.should(
                seeThat("The response code is 200",
                        ResponseCode.status(), is(201))
        );
    }

}
