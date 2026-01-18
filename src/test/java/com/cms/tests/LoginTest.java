package com.cms.tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cms.base.BaseTest;
import com.cms.pages.LoginPage;
import com.cms.start.Start;

public class LoginTest extends Start {
	
	@DataProvider(name = "LoginData")
	public Object[][] signupdata(Method method){
		String testcase = method.getAnnotation(Test.class).testName();
			return BaseTest.getDataForTest("LoginData", testcase);
	
	}
	
	//valid credentials
	@Test(testName ="TC01", dataProvider = "LoginData")
	public void testcase01(String Username, String Password) {
		test=report.createTest("Login -Valid Credentials");
		LoginPage lp = new LoginPage();
		lp.FeedInput01(Username);
		lp.FeedInput02(Password);
		lp.pagewait();
		captureScreenshot("Login-test01");
		test.pass("Login Successful");

	}
	
	//invalid password
	@Test(testName ="TC02", dataProvider = "LoginData")
	public void testcase02(String Username, String Password) {
		test=report.createTest("Login - Invalid Password");
		LoginPage lp = new LoginPage();
		lp.FeedInput01(Username);
		lp.FeedInput02(Password);
		String actualmsg= lp.ReadMessage();
		String expectedmsg = "Incorrect username or password";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Login - test02");
		test.pass("Webpage throwing error as expected");
	}
	
	//Empty values
	@Test(testName ="TC03", dataProvider = "LoginData")
	public void testcase03(String Username, String Password) {
		test=report.createTest("Login - Submitting with Empty values");
		LoginPage lp = new LoginPage();
		lp.FeedInput01(Username);
		lp.FeedInput02(Password);
		String actualmsg= lp.ReadMessage();
		String expectedmsg = "Incorrect username or password";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Login-test03");
		test.pass("Webpage throwing error as expected");
		
	}
	
	//Invalid emailid
	@Test(testName ="TC04", dataProvider = "LoginData")
	public void testcase04(String Username, String Password) {
		test=report.createTest("Login - Invalid email id check");
		LoginPage lp=new LoginPage();
		lp.FeedInput01(Username);
		lp.FeedInput02(Password);
		String actualmsg = lp.ReadMessage();
		String expectedmsg="Incorrect username or password";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Login-test04");
		test.pass("Webpage throwing error as expected");
	}
	
	//Password field masked
	@Test
	public void testcase05() {
		test=report.createTest("Login -  Password field masked check");
		LoginPage lp = new LoginPage();	
		lp.Passwordmasked();
		captureScreenshot("Login-test05");
		test.pass("Password field is masked");
		
	}
}
	
	


