package PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents
{
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	By MycartPage = By.cssSelector(".items.even.ng-star-inserted");
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement CheckoutButton;
	
	
	public Boolean VerifyProductDisplay(String productname) throws InterruptedException
	{
		waitForElementToAppear(MycartPage);
		System.out.println("Cart page with items appeared");
		System.out.println(cartProducts);
	    Thread.sleep(5000);
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));
		return match;		
	}
	
    public PaymentPage clickinCheckout() throws InterruptedException
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", CheckoutButton);
		Thread.sleep(2000);		
    	CheckoutButton.click();
    	PaymentPage pg = new PaymentPage(driver);
    	return pg;
    }

    
}

	