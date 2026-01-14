package com.cms.tests;

import org.testng.annotations.Test;

import com.cms.pages.AddContactPage;
import com.cms.start.Start;

public class UIResponsiveTest extends Start{

	@Test
	public void testcase01() {
		test=report.createTest("UI - alignment check");
		AddContactPage acp = new AddContactPage();
		acp.LoginSection();
		acp.FeedInput01("yoga@test5.com");
		acp.FeedInput02("Test@123");
		acp.pagewait();
		acp.addcontact();
		acp.alignment();
		captureScreenshot("UIResponse - test01");
		test.pass("Page aligned as expected");
	}
	
	//confirmmessage after contactadd - app not giving any confirmation message
	@Test
	public void testcase02() {
		test=report.createTest("UI - Confirm message");
		AddContactPage acp = new AddContactPage();
		acp.LoginSection();
		acp.FeedInput01("yoga@test5.com");
		acp.FeedInput02("Test@123");
		acp.pagewait();
		acp.addcontact();
		acp.enterfirstname("Kannan");
		acp.enterlastname("R");
		acp.enterdob("1963-04-01");
		acp.sendemail("kannan@test.com");
		acp.sendphno("7357386282");
		acp.sendstreet1("Lane 1");
		acp.sendstreet2("Maraimalai nagar");
		acp.sendcity("Chennai");
		acp.sendstate("TamilNadu");
		acp.sendpostalcode("600123");
		acp.sendcountry("India");
		acp.submitform();
		captureScreenshot("UIResponse - test02");
		test.fail("App not generating a confirmation message post contact added");
		
	}
}
