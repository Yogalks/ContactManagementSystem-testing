package com.cms.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddContactPage extends LoginPage{
	
	@FindBy(xpath="//button[@id='add-contact']")
	WebElement Addnewcontact;
	@FindBy(xpath="//input[@id='firstName']")
	WebElement Firstname;
	@FindBy(xpath="//input[@id='lastName']")
	WebElement Lastname;
	@FindBy(xpath="//input[@id='birthdate']")
	WebElement DOB;
	@FindBy(xpath="//input[@id='email']")
	WebElement Email;
	@FindBy(xpath="//input[@id='phone']")
	WebElement Phoneno;
	@FindBy(xpath="//input[@id='street1']")
	WebElement Street1;
	@FindBy(xpath="//input[@id='street2']")
	WebElement Street2;
	@FindBy(xpath="//input[@id='city']")
	WebElement City;
	@FindBy(xpath="//input[@id='stateProvince']")
	WebElement StateProvince;
	@FindBy(xpath="//input[@id='postalCode']")
	WebElement Postalcode;
	@FindBy(xpath="//input[@id='country']")
	WebElement Country;
	@FindBy(id="submit")
	WebElement Submitbutton;
	@FindBy(xpath="//span[@id=\"error\"]")
	WebElement Errormessage;
	
	public AddContactPage() {
		PageFactory.initElements(driver, this);
	}
	public void addcontact() {
		Addnewcontact.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	public void Feedinput(WebElement element, String value) {
		element.sendKeys(value);
	}
	
	public void enterfirstname(String fname) {
		Firstname.sendKeys(fname);
	}
	public void enterlastname(String lname) {
		Lastname.sendKeys(lname);
	}
	public void enterdob(String dob) {
		DOB.sendKeys(dob);
	}
	public void sendemail(String email) {
		Email.sendKeys(email);
	}
	public void sendphno(String phno) {
		Phoneno.sendKeys(phno);
	}
	public void sendstreet1(String street1) {
		Street1.sendKeys(street1);
	}
	public void sendstreet2(String street2) {
		Street2.sendKeys(street2);
	}
	public void sendcity(String Cname) {
		City.sendKeys(Cname);
	}
	public void sendstate(String Statename) {
		StateProvince.sendKeys(Statename);
	}
	public void sendpostalcode(String Pincode) {
		Postalcode.sendKeys(Pincode);
	}
	public void sendcountry(String Countryname) {
		Country.sendKeys(Countryname);
	}
	public void submitform() {
		Submitbutton.click();
	}
	
	public String ReadMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Errormessage));
		String errormsg ="";
		errormsg=ElementGetText(Errormessage);
		System.out.println(errormsg);
		return errormsg;
	}
	
	public void contactaddcheck() {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   wait.until(ExpectedConditions.visibilityOf(Addnewcontact));
	   if(Addnewcontact.isDisplayed()) {
		   System.out.println("Contact added successfully");
	   }
	   else {
		   System.out.println("Contact not added");
	   }
	   
}
	
public void formclearedvalidation() {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   wait.until(ExpectedConditions.visibilityOf(Addnewcontact));
	   Addnewcontact.click();
	   wait.until(ExpectedConditions.visibilityOf(Firstname));
	   if(Firstname != null) {
		   System.out.println("form not cleared");
	   }
	   else {
		   System.out.println("form cleared and ready to enter new values");
	   }
}
	
public void alignment() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(Firstname));
	int x1 = Firstname.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(Lastname));
	int x2 = Lastname.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(DOB));
	int x3 = DOB.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(Email));
	int x4 = Email.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(Phoneno));
	int x5 = Phoneno.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(Street1));
	int x6 = Street1.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(Street2));
	int x7 = Street2.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(City));
	int x8 = City.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(StateProvince));
	int x9 = StateProvince.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(Postalcode));
	int x10 = Postalcode.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(Country));
	int x11 = Country.getLocation().getX();
	wait.until(ExpectedConditions.visibilityOf(Submitbutton));
	int x12 = Submitbutton.getLocation().getX();
	Assert.assertFalse(x1==x2 && x2==x3 && x3==x4 && x4==x5 && x5==x6 && x6==x7 && 
			x7==x8 && x8==x9 && x9==x10 && x10==x11 && x11==x12, "Fields are not properly aligned");
	System.out.println("All fields are properly aligned");
}
	
public void alertbox() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	String alertmsg = driver.switchTo().alert().getText();
	if(!alertmsg.equals(null)) {
		System.out.println("Confirmation message is shown");
	}
	else {
		System.out.println("it is not giving Confirmation message");
	}
	
}

}
