package rahulshettyAcademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyAcademy.AbstractComponent.AbstractComponent;

public class Checkoutpage extends AbstractComponent {

	WebDriver driver;
	public Checkoutpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="input[placeholder*='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement countrybtn;
	
	@FindBy(css=".action__submit")
	WebElement plOrder;
	
	By countryAppear=By.cssSelector(".ta-results");
	
	public void selectCountry() {
		Actions a = new Actions(driver);
		a.sendKeys(Country, "india").build().perform();
		waitForElementToAppear(countryAppear);
		countrybtn.click();
	}
	public  confimationPage placeorder() {
		plOrder.click();
		return new confimationPage(driver);
	}

}
