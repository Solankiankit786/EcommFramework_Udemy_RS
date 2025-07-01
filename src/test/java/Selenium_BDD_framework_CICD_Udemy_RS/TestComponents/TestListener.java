package Selenium_BDD_framework_CICD_Udemy_RS.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Selenium_BDD_framework_CICD_Udemy_RS.resources.ExtentReportTestNG;

public class TestListener extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent= ExtentReportTestNG.getExtentReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
        test= extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String destfile=null;
        extentTest.get().fail(result.getThrowable());
        try {
			destfile= getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        extentTest.get().addScreenCaptureFromPath(destfile, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite started.");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished.");
        extent.flush();
    }
}
