package cbdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src/test/java/cbdd", glue="stepdefinition", tags= "@ErrorValidations",
monochrome=true, plugin= {"html:target/cucumberreport.html"})

// monochrome - to generate the output data in readable format
public class TestNGTestrunner extends AbstractTestNGCucumberTests { // for integrating cucumber and testng we need to extend this class


}
