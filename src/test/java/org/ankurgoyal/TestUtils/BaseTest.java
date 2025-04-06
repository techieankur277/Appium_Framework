package org.ankurgoyal.TestUtils;

//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.ankurgoyal.EcommerceHybridFramework.pageObjects.android.FormPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;

//import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public FormPage formPage; 
	
	@BeforeClass(alwaysRun =true)
	public void appiumServerStart() throws MalformedURLException, IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//ankur//goyal//resources//data.properties");
		prop.load(fis);
		String ipAddress= prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String deviceName = prop.getProperty("deviceName");
		service = new AppiumServiceBuilder().withAppiumJS(new File("//home//ai//.npm-global//lib//node_modules//appium//build//lib//main.js"))
				.withIPAddress(ipAddress)
				.usingPort(Integer.parseInt(port)).build();
		
		service.start();
		UiAutomator2Options options = new UiAutomator2Options();
//		options.setDeviceName("emulator-5556");
//		options.setUdid("emulator-5556");
		options.setDeviceName(deviceName);
		options.setChromedriverExecutable("//home//ai//Downloads//chromedriver-linux64//chromedriver-linux64//chromedriver");
//		options.setApp("//home//ai//eclipse-workspace//Appium//src//test//java//Resources//ApiDemos-debug.apk");
		
		options.setApp("//home//ai//eclipse-workspace//E-commerce_app//EcommerceHybridFramework//src//test//java//Resources//General-Store.apk");
		//Android driver invoke
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage= new FormPage(driver);
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		//System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//testData//eCommerce.json"
				// conver json file content to json string
				String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);

				ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String, String>> data = mapper.readValue(jsonContent,
						new TypeReference<List<HashMap<String, String>>>() {
				});

				return data;

	}
	
	public String getScreenShotPath(String testCaseName, AndroidDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		File file =new File(destinationFile);
		FileUtils.copyFile(source, file);
		return destinationFile;
	}
	

	@AfterClass(alwaysRun =true)
	public void tearDown() {
		driver.quit();
		service.stop();
		
	}
}