package com.cms.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cms.base.BaseTest;

public class LoginPage extends BaseTest {
	
	@FindBy(xpath="//form/p[1]/input")
	WebElement Loginfield;
	
	@FindBy(xpath="//form/p[2]/input")
	WebElement Passwordfield;
	
	@FindBy(xpath="//form/p[3]/button")
	WebElement Submitbutton;
	
	@FindBy(xpath="//span[@id='error']")
	WebElement Errormessage;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void LoginSection() {
		ElementClick(Loginfield);
	}
	
	public void FeedInput01 (String testdata) 
	{
		ElementSendKeys(Loginfield,testdata);
		
	}
	
	public void FeedInput02 (String testdata) {
		ElementSendKeys(Passwordfield, testdata);
		Submitbutton.click();
	}
	
	public void Passwordmasked() {
		String typeattribute = Passwordfield.getAttribute("type");
		if(typeattribute.equalsIgnoreCase("password")) {
			System.out.println("Password Field is masked");
		}
		else {
			System.out.println("Password Field is not masked");
		}
	}
	
	public String ReadMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Errormessage));
		String errormsg="";
		errormsg= ElementGetText(Errormessage);
		System.out.println(errormsg);
		return errormsg;
	}

}
