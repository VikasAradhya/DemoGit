package rahulshettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyAcademy.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent 
{
	WebDriver driver;

public LandingPage(WebDriver driver) {
	// TODO Auto-generated constructor stub
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
//List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
@FindBy(id="userEmail")
WebElement username;

@FindBy(id="userPassword")
WebElement password;

@FindBy(xpath="//input[@id='login']")
WebElement login;

//.ng-tns-c4-2.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
@FindBy(css="[class*='flyInOut']")
WebElement errmsg;

public ProductCart loginapp(String Email,String pass) {
	username.sendKeys(Email);
	password.sendKeys(pass);
	login.click();
	return new ProductCart(driver);
}

public String geterrorMsg() {
	waitForWebElementToAppear(errmsg);
	return errmsg.getText();
	
	
}

public void goTo() {
	driver.get("https://rahulshettyacademy.com/client/");
	
}
}
