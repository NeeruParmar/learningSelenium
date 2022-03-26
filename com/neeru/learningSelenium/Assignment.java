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
		WebElement nameConfirmation = wd.findElement(By.cssSelector("a[class='account']"));
		Assert.assertEquals(nameConfirmation.getText(), "Kavya Singh", "Not Logged In");
		System.out.println("Welcome to your Account");

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
		WebElement quantity = wd.findElement(By.cssSelector("i[class='icon-plus']"));
		quantity.click();

		// selecting size by using selectByValue
		WebElement sizeSelect = wd.findElement(By.id("group_1"));

		Select select = new Select(sizeSelect);
		select.selectByValue("2");

		WebElement addToCart = wd.findElement(By.cssSelector("button[name='Submit']"));
		addToCart.click();
		
		 wd.switchTo().defaultContent();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Asserting the order confirmation, product, qty
		WebElement orderConfirmationMessage=wd.findElement(By.className("icon-ok"));
		Assert.assertEquals("Product successfully added to your shopping cart","Product successfully added to your shopping cart", "not added correctly");
		System.out.println("Product selected correctly");
		
		WebElement productName = wd.findElement(By.id("layer_cart_product_title"));
		Assert.assertEquals(productName.getText(), "Faded Short Sleeve T-shirts", "Wrong prduct selected");
		System.out.println("product description is correct");

	   WebElement quantityConfirmation = wd.findElement(By.cssSelector("span[id='layer_cart_product_quantity']"));
	   Assert.assertEquals(quantity.getText(), "2");
	   System.out.println("Corrected Quantity selected");
	   
	  
	   WebElement verifyOrderDetailscheckOut = wd.findElement(By.cssSelector("[title='Proceed to checkout']"));
		verifyOrderDetailscheckOut.click();
			
		//Asserting total cost
		  WebElement totalPrice=wd.findElement(By.cssSelector("span[id='total_price']"));
		  Assert.assertEquals(totalPrice.getText(), "36.42", "cost not accurate");
		  System.out.println("total amt is accurate ");
		  
		  
		
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
		WebElement paymentMethod=wd.findElement(By.className("page-subheading"));
		Assert.assertEquals("BANK-WIRE PAYMENT", "BANK-WIRE PAYMENT", "incorrect info");
		System.out.println("you have choosen to pay by the bank wire");

		// Confirming the order and asserting with text confirmation
		WebElement confirmMyOrder = wd
				.findElement(By.cssSelector("button[class='button btn btn-default button-medium']"));
		confirmMyOrder.click();
		
		WebElement finalConfirmation=wd.findElement(By.className("cheque-indent"));
		Assert.assertEquals("Your order on My Store is complete", "Your order on My Store is complete", "Order not Complete");
		System.out.println("order Complete go back to place another order ");
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();

	}

}
