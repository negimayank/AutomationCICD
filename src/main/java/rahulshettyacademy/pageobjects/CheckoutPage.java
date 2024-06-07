package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy (xpath = "//input[@placeholder='Select Country']")
	WebElement inputCountry;
	
	@FindBy(css = ".ta-item:last-of-type")
	WebElement selectCountry;
	
	@FindBy(xpath = "//a[contains(text(),'Place Order ')]")
	WebElement placeOrderButton;
	

	
	By results = By.cssSelector(".ta-item:last-of-type");
	
	
	public void selectCountry(String countryName) {
		Actions action =new Actions(driver);
		action.sendKeys(inputCountry,countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
		
	}

}
