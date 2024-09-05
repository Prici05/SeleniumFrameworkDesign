package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents
{
	

	WebDriver driver;
	public ProductCataloguePage(WebDriver driver)  
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".col-lg-4")   
	List<WebElement> products;
	
	@FindBy(css = "button[routerlink='/dashboard/cart']" )
	WebElement CartItems;
	
	By addToCart = By.cssSelector(".btn.w-10.rounded");
	By toastmessage = By.cssSelector(".toast-container");
	By spinner = By.cssSelector(".ng-animating");
	
	
	
	By homepageelement = By.cssSelector("button[routerlink='/dashboard/']");
	
	public List<WebElement> ListOfProducts()
	{
		waitForElementToAppear(homepageelement);
		return products;
	}
	
	public WebElement getCartProduct(String productname)
	{
		WebElement cartprod = ListOfProducts().stream().filter(prod->prod.findElement(By.cssSelector("div h5")).getText().equals(productname)).findFirst().orElse(null);
        return cartprod;
	}
	
	public void addTheProductToCart(String productname)
	{
		WebElement productCart = getCartProduct(productname);
		productCart.findElement(addToCart).click();	
		System.out.println("added the product to cart");
		waitForElementToAppear(toastmessage);
		waitForElementToDisAppear(spinner);
	}
	
    public OrdersPage clickingCartButton()
    {	
		CartItems.click();
		OrdersPage op = new OrdersPage(driver);
		return op;
    }
	
	

	
	
}
	