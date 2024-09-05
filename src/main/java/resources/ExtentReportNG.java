package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject()
	{
		String filepath = System.getProperty("user.dir")+"//reports//testresults.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
		reporter.config().setReportName("Selenium StandAlone TestResults");
		reporter.config().setDocumentTitle("Automation Test Results");
		
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Arockia Pricilla S");
		
		return reports;
	}

}
