package global;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
//import java.util.concurrent.TimeUnit;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static String baseUrl;

	public static WebDriver driver;
	public static JSONParser jsonParser;
	private static JSONObject object;
	public static Logger log = LogManager.getLogger(Base.class.getName());

	static File browser_json = new File("src\\test\\resources\\TestData\\Browser.json");
	static File environment_json = new File("src\\test\\resources\\TestData\\Environment.json");
	static JSONParser parser = new JSONParser();

	public static void setBrowserDriver() throws Exception {
		object = (JSONObject) parser.parse(new FileReader(browser_json));
		String browser = (String) object.get("SetBrowser");
		String headless = (String) object.get("ExecuteInHeadlessMode");

		if (browser.equals("chrome")) {

			// To handle HTTPS certificates
			ChromeOptions c = new ChromeOptions();
			c.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			c.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			WebDriverManager.chromedriver().setup();
	    	ChromeOptions options = new ChromeOptions();
	    	if (headless.contains("true")) {
	    	options.addArguments("--headless");
	    	}
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else {
			fail("Browser not supported by the framework. Enter correct browser!!! Please check your 'Browser.json' file");
		}
		driver.manage().deleteAllCookies();
	}

	public static void setEnv() throws Exception, IOException, ParseException {
		object = (JSONObject) parser.parse(new FileReader(environment_json));
		String environment = (String) object.get("Environment");

		if (environment.equals("qa")) {
			baseUrl = "http://demo.automationtesting.in/Register.html";
		} else if (environment.equals("dev")) {
			baseUrl = "https://opensource-demo.orangehrmlive.com/";
		} else {
			fail("***********Enter correct Environment!!!**********");
		}
	}

	public static void setDriver() throws Exception {
		setBrowserDriver();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setEnvironment() throws IOException, ParseException, Exception {
		setEnv();
	}

	public static String getEnvironment() {
		return baseUrl;
	}

	public static void waitForPageToLoad() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
