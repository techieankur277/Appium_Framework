package org.ankurgoyal.EcommerceHybridFramework.pageObjects.android;

import java.time.Duration;
import java.util.List;

import org.ankur.goyal.EcommerceHybridFramework.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidActions{
	
	AndroidDriver driver;
	public ProductCatalogue(AndroidDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	private WebElement toolbarElement;
	
	public void addCartItemByIndex(int index) {
		addToCart.get(index).click();
	}
	
	public CartPage clickOnCat() throws InterruptedException {
		cartButton.click();
		Thread.sleep(2000);
		webDriverWait(toolbarElement);
		return new CartPage(driver);
	}
}
