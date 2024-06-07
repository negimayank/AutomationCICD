package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckingCart extends AbstractComponent{

	 WebDriver driver;

	public CheckingCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> productTitles;
		
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkout;
		
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match = productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	
	public CheckoutPage goToCheckout() {
		
		checkout.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
		}
	
	
	
	}
