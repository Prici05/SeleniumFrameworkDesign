package SeleniumFramework.Ecommerce;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class StandAloneTest {
	ExtentReports reports;
	
	@BeforeTest
	public void ExtentReports()
	{
		String filepath = System.getProperty("user.dir")+"//reports//testresults.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
		reporter.config().setReportName("Selenium StandAlone TestResults");
		reporter.config().setDocumentTitle("Automation Test Results");
		
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Arockia Pricilla S");
		
	}

	@Test
	public void StandAloneTest() throws InterruptedException {
		// TODO Auto-generated method stub
		
		
        ExtentTest test = reports.createTest("Ecommerce StandALone Test");
		System.setProperty("webdriver.chrome.driver","C:/Users/arockia.p.stephen/Desktop/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/client/");
		String productname = "IPHONE 13 PRO";
		
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("arockiapricilla1999@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pricilla@05");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink='/dashboard/']")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement cartprod = products.stream().filter(prod->prod.findElement(By.cssSelector("div h5")).getText().equals(productname)).findFirst().orElse(null);
		
		cartprod.findElement(By.cssSelector(".btn.w-10.rounded")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='cartSection'] h3")).getText(), productname);
		
		WebElement element = driver.findElement(By.cssSelector(".totalRow button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//span/i)[2]")).click();
		Thread.sleep(3000);
		
		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
		js.executeScript("arguments[0].scrollIntoView(true);", submit);
		driver.findElement(By.cssSelector(".action__submit")).click();
		System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		test.fail("Feature not applicable");
		reports.flush();
	}

}


