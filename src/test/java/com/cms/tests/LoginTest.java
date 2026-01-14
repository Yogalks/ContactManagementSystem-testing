package com.cms.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cms.pages.LoginPage;
import com.cms.start.Start;

public class LoginTest extends Start {
	
	//valid credentials
	@Test
	public void testcase01() {
		test=report.createTest("Login -Valid Credentials");
		LoginPage lp = new LoginPage();
		lp.FeedInput01("yoga@test.com");
		lp.FeedInput02("Test@123");
		lp.pagewait();
		captureScreenshot("Login-test01");
		test.pass("Login Successful");

	}
	
	//invalid password
	@Test
	public void testcase02() {
		test=report.createTest("Login - Invalid Password");
		LoginPage lp = new LoginPage();
		lp.FeedInput01("yoga@test5.com");
		lp.FeedInput02("Test");
		String actualmsg= lp.ReadMessage();
		String expectedmsg = "Incorrect username or password";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Login - test02");
		test.pass("Webpage throwing error as expected");
	}
	
	//Empty values
	@Test
	public void testcase03() {
		test=report.createTest("Login - Submitting with Empty values");
		LoginPage lp = new LoginPage();
		lp.FeedInput01("");
		lp.FeedInput02("");
		String actualmsg= lp.ReadMessage();
		String expectedmsg = "Incorrect username or password";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Login-test03");
		test.pass("Webpage throwing error as expected");
		
	}
	
	//Invalid emailid
	@Test
	public void testcase04() {
		test=report.createTest("Login - Invalid email id check");
		LoginPage lp=new LoginPage();
		lp.FeedInput01("user.com");
		lp.FeedInput02("Test@123");
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
		lp.FeedInput01("user.com");
		lp.FeedInput02("yoga@test5.com");	
		lp.Passwordmasked();
		captureScreenshot("Login-test05");
		test.pass("Password field is masked");
		
	}
}
	
	


