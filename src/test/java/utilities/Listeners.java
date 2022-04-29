package utilities;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.annotations.MarkupIgnore;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.ExtentReportNG;

public class Listeners extends ExtentReportNG implements ITestListener{

	ExtentReports extent = ExtentReportNG.extentReportGenerator();
	ExtentTest parentTest;
	
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		WebDriver driver = null;
		Object testObject = result.getInstance();
		Class classs = result.getTestClass().getRealClass();
		String browsername = null;
		try {
			browsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("myBrowser");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// TODO Auto-generated method stub
		parentTest = extent.createTest(result.getInstanceName().concat("---"+result.getMethod().getMethodName())).assignAuthor("Sabbir").assignCategory(result.getInstanceName());
		parentTest.assignDevice(browsername);
		extentTest.set(parentTest);
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.INFO, MarkupHelper.createLabel("PASSED", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver = null;
		
		extentTest.get().log(Status.FAIL, MarkupHelper.createLabel("Test Failed of below reason", ExtentColor.RED));
		extentTest.get().log(Status.FAIL, result.getThrowable());
		
		Object testObject = result.getInstance();
		Class classs = result.getTestClass().getRealClass();
		
		
		try {
			driver = (WebDriver) classs.getDeclaredField("driver").get(testObject);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName().concat("__"+actualDate),driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	
}
