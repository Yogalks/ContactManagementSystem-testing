package com.cms.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cms.pages.AddContactPage;
import com.cms.start.Start;

public class AddcontactTest extends Start {
	
	//Valid info
	@Test
	public void testcase01() {
		test=report.createTest("Addcontact - valid info");
		AddContactPage ac = new AddContactPage();
		ac.LoginSection();
		ac.FeedInput01("yoga@test6.com");
		ac.FeedInput02("Test@123");
		ac.pagewait();
		ac.addcontact();
		ac.enterfirstname("Priya");
		ac.enterlastname("Raman");
		ac.enterdob("1997-08-22");
		ac.sendemail("priya@test.com");
		ac.sendphno("9746263762");
		ac.sendstreet1("Gandhiji Street");
		ac.sendstreet2("Mountroad");
		ac.sendcity("Chennai");
		ac.sendstate("TamilNadu");
		ac.sendpostalcode("600048");
		ac.sendcountry("India");
		ac.submitform();
		ac.contactaddcheck();
		captureScreenshot("Add contact - test01");
		test.pass("Contact added successfully");
	}
	
	//fname and lname blank
	@Test
	public void testcase02() {
		test=report.createTest("Addcontact - fname and lname blank");
		AddContactPage ac = new AddContactPage();
		ac.LoginSection();
		ac.FeedInput01("yoga@test5.com");
		ac.FeedInput02("Test@123");
		ac.pagewait();
		ac.addcontact();
		ac.enterfirstname("");
		ac.enterlastname("");
		ac.enterdob("1997-08-22");
		ac.sendemail("priya@test.com");
		ac.sendphno("9746263762");
		ac.sendstreet1("Gandhiji Street");
		ac.sendstreet2("Mountroad");
		ac.sendcity("Chennai");
		ac.sendstate("TamilNadu");
		ac.sendpostalcode("600048");
		ac.sendcountry("India");
		ac.submitform();
		String actualmsg=ac.ReadMessage();
		String expectedmsg ="Contact validation failed";
		Assert.assertTrue(actualmsg.contains(expectedmsg), expectedmsg);
		captureScreenshot("Add contact - test02");
		test.pass("Throwing error as expected");
	}
	
	//Phonenumber field validation
	@Test
	public void testcase03() {
		test=report.createTest("Addcontact - Phonenumber field validation");
		AddContactPage ac = new AddContactPage();
		ac.LoginSection();
		ac.FeedInput01("yoga@test5.com");
		ac.FeedInput02("Test@123");
		ac.pagewait();
		ac.addcontact();
		ac.enterfirstname("Priya");
		ac.enterlastname("Ravi");
		ac.enterdob("1997-08-22");
		ac.sendemail("priya@test.com");
		ac.sendphno("abdhkhgjk");
		ac.sendstreet1("Gandhiji Street");
		ac.sendstreet2("Mountroad");
		ac.sendcity("Chennai");
		ac.sendstate("TamilNadu");
		ac.sendpostalcode("600048");
		ac.sendcountry("India");
		ac.submitform();
		String actualmsg=ac.ReadMessage();
		String expectedmsg ="Phone number is invalid";
		Assert.assertTrue(actualmsg.contains(expectedmsg), expectedmsg);
		captureScreenshot("Add contact - test03");
		test.pass("Throwing error as expected");
	}
	
	//Duplicate Values
	@Test
	public void testcase04() {
	test=report.createTest("Addcontact - Duplicate Values");
	AddContactPage ac = new AddContactPage();
	ac.LoginSection();
	ac.FeedInput01("yoga@test5.com");
	ac.FeedInput02("Test@123");
	ac.pagewait();
	ac.addcontact();
	ac.enterfirstname("Priya");
	ac.enterlastname("Raman");
	ac.enterdob("1997-08-22");
	ac.sendemail("priya@test.com");
	ac.sendphno("9746263762");
	ac.sendstreet1("Gandhiji Street");
	ac.sendstreet2("Mountroad");
	ac.sendcity("Chennai");
	ac.sendstate("TamilNadu");
	ac.sendpostalcode("600048");
	ac.sendcountry("India");
	ac.submitform();
	ac.contactaddcheck();
	captureScreenshot("Add contact - test04");
	test.pass("Duplicate contact added without any warning");
	}
	
	//form cleared validation
	@Test
	public void testcase05() {
		test=report.createTest("Addcontact - Form clear validation");
		AddContactPage ac = new AddContactPage();
		ac.LoginSection();
		ac.FeedInput01("yoga@test5.com");
		ac.FeedInput02("Test@123");
		ac.pagewait();
		ac.addcontact();
		ac.enterfirstname("Sanjay");
		ac.enterlastname("Koushik");
		ac.enterdob("2003-05-10");
		ac.sendemail("sanjay@test.com");
		ac.sendphno("9475778683");
		ac.sendstreet1("NehruStreet");
		ac.sendstreet2("panagalpark");
		ac.sendcity("Chennai");
		ac.sendstate("TamilNadu");
		ac.sendpostalcode("600001");
		ac.sendcountry("India");
		ac.submitform();
		ac.contactaddcheck();
		ac.formclearedvalidation();
		captureScreenshot("Add contact - test05");
		test.pass("Form cleared as expected");
	}	
	

}
