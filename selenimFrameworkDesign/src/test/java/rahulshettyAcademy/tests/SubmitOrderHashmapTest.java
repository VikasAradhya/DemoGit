package rahulshettyAcademy.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
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

public class SubmitOrderHashmapTest extends BaseTests {

	@Test(dataProvider = "getdata",groups = {"Purchase"})
	public void submitorder(HashMap<String,String> input) throws IOException {
		
		// TODO Auto-generated method stub
		//String pName="ZARA COAT 3";
		ProductCart pc=lp.loginapp(input.get("Email"),input.get("pwd"));
	List<WebElement> pts = pc.getProductList();
	pc.addToCart(input.get("pName"));
	cartPage crtp = pc.goToCart();
	boolean match = crtp.ProductMatch(input.get("pName"));
	Assert.assertTrue(match);
	Checkoutpage cp=crtp.Checkout();
	cp.selectCountry();
	confimationPage crnPage=cp.placeorder();
	String msg = crnPage.confirmmsg();
	Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@DataProvider
	public Object[][] getdata() throws IOException 
	{
	//HashMap<String, String> map = new HashMap<String,String>();
//		map.put("Email","vikas@gmail.com");
//		map.put("pwd","Vikas123" );
//		map.put("pName","ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String,String>();
//		map1.put("Email","vikas@gmail.com");
//		map1.put("pwd","Vikas123" );
//		map1.put("pName","ADIDAS ORIGINAL");
		
 List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//rahulshettyAcademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
