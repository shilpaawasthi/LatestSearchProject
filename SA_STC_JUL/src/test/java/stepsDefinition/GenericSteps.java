package stepsDefinition;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.BaseUtil;

public class GenericSteps extends BaseUtil {	
	public BaseUtil base;

	public GenericSteps(BaseUtil base) {
		this.base = base;
	}
			
	@Given("^\"([^\"]*)\" element is present$")
	public void Element_Is_Present(String object) {
		Assert.assertTrue("Object not found " + object, base.keyword.checkElementExists(object));
	}
	
	@Then("^\"([^\"]*)\" element should be present$")
	public void element_should_be_present(String object) {
		Assert.assertTrue("Object not found " + object, base.keyword.checkElementExists(object));
	}
   
	@And("^user clicks \"([^\"]*)\"$")
	public void I_click(String object) throws InterruptedException {
		base.log.info("* And user clicks..");
		base.keyword.click(object);
	}
	
	@And("^I enter \"([^\"]*)\" as \"([^\"]*)\"$")
	public void I_enter(String object, String text) throws InterruptedException {

		base.keyword.waitUntilElementIsVisible(object);
		base.keyword.type(object, text);
	}
}
