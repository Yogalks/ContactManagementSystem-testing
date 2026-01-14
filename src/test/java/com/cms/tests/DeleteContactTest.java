package com.cms.tests;

import org.testng.annotations.Test;

import com.cms.pages.DeleteContactPage;
import com.cms.start.Start;

public class DeleteContactTest extends Start {

	//delete contact
	@Test
	public void testcase01() {
		test=report.createTest("Delecte Contact - delete validation");
		DeleteContactPage dp = new DeleteContactPage();
		String Contactname = "Priya Raman";
		dp.LoginSection();
		dp.FeedInput01("yoga@test5.com");
		dp.FeedInput02("Test@123");
		dp.pagewait();
		dp.selectContactByName(Contactname);
		dp.deletecontact();
		dp.acceptalert();
		dp.verifydeletecontact(Contactname);
		captureScreenshot("Delete contact - test01");
		test.pass("Contact deleted as expected");
		
	}
	
	//verify alert
	@Test
	public void testcase02() {
		test=report.createTest("Delecte Contact - Verify alert");
		DeleteContactPage dp = new DeleteContactPage();
		String Contactname = "Priya Raman";
		dp.LoginSection();
		dp.FeedInput01("yoga@test5.com");
		dp.FeedInput02("Test@123");
		dp.pagewait();
		dp.selectContactByName(Contactname);
		dp.deletecontact();
		dp.verifyalert();
		//captureScreenshot("Delete contact - test02");
		test.pass("Generating alert as expected");
	}
	
	@Test
	public void testcase03() {
		test=report.createTest("Delecte Contact - Verify alert");
		DeleteContactPage dp = new DeleteContactPage();
		String Contactname = "Priya Raman";
		dp.LoginSection();
		dp.FeedInput01("yoga@test6.com");
		dp.FeedInput02("Test@123");
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
