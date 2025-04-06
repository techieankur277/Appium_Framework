package org.ankurgoyal.TestUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporter.config();
	AndroidDriver driver;
	public void onTestStart(ITestResult result) {
		
		test  = extent.createTest(result.getMethod().getMethodName());
		
	}
	public void onTestSuccess(ITestResult result) {
			
			test.log(Status.PASS, "Test Passed");
	}
	public void onTestFailure(ITestResult result) {
		
		test.fail(result.getThrowable());
		
		try {
			driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			test.addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}