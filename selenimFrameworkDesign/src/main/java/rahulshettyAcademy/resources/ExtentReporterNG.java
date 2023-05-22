package rahulshettyAcademy.resources;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReporterObject() {
			String path = System.getProperty("user.dir")+"\\reports\\index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			reporter.config().setReportName("Web Auto");
			reporter.config().setDocumentTitle("Test Results");
			
			ExtentReports extent = new  ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Vikas Aradhya");
			return extent;
	}

}
