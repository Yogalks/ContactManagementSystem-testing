package com.cms.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionNavigation extends DeleteContactPage{
	
	@FindBy(xpath="//button[@id='logout']")
	WebElement Logoutbutton;
	
	public SessionNavigation() {
		PageFactory.initElements(driver, this);
	}
	
	public void logoutvalidation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Logoutbutton));
		Logoutbutton.click();
				
	}
	
	public String getpagetitle() {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		String pagetitle = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Contact List App']"))).getText();
		if(pagetitle.contains("Contact List App")) {
			System.out.println("Page redirected to Login after Logout");
		}
		return pagetitle;
	}
	
	public void comparecontactlist() {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

	    String beforerefreshtext = driver.findElement(By.xpath("//table")).getText().replace("/n", " ").trim();
	    System.out.println("Before refresh => " +beforerefreshtext);
	    driver.navigate().refresh();
	    System.out.println("page got refreshed");
	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

	    String afterrefreshtext = driver.findElement(By.xpath("//table")).getText().replace("/n", " ").trim();
	    System.out.println("After refresh => " +afterrefreshtext);
	    if(beforerefreshtext.equals(afterrefreshtext)) {
	    	System.out.println("Contact list remains same after refresh");
	    }else {
	    	System.out.println("contact list doesn't remain same after refresh");
	    }
	    
		
	}
	
	public String navigatewithoutlogin() {
		driver.get("https://thinking-tester-contact-list.herokuapp.com/contactList");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String url = driver.getCurrentUrl();
		return url;
	}
	
	public void backtocontactlistpage() {
		driver.navigate().back();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String url = driver.getCurrentUrl();
		if(!url.equals("https://thinking-tester-contact-list.herokuapp.com/contactList")) {
			System.out.println("App behaving as expected, it is not going back to the Contact list page");
		}else {
			System.out.println("App not behaving as expected, it is going back to the Contact list page");
		}
	}

}
