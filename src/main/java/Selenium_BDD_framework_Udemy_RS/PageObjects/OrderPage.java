package Selenium_BDD_framework_Udemy_RS.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium_BDD_framework_Udemy_RS.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
    WebDriver driver;
    
	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By countriesLoaded = By.cssSelector(".ta-item");
	
	@FindBy(xpath = "//*[@placeholder='Select Country']")
	private WebElement selcountry;
	@FindBy(css = ".ta-item")
	private List<WebElement> countries;
	@FindBy(css = ".action__submit")
	private WebElement confirm;
	
	public void selectCountry(String countryName)
	{
		selcountry.sendKeys(countryName);
		visibilityOfEle(countriesLoaded);
		for (WebElement ele : countries) {
			
			if(ele.getText().equalsIgnoreCase("India"))
			{
				
				ele.click();
				break;
			}
		}
	}
	
	public ConfirmPage confirmPage()
	{
		confirm.click();
		
		return new ConfirmPage(driver);
	}

}