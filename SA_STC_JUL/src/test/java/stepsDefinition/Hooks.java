package stepsDefinition;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import util.BaseUtil;
import util.Constants;
import webConnector.AppSpecificKeywords;

public class Hooks extends BaseUtil {

	public BaseUtil base;

	public Hooks(BaseUtil base) {
		this.base = base;
	}

	@Before
	public void beforeScenario(Scenario scenario) throws IOException {
		base.scenario = scenario;
		
		if (base.property == null) {
			try {
				// Initialise logs
				DOMConfigurator.configure(Constants.Log4JPath);				
				base.log = Logger.getLogger(Log.class.getName());

				// Initialise properties
				base.property = new Properties();
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir") + Constants.ProjectPropertiesPath);
				base.property.load(fs);
				
				// Start logging for scenario				
				base.log.info("****************************************************************************************");
				base.log.info("**                    START SCENARIO EXECUTION " + scenario.getName() +              "**");                  
				base.log.info("****************************************************************************************");
				base.log.info("initialising properties..");
				base.log.info("Properties initialised!");
			} catch (Exception e) {
				base.log.fatal("Error occured while initialising properties file");
				throw new IOException("Error on initialising properties file");
			}
		}

		// Instantiate app keywords and pass 'base' object
		base.keyword = new AppSpecificKeywords(base);
		base.scenario.write("Starting scenario..");
		base.scenario.write(scenario.getName());
		
		// Open browser and navigate to test site
		base.keyword.openBrowser();
		base.keyword.navigate();
	}

	@After
	public void afterScenario(Scenario scenario) {
		base.scenario = scenario;
		base.scenario.write("Ending scenario..");
		base.log.info("Closing browser after running the Scenario..");
		base.log.info("****************************************************************************************");
		base.log.info("**                    END SCENARIO EXECUTION " + scenario.getName() + "              **");
		base.log.info("****************************************************************************************");
		base.keyword.closeBrowser();
	}
}
