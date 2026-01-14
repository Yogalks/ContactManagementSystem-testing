package com.cms.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteContactPage extends EditContactPage {
	
	@FindBy(xpath="//button[@id='delete']")
	WebElement deletebutton;
	
	
	public DeleteContactPage() {
		PageFactory.initElements(driver, this);
	}
	public void deletecontact() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(deletebutton));
		deletebutton.click();
	}
	
	public void acceptalert() {
		driver.switchTo().alert().accept();
	}
	
	public void refreshpage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));
		driver.navigate().refresh();
	}
	
	public void verifyalert() {
		
		String alerttext = driver.switchTo().alert().getText();
		if(!alerttext.equals(null)) {
			System.out.println("Alert box is apperaing before contact delete");
		}
		else {
			System.out.println("Alert box is not appearing before contact delete");
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	
	public boolean verifydeletecontact(String contactname) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

		    List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));

		    for (WebElement row : rows) {

		        String rowText = row.getText().replace("\n", " ").trim();

		        System.out.println("ROW TEXT => " + rowText);

		        if (!rowText.contains(contactname)) {
		        	System.out.println("Contact deleted successfully");
		            return true;
		        }
		    }
		    return false;
		}
		
		   
		   
			
	}


