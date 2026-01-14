package com.cms.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditContactPage extends LoginPage {
	
	
	@FindBy(xpath="//button[@id='edit-contact']")
	WebElement Editcontactbutton;
	@FindBy(xpath="//input[@id='email']")
	WebElement Emailid;
	@FindBy(xpath="//button[@id='submit']")
	WebElement EditSubmitbutton;
	@FindBy(xpath="//button[@id='cancel']")
	WebElement Editcancelbutton;
	@FindBy(xpath="//button[@id='return']")
	WebElement Returntocontactlistbutton;
	@FindBy(xpath="//input[@id='lastName']")
	WebElement lastnamefield;
	@FindBy(xpath="//span[@id='error']")
	WebElement Errormessage;
	
	public EditContactPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void selectContactByName(String name) {
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement table = driver.findElement(By.tagName("table"));
		List<WebElement> rows =table.findElements(By.tagName("tr"));
		boolean clicked =false;
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			 for (WebElement cell : cells) {
				 if (cell.getText().trim().equals(name)) {
					 cell.click();
                     System.out.println("Clicked on cell with text: " + name);
                     clicked = true;
                     break;
				 }
			 }
                     if (clicked) {
                         break; // Exit outer loop once found
                     }
                 }
                 
                 if (!clicked) {
                     System.out.println("Target text not found in the table.");
                 }
			 
		
			 } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
	    }
    }
		

	
	public void editcontact() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Editcontactbutton));
		Editcontactbutton.click();
	}
	
	public void updateemailid(String emailid) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Emailid));
		Emailid.click();
		Emailid.clear();
		Emailid.sendKeys(emailid);
	}
	public void editlastname() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(lastnamefield));
		lastnamefield.click();
		lastnamefield.clear();
		
		
	}
	public void editsubmit() {
		EditSubmitbutton.click();
	}
	public void editcancel() {
		Editcancelbutton.click();
	}
	public void returntocontactlist() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(Returntocontactlistbutton));
		Returntocontactlistbutton.click();
	}	 
	 
	 public boolean isEmailUpdatedInTable(String contactName, String expectedEmail) {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

		    List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));

		    for (WebElement row : rows) {

		        String rowText = row.getText().replace("\n", " ").trim();

		        System.out.println("ROW TEXT => " + rowText);

		        if (rowText.contains(contactName) && rowText.contains(expectedEmail)) {
		            return true;
		        }
		    }
		    return false;
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

