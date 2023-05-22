package rahulshettyAcademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyAcademy.TestComponents.BaseTests;
import rahulshettyAcademy.pageObjects.Checkoutpage;
import rahulshettyAcademy.pageObjects.LandingPage;
import rahulshettyAcademy.pageObjects.ProductCart;
import rahulshettyAcademy.pageObjects.cartPage;
import rahulshettyAcademy.pageObjects.confimationPage;


public class stepDefinitionImpl extends BaseTests {
	public LandingPage landingpage;
	public ProductCart productCart;
	public confimationPage crnPage;
	
@Given("I landed on Ecommerce page")
public void I_landed_on_Ecommerce_page() throws IOException {
	landingpage=launchapp();
}

@Given("^Logged in with username(.+) and password(.+)$ ")
public void Logged_in_with_username_and_password(String Email, String pwd) {
	productCart=lp.loginapp(Email, pwd);
}
//@Given("^Logged in with username (.+) and password (.+) $")
//public void logged_in_with_username_and_password(String name, String password) {
//	productCart=lp.loginapp(name, password);
//}
@When("^I add product (.+) to cart$")
public void I_add_product_to_cart(String pName) {
	List<WebElement> pts = productCart.getProductList();
	productCart.addToCart(pName);
}
@And("^Checkout(.+) and submit the order$")
public void Checkout_and_submit_the_order(String pName) {
	cartPage crtp = productCart.goToCart();
	boolean match = crtp.ProductMatch(pName);
	Assert.assertTrue(match);
	Checkoutpage cp=crtp.Checkout();
	cp.selectCountry();
	 crnPage=cp.placeorder();
}
@Then("{string} message is to be displayed on confirmation page")
public void message_is_to_be_displayed_on_confirmation_page(String string) {
	String msg = crnPage.confirmmsg();
	Assert.assertTrue(msg.equalsIgnoreCase(string));
}
}
