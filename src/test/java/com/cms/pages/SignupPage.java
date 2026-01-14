package com.cms.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cms.base.BaseTest;

public class SignupPage extends BaseTest {

	@FindBy(xpath="//button[@id='signup']")
	WebElement Signupbutton;
	
	@FindBy(xpath="//input[@id='firstName']")
	WebElement Firstname;
	
	@FindBy(xpath="//input[@id='lastName']")
	WebElement Lastname;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement Email;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement Password;
	
	@FindBy(xpath="//button[@id='submit']")
	WebElement Submitbutton;
	
	@FindBy(xpath="//span[@id='error']")
	WebElement errormessage;
	
	
	
	public SignupPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void Signupsession() {
		ElementClick(Signupbutton);
	}
	
	public void Feedinput(WebElement element, String value) {
		element.sendKeys(value);
	}
	
	public void enterfirstname(String fname) {
		Feedinput(Firstname,fname);
	}
	
	public void enterlastname(String lname) {
		Feedinput(Lastname,lname);
	}
	public void enteremail(String email) {
		Feedinput(Email,email);
	}
	public void enterpassword(String pwd) {
		Feedinput(Password,pwd);
	}
	public void clicksubmit() {
		ElementClick(Submitbutton);
	}
	
	public String ReadMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(errormessage));
		String errormsg ="";
		errormsg=ElementGetText(errormessage);
		System.out.println(errormsg);
		return errormsg;
	}
	
	
}
