package com.cms.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cms.pages.EditContactPage;
import com.cms.start.Start;

public class EditContactTest extends Start {

	@Test
	public void testcase01() {
		test=report.createTest("Editcontact - Email id update");
		EditContactPage ec = new EditContactPage();
		String contactname ="Priya Raman";
		String updatedemail="priyaupdated@test.com";
		ec.LoginSection();
		ec.FeedInput01("yoga@test5.com");
		ec.FeedInput02("Test@123");
		ec.pagewait();
		ec.selectContactByName(contactname);
		ec.editcontact();
		ec.updateemailid(updatedemail);
		ec.editsubmit();
		ec.returntocontactlist();
		boolean updated = ec.isEmailUpdatedInTable(contactname, updatedemail);
		Assert.assertTrue(updated, "email id was not updated");
		captureScreenshot("Edit contact - test01");
		test.pass("Email id updated as expected");
		
	}
	
	//Cancelling edit
	@Test
	public void testcase02() {
		test=report.createTest("Edit contact - cancelling edit");
		EditContactPage ec = new EditContactPage();
		String contactname ="Priya Raman";
		String updatedemail="sanjayupdate@test.com";
		ec.LoginSection();
		ec.FeedInput01("yoga@test5.com");
		ec.FeedInput02("Test@123");
		ec.pagewait();
		ec.selectContactByName(contactname);
		ec.editcontact();
		ec.updateemailid(updatedemail);
		ec.editcancel();
		ec.returntocontactlist();
		boolean isoriginalemailpresent = ec.isEmailUpdatedInTable(contactname, updatedemail);
		Assert.assertFalse(isoriginalemailpresent, "Cancel failed email id got updated");
		captureScreenshot("Edit contact - test02");
		test.pass("Email id not updated");
	}
	
	
	//lastname blank
	@Test
	public void testcase03() {
		test=report.createTest("Edit contact - lastname blank");
		EditContactPage ec = new EditContactPage();
		String contactname ="Sanjay Koushik";
		ec.LoginSection();
		ec.FeedInput01("yoga@test5.com");
		ec.FeedInput02("Test@123");
		ec.pagewait();
		ec.selectContactByName(contactname);
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





