package rahulshettyAcademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyAcademy.TestComponents.BaseTests;
import rahulshettyAcademy.pageObjects.Checkoutpage;
import rahulshettyAcademy.pageObjects.LandingPage;
import rahulshettyAcademy.pageObjects.ProductCart;
import rahulshettyAcademy.pageObjects.cartPage;
import rahulshettyAcademy.pageObjects.confimationPage;

public class ErrorValidation extends BaseTests {

	@Test (groups = {"erorHandling"},retryAnalyzer = rahulshettyAcademy.TestComponents.Retry.class)
	public void errorval() throws IOException,InterruptedException {
		
		String pName="ZARA COAT 3";
		ProductCart pc=lp.loginapp("vikas@gmail.com","Vika3");
	Assert.assertEquals("Incorrect email or password.", lp.geterrorMsg());

	}
	
	@Test
	public void submitorder() throws IOException {
		
		// TODO Auto-generated method stub
		String pName="ZARA COAT 3";
		ProductCart pc=lp.loginapp("vikas@gmail.com","Vikas123");
	List<WebElement> pts = pc.getProductList();
	pc.addToCart(pName);
	cartPage crtp = pc.goToCart();
	boolean match = crtp.ProductMatch(pName);
	Assert.assertTrue(match);
	}

}
