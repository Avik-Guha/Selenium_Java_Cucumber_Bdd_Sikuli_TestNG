package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import global.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

@SuppressWarnings("deprecation")
public class Hooks {
	
	WebDriver driver;
	
	@Before
    public void setUp() throws Exception {
		Base.setDriver();
		this.driver = Base.getDriver();
		Base.setEnvironment();
    }
	
	@After
    public void tearDown(Scenario scenario) {
    	if (scenario.isFailed()) {
    		scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png", "Step FAILED (Screenshot):");
        }
    
    	//driver.close();
        driver.quit();
    }

}
