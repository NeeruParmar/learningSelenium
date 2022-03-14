package com.neeru.learningSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstSeleniumScript {

	WebDriver webDriver;

	@BeforeMethod
	public void setUp() {
		// setting up the chrome driver path

		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		// Creating Ref. variable and initializing with Chrome driver
		webDriver = new ChromeDriver();
		// Get the URL of the page of the application need to be tested 
		webDriver.get("http://automationpractice.com/index.php?controller=contact");
		//MAximize the browser 
		webDriver.manage().window().maximize();
	}

	@Test
	public void sendConatactPage() {
		WebElement subject = webDriver.findElement(By.cssSelector("#id_contact"));
	//object created for select, we do this in case there is a dropdown and one option need to be selected
	//three types: 1)select by index 2)Selct By value  3) Select by Visible Text

		Select select = new Select(subject);
		//select by index
		select.selectByIndex(1);

		WebElement email = webDriver.findElement(By.cssSelector("input[id='email']"));
		email.sendKeys("neer@gmail.com");
		WebElement order = webDriver.findElement(By.cssSelector("input[id='id_order']"));
		order.sendKeys("120202");

		WebElement message = webDriver.findElement(By.cssSelector("#message"));
		message.sendKeys("my order have all details");

		WebElement submitMess = webDriver.findElement(By.cssSelector("#submitMessage"));
		submitMess.submit();

	}
//using quit method to close all the tabs opened for that instance (all tabs for same browser)
	@AfterMethod
	public void tearDown() {
		webDriver.quit();
	}

}
