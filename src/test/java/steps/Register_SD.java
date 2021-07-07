package steps;

import org.openqa.selenium.WebDriver;

import global.Base;
import io.cucumber.java.en.Then;
import pages.Register_BC;

public class Register_SD {
	
	WebDriver driver = Base.getDriver();
	
	Register_BC register_BC = new Register_BC(driver);
	
	
	@Then("Verify All Text in Skills dropdown field")
	public void verify_All_Text_in_Skills_dropdown_field() {
		register_BC.verify_All_Text_in_Skills_dropdown_field();
		
	}

}
