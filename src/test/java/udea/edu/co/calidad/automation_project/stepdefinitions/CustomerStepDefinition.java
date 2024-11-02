package udea.edu.co.calidad.automation_project.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import udea.edu.co.calidad.automation_project.models.CustomerModel;
import udea.edu.co.calidad.automation_project.questions.CustomerInList;
import udea.edu.co.calidad.automation_project.tasks.CreateCustomer;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class CustomerStepDefinition {

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
    }

    @Given("I have a new customer")
    public void iHaveANewCustomer() {
        CustomerModel customer = new CustomerModel("John Doe", "john.doe@example.com", "1234567890", "123 Main St");
        OnStage.theActorInTheSpotlight().remember("newCustomer", customer);
    }

    @When("I create a new customer")
    public void iCreateANewCustomer() {
        CustomerModel customer = OnStage.theActorInTheSpotlight().recall("newCustomer");
        OnStage.theActorInTheSpotlight().attemptsTo(
                CreateCustomer.withDetails(customer)
        );
    }

    @Then("I should see the customer in the list")
    public void iShouldSeeTheCustomerInTheList() {
        CustomerModel expectedCustomer = OnStage.theActorInTheSpotlight().recall("newCustomer");

        OnStage.theActorInTheSpotlight().should(
                seeThat("the customer is in the list", CustomerInList.matches(expectedCustomer))
        );
    }

}
