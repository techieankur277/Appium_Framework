package org.ankurgoyal.EcommerceHybridFramework.pageObjects.android;

import java.util.List;

import org.ankur.goyal.EcommerceHybridFramework.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPrice;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkBoxBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement longPressElement;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"android:id/button1\"]")
	private WebElement closeButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;
	
	public double getFormattedProductPrices() {
		int count = productPrices.size();
		double sum =0;
		for(int i=0; i<count;i++) {
			String amountPrices = productPrices.get(i).getText();
			double price = formattedAmount(amountPrices);
			sum+=price;
		}
		return sum;
	}
	
	public double getFormattedTotalPrice() {
//		String newTotalPrice=totalPrice.getText();
//		double convertedTotalPrice = formattedAmount(newTotalPrice);
		
		return formattedAmount(totalPrice.getText());
	}
	
	public void acceptTermsAndConditions() {
		longPressGesture(longPressElement);
		closeButton.click();
	}
	
	public void submitOrder() {
		checkBoxBtn.click();
		proceed.click();
	}
	
}
