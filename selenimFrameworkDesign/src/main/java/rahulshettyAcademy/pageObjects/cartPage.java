package rahulshettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyAcademy.AbstractComponent.AbstractComponent;

public class cartPage extends AbstractComponent {

	WebDriver driver;
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartPro;

	@FindBy(css=".totalRow button")
	WebElement chkOut;
	
	public List<WebElement> cartProducts() {
		return cartPro;
	}
	public boolean ProductMatch(String proName) {
	boolean match = cartProducts().stream().anyMatch(Product->Product.getText().equalsIgnoreCase(proName));
	return match;
	}
	public Checkoutpage Checkout() {
		chkOut.click();
		return new Checkoutpage(driver);
	}

}
