package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {

	WebDriver driver;// This driver has no life so we need to create constructor this is the current
						// class driver

	public LoginPage(WebDriver driver) // The constructor should have same name as the classname This constructor
										// function will be first executed
	{
		super(driver);
//		this super function is to give its driver object to the parent class since parent class has no idea of driver object
		this.driver = driver;
//		this.driver ---> refers to the local created driver in the current class
//		driver = driver object that we are obtaining from creating the classobject and then passing the driver object in the mainclass and this has life only within this method so that only we are assigning this driver object to the locally created driver variable object

		PageFactory.initElements(driver, this);
//		this refers to the current class driver
	}

//	Now we are going to use page factory model
//	WebElement email = driver.findElement(By.id("userEmail"));
	@FindBy(id = "userEmail")
	// this function constructs the above selenium function at the runtime and then
//	it does its operation but it does not have a knowledge about driver so we are including another line in the constructor that is init function
	WebElement email;

//	WebElement password = driver.findElement(By.id("userPassword"));
	@FindBy(id = "userPassword")
	WebElement password;

//	WebElement loginbutton = driver.findElement(By.id("login"));
	@FindBy(id = "login")
	WebElement loginbutton;

	@FindBy(id = "login")
	WebElement submit;
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public void goToURL() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public ProductCataloguePage LoginFunction(String emailid, String passworde) {
		email.sendKeys(emailid);
		password.sendKeys(passworde);
		loginbutton.click();
		ProductCataloguePage productcatalogue = new ProductCataloguePage(driver);
		return productcatalogue;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
