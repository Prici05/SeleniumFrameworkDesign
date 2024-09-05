package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import resources.ExtentReportNG;

public class Listeners extends testBase implements ITestListener
{
	ExtentTest test;
	ExtentReports reports = ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> tlextentTest = new ThreadLocal<ExtentTest>(); 
//	 to avoid synchronisation issues while running many TC locally
//	So using thread local you will make this object thread safe. So basically saying thread safe means no matter even if you run concurrently, each object creation
//have its own thread so it won't interrupt the other overriding variable.
	WebDriver driver;

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return ITestListener.super.isEnabled();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = reports.createTest(result.getMethod().getMethodName());
		tlextentTest.set(test); // unique threadid will be created for each tests it knows which test is actually pushing this into that set.
	//So when this test is pushing this object to thread local, it also picks up that test unique ID. It captures the unique thread ID of that test case because every Java class, every instance of runninghave its own thread.
	}
	

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		tlextentTest.get().log(Status.PASS, "Case successfully passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String filepath = null;
		// TODO Auto-generated method stub
		test.fail("Case failed");
		tlextentTest.get().fail(result.getThrowable());
		
		
		// for giving life to the driver defined in the getScreenshot method
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			filepath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tlextentTest.get().addMedia(MediaEntityBuilder.createScreenCaptureFromPath(filepath).build());
//		test.addScreenCaptureFromBase64String(filepath, result.getMethod().getMethodName());
//		Issue: When I opened extent report, I saw the "base64 img" tag appeared in the failed test case. 
//		But when I opened it, it did not display the screenshot.
//		Solution: I found out that base64 image wont work well with offline image. After reading the 
//		documentation and experimenting the method, I found that we can use the addMedia() method with
//		MediaEntityBuilder.createScreenCaptureFromPath() can perform the action to attach the screenshot to the report correctly. 
//		Remember to use build() in when providing the path. See the example below:
//		Please replace the line
//		test.addScreenCaptureFromBase64String(filePath, result.getMethod().getMethodName());
//		To
//		test.addMedia(MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
//		Extent Reports version: 5.1.2
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		reports.flush();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	
	

}
