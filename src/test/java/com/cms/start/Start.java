package com.cms.start;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.cms.base.BaseTest;

public class Start extends BaseTest {

	@BeforeSuite
	public void beforesuite() {
		getReport();
	}
	
	@AfterSuite
	public void aftersuite() {
		report.flush();
		//CloseBrowser();
	}
	
	@BeforeMethod()
	public void beforemethod() {
		StartBrowser("chrome");
		driver.get("https://thinking-tester-contact-list.herokuapp.com/");
	}
	
	@AfterMethod()
	public void aftermethod() {
	
	}
}
	

