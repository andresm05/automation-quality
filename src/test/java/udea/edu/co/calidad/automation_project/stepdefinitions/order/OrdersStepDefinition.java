package udea.edu.co.calidad.automation_project.stepdefinitions.order;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import udea.edu.co.calidad.automation_project.models.OrderModel;
import udea.edu.co.calidad.automation_project.questions.ResponseCode;
import udea.edu.co.calidad.automation_project.questions.ResponseErrorMessage;
import udea.edu.co.calidad.automation_project.questions.ResponseOrderId;
import udea.edu.co.calidad.automation_project.tasks.CreateOrder;
import udea.edu.co.calidad.automation_project.tasks.HasAccess;
import udea.edu.co.calidad.automation_project.tasks.RetrieveOrderById;
import udea.edu.co.calidad.automation_project.tasks.RetrieveOrders;

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

    @When("I retrieve all orders")
    public void iRetrieveAllOrders() {
        author.attemptsTo(
                RetrieveOrders.list());

    }

    @Then("I should see the list of orders")
    public void iShouldSeeTheListOfOrders() {
        author.should(
                seeThat("The response code is 200",
                        ResponseCode.status(), is(200)));
    }

    @When("I retrieve order by id")
    public void iRetrieveTheOrderById() {
        author.attemptsTo(
                RetrieveOrderById.forOrder("1")
        );
    }

    @Then("I should see the order with the given id")
    public void iShouldSeeTheOrderWithTheGivenId() {
        String expectedOrderId = "1";

        author.should(
                seeThat("The order id is correct",
                        ResponseOrderId.orderId(), is(expectedOrderId))
        );
        author.should(
                seeThat("The response code is 200",
                        ResponseCode.status(), is(200))
        );
    }

    @When("I retrieve order with invalid id")
    public void iRetrieveTheOrderWithInvalidId() {
        author.attemptsTo(
                RetrieveOrderById.forOrder("100")
        );
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        String expectedId = "100";
        String expectedErrorMessage = "Order with id: " + expectedId + " not found";

        author.should(
                seeThat("The error message is correct",
                        ResponseErrorMessage.message(), is(expectedErrorMessage))
        );

        author.should(
                seeThat("The response code is 400",
                        ResponseCode.status(), is(400))
        );
    }

}
