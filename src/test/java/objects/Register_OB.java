package objects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class Register_OB {

	WebDriver driver;

	@FindBy(how = How.CSS, using = "#Skills > option")
	public List<WebElement> skills_list;
	
	@FindBy(how = How.CSS, using = "#Skills")
	public WebElement skills_ob;
	
	public Register_OB(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
