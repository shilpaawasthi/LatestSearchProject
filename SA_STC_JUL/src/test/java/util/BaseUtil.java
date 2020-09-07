package util;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;
import webConnector.AppSpecificKeywords;

public class BaseUtil {
	public Properties property;
	public AppSpecificKeywords keyword;
	public WebDriver driver;
	public Scenario scenario;	
	public Logger log;
}
