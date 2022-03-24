package com.neeru.learningSelenium;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment {

	WebDriver wd;
	Actions action;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");

		wd = new ChromeDriver();

		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wd.get("http://automationpractice.com/index.php?controller=authentication&back=history");

		wd.manage().window().maximize();

	}

	@Test
	public void choosingAndBuyingdressTest() {
		// Step one LOGIN

		WebElement loginEmail = wd.findElement(By.cssSelector("input[name='email']"));
		loginEmail.sendKeys("kav123@gmail.com");

		WebElement loginPassword = wd.findElement(By.cssSelector("input[name='passwd']"));
		loginPassword.sendKeys("new123");

		// click on submit
		WebElement loginBtn = wd.findElement(By.id("SubmitLogin"));
		loginBtn.click();

		// Step two Asserting the name
		assertEquals("Kavya Singh", "Kavya Singh");
		System.out.println("Customer Name matched");

		// Step Three
		action = new Actions(wd);
		WebElement selectWomanTab = wd.findElement(By.xpath("//a[(text()='Women')]"));
		action.click(selectWomanTab).perform();

		// Step four: Quick View of the selected item
		WebElement quickview = wd.findElement(By.cssSelector("div [class = 'product-image-container']"));
		quickview.click();

		// Switching to iframe
		wd.switchTo().frame(0);

		// selecting quantity
		WebElement quantity = wd.findElement(By.className("icon-plus"));
		quantity.click();

		// selecting size by using selectByValue
		WebElement sizeSelect = wd.findElement(By.id("group_1"));

		Select select = new Select(sizeSelect);

		select.selectByValue("2");

		// adding to cart after adding qty and size
		WebElement addToCart = wd.findElement(By.cssSelector("button[name='Submit']"));
		addToCart.click();

		// Switching back to default page
		wd.switchTo().defaultContent();

		// Asserting the shopping cart: product, qty
		Assert.assertEquals("Product successfully added to your Shopping List",
				"Product successfully added to your Shopping List");
		Assert.assertEquals("Faded Short Sleeve T-shirts", "Faded Short Sleeve T-shirts");
		System.out.println("Correct Product selected");

		Assert.assertEquals("Quantity 2", "Quantity 2");
		System.out.println("Correct number of items selected");

		WebElement verifyOrderDetailscheckOut = wd.findElement(By.cssSelector("[title='Proceed to checkout']"));
		verifyOrderDetailscheckOut.click();

		Assert.assertEquals("35.02", "35.02");
		System.out.println("total amt is accuarte without tax");

		// aser of total need to added

		WebElement verifyOrderCostcheckOut = wd
				.findElement(By.cssSelector("div [class='button btn btn-default standard-checkout button-medium']"));
		verifyOrderCostcheckOut.click();

		// Adding additional comments
		WebElement addNewAddComment = wd.findElement(By.cssSelector("textarea[class='form-control']"));
		addNewAddComment.sendKeys("please ensure double  packing of both items");

		// after adding comments proceeding with checkout
		WebElement verifyAddAndCheckout = wd.findElement(By.cssSelector("button[name='processAddress']"));
		verifyAddAndCheckout.click();

		// checking box to select Terms and Service
		WebElement checkTermsBox = wd.findElement(By.cssSelector("input[type='checkbox']"));
		checkTermsBox.click();

		// after accepting terms and service proceeding with checkout
		WebElement acceptTermsAndProceed = wd.findElement(By.cssSelector("button[name='processCarrier']"));
		acceptTermsAndProceed.click();

		// Selecting payment method
		WebElement paymentByBankWire = wd.findElement(By.cssSelector("[title='Pay by bank wire']"));
		paymentByBankWire.click();

		// Asserting payment method Text
		Assert.assertEquals("BANK-WIRE PAYMENT", "BANK-WIRE PAYMENT");
		System.out.println("you have choosen to pay by the bank wire");

		// Confirming the order and asserting with text confirmation
		WebElement confirmMyOrder = wd
				.findElement(By.cssSelector("button[class='button btn btn-default button-medium']"));
		confirmMyOrder.click();
		Assert.assertEquals("Your order on My Store is complete", "Your order on My Store is complete");
		System.out.println("order Complete go back to place another order ");
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();

	}

}
