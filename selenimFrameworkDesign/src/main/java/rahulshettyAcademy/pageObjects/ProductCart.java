package rahulshettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyAcademy.AbstractComponent.AbstractComponent;

public class ProductCart extends AbstractComponent
{
	WebDriver driver;

public ProductCart(WebDriver driver) {
	// TODO Auto-generated constructor stub
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
@FindBy(css=".mb-3")
List<WebElement> products;

@FindBy(css="#toast-container")
WebElement spinner;



By productsBy=By.cssSelector(".mb-3");
By addTocart=By.cssSelector(".card-body button:last-of-type");
By ToastAppear=By.cssSelector("#toast-container");

public List<WebElement> getProductList() {
	
	waitForElementToAppear(productsBy);
	return products;
}

public WebElement getProductByName(String ProductName) {
	WebElement prod = getProductList().stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
	return prod;
}
public void addToCart(String ProductName) {
	WebElement prod = getProductByName(ProductName);
	prod.findElement(addTocart).click();
	waitForElementToAppear(ToastAppear);
	waitForElementToDisappear(spinner);
	
}



}
