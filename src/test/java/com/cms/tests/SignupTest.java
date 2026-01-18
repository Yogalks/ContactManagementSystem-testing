package com.cms.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cms.base.BaseTest;
import com.cms.pages.SignupPage;
import com.cms.start.Start;

public class SignupTest extends Start {
	
	@DataProvider(name = "SignupData")
	public Object[][] signupdata(Method method){
		String testcase = method.getAnnotation(Test.class).testName();
			return BaseTest.getDataForTest("SignupData", testcase);
	
	}
	
	//Valid values
	@Test(testName ="TC01", dataProvider = "SignupData")
	public void testcase01(String FirstName, String LastName, String Email, String Password) {
		test=report.createTest("Signup -Valid values");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname(FirstName);
		sp.enterlastname(LastName);
		sp.enteremail(Email);
		sp.enterpassword(Password);
		sp.clicksubmit();
		sp.pagewait();
		captureScreenshot("Signup-test01");
		test.pass("Signup Successful");
	}
	
	//Already registered emailid
	@Test(testName ="TC02", dataProvider = "SignupData")
	public void testcase02(String FirstName, String LastName, String Email, String Password) {
		test=report.createTest("Signup -Already registered email");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname(FirstName);
		sp.enterlastname(LastName);
		sp.enteremail(Email);
		sp.enterpassword(Password);
		sp.clicksubmit();
		String actualmsg= sp.ReadMessage();
		String expectedmsg = "Email address is already in use";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Signup-test02");
		test.pass("Throwing error as expected");
	}
	
	//empty values
	@Test(testName ="TC03", dataProvider = "SignupData")
	public void testcase03(String FirstName, String LastName, String Email, String Password) {
		test=report.createTest("Signup - empty values");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname(FirstName);
		sp.enterlastname(LastName);
		sp.enteremail(Email);
		sp.enterpassword(Password);
		sp.clicksubmit();
		String actualmsg= sp.ReadMessage();
		String expectedmsg = "User validation failed";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Signup-test03");
		test.pass("Throwing error as expected");
	}
	
	//incorrect password
	@Test(testName ="TC04", dataProvider = "SignupData")
	public void testcase04(String FirstName, String LastName, String Email, String Password) {
		test=report.createTest("Signup - Incorrect Password");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname(FirstName);
		sp.enterlastname(LastName);
		sp.enteremail(Email);
		sp.enterpassword(Password);
		sp.clicksubmit();
		String actualmsg= sp.ReadMessage();
		String expectedmsg = "Email address is already in use";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Signup-test04");
		test.pass("Throwing error as expected");
	}
	
	//invalid emailid
	@Test(testName="TC05", dataProvider ="SignupData")
	public void testcase05(String FirstName, String LastName, String Email, String Password) {
		test=report.createTest("Signup - Invalid emailid");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname(FirstName);
		sp.enterlastname(LastName);
		sp.enteremail(Email);
		sp.enterpassword(Password);
		sp.clicksubmit();
		String actualmsg= sp.ReadMessage();
		String expectedmsg = "Email is invalid";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Signup-test05");
		test.pass("Throwing error as expected");
	}
	

}
