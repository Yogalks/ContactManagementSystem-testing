package com.cms.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cms.base.BaseTest;
import com.cms.pages.EditContactPage;
import com.cms.start.Start;

public class EditContactTest extends Start {
	
	@DataProvider(name = "EditContactData")
	public Object[][] signupdata(Method method){
		String testcase = method.getAnnotation(Test.class).testName();
			return BaseTest.getDataForTest("EditContactData", testcase);
	}

	@Test (testName="TC01", dataProvider ="EditContactData")
	public void testcase01(String Username, String Password, String Contactname, String Updatedemail) {
		test=report.createTest("Editcontact - Email id update");
		EditContactPage ec = new EditContactPage();
		ec.LoginSection();
		ec.FeedInput01(Username);
		ec.FeedInput02(Password);
		ec.pagewait();
		ec.selectContactByName(Contactname);
		ec.editcontact();
		ec.updateemailid(Updatedemail);
		ec.editsubmit();
		ec.returntocontactlist();
		boolean updated = ec.isEmailUpdatedInTable(Contactname, Updatedemail);
		Assert.assertTrue(updated, "email id was not updated");
		captureScreenshot("Edit contact - test01");
		test.pass("Email id updated as expected");
		
	}
	
	//Cancelling edit
	@Test(testName="TC02", dataProvider ="EditContactData")
	public void testcase02(String Username, String Password, String Contactname, String Updatedemail) {
		test=report.createTest("Edit contact - cancelling edit");
		EditContactPage ec = new EditContactPage();
		ec.LoginSection();
		ec.FeedInput01(Username);
		ec.FeedInput02(Password);
		ec.pagewait();
		ec.selectContactByName(Contactname);
		ec.editcontact();
		ec.updateemailid(Updatedemail);
		ec.editcancel();
		ec.returntocontactlist();
		boolean isoriginalemailpresent = ec.isEmailUpdatedInTable(Contactname, Updatedemail);
		Assert.assertFalse(isoriginalemailpresent, "Cancel failed email id got updated");
		captureScreenshot("Edit contact - test02");
		test.pass("Email id not updated");
	}
	
	
	//lastname blank
	@Test (testName="TC03", dataProvider ="EditContactData")
	public void testcase03(String Username, String Password, String Contactname, String Updatedemail) {
		test=report.createTest("Edit contact - lastname blank");
		EditContactPage ec = new EditContactPage();
		ec.LoginSection();
		ec.FeedInput01(Username);
		ec.FeedInput02(Password);
		ec.pagewait();
		ec.selectContactByName(Contactname);
		ec.editcontact();
		ec.editlastname();
		ec.editsubmit();
		String actualmsg= ec.ReadMessage();
		String expectedmsg = "lastName: Path `lastName` is required";
		Assert.assertTrue(actualmsg.contains(expectedmsg), "Expected error msg not displayed");
		captureScreenshot("Edit contact - test03");
		test.pass("Throwing error as expected");
		
		
		
	}
}





