package com.cms.tests;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cms.base.BaseTest;
import com.cms.pages.DeleteContactPage;
import com.cms.start.Start;

public class DeleteContactTest extends Start {
	
	@DataProvider(name = "DeleteContactData")
	public Object[][] signupdata(Method method){
		String testcase = method.getAnnotation(Test.class).testName();
			return BaseTest.getDataForTest("DeleteContactData", testcase);
	
	}

	//delete contact
	@Test (testName= "TC01", dataProvider = "DeleteContactData")
	public void testcase01(String Username, String Password, String Contactname) {
		test=report.createTest("Delecte Contact - delete validation");
		DeleteContactPage dp = new DeleteContactPage();
		dp.LoginSection();
		dp.FeedInput01(Username);
		dp.FeedInput02(Password);
		dp.pagewait();
		dp.selectContactByName(Contactname);
		dp.deletecontact();
		dp.acceptalert();
		dp.verifydeletecontact(Contactname);
		captureScreenshot("Delete contact - test01");
		test.pass("Contact deleted as expected");
		
	}
	
	//verify alert
	@Test(testName= "TC02", dataProvider = "DeleteContactData")
	public void testcase02(String Username, String Password, String Contactname) {
		test=report.createTest("Delecte Contact - Verify alert");
		DeleteContactPage dp = new DeleteContactPage();
		dp.LoginSection();
		dp.FeedInput01(Username);
		dp.FeedInput02(Password);
		dp.pagewait();
		dp.selectContactByName(Contactname);
		dp.deletecontact();
		dp.verifyalert();
		//captureScreenshot("Delete contact - test02");
		test.pass("Generating alert as expected");
	}
	
	@Test(testName= "TC03", dataProvider = "DeleteContactData")
	public void testcase03(String Username, String Password, String Contactname) {
		test=report.createTest("Delecte Contact - Verify alert");
		DeleteContactPage dp = new DeleteContactPage();
		dp.LoginSection();
		dp.FeedInput01(Username);
		dp.FeedInput02(Password);
		dp.pagewait();
		dp.selectContactByName(Contactname);
		dp.deletecontact();
		dp.acceptalert();
		dp.refreshpage();
		dp.verifydeletecontact(Contactname);
		captureScreenshot("Delete contact - test03");
		test.pass("Deleted contact removed from the table");
		
	}
}
