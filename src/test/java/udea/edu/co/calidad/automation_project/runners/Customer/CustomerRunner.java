package udea.edu.co.calidad.automation_project.runners.Customer;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/customer/customer.feature",
        glue = "udea.edu.co.calidad.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class CustomerRunner {}