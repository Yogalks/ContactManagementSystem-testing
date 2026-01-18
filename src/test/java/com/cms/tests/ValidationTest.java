package com.cms.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cms.base.BaseTest;
import com.cms.pages.AddContactPage;
import com.cms.start.Start;

public class ValidationTest extends Start {
	
	@DataProvider(name = "ValidationData")
	public Object[][] signupdata(Method method){
		String testcase = method.getAnnotation(Test.class).testName();
			return BaseTest.getDataForTest("ValidationData", testcase);
	
	}

	@Test(testName="TC01", dataProvider="ValidationData")
	public void testcase01(String Username, String Password, String Firstname, String Lastname, 
			String DOB, String Email, String Phno, String Street1, String Street2, String City, String State, 
			String Postalcode, String Country) {
		test=report.createTest("validation - exceeding char limit");
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
		String actualtext = acp.ReadMessage();
		String expectedtext = "longer than the maximum allowed length (20)";
		Assert.assertTrue(actualtext.contains(expectedtext), "app not throwing an error");
		captureScreenshot("validation - test01");
		test.pass("App throwing an char exceeding error msg as expected");
	}
	
	@Test(testName="TC02", dataProvider="ValidationData")
	public void testcase02(String Username, String Password, String Firstname, String Lastname, 
			String DOB, String Email, String Phno, String Street1, String Street2, String City, String State, 
			String Postalcode, String Country) {
		test=report.createTest("validation - Special char in address");
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
		acp.contactaddcheck();
		captureScreenshot("validation - test02");
		test.pass("App not throwing an error msg as expected for special char in the address");
	}
}
