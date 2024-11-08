package udea.edu.co.calidad.automation_project.runners.order;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/order/order.feature",
        glue = "udea.edu.co.calidad.automation_project.stepdefinitions.order", 
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {"pretty", "html:target/cucumber-reports"}
)

public class OrdersRunner {
    
}
