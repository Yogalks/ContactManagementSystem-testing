package com.cms.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class BaseTest {
	
	public static WebDriver driver=null;
	public static ExtentSparkReporter reporter;
	public static ExtentReports report = null;
	public static ExtentTest test = null;
	
	public static void getReport() {
        reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/CmdTestReport.html");

        reporter.config().setReportName("CMS Automation Report");
        reporter.config().setDocumentTitle("Test Execution Report");

        report = new ExtentReports();
        report.attachReporter(reporter);
      
    }
    
	public static void StartBrowser(String browser) {
		
		switch(browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			driver = new ChromeDriver();
		}
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
    }

   protected void CloseBrowser() {
  
            driver.close();
        }
   
   
   public static void ElementClick (WebElement element) {
	   element.click();
   }
   
   public static void ElementSendKeys (WebElement element,String Value) {
	   element.clear();
	   element.sendKeys(Value);
   }
   
   public static String ElementGetText (WebElement element) {
	   String value ="";
	   value = element.getText();
	   return value;
   }
   
   public void pagewait() {
	   
	  
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   WebElement expectedelement= driver.findElement(By.xpath("//h1[text()='Contact List']"));
	   wait.until(ExpectedConditions.visibilityOf(expectedelement));
	   if(expectedelement.isDisplayed()) {
		   System.out.println("Redirected to the expected page");
	   }
	   else {
		   System.out.println("Redirected to the incorrect page");
	   }
	   
   }
   
   public static String captureScreenshot(String testName) {
	   TakesScreenshot ts =(TakesScreenshot)driver;
	   File src=ts.getScreenshotAs(OutputType.FILE);
	   String destPath = System.getProperty("user.dir")+"/Screenshotsfolder/"+testName+".png";
	   try {
		FileUtils.copyFile(src, new File(destPath));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return destPath;
   }
   
   
}
