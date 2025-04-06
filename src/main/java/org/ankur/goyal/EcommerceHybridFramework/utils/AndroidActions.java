package org.ankur.goyal.EcommerceHybridFramework.utils;

//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Path;
import java.time.Duration;
//import java.util.HashMap;
//import java.util.List;

//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
//import com.google.common.io.Files;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {
	public AndroidDriver driver;
	public AndroidActions(AndroidDriver driver) {
			this.driver = driver;
			// TODO Auto-generated constructor stub
		}

	public void longPressGesture(WebElement ele) {
			
			((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId()
			));
		}
	
	public void scrollToText(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	
	public double formattedAmount (String amount) {
		double price =  Double.parseDouble(amount.substring(1));
		return price;
	}
	
	
	
	public void webDriverWait(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(ele, "text", "Cart"));
	}

}
