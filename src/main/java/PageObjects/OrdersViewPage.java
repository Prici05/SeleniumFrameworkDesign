package PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponents;

public class OrdersViewPage extends AbstractComponents
{
	
	WebDriver driver;
	
	public OrdersViewPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> products;
	
	
	public Boolean VerifyOrderDisplay(String productname) throws InterruptedException
	{
		Boolean match = products.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));
		return match;		
	}
	
 
    
}

	