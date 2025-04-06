package org.ankurgoyal.EcommerceHybridFramework.pageObjects.android;

import org.ankur.goyal.EcommerceHybridFramework.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.util.Assert;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class FormPage extends AndroidActions{
	//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ankur Goyal");
	
	AndroidDriver driver;
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioMale\"]")
	private WebElement maleOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;
	
//	@AndroidFindBy(xpath="//android.widget.TextView[@text='Argentina']")
//	private WebElement countrySelection;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopButton;
	
	public void setName(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setActivity() {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
	}
	
	public void setGender(String gender) {
		if(gender.contains("female")) {
			femaleOption.click();
		} else {
			maleOption.click();
		}
	}
	
	public void setCountrySelection(String country) {
		countryDropdown.click();
		scrollToText(country);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click(); //we directly write down here because there are 100s of countries we can not write 100s of page factories.
	}
	
	public ProductCatalogue completeTheForm() {
		letsShopButton.click();
		return new ProductCatalogue(driver); //by this have optimized some code using this.
	}
}
