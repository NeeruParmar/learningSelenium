package com.neeru.learningSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationPage {
	WebDriver wd;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");

		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wd.get("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation");

		wd.manage().window().maximize();
	}

	@Test
	public void createAccount() {

		WebElement email = wd.findElement(By.id("email_create"));
		email.sendKeys("kav123@gmail.com");

		WebElement createAccBtn = wd.findElement(By.id("SubmitCreate"));
		createAccBtn.submit();

		wd.findElement(By.cssSelector("label[for='id_gender2']")).click();

		WebElement firstName = wd.findElement(By.cssSelector("input[name='customer_firstname']"));
		firstName.sendKeys("kavya");

		WebElement lastName = wd.findElement(By.cssSelector("input[name='customer_lastname']"));
		lastName.sendKeys("Pathak");

		WebElement password = wd.findElement(By.cssSelector("input[data-validate ='isPasswd']"));
		password.sendKeys("kavya123");

		WebElement dateSelect = wd.findElement(By.id("days"));

		Select selectA = new Select(dateSelect);

		selectA.selectByIndex(2);

		WebElement monthSelect = wd.findElement(By.id("months"));

		Select selectB = new Select(monthSelect);

		selectB.selectByIndex(3);

		WebElement yearSelect = wd.findElement(By.id("years"));

		Select selectC = new Select(yearSelect);

		selectC.selectByValue("2000");

		WebElement companyName = wd.findElement(By.cssSelector("input[name='company']"));
		companyName.sendKeys("Wipro");

		WebElement addresslineOne = wd.findElement(By.cssSelector("input[name=address1]"));
		addresslineOne.sendKeys("House Number 198");

		WebElement addresslineTwo = wd.findElement(By.cssSelector("input[name=address2]"));
		addresslineTwo.sendKeys("Brampton");

		WebElement cityName = wd.findElement(By.cssSelector("input[name=city]"));
		cityName.sendKeys("Brampton");

		WebElement state = wd.findElement(By.id("id_state"));

		 Select selectState = new Select(state);

		selectState.selectByIndex(2);

		WebElement pinCode = wd.findElement(By.cssSelector("input[name='postcode']"));
		pinCode.sendKeys("909090");
		
		WebElement additionalInfo = wd.findElement(By.cssSelector("textarea[name='other']"));
		additionalInfo.sendKeys("notes");

		WebElement homePhone = wd.findElement(By.cssSelector("input[name= phone]"));
		homePhone.sendKeys("101001010");

		WebElement mobilePhone = wd.findElement(By.cssSelector("input[name= phone_mobile]"));
		mobilePhone.sendKeys("101111111");
		
		WebElement register = wd.findElement(By.cssSelector("button[name=submitAccount]"));
		
		register.submit();
	}
		@AfterMethod
		public void tearDown() {
		wd.quit();

	}
}
