package udea.edu.co.calidad.automation_project.stepdefinitions.order;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

import udea.edu.co.calidad.automation_project.models.OrderModel;
import udea.edu.co.calidad.automation_project.questions.ResponseCode;
import udea.edu.co.calidad.automation_project.tasks.CreateOrder;
import udea.edu.co.calidad.automation_project.tasks.HasAccess;

import java.time.LocalDate;

public class OrdersStepDefinition {

    Actor author = Actor.named("author");

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("author");
    }

    @Given("I have access to the system")
    public void iHaveAccessToTheSystem() {
        author.attemptsTo(
                HasAccess.toTheApi());
    }

    @When("I create a new order")
    public void iCreateTheOrder() {
        // Date now
        LocalDate now = LocalDate.now();
        // create a new order
        OrderModel order = new OrderModel("caviar", now, 20.4, "Hamburger", 2, 1L);
        author.remember("newOrder", order);
        author.attemptsTo(
                CreateOrder.withDetails(order));
    }

    @Then("the order should be saved in the system")
    public void iShouldSeeTheOrderInTheOrderList() {
        // verify that the order is saved
        author.should(
                seeThat("The response code is 201",
                        ResponseCode.status(), is(201)));

    }
}
