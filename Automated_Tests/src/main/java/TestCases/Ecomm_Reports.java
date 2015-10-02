package TestCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import AutomationFramework.TestSuite;
import PageObjects.DeliveryNotesPage_EComm;
import PageObjects.EcommPage;
import PageObjects.ExportDownloadPage;
import PageObjects.InvoicesPage_EComm;
import PageObjects.OrderViewPage;
import org.junit.Ignore;

public class Ecomm_Reports {

	@Ignore @Test //Invoices Page :: Page and filter checks, reset, view, print, and export
	public void I1() throws IOException, InterruptedException {
            //new driver instance
            WebDriver driver = new ChromeDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
            EcommPage eCommPage = baseTest.SUSST_SetUp("SAP Interface Log SILM1: Page and filter checks, views and reset", "G_CoUA_SILM_1 to 4");
				
            InvoicesPage_EComm invPage = eCommPage.clickInvoices();
            invPage.waitForLoad();
		
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\1Invoices Page.png"));
		
            System.out.println("Invoices page reached.");
		
            invPage.assertBaseElements();
		
            System.out.println("Checking fields...");
		
            invPage.checkFields();
		
            System.out.println("Fields checked. Entering filter criteria...");
		
            invPage.setCustName(TestSuite.custDetails[0]);
            invPage.setShipToName(TestSuite.custDetails[1]);
		
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\2Filter criteria entered.png"));
		
            System.out.println("Filter criteria entered. Resetting filter...");
		
            invPage.pressReset();
            invPage.waitForLoad();
		
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\3Filter reset.png"));
		
            System.out.println("Filter reset. Viewing invoice...");
		
            OrderViewPage viewPage = invPage.pressView();
            viewPage.waitForContent();
		
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\4Order view.png"));
		
		System.out.println("View displayed. Closing view...");
		
		viewPage.closeView();
		viewPage.waitForInvisibility();
		
		System.out.println("View closed. Printing invoice...");
		
		OrderViewPage printPage = invPage.pressPrint();
		printPage.waitForContent();
		
		//Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\5Invoice view.png"));
		
		printPage.pressPrint();
		
		invPage.waitForLoad();
		
		System.out.println("Exporting records...");
		
		ExportDownloadPage dlPage = invPage.pressExport();
		dlPage.waitForDownloadCompletion();
		
		System.out.println("Records exported.");
		
		System.out.println("----------------------------------------------------");
		
		driver.close();
		
	}
	
	@Test //Delivery Notes Page :: Page and filter checks
        public void D1() throws InterruptedException, IOException {
            //new driver instance
            WebDriver driver = new ChromeDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
            EcommPage eCommPage = baseTest.SUSST_SetUp("SAP Interface Log SILM1: Page and filter checks, views and reset", "G_CoUA_SILM_1 to 4");
				
            DeliveryNotesPage_EComm dnPage = eCommPage.clickDeliveryNotes();
            dnPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\1Delivery Notes Page.png"));
		
            System.out.println("Delivery Notes page reached.");
		
            dnPage.assertBaseElements();
		
            System.out.println("Checking fields...");
		
            dnPage.checkFields();
		
            System.out.println("Fields checked. Entering filter criteria...");
		
            dnPage.setCustName(TestSuite.custDetails[0]);
            dnPage.setRequester(TestSuite.custDetails[2]);
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\2Filter criteria entered.png"));
            
            System.out.println("Filter criteria entered. Listing orders...");
            
            dnPage.pressSearch();
            dnPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\3Orders listed.png"));
            
            System.out.println("Orders listed. Resetting filter...");
            
            dnPage.pressReset();
            dnPage.waitForLoad();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\4Filter reset.png"));
            
            System.out.println("Filter reset. Viewing record...");
            
            OrderViewPage viewPage = dnPage.pressView();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\5View displayed.png"));
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            
            System.out.println("View closed. Pressing print...");
            
            OrderViewPage printPage = dnPage.pressPrint();
            printPage.waitForContent();
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\6Print view.png"));
            
            System.out.println("Print view displayed. Sending item to printer...");
            
            printPage.pressPrint();
            
            System.out.println("Exporting records...");
            
            ExportDownloadPage dlPage = dnPage.pressExport();           
            dlPage.waitForDownloadCompletion();
            
            System.out.println("Records exported.");
            
            driver.close();
            
            System.out.println("----------------------------------------------------");
        }
	
        @Test public void SoP1() {
            
        }
}
