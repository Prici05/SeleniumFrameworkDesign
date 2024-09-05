package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents
{
	
	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(css="button[type='button']:nth-child(3)")
	WebElement selectIndiaCountry;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement Submitbutton;
	
	By countryInputs = By.cssSelector(".ta-results");
	
	public ConfirmationPage addCountry()
	{
		Actions a = new Actions(driver);
		a.sendKeys(Country, "ind").build().perform();
		waitForElementToAppear(countryInputs);
		selectIndiaCountry.click();
		Submitbutton.click();
		return new ConfirmationPage(driver);

	}

}

	
