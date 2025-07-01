package Selenium_BDD_framework_Udemy_RS.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium_BDD_framework_Udemy_RS.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
WebDriver driver;

public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	    super(driver);
	    this.driver = driver;
	    PageFactory.initElements(driver,this);
	}

@FindBy(id = "userEmail")
private WebElement Email;
@FindBy(id = "userPassword")
private WebElement Password;
@FindBy(name = "login")
private WebElement Login;
@FindBy(css = ".toast-message")
private WebElement errorMsg;

public String errorMeesage()
{
	visibilityOfWebEle(errorMsg);
	return errorMsg.getText();
}

public CataloguePage loginApplication(String username,String password)
{
	Email.sendKeys(username);
	Password.sendKeys(password);
	Login.click();
	
	return new CataloguePage(driver);
}
public void goTo()
{
	driver.get("https://rahulshettyacademy.com/client");
}
}
