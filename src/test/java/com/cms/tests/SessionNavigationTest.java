package com.cms.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cms.base.BaseTest;
import com.cms.pages.SessionNavigation;
import com.cms.start.Start;

public class SessionNavigationTest extends Start {
	
	@DataProvider(name = "SessionNavigationData")
	public Object[][] signupdata(Method method){
		String testcase = method.getAnnotation(Test.class).testName();
			return BaseTest.getDataForTest("SessionNavigationData", testcase);
	
	}
	
	//Page redirect to login
	@Test(testName="TC01", dataProvider="SessionNavigationData")
	public void testcase01(String Username, String Password, String Expectedtext, String ExpectedUrl) {
		test=report.createTest("Session - Page redirect check");
		SessionNavigation sn = new SessionNavigation();
		sn.LoginSection();
		sn.FeedInput01(Username);
		sn.FeedInput02(Password);
		sn.pagewait();
		sn.logoutvalidation();
		String actualtext = sn.getpagetitle();
		Assert.assertTrue(actualtext.contains(Expectedtext), "Page not redirected to Login page");
		captureScreenshot("Session - test01");
		test.pass("Page redirected as expected");

	}
	
	//compare contact list after refresh
	@Test(testName="TC02", dataProvider="SessionNavigationData")
	public void testcase02(String Username, String Password, String Expectedtext, String ExpectedUrl) {
		test=report.createTest("Session - Compare contact list");
		SessionNavigation sn = new SessionNavigation();
		sn.LoginSection();
		sn.FeedInput01(Username);
		sn.FeedInput02(Password);
		sn.pagewait();
		sn.comparecontactlist();
		captureScreenshot("Session - test02");
		test.pass("Contact list remains same even after refresh ans user stays login");
	}
	
	@Test(testName="TC03", dataProvider="SessionNavigationData")
	public void testcase03(String Username, String Password, String Expectedtext, String ExpectedUrl) {
		test=report.createTest("Session - Page redirect without login");
		SessionNavigation sn = new SessionNavigation();
		String actualurl =sn.navigatewithoutlogin();
		Assert.assertFalse(actualurl.equals(ExpectedUrl), "Page getting directly navigated without login");
		captureScreenshot("Session - test03");
		test.fail("Page directly heading to the contactlist without login");
	}
	
	@Test(testName="TC04", dataProvider="SessionNavigationData")
	public void testcase04(String Username, String Password, String Expectedtext, String ExpectedUrl) {
		test=report.createTest("Session - Page redirect after logout");
		SessionNavigation sn = new SessionNavigation();
		sn.LoginSection();
		sn.FeedInput01(Username);
		sn.FeedInput02(Password);
		sn.pagewait();
		sn.logoutvalidation();
		sn.getpagetitle();
		sn.backtocontactlistpage();
		captureScreenshot("Session - test04");
		test.pass("Page not redirecting to the contactlist after logout");
		
	}

}
