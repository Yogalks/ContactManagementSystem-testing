package com.cms.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cms.pages.SignupPage;
import com.cms.start.Start;

public class SignupTest extends Start {
	
	//Valid values
	@Test
	public void testcase01() {
		test=report.createTest("Signup -Valid values");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname("Yoga5");
		sp.enterlastname("Lakshmi");
		sp.enteremail("yoga@test6.com");
		sp.enterpassword("Test@123");
		sp.clicksubmit();
		sp.pagewait();
		captureScreenshot("Signup-test01");
		test.pass("Signup Successful");
	}
	
	//Already registered emailid
	@Test
	public void testcase02() {
		test=report.createTest("Signup -Already registered email");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname("Yoga2");
		sp.enterlastname("Lakshmi");
		sp.enteremail("yoga@test6.com");
		sp.enterpassword("Test@123");
		sp.clicksubmit();
		String actualmsg= sp.ReadMessage();
		String expectedmsg = "Email address is already in use";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Signup-test02");
		test.pass("Throwing error as expected");
	}
	
	//empty values
	@Test
	public void testcase03() {
		test=report.createTest("Signup - emplty values");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname("");
		sp.enterlastname("");
		sp.enteremail("");
		sp.enterpassword("");
		sp.clicksubmit();
		String actualmsg= sp.ReadMessage();
		String expectedmsg = "User validation failed";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Signup-test03");
		test.pass("Throwing error as expected");
	}
	
	//incorrect password
	@Test
	public void testcase04() {
		test=report.createTest("Signup - Incorrect Password");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname("Yoga2");
		sp.enterlastname("Lakshmi");
		sp.enteremail("yoga@test2.com");
		sp.enterpassword("Test@123&Test*765");
		sp.clicksubmit();
		String actualmsg= sp.ReadMessage();
		String expectedmsg = "Email address is already in use";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Signup-test04");
		test.pass("Throwing error as expected");
	}
	
	//invalid emailid
	@Test
	public void testcase05() {
		test=report.createTest("Signup - Invalid emailid");
		SignupPage sp = new SignupPage();
		sp.Signupsession();
		sp.enterfirstname("Yoga2");
		sp.enterlastname("Lakshmi");
		sp.enteremail("name@name");
		sp.enterpassword("Test@123");
		sp.clicksubmit();
		String actualmsg= sp.ReadMessage();
		String expectedmsg = "Email is invalid";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Signup-test05");
		test.pass("Throwing error as expected");
	}
	

}
