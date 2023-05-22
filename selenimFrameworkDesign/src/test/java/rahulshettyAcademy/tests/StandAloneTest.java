package rahulshettyAcademy.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		String pName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
	
	//launch the URL
	driver.get("https://rahulshettyacademy.com/client/");
	//Enter login credentials
	driver.findElement(By.id("userEmail")).sendKeys("vikas@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Vikas123");
	driver.findElement(By.xpath("//input[@id='login']")).click();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	//search the add to cart element and click
	List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod = Products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(pName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	List<WebElement> cartproducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	boolean match = cartproducts.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(pName));
	Assert.assertTrue(match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	Actions a = new Actions(driver);
	
	a.sendKeys(driver.findElement(By.cssSelector("input[placeholder*='Select Country']")),"india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	String cnmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(cnmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.quit();
	
	}

}
