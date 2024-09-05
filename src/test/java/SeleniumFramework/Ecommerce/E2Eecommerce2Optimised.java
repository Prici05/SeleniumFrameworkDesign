package SeleniumFramework.Ecommerce;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.ConfirmationPage;
import PageObjects.LoginPage;
import PageObjects.OrdersPage;
import PageObjects.OrdersViewPage;
import PageObjects.PaymentPage;
import PageObjects.ProductCataloguePage;
import TestComponents.testBase;

public class E2Eecommerce2Optimised extends testBase
{
//	String productname = "IPHONE 13 PRO";

      @Test(dataProvider="getData", groups= {"Purchase"},retryAnalyzer=TestComponents.Retry.class)
      public void submitOrderTest(HashMap <String, String> input) throws IOException, InterruptedException
{
		
		ProductCataloguePage productcatalogue = login.LoginFunction(input.get("email"), input.get("pass"));
        
        List<WebElement> products = productcatalogue.ListOfProducts();
        productcatalogue.addTheProductToCart(input.get("prod"));
        
        OrdersPage op = productcatalogue.clickingCartButton();
        System.out.println("clicked cart button");
        
        Assert.assertTrue(op.VerifyProductDisplay(input.get("prod")));
              
        PaymentPage pg = op.clickinCheckout();
        
        ConfirmationPage cp = pg.addCountry();
        
        String ConfirmMessage = cp.getConfirmationMessage();
        
        Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


	}
      @Test(dependsOnMethods= {"submitOrderTest"})  //This test will run only if submit order test has ran
      public void verifyOrdersPage() throws InterruptedException
      {
    	  ProductCataloguePage productcatalogue = login.LoginFunction("arockiapricilla1999@gmail.com", "Pricilla@05");
    	  OrdersViewPage op = productcatalogue.goToOrdersPage();
//    	  Assert.assertTrue(op.VerifyOrderDisplay(productname));
      }
      
//      @DataProvider
////      data provider also returns hashmaps
//      public Object[][] getData()
//      {
//    	  return new Object[][] {{"arockiapricilla1999@gmail.com","Pricilla@05", "IPHONE 13 PRO"},
//    		  {"anshika@gmail.com", "Iamking@000", "ZARA COAT 3"}};
//      }
      
      @DataProvider
      public Object[][] getData() throws IOException
      {
    	  
    	  List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//TestData//Data.json");
    		return new Object[][]  {{data.get(0)}, {data.get(1) } };

//    	  HashMap<String, String> map = new HashMap<String, String>();
//    	  map.put("email", "arockiapricilla1999@gmail.com");
//    	  map.put("pass", "Pricilla@05");
//    	  map.put("prod", "IPHONE 13 PRO");
//    	  
//    	  HashMap<String, String> map1 = new HashMap<String, String>();
//    	  map1.put("email", "anshika@gmail.com");
//    	  map1.put("pass", "Iamking@000");
//    	  map1.put("prod", "ZARA COAT 3");
//    	  
//    	  return new Object [][]{{map}, {map1}};
    	  
    	  
      }
      

}

//parallel = tests ---> all the tests will be run parallely
//parallel = methods ----> all the methods in the same java class file will run parallely
//thread-count - 5 ---> max it will allows 5 methods to run parallely
