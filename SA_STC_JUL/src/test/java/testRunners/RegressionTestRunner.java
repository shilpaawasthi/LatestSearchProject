package testRunners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//http://cucumber.github.io/api/cucumber/jvm/javadoc/cucumber/api/junit/package-summary.html

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, 
		features = "classpath:features",
		glue = { "stepsDefinition" }, 
		plugin = { "html:target/cucumber-pretty-report/regression-tests", "json:target/cucumber.json" }, 
		monochrome = true,
		dryRun = false,
		tags = {"@RegressionTest"} 
)

public class RegressionTestRunner {

}