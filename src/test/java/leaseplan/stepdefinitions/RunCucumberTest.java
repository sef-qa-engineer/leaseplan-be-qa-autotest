package leaseplan.stepdefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:src/test/resources/features/*.feature"},
        glue = {"leaseplan.stepdefinitions"})

public class RunCucumberTest {
}

