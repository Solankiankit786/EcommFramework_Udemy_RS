package Selenium_BDD_framework_Udemy_RS.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import Selenium_BDD_framework_CICD_Udemy_RS.TestComponents.BaseTest;
import Selenium_BDD_framework_Udemy_RS.PageObjects.CartPage;
import Selenium_BDD_framework_Udemy_RS.PageObjects.CataloguePage;

public class ErrorValidations extends BaseTest {

	@Test(groups = {"Regression"})
	public void LoginErrorValidation()
	{
		lp.loginApplication("ankit.solanki@appfoster.com","ankit13");
		String errorMsg= lp.errorMeesage();
		AssertJUnit.assertEquals(errorMsg, "Incorrect email or password.");

	}
	
	@Test(retryAnalyzer = Selenium_BDD_framework_CICD_Udemy_RS.TestComponents.Retry.class)
	public void productErrorValidation() throws InterruptedException, IOException
	{
		String prodName="ADIDAS ORIGINAL";
		CataloguePage cp =lp.loginApplication("solankiankit786@gmail.com","Ankit@123");
		cp.AddToCart(prodName);
		CartPage cartpage =cp.goToCartPage();
		Boolean match= cartpage.verifyProduct("ADIDASA ORIGINAL");
		Assert.assertFalse(match);
	}
}
