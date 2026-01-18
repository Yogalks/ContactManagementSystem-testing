package com.cms.tests;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cms.base.BaseTest;
import com.cms.pages.AddContactPage;
import com.cms.start.Start;

public class UIResponsiveTest extends Start{
	
	@DataProvider(name = "UIresponseData")
	public Object[][] signupdata(Method method){
		String testcase = method.getAnnotation(Test.class).testName();
			return BaseTest.getDataForTest("UIresponseData", testcase);
	
	}

	@Test(testName="TC01", dataProvider="UIresponseData")
	public void testcase01(String Username, String Password, String Firstname, String Lastname, 
			String DOB, String Email, String Phno, String Street1, String Street2, String City, String State, 
			String Postalcode, String Country) {
		test=report.createTest("UI - alignment check");
		AddContactPage acp = new AddContactPage();
		acp.LoginSection();
		acp.FeedInput01(Username);
		acp.FeedInput02(Password);
		acp.pagewait();
		acp.addcontact();
		acp.alignment();
		captureScreenshot("UIResponse - test01");
		test.pass("Page aligned as expected");
	}
	
	//confirmmessage after contactadd - app not giving any confirmation message
	@Test(testName="TC02", dataProvider="UIresponseData")
	public void testcase02(String Username, String Password, String Firstname, String Lastname, 
			String DOB, String Email, String Phno, String Street1, String Street2, String City, String State, 
			String Postalcode, String Country) {
		test=report.createTest("UI - Confirm message");
		AddContactPage acp = new AddContactPage();
		acp.LoginSection();
		acp.FeedInput01(Username);
		acp.FeedInput02(Password);
		acp.pagewait();
		acp.addcontact();
		acp.enterfirstname(Firstname);
		acp.enterlastname(Lastname);
		acp.enterdob(DOB);
		acp.sendemail(Email);
		acp.sendphno(Phno);
		acp.sendstreet1(Street1);
		acp.sendstreet2(Street2);
		acp.sendcity(City);
		acp.sendstate(State);
		acp.sendpostalcode(Postalcode);
		acp.sendcountry(Country);
		acp.submitform();
		captureScreenshot("UIResponse - test02");
		test.fail("App not generating a confirmation message post contact added");
		
	}
}
