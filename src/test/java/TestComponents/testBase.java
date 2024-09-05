package TestComponents;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
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
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LoginPage;

public class testBase {
	public WebDriver driver;
	public LoginPage login;

	public  WebDriver initializeDriver() throws IOException {
		
		
		
//		Properties class will help us to invoke all the global properties set on that file
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/GlobalData.properties");
//		this will convert file into input stream format
		prop.load(fis); // It will load all the global data set in the global file it accepts only input stream file format so we need to use another class
		
		//In order to get the global properties from the maven commands in the terminal i use below logic
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
//		maven command - mvn test-Dbrowser
//		String browserName = prop.getProperty("browser");
		
		
		if (browserName.contains("chrome"))
		{
			   ChromeOptions option = new ChromeOptions();
//			   WebDriverManager.chromedriver().setup();
			   if(browserName.contains("headless"))
			   {
				   option.addArguments("headless");
			   }
			   driver = new ChromeDriver(option);
			   driver.manage().window().setSize(new Dimension(1990,400)); //this will run in full max mode
//			   Headless mode - we will not see invoking chrome browser in our local machine it will be running in backend

		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	
	public String getScreenShot(String Testcasename, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;  // This driver needs life so we have written steps in listeners
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+ "\\reports\\" +Testcasename+ ".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+ "\\reports\\" +Testcasename+ ".png"; 
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(Filepath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Datbind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	}
	

	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException
	{
		WebDriver driver = initializeDriver();
		login= new LoginPage(driver);
		login.goToURL();
		return login;
	}
	

	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
}

