package Selenium_BDD_framework_Udemy_RS.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Selenium_BDD_framework_Udemy_RS.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	@FindBy(css = ".totalRow button")
	private WebElement checkout;
	
	public List<WebElement> getCartProducts()
	{
		return cartProducts;
	}
	
	public Boolean verifyProduct(String prodName)
	{
		Boolean match = getCartProducts().stream().anyMatch(s->s.getText().equalsIgnoreCase(prodName));
		return match;
	}
	
	public OrderPage Checkout()
	{
		checkout.click();
		
		return new OrderPage(driver);
	}

}