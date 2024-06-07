package rahulshettyacademy.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CheckingCart;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
@Test (dataProvider = "getData" , groups = {"Purchase"})
public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
				
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> productsCard = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CheckingCart cart =productCatalogue.goToCartPage();
		Boolean match =cart.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cart.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String expectedMsg = confirmationPage.verifyConfirmationMsg();
		assertEquals("THANKYOU FOR THE ORDER.",expectedMsg);
	}

@Test(dependsOnMethods = {"submitOrder()"})
public void orderHistory() {
	
	ProductCatalogue productCatalogue = landingPage.loginApplication("mayanknegi0017@gmail.com","Nifty@6306");
	OrderPage orderPage=productCatalogue.goToOrdersPage();
	Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
}



@DataProvider
public Object [][] getData() throws IOException{
	
	/*HashMap<String,String> map =  new HashMap<String,String>();
	map.put("email","mayanknegi0017@gmail.com");
	map.put("password", "Nifty@6306");
	map.put("product", "ZARA COAT 3");
	
	HashMap<String,String> map2 = new HashMap<String, String>();
	map2.put("email","anshika@gmail.com");
	map2.put("password","Iamking@000");
	map2.put("product","ADIDAS ORIGINAL");
	*/
	List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\Purchaseorder.json");
	
	return new Object[][]  {{data.get(0)} ,{data.get(1)}};
	
}
}

