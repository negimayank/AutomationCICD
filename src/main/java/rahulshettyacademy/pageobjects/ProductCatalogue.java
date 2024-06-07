package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	 WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement username = driver.findElement(By.id("userEmail"));
	
	//PageFactory
	
	@FindBy(css=".mb-3")
	List<WebElement> productsCard;
	
	@FindBy (css=".ng-animating")
	WebElement animation;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return productsCard;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	

	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement prod =getProductByName(productName);
		prod.findElement(addTocart).click();
		waitForElementToAppear(toast);
		waitForElementToDisappear(animation);
	}
}
