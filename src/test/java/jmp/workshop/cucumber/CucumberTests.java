package jmp.workshop.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Author: Bakhodirjon_Marupov
 * Date: 26/07/2022
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class CucumberTests {
}
