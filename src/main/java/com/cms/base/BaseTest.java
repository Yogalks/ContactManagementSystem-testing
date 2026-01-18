package com.cms.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
   
   
   public static Object[][] getDataForTest(String sheetName, String testCase) {
	   
	   List<Object[]> dataList;

       try {
           FileInputStream fis = new FileInputStream(
        		   System.getProperty("user.dir")+"/TestData.xlsx");

           Workbook workbook = new XSSFWorkbook(fis);
           Sheet sheet = workbook.getSheet(sheetName);

           int colCount = sheet.getRow(0).getLastCellNum();
           dataList = new ArrayList<>();

           for (int i = 1; i <= sheet.getLastRowNum(); i++) {

               Row row = sheet.getRow(i);
               
               if(row==null)continue;
               
               Cell tccell = row.getCell(0);
               if(tccell == null)continue;
               if (row.getCell(0).toString().equalsIgnoreCase(testCase)) {

                   Object[] rowData = new Object[colCount - 1];

                   for (int j = 1; j < colCount; j++) {
                	   
                	   Cell cell = row.getCell(j);

                       // Empty cell
                       if (cell == null) {
                           rowData[j - 1] = "";
                       }
                       else {
                           String value = cell.toString().trim();

                           // NULL keyword
                           if (value.equalsIgnoreCase("NULL")) {
                               rowData[j - 1] = null;
                           }
                           // EMPTY keyword
                           else if (value.equalsIgnoreCase("EMPTY")) {
                               rowData[j - 1] = "";
                           }
                           // Normal value
                           else {
                               rowData[j - 1] = value;
                           }
                       }
                   }

                   dataList.add(rowData);
               }
           }
           
       }catch (Exception e){
    	   e.printStackTrace();
    	   throw new RuntimeException("Error While reading excel data");
    	   
       }
       finally {
    	   
       }

           return dataList.toArray(new Object[0][0]);
       
       }
       
   }
   



