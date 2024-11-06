package udea.edu.co.calidad.automation_project.stepdefinitions.customer;

import static org.hamcrest.Matchers.is;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import udea.edu.co.calidad.automation_project.models.CustomerModel;
import udea.edu.co.calidad.automation_project.questions.ResponseCode;
import udea.edu.co.calidad.automation_project.questions.ResponseEmail;
import udea.edu.co.calidad.automation_project.tasks.CreateCustomer;
import udea.edu.co.calidad.automation_project.tasks.HasAccess;
import udea.edu.co.calidad.automation_project.tasks.RetrieveCustomers;

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
                HasAccess.toTheApi());
    }

    @When("I create a new customer")
    public void iCreateANewCustomer() {
        double number = Math.random();
        CustomerModel customer = new CustomerModel("John Doe", "john.doe@example.com" + number, "1234567890",
                "123 Main St");
        author.remember("newCustomer", customer);
        author.attemptsTo(
                CreateCustomer.withDetails(customer));
    }

    @Then("the customer should be saved in the system")
    public void theCustomerShouldBeSavedInTheSystem() {
        CustomerModel newCustomer = author.recall("newCustomer");
        String expectedEmail = newCustomer.getEmail();
        author.should(
                seeThat("The email is correct",
                        ResponseEmail.email(), is(expectedEmail)));

        author.should(
                seeThat("The response code is 201",
                        ResponseCode.status(), is(201)));
    }

    @When("I retrieve all customers")
    public void iRetrieveAllCustomers() {
        author.attemptsTo(
                RetrieveCustomers.list());
    }

    @Then("I should see the list of customers")
    public void iShouldSeeTheListOfCustomers() {
        author.should(
                seeThat("The response code is 200",
                        ResponseCode.status(), is(200)));
    }

    @When("I create a new customer with existing email")
    public void iCreateANewCustomerWithExistingEmail() {
        CustomerModel existingCustomer = new CustomerModel("Jane Doe", "john.doe@example.com", "1234567890", "123 Main St");
        author.remember("existingCustomer", existingCustomer);
        author.attemptsTo(
                CreateCustomer.withDetails(existingCustomer)
        );
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        author.should(
                seeThat("The response code indicates an error",
                        ResponseCode.status(), is(400)));
    }
}
