package org.ankurgoyal;

import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

//import java.time.Duration;
//import java.util.List;
//import java.util.Set;
//import org.ankurgoyal.EcommerceHybridFramework.pageObjects.android.FormPage;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;
import org.testng.Assert;
//import org.testng.AssertJUnit;
//import org.testng.Assert;
//import org.testng.annotations.Test;
import org.ankurgoyal.EcommerceHybridFramework.pageObjects.android.ProductCatalogue;
import org.ankurgoyal.TestUtils.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//import org.ankur.goyal.EcommerceHybridFramework.utils.AndroidActions;
import org.ankurgoyal.EcommerceHybridFramework.pageObjects.android.CartPage;

//import io.appium.java_client.AppiumBy;

public class AmountCaluculationValidation extends BaseTest {
	
	@Test(dataProvider="getData", groups={"smoke"})
	public void amountCalculation (HashMap<String, String> input) throws InterruptedException {
		
//		FormPage formPage = new FormPage(driver);
		
		formPage.setName(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue= formPage.completeTheForm();
		
		//Add items to cart object
		
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);  we can create an object directly inside in it's previous page class.
		productCatalogue.addCartItemByIndex(0);
		productCatalogue.addCartItemByIndex(0);
		CartPage cartPage = productCatalogue.clickOnCat();
		
		
		double formattedProductPrices= cartPage.getFormattedProductPrices();
		double formattedConvertedTotalPrice = cartPage.getFormattedTotalPrice();
		
		Assert.assertEquals(formattedProductPrices, formattedConvertedTotalPrice);
		
		cartPage.acceptTermsAndConditions();
		cartPage.submitOrder();
		
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void preSetup() {
		formPage.setActivity();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//org//ankurgoyal//testData//eCommerce.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
}
