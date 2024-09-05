package SeleniumFramework.Ecommerce;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestComponents.testBase;
import PageObjects.LoginPage;
import PageObjects.OrdersPage;
import PageObjects.PaymentPage;
import PageObjects.ProductCataloguePage;

public class ErrorValidations extends testBase
{

      @Test(groups={"ErrorValidations"})
      public void LoginErrorValidations() throws IOException, InterruptedException
{
		String productname = "IPHONE 13 PRO";
		
		login.LoginFunction("arockiapricilla21999@gmail.com", "Pricill1a@05");
		login.getErrorMessage();
		Assert.assertEquals("Incorrect email or// password.", login.getErrorMessage());
	}
      
      @Test
  	public void ProductErrorValidation() throws IOException, InterruptedException
  	{

  		String productName = "ZARA COAT 3";
  		ProductCataloguePage productCatalogue = login.LoginFunction("arockiapricilla1999@gmail.com", "Pricilla@05");
  	    List<WebElement> products = productCatalogue.ListOfProducts();
        productCatalogue.addTheProductToCart(productName);
        OrdersPage op = productCatalogue.clickingCartButton();
        System.out.println("clicked cart button");
        Assert.assertFalse(op.VerifyProductDisplay("Prici"));
  		
}
}

