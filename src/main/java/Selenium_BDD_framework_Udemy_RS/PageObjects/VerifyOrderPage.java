package Selenium_BDD_framework_Udemy_RS.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Selenium_BDD_framework_Udemy_RS.AbstractComponents.AbstractComponent;

public class VerifyOrderPage extends AbstractComponent {

	WebDriver driver;
	public VerifyOrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	@FindBy(xpath = "//td[2]")
	private List<WebElement> orderedProducts;
	
	public List<WebElement> getOrderedProducts()
	{
		return orderedProducts;
	}
	
	public Boolean verifyOrderProduct(String prodName)
	{
		Boolean match = getOrderedProducts().stream().anyMatch(s->s.getText().equalsIgnoreCase(prodName));
		return match;
	}

}