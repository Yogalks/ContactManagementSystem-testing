package com.cms.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cms.pages.AddContactPage;
import com.cms.start.Start;

public class ValidationTest extends Start {

	@Test
	public void testcase01() {
		test=report.createTest("validation - exceeding char limit");
		AddContactPage acp = new AddContactPage();
		acp.LoginSection();
		acp.FeedInput01("yoga@test5.com");
		acp.FeedInput02("Test@123");
		acp.pagewait();
		acp.addcontact();
		acp.enterfirstname("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		acp.enterlastname("b");
		acp.enterdob("1726-05-08");
		acp.sendemail("aaaa@test.com");
		acp.sendphno("7777777777");
		acp.sendstreet1("aaaaa");
		acp.sendstreet2("abcdef");
		acp.sendcity("bsgjsgs");
		acp.sendstate("nmskk");
		acp.sendpostalcode("234323");
		acp.sendcountry("India");
		acp.submitform();
		String actualtext = acp.ReadMessage();
		String expectedtext = "longer than the maximum allowed length (20)";
		Assert.assertTrue(actualtext.contains(expectedtext), "app not throwing an error");
		captureScreenshot("validation - test01");
		test.pass("App throwing an char exceeding error msg as expected");
	}
	
	@Test
	public void testcase02() {
		test=report.createTest("validation - Special char in address");
		AddContactPage acp = new AddContactPage();
		acp.LoginSection();
		acp.FeedInput01("yoga@test5.com");
		acp.FeedInput02("Test@123");
		acp.pagewait();
		acp.addcontact();
		acp.enterfirstname("Anu");
		acp.enterlastname("b");
		acp.enterdob("1726-05-08");
		acp.sendemail("aaaa@test.com");
		acp.sendphno("7777777777");
		acp.sendstreet1("@#*&%$%^&^");
		acp.sendstreet2("&&&&&&&&");
		acp.sendcity("bsgjsgs");
		acp.sendstate("nmskk");
		acp.sendpostalcode("234323");
		acp.sendcountry("India");
		acp.submitform();
		acp.contactaddcheck();
		captureScreenshot("validation - test02");
		test.pass("App not throwing an error msg as expected for special char in the address");
	}
}
