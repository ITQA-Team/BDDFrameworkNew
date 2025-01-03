package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags="",features= {"src/test/resources/Features/performance.feature"},
        glue= {"StepDefinitions"},
        plugin= {"pretty","html:target/reportorial.html"})

public class CucumberTestRunner extends AbstractTestNGCucumberTests  {

}