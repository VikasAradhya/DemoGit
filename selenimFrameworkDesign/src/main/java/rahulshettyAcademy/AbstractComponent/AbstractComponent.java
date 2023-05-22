package rahulshettyAcademy.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyAcademy.pageObjects.cartPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartbtn;
	
	public void waitForElementToAppear(By findby) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForWebElementToAppear(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
		wait.until(ExpectedConditions.visibilityOf(findby));
		}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public  cartPage goToCart() {
		cartbtn.click();
		cartPage crtp = new cartPage(driver);
		return crtp;
	}
	
}
