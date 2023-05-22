 package rahulshettyAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyAcademy.pageObjects.LandingPage;

public class BaseTests {
	protected WebDriver driver;
	public LandingPage lp;
	public WebDriver initialzeDriver() throws IOException {
		
		Properties pp = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyAcademy//resources//GlobalData.properties");
		pp.load(fis);
		String BrowserName=System.getProperty("browser")!=null?System.getProperty("browser"):pp.getProperty("browser");
		//String BrowserName = pp.getProperty("browser");
		
		if(BrowserName.contains("chrome")){
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		if(BrowserName.contains("headless")) {
		option.addArguments("headless");
		}
		 driver = new ChromeDriver(option);
		 driver.manage().window().setSize(new Dimension(1440,990));//full screen
	
	}else if (BrowserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String,String>> getJsonData(String filepath) throws IOException {
		//read json to String
		String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	//String to hashmap jacksonDtabind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
	}
	public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\ScreenShot\\"+TestCaseName+".jpg");
		FileUtils.copyFile(src,dest );
		return System.getProperty("user.dir")+"\\ScreenShot\\"+TestCaseName+".jpg";
	}
	
	@BeforeMethod
	public LandingPage launchapp() throws IOException {
		driver=initialzeDriver();
		 lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	public void closeBrowser() {
		driver.quit();
	}

}
