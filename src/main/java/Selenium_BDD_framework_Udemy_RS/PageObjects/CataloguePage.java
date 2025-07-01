package Selenium_BDD_framework_Udemy_RS.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium_BDD_framework_Udemy_RS.AbstractComponents.AbstractComponent;

public class CataloguePage extends AbstractComponent{
WebDriver driver;
	
public CataloguePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

@FindBy(css = ".mb-3")
List<WebElement> productList;
@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
WebElement cart;
@FindBy(css = ".ng-animating")
WebElement animatingToast;

By tagname = By.tagName("b");
By loading = By.cssSelector(".mb-3");
By addToCart= By.cssSelector(".card-body button:last-of-type");
By toastMsg = By.cssSelector(".toast-message");

public List<WebElement> getProductList()
{
	scrollDown();
	visibilityOfEle(loading);
	return productList;
}
public WebElement getProductListByName(String prodName)
{
	WebElement prod = getProductList().stream().filter(s->s.findElement(tagname).getText().equalsIgnoreCase(prodName)).findFirst().orElse(null);
	return prod;
}
public void AddToCart(String prodName)
{
	getProductListByName(prodName).findElement(addToCart).click();
}
public CartPage goToCartPage() throws InterruptedException
{
	visibilityOfEle(toastMsg);
	invisibilityOf(animatingToast);
	scrollUp();
	Thread.sleep(2000);
	cart.click();
	
	return new CartPage(driver);
}
}