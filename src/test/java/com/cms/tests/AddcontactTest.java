package com.cms.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cms.base.BaseTest;
import com.cms.pages.AddContactPage;
import com.cms.start.Start;

public class AddcontactTest extends Start {
	
	@DataProvider(name = "AddContactData")
	public Object[][] signupdata(Method method){
		String testcase = method.getAnnotation(Test.class).testName();
			return BaseTest.getDataForTest("AddContactData", testcase);
	
	}
	
	//Valid info
	@Test(testName="TC01", dataProvider ="AddContactData")
	public void testcase01(String Username, String Password, String Firstname, String Lastname, 
			String DOB, String Email, String Phno, String Street1, String Street2, String City, String State, 
			String Postalcode, String Country) {
		test=report.createTest("Addcontact - valid info");
		AddContactPage ac = new AddContactPage();
		ac.LoginSection();
		ac.FeedInput01(Username);
		ac.FeedInput02(Password);
		ac.pagewait();
		ac.addcontact();
		ac.enterfirstname(Firstname);
		ac.enterlastname(Lastname);
		ac.enterdob(DOB);
		ac.sendemail(Email);
		ac.sendphno(Phno);
		ac.sendstreet1(Street1);
		ac.sendstreet2(Street2);
		ac.sendcity(City);
		ac.sendstate(State);
		ac.sendpostalcode(Postalcode);
		ac.sendcountry(Country);
		ac.submitform();
		ac.contactaddcheck();
		captureScreenshot("Add contact - test01");
		test.pass("Contact added successfully");
	}
	
	//fname and lname blank
	@Test(testName="TC02", dataProvider ="AddContactData")
	public void testcase02(String Username, String Password, String Firstname, String Lastname, 
			String DOB, String Email, String Phno, String Street1, String Street2, String City, String State, 
			String Postalcode, String Country) {
		test=report.createTest("Addcontact - fname and lname blank");
		AddContactPage ac = new AddContactPage();
		ac.LoginSection();
		ac.FeedInput01(Username);
		ac.FeedInput02(Password);
		ac.pagewait();
		ac.addcontact();
		ac.enterfirstname(Firstname);
		ac.enterlastname(Lastname);
		ac.enterdob(DOB);
		ac.sendemail(Email);
		ac.sendphno(Phno);
		ac.sendstreet1(Street1);
		ac.sendstreet2(Street2);
		ac.sendcity(City);
		ac.sendstate(State);
		ac.sendpostalcode(Postalcode);
		ac.sendcountry(Country);
		ac.submitform();
		String actualmsg=ac.ReadMessage();
		String expectedmsg ="Contact validation failed";
		Assert.assertTrue(actualmsg.contains(expectedmsg), expectedmsg);
		captureScreenshot("Add contact - test02");
		test.pass("Throwing error as expected");
	}
	
	//Phonenumber field validation
	@Test(testName="TC03", dataProvider ="AddContactData")
	public void testcase03(String Username, String Password, String Firstname, String Lastname, 
			String DOB, String Email, String Phno, String Street1, String Street2, String City, String State, 
			String Postalcode, String Country) {
		test=report.createTest("Addcontact - Phonenumber field validation");
		AddContactPage ac = new AddContactPage();
		ac.LoginSection();
		ac.FeedInput01(Username);
		ac.FeedInput02(Password);
		ac.pagewait();
		ac.addcontact();
		ac.enterfirstname(Firstname);
		ac.enterlastname(Lastname);
		ac.enterdob(DOB);
		ac.sendemail(Email);
		ac.sendphno(Phno);
		ac.sendstreet1(Street1);
		ac.sendstreet2(Street2);
		ac.sendcity(City);
		ac.sendstate(State);
		ac.sendpostalcode(Postalcode);
		ac.sendcountry(Country);
		ac.submitform();
		String actualmsg=ac.ReadMessage();
		String expectedmsg ="Phone number is invalid";
		Assert.assertTrue(actualmsg.contains(expectedmsg), expectedmsg);
		captureScreenshot("Add contact - test03");
		test.pass("Throwing error as expected");
	}
	
	//Duplicate Values
	@Test(testName="TC04", dataProvider ="AddContactData")
	public void testcase04(String Username, String Password, String Firstname, String Lastname, 
			String DOB, String Email, String Phno, String Street1, String Street2, String City, String State, 
			String Postalcode, String Country) {
	test=report.createTest("Addcontact - Duplicate Values");
	AddContactPage ac = new AddContactPage();
	ac.LoginSection();
	ac.FeedInput01(Username);
	ac.FeedInput02(Password);
	ac.pagewait();
	ac.addcontact();
	ac.enterfirstname(Firstname);
	ac.enterlastname(Lastname);
	ac.enterdob(DOB);
	ac.sendemail(Email);
	ac.sendphno(Phno);
	ac.sendstreet1(Street1);
	ac.sendstreet2(Street2);
	ac.sendcity(City);
	ac.sendstate(State);
	ac.sendpostalcode(Postalcode);
	ac.sendcountry(Country);
	ac.submitform();
	ac.contactaddcheck();
	captureScreenshot("Add contact - test04");
	test.pass("Duplicate contact added without any warning");
	}
	
	//form cleared validation
	@Test(testName="TC05", dataProvider ="AddContactData")
	public void testcase05(String Username, String Password, String Firstname, String Lastname, 
			String DOB, String Email, String Phno, String Street1, String Street2, String City, String State, 
			String Postalcode, String Country) {
		test=report.createTest("Addcontact - Form clear validation");
		AddContactPage ac = new AddContactPage();
		ac.LoginSection();
		ac.FeedInput01(Username);
		ac.FeedInput02(Password);
		ac.pagewait();
		ac.addcontact();
		ac.enterfirstname(Firstname);
		ac.enterlastname(Lastname);
		ac.enterdob(DOB);
		ac.sendemail(Email);
		ac.sendphno(Phno);
		ac.sendstreet1(Street1);
		ac.sendstreet2(Street2);
		ac.sendcity(City);
		ac.sendstate(State);
		ac.sendpostalcode(Postalcode);
		ac.sendcountry(Country);
		ac.submitform();
		ac.contactaddcheck();
		ac.formclearedvalidation();
		captureScreenshot("Add contact - test05");
		test.pass("Form cleared as expected");
	}	
	

}
