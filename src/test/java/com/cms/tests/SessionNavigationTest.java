package com.cms.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cms.pages.SessionNavigation;
import com.cms.start.Start;

public class SessionNavigationTest extends Start {
	
	//Page redirect to login
	@Test
	public void testcase01() {
		test=report.createTest("Session - Page redirect check");
		SessionNavigation sn = new SessionNavigation();
		sn.LoginSection();
		sn.FeedInput01("yoga@test.com");
		sn.FeedInput02("Test@123");
		sn.pagewait();
		sn.logoutvalidation();
		String actualtext = sn.getpagetitle();
		String expectedtext ="Contact List App";
		Assert.assertTrue(actualtext.contains(expectedtext), "Page not redirected to Login page");
		captureScreenshot("Session - test01");
		test.pass("Page redirected as expected");

	}
	
	//compare contact list after refresh
	@Test
	public void testcase02() {
		test=report.createTest("Session - Compare contact list");
		SessionNavigation sn = new SessionNavigation();
		sn.LoginSection();
		sn.FeedInput01("yoga@test5.com");
		sn.FeedInput02("Test@123");
		sn.pagewait();
		sn.comparecontactlist();
		captureScreenshot("Session - test02");
		test.pass("Contact list remains same even after refresh ans user stays login");
	}
	
	@Test
	public void testcase03() {
		test=report.createTest("Session - Page redirect without login");
		SessionNavigation sn = new SessionNavigation();
		String actualurl =sn.navigatewithoutlogin();
		String expectedurl = "https://thinking-tester-contact-list.herokuapp.com/";
		Assert.assertFalse(actualurl.equals(expectedurl), "Page getting directly navigated without login");
		captureScreenshot("Session - test03");
		test.fail("Page directly heading to the contactlist without login");
	}
	
	@Test
	public void testcase04() {
		test=report.createTest("Session - Page redirect after logout");
		SessionNavigation sn = new SessionNavigation();
		sn.LoginSection();
		sn.FeedInput01("yoga@test.com");
		sn.FeedInput02("Test@123");
		sn.pagewait();
		sn.logoutvalidation();
		sn.getpagetitle();
		sn.backtocontactlistpage();
		captureScreenshot("Session - test04");
		test.pass("Page not redirecting to the contactlist after logout");
		
	}

}
