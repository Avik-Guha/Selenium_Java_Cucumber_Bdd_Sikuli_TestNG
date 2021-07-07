package utility;

import static org.junit.Assert.fail;

import java.awt.datatransfer.StringSelection;

import java.io.File;
import java.io.FileInputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.json.simple.JSONArray;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import global.Base;
import junit.framework.Assert;

@SuppressWarnings({ "deprecation", "unchecked", "resource" })

public class CommonFunctions extends Base {

	public WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions actionObject;

	public CommonFunctions(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		js = (JavascriptExecutor) driver;
		actionObject = new Actions(driver);
	}

//	 																		\\
//***************************** Browser Commands ****************************\\
//    																		  \\

	/******
	 * Method to launch an url
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void launch_URL(String url) {
		Base.waitForPageToLoad();
		driver.get(url);
		wait.until(ExpectedConditions.urlToBe(url));
		Assert.assertTrue("****Method: launch_URL (FAILED); Url did not match******",
				driver.getCurrentUrl().equals(url));
	}

	/******
	 * Method to refresh a page
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void refresh_Page() {
		driver.navigate().refresh();
	}

	/******
	 * Method to verify Url of the page
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void verify_Url(String expected_Url) {
		Assert.assertEquals(expected_Url, driver.getCurrentUrl());
	}

	/******
	 * Method to verify Title of the page
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void verify_Title(String expected_Title) {
		Assert.assertEquals(expected_Title, driver.getTitle());
	}

	/******
	 * Method to navigate to previous Page
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void navigate_to_previous_Page() {
		driver.navigate().back();
	}

	/******
	 * Method to navigate to next Page
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void navigate_to_next_Page() {
		driver.navigate().forward();
	}

	/******
	 * Method to scroll at the bottom of the page
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void scroll_at_the_bottom_of_the_page() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

//																			\\
//***************************** Keyboard Commands ****************************\\
//	  																		  \\

	/******
	 * Press Down Arrow key in keyboard
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void press_DOWN_Arrow_key() throws Throwable {
		actionObject.sendKeys(Keys.ARROW_DOWN).build().perform();
		actionObject.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
	}

	/******
	 * Press ENTER key in keyboard
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void press_ENTER_key() throws Throwable {
		actionObject.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
	}

//																			 \\
//***************************** Mouse Action Commands ****************************\\
//		  																	   \\

	/******
	 * Method to hover mouse over an element
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void hover_mouse(WebElement element_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		actionObject.moveToElement(element_Location).build().perform();
		Thread.sleep(1000);
	}

	/******
	 * Method to hover mouse over an element and click
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void hover_mouse_and_click(WebElement element_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		actionObject.moveToElement(element_Location).click().build().perform();
		Thread.sleep(1000);
	}

	/******
	 * Method to Drag & Drop
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void drag_and_drop(WebElement source_Location, WebElement destination_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(source_Location));
		wait.until(ExpectedConditions.visibilityOf(destination_Location));
		actionObject.dragAndDrop(source_Location, destination_Location).build().perform();
		Thread.sleep(2000);
	}

	/******
	 * Method to Slide (drag and drop by x axis)
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void slide_horizontally(WebElement slider_Location, WebElement slider_x_axis_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(slider_Location));
		Integer slider_width_half = slider_x_axis_Location.getSize().width / 2;

		actionObject.dragAndDropBy(slider_Location, slider_width_half, 0).build().perform();
		Thread.sleep(2000);
	}

	/******
	 * Method to handle Resizable element
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void resize(WebElement slider_Location, Integer x_axis, Integer y_axis) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(slider_Location));

		actionObject.dragAndDropBy(slider_Location, x_axis, y_axis).build().perform();
		Thread.sleep(3000);
	}

	/******
	 * Method to Right Click on an element
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void right_click(WebElement element_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));

		actionObject.contextClick(element_Location).build().perform();
		Thread.sleep(1000);
	}

//																				  \\
//*********************** Operations Common for all Elements **********************\\
//																					\\

	/******
	 * Check displayed status (common for any element) Method to verify if the field
	 * is displayed
	 **** 
	 * @Creator Avik Guha
	 **/
	public void is_Displayed(WebElement element_Location) {
		Assert.assertTrue(element_Location.isDisplayed());
	}

	/******
	 * Method to fetch the value of the element
	 * 
	 * @return
	 **** @Creator Avik Guha
	 **/
	public String get_Value(WebElement element_Location) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		String x = element_Location.getText();
		System.out.println("The value returned is: " + x);
		return x;
	}

	/******
	 * Method to get Attribute Value using index
	 * 
	 * @return String
	 **** @Creator Avik Guha
	 **/
	public String get_Value_using_index(WebElement parent_element_Location, List<WebElement> list, Integer index,
			String expected_value) {
		wait.until(ExpectedConditions.visibilityOf(parent_element_Location));
		String actual_value = list.get(index).getAttribute("Value");
		return actual_value;
	}

	public String get_Value_using_index(List<WebElement> list, Integer index, String expected_value) {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		String actual_value = list.get(index).getAttribute("Value");
		return actual_value;
	}

	/******
	 * Method to get Text using index
	 * 
	 * @return String
	 **** @Creator Avik Guha
	 **/
	public String get_Text_using_index(WebElement parent_element_Location, List<WebElement> list, Integer index,
			String expected_value) {
		wait.until(ExpectedConditions.visibilityOf(parent_element_Location));
		String actual_text = list.get(index).getText();
		return actual_text;
	}

	public String get_Text_using_index(List<WebElement> list, Integer index, String expected_value) {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		String actual_text = list.get(index).getText();
		return actual_text;
	}

	/******
	 * Method to verify locator contains expected value
	 * 
	 **** @Creator Avik Guha
	 **/
	public void verify_if_contains_Value(WebElement element_Location, String value_To_Check) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		String actual_Value = element_Location.getText();
		System.out.println("Actual Value: " + actual_Value);
		Assert.assertTrue(actual_Value.contains(value_To_Check));
	}

	/******
	 * Method to verify locator equals (exact match) expected value
	 * 
	 **** @Creator Avik Guha
	 **/
	public void verify_if_equals_Value(WebElement element_Location, String value_To_Check) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Assert.assertTrue(element_Location.getText().equals(value_To_Check));
	}

	/******
	 * Method to verify Attribute Value using index
	 * 
	 **** @Creator Avik Guha
	 **/
	public void verify_if_equals_Value_using_index(WebElement parent_element_Location, List<WebElement> list,
			Integer index, String expected_value) {
		wait.until(ExpectedConditions.visibilityOf(parent_element_Location));
		String actual_value = list.get(index).getAttribute("Value");
		Assert.assertTrue(expected_value.matches(actual_value));
	}

	public void verify_if_equals_Value_using_index(List<WebElement> list, Integer index, String expected_value) {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		String actual_value = list.get(index).getAttribute("Value");
		Assert.assertTrue(expected_value.matches(actual_value));
	}

	/******
	 * Method to verify if locator matches text using index
	 * 
	 **** @Creator Avik Guha
	 **/
	public void verify_if_matches_text_using_index(WebElement parent_element_Location, List<WebElement> list,
			Integer index, String expected_value) {
		wait.until(ExpectedConditions.visibilityOf(parent_element_Location));
		String actual_value = list.get(index).getText();
		Assert.assertTrue("******Method: verify_if_matches_text_using_index (FAILED)******" + " expected_value: "
				+ expected_value + "," + " actual_value: " + actual_value, expected_value.matches(actual_value));
	}

	public void verify_if_matches_text_using_index(List<WebElement> list, Integer index, String expected_value) {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		String actual_value = list.get(index).getText();
		Assert.assertTrue("******Method: verify_if_matches_text_using_index (FAILED)******" + " expected_value: "
				+ expected_value + "," + " actual_value: " + actual_value, expected_value.matches(actual_value));
	}

	/******
	 * Method to verify total number of elements displayed with same locator
	 * 
	 **** @Creator Avik Guha
	 **/
	public void verify_total_number_of_elements_with_same_locator(List<WebElement> list, Integer expected_Count) {
		Integer actualCount = list.size();
		Assert.assertTrue(actualCount.equals(expected_Count));
	}

	/******
	 * Method to verify if an Element List contains a required text to match
	 * 
	 **** @Creator Avik Guha
	 **/
	public void verify_if_contains_text_in_WebElement_List(WebElement element_Location, List<WebElement> list,
			String expected_text) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Boolean x = null;
		Iterator<WebElement> iterator = list.iterator();
		while (iterator.hasNext()) {
			WebElement actual_element = iterator.next();
			String text = actual_element.getText();
			// System.out.println("*********actual_element********** " + text);
			if (text.contains(expected_text)) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		Assert.assertTrue(
				"********Method: verify_if_contains_text_in_WebElement_List (FAILED); Expected text did not match*********",
				x);
	}

	public void verify_if_contains_text_in_WebElement_List(List<WebElement> list, String expected_text) {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		Boolean x = null;
		Iterator<WebElement> iterator = list.iterator();
		while (iterator.hasNext()) {
			WebElement actual_element = iterator.next();
			String text = actual_element.getText();
			// System.out.println("*********actual_element********** " + text);
			if (text.contains(expected_text)) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		Assert.assertTrue(
				"********Method: verify_if_contains_text_in_WebElement_List (FAILED); Expected text did not match*********",
				x);
	}

	/******
	 * Method to fetch the index by matching a value, then perform click using that
	 * index value String array variable used for matching
	 * 
	 * @throws Throwable
	 * 
	 * @Creator Avik Guha
	 **/

	public void iterate_and_click_where_contains_text(WebElement element_Location, List<WebElement> list,
			List<WebElement> toClick_list, String[] expected_values) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));

		Integer i = 0;

		for (String expected_value : expected_values) {
			for (WebElement listIterator : list) {
				if (listIterator.getText().contains(expected_value)) {
					toClick_list.get(i).click();
					i++;
					Thread.sleep(1000);
					break;
				} else {
					i++;
					continue;
				}
			}
			i = 0;
		}
	}

	public void iterate_and_click_where_contains_text(List<WebElement> list, List<WebElement> toClick_list,
			String[] expected_values) throws Throwable {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));

		Integer i = 0;

		for (String expected_value : expected_values) {
			for (WebElement listIterator : list) {
				if (listIterator.getText().contains(expected_value)) {
					toClick_list.get(i).click();
					i++;
					Thread.sleep(1000);
					break;
				} else {
					i++;
					continue;
				}
			}
			i = 0;
		}
	}

	/******
	 * Method to fetch the index by matching a value, then perform click using that
	 * index value JSONArray variable used for matching
	 * 
	 * @throws Throwable
	 * 
	 * @Creator Avik Guha
	 **/

	public void iterate_and_click_where_contains_text(WebElement element_Location, List<WebElement> list,
			List<WebElement> toClick_list, JSONArray expected_values) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));

		Integer i = 0;

		Iterator<String> iterator = expected_values.iterator();

		while (iterator.hasNext()) {
			String value_to_match = iterator.next().toString();
			for (WebElement listIterator : list) {
				if (listIterator.getText().contains(value_to_match)) {
					toClick_list.get(i).click();
					i++;
					Thread.sleep(1000);
					break;
				} else {
					i++;
					continue;
				}
			}
			i = 0;
		}
	}

	public void iterate_and_click_where_contains_text(List<WebElement> list, List<WebElement> toClick_list,
			JSONArray expected_values) throws Throwable {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));

		Integer i = 0;

		Iterator<String> iterator = expected_values.iterator();

		while (iterator.hasNext()) {
			String value_to_match = iterator.next().toString();
			for (WebElement listIterator : list) {
				if (listIterator.getText().contains(value_to_match)) {
					toClick_list.get(i).click();
					i++;
					Thread.sleep(1000);
					break;
				} else {
					i++;
					continue;
				}
			}
			i = 0;
		}
	}

//																						\\
//*********************** Operations on Editable/Non-Editable Box **********************\\
//																						\\

	/******
	 * Method to enter a value in text box
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void enter_Value(WebElement element_Location, String value_To_Enter) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		element_Location.clear();
		element_Location.sendKeys(value_To_Enter);
		Thread.sleep(2000);
	}

	/******
	 * Method to enter a value in text box
	 **** 
	 * @Creator Avik Guha
	 ***/
	public void enter_text_in_Upper_Case(WebElement element_Location, String text_To_Enter) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		element_Location.clear();
		element_Location.click();
		actionObject.keyDown(Keys.SHIFT).sendKeys(text_To_Enter).keyUp(Keys.SHIFT).build().perform();
		Thread.sleep(1000);
	}

	/******
	 * Method to clear the value in text box
	 **** 
	 * @Creator Avik Guha
	 **/
	public void clear_Text(WebElement element_Location) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		element_Location.clear();
	}

	/******
	 * Method to verify maximum length of the edit box
	 * 
	 * @return
	 **** @Creator Avik Guha
	 **/
	public void test_maximum_Length(WebElement element_Location, Integer expected_Length) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Assert.assertEquals(expected_Length, element_Location.getAttribute("maxlength"));
	}

	/******
	 * Method to verify minimum length of the edit box
	 * 
	 **** @Creator Avik Guha
	 **/
	public void test_minimum_Length(WebElement element_Location, Long expected_Length) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Long actual_Length = Long.parseLong(element_Location.getAttribute("minlength"));
		Assert.assertEquals(expected_Length, actual_Length);
	}

//																			\\
//**************************** Operations on Link ****************************\\
//																				\\

	/******
	 * Click (common for all click-able element) Method to click on link, button,
	 * drop-down, check-box, radio button
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void click(WebElement element_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		element_Location.click();
		Thread.sleep(1000);
	}

	/******
	 * Double-Click (common for all click-able element) Method to double click on
	 * link, button, drop-down, check-box, radio button
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void double_click(WebElement element_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Actions actions = new Actions(driver);
		actions.doubleClick(element_Location).perform();
		Thread.sleep(1000);
	}

	/******
	 * Method to click on an element. If the element is anot available then ignore
	 * and proceed with the execution without failing the script
	 * 
	 **** @Creator Avik Guha
	 **/
	public void click_IgnoreIfUnavailable(WebElement element_Location) throws Throwable {
		if (element_Location.isDisplayed()) {
			element_Location.click();
			Thread.sleep(1000);
		}
	}

	/******
	 * Click from list of elements (common for all click-able element) Method to
	 * click on link, button, drop-down, check-box, radio button
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void click_from_list_of_elements(WebElement parent_element_Location, List<WebElement> list,
			String element_to_select) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(parent_element_Location));
		Boolean x = null;
		Iterator<WebElement> iterator = list.iterator();
		while (iterator.hasNext()) {
			WebElement actual_element = iterator.next();
			String text = actual_element.getText();
			// System.out.println("*********actual_element********** " + text);
			if (text.matches(element_to_select)) {
				actual_element.click();
				Thread.sleep(1000);
				x = true;
				break;
			} else {
				x = false;
			}
		}
		Assert.assertTrue("********Method: click_from_list_of_elements*********", x);
	}

	public void click_from_list_of_elements(List<WebElement> list, String element_to_select) throws Throwable {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		Boolean x = null;
		Iterator<WebElement> iterator = list.iterator();
		while (iterator.hasNext()) {
			WebElement actual_element = iterator.next();
			String text = actual_element.getText();
			// System.out.println("*********actual_element********** " + text);
			if (text.matches(element_to_select)) {
				actual_element.click();
				Thread.sleep(1000);
				x = true;
				break;
			} else {
				x = false;
			}
		}
		Assert.assertTrue("********Method: click_from_list_of_elements*********", x);
	}

	/******
	 * Click (common for all click-able element) Method to click on element using
	 * index value that is passed as a parameter
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void click_using_index_value(WebElement parent_element_Location, List<WebElement> list, Integer index)
			throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(parent_element_Location));
		list.get(index).click();
		Thread.sleep(1000);
	}

	public void click_using_index_value(List<WebElement> list, Integer index) throws Throwable {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		list.get(index).click();
		Thread.sleep(1000);
	}

	/******
	 * Method to fetch index value of the element
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public int get_index_from_list(WebElement parent_element_Location, List<WebElement> list, String text_to_match) {
		wait.until(ExpectedConditions.visibilityOf(parent_element_Location));
		int return_value = 0;
		for (int i = 0; i < list.size(); i++) {
			String actual_element = list.get(i).getText();
			if (actual_element.contains(text_to_match)) {
				return_value = i;
				break;
			} else {
				continue;
			}
		}
		return return_value;
	}

	public int get_index_from_list(List<WebElement> list, String text_to_match) {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		int return_value = 0;
		for (int i = 0; i < list.size(); i++) {
			String actual_element = list.get(i).getText();
			if (actual_element.contains(text_to_match)) {
				return_value = i;
				break;
			} else {
				continue;
			}
		}
		return return_value;
	}

	/******
	 * Method to type text and then press ENTER key
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void type_then_enter(WebElement element_Location, String element_to_enter) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		element_Location.sendKeys(element_to_enter);
		Thread.sleep(1000);
		actionObject.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);

	}

	/******
	 * Method to verify total number of links available in a page
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void verify_total_number_of_links_in_page(Integer expected_number_of_links) {
		Integer actual_number_of_links = driver.findElements(By.tagName("a")).size();
		Assert.assertEquals(expected_number_of_links, actual_number_of_links);

	}

	/******
	 * Method to verify total number of links available in a block inside a page
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void verify_total_number_of_links_in_block(WebElement block_Location, Integer expected_number_of_links) {
		Integer actual_number_of_links = block_Location.findElements(By.tagName("a")).size();
		Assert.assertEquals(expected_number_of_links, actual_number_of_links);

	}

	/******
	 * Method to open link in a separate tab
	 * 
	 * @throws Throwable
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void open_link_in_separate_tab(WebElement link_location) throws Throwable {
		String clickOnLinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
		link_location.sendKeys(clickOnLinkTab);
		Thread.sleep(3000);

	}

	/******
	 * Method to verify if any broken link is available
	 * 
	 * @throws Throwable
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void verify_if_any_broken_link(List<WebElement> list) throws Throwable {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		String text = null;
		ArrayList<String> broken_links = new ArrayList<String>();

		Iterator<WebElement> iterator = list.iterator();
		while (iterator.hasNext()) {
			WebElement actual_element = iterator.next();
			text = actual_element.getText();
			String url = actual_element.getAttribute("href");

			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();

			int respCode = conn.getResponseCode();

			if (respCode > 400) {
				System.out.println("Broken link: " + text + " ------> " + respCode + "   " + url);
				broken_links.add(text);
			}
		}
		System.out.println("broken_links ---------> " + broken_links);
		Assert.assertTrue("Method: verify_if_any_broken_link (FAILED), broken link detected", broken_links.isEmpty());
	}

//																				\\
//***************************** Operations on Checkbox **************************\\
//																				  \\

	/******
	 * Check enabled status (common for any element) Method to verify if the field
	 * is enabled
	 **** 
	 * @Creator Avik Guha
	 **/
	public void is_Enabled(WebElement element_Location) {
		Assert.assertTrue(element_Location.isEnabled());
	}

	/******
	 * Selected (common for all selectable element) Method to verify if the element
	 * is in Selected state. For Checkbox, Radio buttons etc.
	 **** 
	 * @Creator Avik Guha
	 **/
	public void is_Selected(WebElement element_Location) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Assert.assertTrue(element_Location.isSelected());
	}

	/******
	 * Select (common for Checkbox and Radio button) Method to select Checkbox and
	 * Radio button
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void check(WebElement element_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		if (element_Location.isSelected()) {
			Assert.assertTrue(element_Location.isSelected());
		} else {
			element_Location.click();
			Thread.sleep(1000);
			if (element_Location.isSelected()) {
				Assert.assertTrue(element_Location.isSelected());
			} else
				Assert.assertTrue(element_Location.isSelected());
		}
	}

	/******
	 * Method to UnCheck checkbox
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void uncheck(WebElement element_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		if (element_Location.isSelected()) {
			element_Location.click();
			Thread.sleep(1000);
			Assert.assertFalse(element_Location.isSelected());
		} else {
			Assert.assertFalse(element_Location.isSelected());
		}
	}

//																				\\
//***************************** Operations on Drop-down **************************\\
//	  																			  \\

	/******
	 * Method to select value in dropdown, select by Visible Text
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void select_by_visible_text(WebElement element_Location, String value_to_select) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Select dropdown = new Select(element_Location);
		dropdown.selectByVisibleText(value_to_select);
		Thread.sleep(1000);
	}

	/******
	 * Method to select value in dropdown, select by Value
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void select_by_value(WebElement element_Location, String value_to_select) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Select dropdown = new Select(element_Location);
		dropdown.selectByValue(value_to_select);
		Thread.sleep(1000);
	}

	/******
	 * Method to verify value in dropdown select by Value
	 **** 
	 * @Creator Avik Guha
	 **/
	public void verify_value_in_dropdown(WebElement element_Location, String value_to_match) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Select dropdown = new Select(element_Location);
		Boolean x = null;
		// getting the list of options in the drop-down with getOptions()
		List<WebElement> options = dropdown.getOptions();
		for (WebElement option : options) {
			if (option.getText().matches(value_to_match)) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		Assert.assertTrue("********Method: verify_value_in_dropdown ; Assertion ---> (FAILED)*********", x);
	}

	/******
	 * Method to verify all values in dropdown 
	 * select by Value 
	 * Pass values from Json file
	 * 
	 * @Creator Avik Guha
	 **/
	public void verify_all_values_in_dropdown(WebElement element_Location, JSONArray expected_values) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Select dropdown = new Select(element_Location);

		// getting the list of options in the dropdown with getOptions()
		List<WebElement> actual_values = dropdown.getOptions();

		Boolean x = null;

		Iterator<String> iterator = expected_values.iterator();

		while (iterator.hasNext()) {
			String value_to_match = iterator.next().toString();
			// System.out.println("****valueToMatch: " + value_to_match);
			for (WebElement actual_value : actual_values) {
				String option = actual_value.getText();
				if (option.matches(value_to_match)) {
					x = true;
					break;
				} else {
					x = false;
				}
			}
			if (x.equals(true)) {
				continue;
			} else if (x.equals(false)) {
				break;
			}
			iterator.next();
		}
		Assert.assertTrue(
				"********Method: verify_all_values_in_dropdown(WebElement element_Location, JSONArray expected_values) ; Assertion ---> (FAILED)*********",
				x);
	}

	/******
	 * Method to verify all values in dropdown 
	 * select by Value 
	 * Pass values from String array variable
	 * 
	 * @Creator Avik Guha
	 **/
	public void verify_all_values_in_dropdown(WebElement element_Location, String[] expected_values) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Select dropdown = new Select(element_Location);

		// getting the list of options in the dropdown with getOptions()
		List<WebElement> actual_values = dropdown.getOptions();

		Boolean x = null;

		for (String expected_value : expected_values) {
			for (WebElement actual_value : actual_values) {
				if (actual_value.getText().matches(expected_value)) {
					x = true;
					break;
				} else {
					x = false;
				}
			}
			if (x.equals(true)) {
				continue;
			} else if (x.equals(false)) {
				break;
			}
		}
		Assert.assertTrue(
				"********Method: verify_all_values_in_dropdown(WebElement element_Location, String[] expected_values) ; Assertion ---> (FAILED)*********",
				x);
	}

	/******
	 * Method to verify total number of values in dropdown
	 **** 
	 * @Creator Avik Guha
	 **/
	public void verify_total_number_of_values_in_dropdown(WebElement element_Location,
			Integer expected_number_of_values) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Select dropdown = new Select(element_Location);
		// getting the list of options in the drop-down with getOptions()
		List<WebElement> op = dropdown.getOptions();
		Integer actual_number_of_values = op.size();
		Assert.assertEquals(expected_number_of_values, actual_number_of_values);
	}

	/******
	 * Method to verify selected value in dropdown
	 **** 
	 * @Creator Avik Guha
	 **/
	public void verify_selected_value_in_dropdown(WebElement element_Location, String expected_value) {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Select dropdown = new Select(element_Location);
		WebElement value = dropdown.getFirstSelectedOption();
		String actual_value = value.getText();
		Assert.assertEquals(expected_value, actual_value);
	}
	
	/******
	 * Method to handle multi select dropdown
	 **** 
	 * @Creator Avik Guha
	 **/
	public void selected_value_in_multi_select_dropdown(WebElement element_Locator, List<WebElement> elements_Locator, String[] values_to_select) {
		wait.until(ExpectedConditions.visibilityOf(element_Locator));
		
		try {
			element_Locator.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<WebElement> dropdown_list = elements_Locator;

		String[] req_list_to_select = values_to_select;

		if (req_list_to_select[0] != "ALL") {
			for (WebElement element : dropdown_list) {
				for (Integer i=0; i<req_list_to_select.length; i++) {
					if (req_list_to_select[i].matches(element.getText())) {
						element.click();
						break;
					}
				}
			}
		}
		else {
			for (WebElement element : dropdown_list) {
				try {
					element.click();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

//																				\\
//***************************** Operations on Datepicker **************************\\
//		  																			\\

	/******
	 * Method to select Current Date
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void select_current_date(WebElement element_Location) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		element_Location.click();
		Thread.sleep(1000);
	}

	/******
	 * Method to select Month
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void select_month(WebElement element_Location, WebElement month_location, String month_text)
			throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Select dropdown_select_Month = new Select(month_location);
		dropdown_select_Month.selectByVisibleText(month_text);
		Thread.sleep(3000);
	}

	/******
	 * Method to select Date
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void select_date(WebElement element_Location, List<WebElement> dates, String day) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));

		Integer count = dates.size();
		Boolean x = false;
		// String date = date_to_select.toString();

		for (Integer i = 0; i < count; i++) {
			String text = dates.get(i).getText();

			if (text.equals(day)) {
				dates.get(i).click();
				x = true;
				break;
			}
		}
		Assert.assertTrue(
				"********Method: select_date(WebElement element_Location, List<WebElement> dates, Integer date_to_select) ; Assertion ---> (FAILED)*********",
				x);
		Thread.sleep(3000);
	}

	/******
	 * Method to select Month
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void select_year(WebElement element_Location, WebElement year_location, Integer year) throws Throwable {
		wait.until(ExpectedConditions.visibilityOf(element_Location));
		Select dropdown_select_Year = new Select(year_location);
		String year_text = year.toString();
		dropdown_select_Year.selectByVisibleText(year_text);
		Thread.sleep(3000);
	}

	/******
	 * Method to get Date to select in datepicker element
	 * 
	 * @return
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public String get_date_to_select(String date) throws Throwable {
		SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
		Date myDate = date_format.parse(date);
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(myDate);

		Integer day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
		String Date = day.toString();
		return Date;
	}

	/******
	 * Method to get Month to select in datepicker element
	 * 
	 * @return
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public String get_month_to_select(String date) throws Throwable {
		SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
		Date myDate = date_format.parse(date);
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(myDate);

		Integer month = calendar.get(java.util.Calendar.MONTH);
		String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		// String Month = month.toString();
		return months[month];
	}

	/******
	 * Method to get date to select in datepicker element
	 * 
	 * @return
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public String get_year_to_select(String date) throws Throwable {
		SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
		Date myDate = date_format.parse(date);
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(myDate);

		Integer year = calendar.get(java.util.Calendar.YEAR);
		String Year = year.toString();
		return Year;
	}

//																				  \\
//***************************** Operations on Alerts **************************\\
//																				    \\

	/******
	 * Method to accept alert
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void accept_alert() {
		driver.switchTo().alert().accept();
	}

	/******
	 * Method to dismiss alert
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void dismiss_alert() {
		driver.switchTo().alert().dismiss();
	}

	/******
	 * Method to verify text in alert
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void verify_text_in_alert(String expected_text) {
		String actual_text = driver.switchTo().alert().getText();
		Assert.assertEquals(expected_text, actual_text);
	}

	/******
	 * Method to send data to the alert box
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void send_data_to_alert(String data_to_enter) {

		// System.out.println("****************send_data_to_alert**********: " +
		// data_to_enter);
		Alert promptAlert = driver.switchTo().alert();
		promptAlert.sendKeys(data_to_enter);
	}

	/******
	 * Method to verify alert is displayed
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void alert_is_displayed() {

		boolean foundAlert = false;
		wait = new WebDriverWait(driver, 0 /* timeout in seconds */);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		Assert.assertTrue(
				"********Method: alert_is_displayed ; Assertion ---> (FAILED) ; Alert did not get displayed*********",
				foundAlert);
	}

//	  																		  \\
//***************************** File Upload ***********************************\\
//	    																		\\

	/******
	 * Method to upload file [Yet to be completed]
	 * 
	 * @throws Throwable
	 **** 
	 * @Creator Avik Guha
	 **/
	public void file_upload(WebElement element_Location, StringSelection ss) throws Throwable {
		element_Location.click();
	}

//	  																							\\
//***************************** Handle Multiple Tabs / Windows ***********************************\\
//																									\\

	/******
	 * Method to Switch control to next tab
	 * 
	 * @throws Throwable
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void switch_to_next_tab() throws Throwable {
		// Switch control to next tab
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
		Thread.sleep(2000);
	}

	/******
	 * Method to fetch count of all tabs
	 * 
	 * @return
	 * @throws Throwable
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public Set<String> get_count_of_all_tabs() {
		return driver.getWindowHandles();
	}

	/******
	 * Method to fetch count of all tabs This method works only if there is one
	 * parent and one child tab
	 * 
	 * @throws Throwable
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void switch_to_tab(Set<String> total_set_of_tab, String tab_name) {
		Iterator<String> iterator = total_set_of_tab.iterator();
		String parent_Id = iterator.next();
		String child_Id = iterator.next();

		switch (tab_name) {
		case "Parent":
			driver.switchTo().window(parent_Id);
			break;

		case "Child":
			driver.switchTo().window(child_Id);
			break;

		default:
			fail("************Unable to switch tab. Please enter correct tab name***********");
		}

	}

//																				\\
//***************************** Handle Frames ***********************************\\
//																				  \\

	/******
	 * Method to Switch control to frame
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void switch_to_frame(WebElement frame_location) {
		driver.switchTo().frame(frame_location);
	}

	/******
	 * Method to Switch control out of the frame to the default content
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void switch_out_of_the_frame() {
		driver.switchTo().defaultContent();
	}

//																				\\
//***************************** JDBC Database ***********************************\\
//	  																			  \\

	/******
	 * Method to set JDBC MySQL connection
	 * 
	 * @return
	 * @throws SQLException
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public Connection set_JDBC_MySQL_connection(String Host, String Port, String database_Name, String database_Id,
			String database_Password) throws SQLException {

		String host = Host;
		String port = Port;
		String database_name = database_Name;
		String database_id = database_Id;
		String database_password = database_Password;

		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database_name,
				database_id, database_password);

		return (con);
	}

	/******
	 * Method to run SQL query
	 * 
	 * @return
	 * @throws SQLException
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public String[] run_SQL_query(Connection con, String sql_Query_To_Execute, String column1, String column2)
			throws SQLException {

		Statement s = con.createStatement();

		String sql_Query = sql_Query_To_Execute;

		ResultSet rs = s.executeQuery(sql_Query);

		String string1 = null;
		String string2 = null;

		while (rs.next())

		{
			string1 = rs.getString(column1);
			string2 = rs.getString(column2);
		}

		String[] string_final = { string1, string2 };

		return string_final;
	}

//																	\\
//************************** Sikuli  ********************************\\
//	  																  \\

	/******
	 * Method To Attach File using Sikuli
	 * 
	 * @throws FindFailed
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void attach_File_using_Sikuli() throws FindFailed {

		Screen screen = new Screen();

		String folderPath = "src\\test\\resources\\sikuli_Images\\";
		String filePath = System.getProperty("user.dir") + File.separator + folderPath + "Joker&HarleyQueen.jpeg";
		String addFilePathElement_Image = System.getProperty("user.dir") + File.separator + folderPath
				+ "EnterFilePathAndName.png";
		String open_Button_Image = System.getProperty("user.dir") + File.separator + folderPath + "OpenButton.png";

		Pattern addFilePathElement = new Pattern(addFilePathElement_Image);
		Pattern open_Button = new Pattern(open_Button_Image);

		Assert.assertEquals(1, screen.type(addFilePathElement, filePath));
		Assert.assertEquals(1, screen.click(open_Button));
	}

	/******
	 * Method To Attach File using Sikuli
	 * 
	 * @throws FindFailed
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public void hover_mouse_using_Sikuli(String file_name_with_extension) throws FindFailed {

		Screen screen = new Screen();

		String folderPath = "src\\test\\resources\\sikuli_Images\\";

		String element_Image = System.getProperty("user.dir") + File.separator + folderPath + file_name_with_extension;

		Pattern open_Button = new Pattern(element_Image);

		Assert.assertEquals(1, screen.hover(open_Button));
	}

//																		 \\
//************************** Data Driven  ********************************\\
//		  															  	   \\

	/******
	 * Method To fetch data from excel using Apache Poi
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * 
	 **** 
	 * @Creator Avik Guha
	 **/
	public String fetch_data_from_excel(String excel_name, Integer sheet_index, Integer row_index, Integer column_index)
			throws Exception {

		String folderPath = "src\\test\\java\\TestData\\"; // keep excel in this folder path
		String element_Image = System.getProperty("user.dir") + File.separator + folderPath + excel_name;

		File file = new File(element_Image);
		FileInputStream fis = new FileInputStream(file); // this contains raw data from the excel
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // creating workbook
		XSSFSheet sheet = workbook.getSheetAt(sheet_index); // getting the sheet from workbook

		String cellValue = sheet.getRow(row_index).getCell(column_index).getStringCellValue(); // fetching data from the
																								// sheet
		return cellValue;
	}

}
