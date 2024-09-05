package AbstractComponents;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.OrdersViewPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(css = "button[routerlink='/dashboard/myorders']" )
	WebElement OrdersButton;
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public OrdersViewPage goToOrdersPage()
	{
		OrdersButton.click();
		OrdersViewPage orders = new OrdersViewPage(driver);
		return orders;
	}

	
	public void waitForElementToAppear(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
	}
	
	public void waitForWebElementToAppear(WebElement WebElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(WebElement));
	}
	
	public void waitForElementToDisAppear(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			
	}	

}
