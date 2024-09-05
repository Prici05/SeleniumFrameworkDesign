package stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PageObjects.ConfirmationPage;
import PageObjects.LoginPage;
import PageObjects.OrdersPage;
import PageObjects.PaymentPage;
import PageObjects.ProductCataloguePage;
import TestComponents.testBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefinitions extends testBase {
	
	public LoginPage login; // since the first method returns login page object
	public ProductCataloguePage productcatalogue;
	public  OrdersPage op;
	public ConfirmationPage cp;
	
	@Given("I landed on ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException
	{
		login = launchApplication();
	}
	
	@Given("^Log in with username (.+) and password (.+)$")
	public void login_with_username_and_password(String username, String password)
	{
		productcatalogue = login.LoginFunction(username,password);
	}

	
	@ When("^Add the product items (.+) to cart$")
	public void add_product_to_card(String productname)
	{
		List<WebElement> products = productcatalogue.ListOfProducts();
        productcatalogue.addTheProductToCart(productname);
	}
	
	@And("^checkout the product item (.+) and submit the order$")
	public void checkout_product(String productname) throws InterruptedException
	{
		    op = productcatalogue.clickingCartButton();
	        System.out.println("clicked cart button");
	        
	        Assert.assertTrue(op.VerifyProductDisplay(productname));
	              
	        PaymentPage pg = op.clickinCheckout();
	        
	        cp = pg.addCountry();
	        
	}
	
	@Then("I verify the display message {string}")
	public void checkmessage(String confirmationmsg)
	{
		 String ConfirmMessage = cp.getConfirmationMessage();
	        
	        Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(confirmationmsg));
	}
	
	@Then("verify the display message {string}")
	public void errormessage(String confirmationmsg)
	{
		login.getErrorMessage();
		Assert.assertEquals(confirmationmsg, login.getErrorMessage());
	}
}
