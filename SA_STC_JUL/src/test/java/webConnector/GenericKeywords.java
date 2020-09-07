package webConnector;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.BaseUtil;

public class GenericKeywords extends BaseUtil {

	public BaseUtil base;

	public GenericKeywords(BaseUtil b) {
		this.base = b;
	}

	// opening the browser
	public void openBrowser() {
		base.log.info("opening browser..");
		if (base.property.getProperty("browser").equalsIgnoreCase("Mozilla")) {
			base.log.info("Mozilla..");
			System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver-v0.23.0-win64.exe");
			base.driver = new FirefoxDriver();
		} else if (base.property.getProperty("browser").equalsIgnoreCase("chrome")) {
			base.log.info("Chrome..");
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			base.driver = new ChromeDriver();
		} else if (property.getProperty("browser").equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					".\\driver\\IEDriverServer.exe");
			base.driver = new InternetExplorerDriver();
		}

		base.driver.manage().window().maximize();
	}

	public void closeBrowser(String browserType) {
		base.log.info("closing browser..");
		if (base.driver != null) {
			base.driver.quit();
        }
	}

	// Navigate to a URL
	public void navigate(String testUrl) {
		base.log.info("Navigating to.." + testUrl);
		base.driver.get(testUrl);
	}

	public void navigate() {
		base.log.info("Navigating to.." + base.property.getProperty("testURL"));
		base.driver.get(base.property.getProperty("testURL"));
	}

	// Input Text
	public void type(String object, String data) {
		base.log.info("Entering data - " + data + " into + " + object);
		// base.driver.findElement(By.xpath(base.project.getProperty(object))).sendKeys(data);
		getElement(object).sendKeys(data);
	}

	// Clear Text
	public void clear(String object) {
		base.log.info("clearing text..");
		try {
			getElement(object).clear();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public String readTextInput(String object) {
		base.log.info("reading text valuer..");
		String actual = getElement(object).getAttribute("value");

		return actual.trim();
	}

	// Click object
	public void click(String object) {
		base.log.info("Clicking object - " + object);
		getElement(object).click();
	}

	// check element exists
	public boolean checkElementExists(String object) {
		base.log.info("Checking Element Exists - " + object);
		int element = base.driver.findElements(By.xpath(base.property.getProperty(object))).size();
		// base.log.info("element size:" + element);
		if (element == 0)
			return false;
		else
			return true;
	}

	// Verify label text
	public boolean verifyText(String object, String data) {
		base.log.info("Verifying text - " + object);
		String expected = data.trim();
		String actual = getElement(object).getText().trim();

		if (expected.equals(actual)) {
			return true;
		} else {
			return false;
		}
	}

	// Verify partial text
	public boolean verifyPartialText(String object, String data) {
		base.log.info("Verifying partial text - " + object);
		String expected = data.trim();
		String actual = getElement(object).getText().trim();

		if (actual.contains(expected)) {
			return true;
		} else {
			return false;
		}
	}

	// Read label text
	public String readLabel(String object) {
		base.log.info("Reading label from - " + object);
		String actual = getElement(object).getText().trim();

		return actual;
	}

	public void closeBrowser() {
		//base.log.info("Closing browser..");
		base.driver.quit();
	}

	public void waitUntilElementIsVisible(String object) {
		base.log.info("Waiting Until Element Is Visible - " + base.property.getProperty(object));
		WebDriverWait wait = new WebDriverWait(base.driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getElement(object)));
	}

	public void waitUntilElementIsClickable(String object) {
		base.log.info("Waiting Until Element Is Clickable - " + base.property.getProperty(object));
		WebDriverWait wait = new WebDriverWait(base.driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(getElement(object)));
	}
	

	public void sleep(int sleepTime) throws InterruptedException {
		Thread.sleep(sleepTime * 1000);		
	}

	public void keyboardEnter(String object) {
		try {
			getElement(object).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void actionMovetoElement(String object) {
		try {
			WebElement root = getElement(object);

			// Build action - Perform mouse hover (moves to centre of element)
			Actions builder = new Actions(driver);
			builder.moveToElement(root).build().perform();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void actionClick(String object) {
		try {
			WebElement root = getElement(object);

			// Build action - Perform mouse hover (moves to centre of the
			// element) and click
			Actions builder = new Actions(driver);
			builder.moveToElement(root).click().build().perform();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	// validate web element is located before performing any keyword / action
	public WebElement getElement(String locator) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(base.driver, 10);
		try {
			element = base.driver.findElement(By.xpath(base.property.getProperty(locator)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(base.property.getProperty(locator))));
		} catch (Exception e) {
			Assert.fail("Cannot find element: " + e.getMessage());
		}
		return element;
	}

	public void waitUntilPageLoadsComplelety() {
		base.log.info("Waiting until page loads completely..");
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) base.driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(base.driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
}
