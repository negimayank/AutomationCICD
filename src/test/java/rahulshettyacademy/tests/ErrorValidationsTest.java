package rahulshettyacademy.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CheckingCart;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		landingPage.loginApplication("mayanknegi0017@gmail.com","Nifty@6306");
		Assert.assertEquals("Login Successfully",landingPage.getErrorMessage()); 
	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		
			// TODO Auto-generated method stub
			
			String productName = "ZARA COAT 3";		
			ProductCatalogue productCatalogue=landingPage.loginApplication("anshika@gmail.com","Iamking@000");
			List<WebElement> productsCard = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CheckingCart cart =productCatalogue.goToCartPage();
			Boolean match =cart.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			}


}