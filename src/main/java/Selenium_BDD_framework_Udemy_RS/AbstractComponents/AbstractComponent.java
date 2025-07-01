package Selenium_BDD_framework_Udemy_RS.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium_BDD_framework_Udemy_RS.PageObjects.VerifyOrderPage;

public class AbstractComponent {
WebDriver driver;

public AbstractComponent(WebDriver driver){
	this.driver = driver;
	PageFactory.initElements(driver, this);
	
}
//		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[2]")).click();
@FindBy(xpath = "(//button[@class='btn btn-custom'])[2]")
private WebElement verifyorderPage;

    public VerifyOrderPage goToVerifyOrderPage()
    {
    	verifyorderPage.click();
    	return new VerifyOrderPage(driver);
    }
    
	public void scrollDown()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)","");
	}

	public void scrollUp()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-900)","");
	}
	
	public void visibilityOfEle(By loading)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		w.until(ExpectedConditions.visibilityOfElementLocated(loading));

	}
	
	public void visibilityOfWebEle(WebElement load)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(load));

	}
	
	public void invisibilityOf(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
//		w.until(ExpectedConditions.invisibilityOf(ele));

	}
}
