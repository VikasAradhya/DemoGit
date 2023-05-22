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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyAcademy.TestComponents.BaseTests;
import rahulshettyAcademy.pageObjects.Checkoutpage;
import rahulshettyAcademy.pageObjects.LandingPage;
import rahulshettyAcademy.pageObjects.ProductCart;
import rahulshettyAcademy.pageObjects.cartPage;
import rahulshettyAcademy.pageObjects.confimationPage;

public class SubmitOrderTest extends BaseTests {

	@Test(dataProvider = "getdata",groups = {"Purchase"})
	public void submitorder(String Email, String pwd,String pName) throws IOException {
		
		// TODO Auto-generated method stub
		//String pName="ZARA COAT 3";
		ProductCart pc=lp.loginapp(Email,pwd);
	List<WebElement> pts = pc.getProductList();
	pc.addToCart(pName);
	cartPage crtp = pc.goToCart();
	boolean match = crtp.ProductMatch(pName);
	Assert.assertTrue(match);
	Checkoutpage cp=crtp.Checkout();
	cp.selectCountry();
	confimationPage crnPage=cp.placeorder();
	String msg = crnPage.confirmmsg();
	Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	@DataProvider
	public Object[][] getdata() 
	{
		return new Object[][] {{"vikas@gmail.com","Vikas123","ZARA COAT 3"},{"vikas@gmail.com","Vikas123","ZARA COAT 3"}};
	}

}
