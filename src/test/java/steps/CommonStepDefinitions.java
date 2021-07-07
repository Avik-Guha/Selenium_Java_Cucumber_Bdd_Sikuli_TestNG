package steps;

import static org.junit.Assert.fail;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import global.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utility.CommonFunctions;

public class CommonStepDefinitions {

	WebDriver driver = Base.getDriver();
	CommonFunctions commonFunctions = new CommonFunctions(driver);

	
	@Given("Launch Application")
	public void launch_Application() {
		
		String url = Base.getEnvironment();
		commonFunctions.launch_URL(url);
		Base.log.info("launch_Application ---> Success");
		Base.log.debug("launch_Application ---> Success");
	}

	@When("Scroll down till the end of the page")
	public void scroll_down_till_the_end_of_the_page() throws Throwable {
		// System.out.println("************scroll_down_till_the_end_of_the_page***************");
		commonFunctions.scroll_at_the_bottom_of_the_page();
		Thread.sleep(1000);
	}

	@When("Switch control to child tab")
	public void switch_control_to_child_tab() throws Throwable {
		// commonFunctions.switch_to_next_tab();
		Set<String> x = commonFunctions.get_count_of_all_tabs();
		commonFunctions.switch_to_tab(x, "Child");
	}

	@When("Switch control to {string} tab")
	public void switch_control_to_tab(String tab_name) {
		Set<String> x = commonFunctions.get_count_of_all_tabs();

		switch (tab_name) {
		case "parent":
			commonFunctions.switch_to_tab(x, "Parent");
			break;

		case "child":
			commonFunctions.switch_to_tab(x, "Child");
			break;

		default:
			fail("************Please enter correct tab name***********");
		}
	}

}
