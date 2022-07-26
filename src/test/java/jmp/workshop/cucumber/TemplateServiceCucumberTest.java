package jmp.workshop.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jmp.workshop.service.TemplateService;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 26/07/2022
 */
public class TemplateServiceCucumberTest {

    private final static Map<String, String> PLACEHOLDERS = Map.of(
        "name", "Liam",
        "link", "https://www.test.com/",
        "deadline", "12.05.2022",
        "team", "Support");

    private TemplateService templateService = new TemplateService();
    private String message;


    @When("call prepareMessage with {string}")
    public void callPrepareMessageMethod(String template) {
        message = templateService.prepareMessage(template, PLACEHOLDERS);
    }

    @Then("should return message {string}")
    public void shouldReturnMessage(String expectedMessage) {
        Assertions.assertEquals(message, expectedMessage);
    }
}
