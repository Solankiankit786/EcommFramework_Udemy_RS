package Selenium_BDD_framework_Udemy_RS.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium_BDD_framework_CICD_Udemy_RS.TestComponents.BaseTest;
import Selenium_BDD_framework_Udemy_RS.PageObjects.CartPage;
import Selenium_BDD_framework_Udemy_RS.PageObjects.CataloguePage;
import Selenium_BDD_framework_Udemy_RS.PageObjects.ConfirmPage;
import Selenium_BDD_framework_Udemy_RS.PageObjects.OrderPage;
import Selenium_BDD_framework_Udemy_RS.PageObjects.VerifyOrderPage;

public class SubmitOrderTest extends BaseTest {
	String prodName="ADIDAS ORIGINAL";
	@Test(dataProvider = "getData",groups = "Purchase",retryAnalyzer = Selenium_BDD_framework_CICD_Udemy_RS.TestComponents.Retry.class)
	public void SubmitOrder(HashMap<String, String> map) throws InterruptedException, IOException
	{
		//New comments are added in the Selenium Project for cicd 
		CataloguePage cp =lp.loginApplication(map.get("emailId"),map.get("password"));
		cp.AddToCart(map.get("item"));
		CartPage cartpage =cp.goToCartPage();
		Boolean match= cartpage.verifyProduct(map.get("item"));
		Assert.assertTrue(match);
		OrderPage op=cartpage.Checkout();
		op.selectCountry("India");
		ConfirmPage cmp=op.confirmPage();
		String message=cmp.confirmMsg();
		Assert.assertEquals(message,"THANKYOU FOR THE ORDER.");
	}
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest()
	{
		CataloguePage cp =lp.loginApplication("ankit.solanki@appfoster.com","ankit123");
		VerifyOrderPage vop= cp.goToVerifyOrderPage();
		Boolean match = vop.verifyOrderProduct(prodName);
		Assert.assertTrue(match);

	}
	
	@DataProvider
	public  Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> ls = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Selenium_BDD_framework_CICD_Udemy_RS//DataSet//Purchase.json");
		return new Object[][] {{ls.get(0)},{ls.get(1)}};
	}
	
}
