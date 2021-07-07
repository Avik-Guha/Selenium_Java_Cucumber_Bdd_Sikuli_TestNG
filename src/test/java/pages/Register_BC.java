package pages;

import org.openqa.selenium.WebDriver;

import objects.Register_OB;
import utility.CommonFunctions;

public class Register_BC {
	
	WebDriver driver;
	CommonFunctions commonFunctions;
	Register_OB register_OB;
	
	public Register_BC(WebDriver driver) {
		commonFunctions = new CommonFunctions(driver);
		this.driver = driver;
		register_OB = new Register_OB(driver);
	}
	

	public void verify_All_Text_in_Skills_dropdown_field() {
		//commonFunctions.verify_value_in_dropdown(register_OB.skills_ob, "Java");
		commonFunctions.verify_if_contains_text_in_WebElement_List(register_OB.skills_list, "Java");
	}

}
