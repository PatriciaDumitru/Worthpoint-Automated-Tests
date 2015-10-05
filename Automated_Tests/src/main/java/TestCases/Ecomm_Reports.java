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
import PageObjects.MyReportsPage_EComm;
import PageObjects.OrderViewPage;
import PageObjects.OutstandingPaymentsPage_EComm;
import PageObjects.SaveReportPage;
import PageObjects.SummaryOfPurchasePage_EComm;
import org.junit.Ignore;

public class Ecomm_Reports {

	@Ignore @Test //Invoices Page :: Page and filter checks, reset, view, print, and export
	public void I1() throws IOException, InterruptedException {
            //new driver instance
            WebDriver driver = new ChromeDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
            EcommPage eCommPage = baseTest.SUSST_SetUp("Invoice Report I1: Page and filter checks, reset, view, print, export", "G_R_CU_SUSST_1");
				
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
	
	@Ignore @Test //Delivery Notes Page :: Page and filter checks, view, print, and export
        public void D1() throws InterruptedException, IOException {
            //new driver instance
            WebDriver driver = new ChromeDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
            EcommPage eCommPage = baseTest.SUSST_SetUp("Delivery Notes Report D1: Page and filter checks, reset, view, print, export", "G_R_CU_SUSST_2");
				
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
	
        @Ignore @Test //Summary of Purchases Page :: Page and filter checks,reset, view and export
        public void SoP1() throws IOException, InterruptedException {
            //new driver instance
            WebDriver driver = new ChromeDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
            EcommPage eCommPage = baseTest.SUSST_SetUp("Summary of Purchases SoP1: Page and filter checks, views and reset", "G_R_CU_SUSST_3");
				
            SummaryOfPurchasePage_EComm spPage = eCommPage.clickSummaryOfPurchases();
            spPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\1Summary of Purchases Page.png"));
		
            System.out.println("Summary of Purchases page reached.");
		
            spPage.assertBaseElements();
		
            System.out.println("Checking fields...");
		
            spPage.checkFields();
		
            System.out.println("Fields checked. Entering filter criteria...");
            
            spPage.setCustName(TestSuite.custDetails[0]);
            spPage.setBrand(TestSuite.expBrand);
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\2Filter criteria entered.png"));
            
            System.out.println("Criteria entered. Listing records...");
            
            spPage.pressSearch();
            spPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\3Records listed.png"));
            
            System.out.println("Records listed. Resetting filter...");
            
            spPage.pressReset();
            spPage.waitForLoad();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\4Filter reset.png"));
            
            System.out.println("Filter reset. Viewing top item...");
            
            OrderViewPage viewPage = spPage.pressView();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\5View displayed.png"));
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            
            System.out.println("View closed. Exporting records...");

            ExportDownloadPage dlPage = spPage.pressExport();
            dlPage.waitForDownloadCompletion();
            
            System.out.println("Records exported.");
            
            System.out.println("----------------------------------------------------");
            
            driver.close();
        }
        
        @Ignore @Test //Outstanding Payments Page :: Page and filter checks, reset, view, and export
        public void OP1() throws IOException {
            //new driver instance
            WebDriver driver = new ChromeDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
            EcommPage eCommPage = baseTest.SUSST_SetUp("Outstanding Payments Page OP1: Page and filter checks, reset, view, and export", "G_R_CU_SUSST_4");
				
            OutstandingPaymentsPage_EComm opPage = eCommPage.clickOutstandingPayments();
            opPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\1Outstanding Payments Page.png"));
		
            System.out.println("Outstanding Payments page reached.");
		
            opPage.assertBaseElements();
		
            System.out.println("Checking fields...");
		
            opPage.checkFields();
		
            System.out.println("Fields checked. Entering filter criteria...");
            
            opPage.setCustName(TestSuite.custDetails[0]);
            opPage.pressOverdue30();
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\2Filter criteria entered.png"));
            
            System.out.println("Filter criteria entered. Listing records...");
            
            opPage.pressSearch();
            opPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\3Records listed.png"));
            
            System.out.println("Records listed. Resetting filter...");
            
            opPage.pressReset();
            opPage.waitForLoad();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\4Filter reset.png"));
            
            System.out.println("Filter reset. Viewing oustsanding payment...");
            
            OrderViewPage viewPage = opPage.pressView();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\5View displayed.png"));
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("View closed. Re-entering filter criteria...");
            
            opPage.pressOverdue90();
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\6Overdue by 90 selected.png"));
            
            System.out.println("Criteria entered. Listing orders...");
            
            opPage.pressSearch();
            opPage.waitForLoad();
            
            System.out.println("Orders listed. Exporting file...");
            
            ExportDownloadPage dlPage = opPage.pressExport();
            dlPage.waitForDownloadCompletion();
            
            System.out.println("File exported.");
            
            System.out.println("----------------------------------------------------");
            
            driver.close();
            
        } 
        
        @Test //My Reports Page :: Page and filter checks, print, export, save, and reset
        public void MR1() throws IOException {
            //new driver instance
            WebDriver driver = new ChromeDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_SUSST_Base baseTest = new Ecomm_SUSST_Base(driver);
            EcommPage eCommPage = baseTest.SUSST_SetUp("My Report Page MR1: Page and filter checks, reset, print, save, and export", "G_R_CU_SUSST_5");
				
            MyReportsPage_EComm mrPage = eCommPage.clickMyReports();
            mrPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\1My Reports Page.png"));
		
            System.out.println("My Reports page reached.");
		
            mrPage.assertBaseElements();
		
            System.out.println("Checking fields...");
		
            mrPage.checkFields();
		
            System.out.println("Fields checked. Selecting all items...");
            
            mrPage.setSelectAll();
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\2All fields selected.png"));
            
            System.out.println("All selected. Resetting filter...");
            
            mrPage.pressReset();
            mrPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\3Filter reset.png"));
            
            System.out.println("Filter reset. Selecting PO Number, Article, Brand, Ticket, Invoice No, Delivery No...");
            
            mrPage.setPONumber();
            mrPage.setArticle();
            mrPage.setBrand();
            mrPage.setTicket();
            mrPage.setInvoiceNo();
            mrPage.setDeliveryNo();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\4Fields set.png"));
            
            System.out.println("Items selected. Entering filter criteria...");
            
            mrPage.setCustName(TestSuite.custDetails[0]);
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\5Filter criteria entered.png"));
            
            System.out.println("Criteria entered. Printing report...");
            
            OrderViewPage viewPage = mrPage.pressPrint();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\6Print view displayed.png"));
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("Exporting file...");
            
            ExportDownloadPage dlPage = mrPage.pressExport();
            
            System.out.println("Export view displayed. Selecting 'no' to wait for download to complete");
            dlPage.pressNo();
            
            dlPage.waitForDownloadCompletion();
            
            System.out.println("File exported. Resetting filter...");
            
            mrPage.pressReset();
            mrPage.waitForLoad();
            
            System.out.println("Filter reset. Saving report...");
            
            SaveReportPage srPage = mrPage.pressSaveReport();
            srPage.waitForContent();
            
            //Take a screenshot
            File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile7,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\7Save report page.png"));
            
            System.out.println("Page reached. Entering title...");
            
            srPage.setTitle(TestSuite.reportTitle);
            
            //Take a screenshot
            File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile8,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\8Title entered.png"));
            
            srPage.pressSave();
            
            //Take a screenshot
            File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile8,new File(TestSuite.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\9Report saved.png"));
            
            String message = mrPage.getFlashMessage().getText();
            if (message.contains("has been saved")) {
                System.out.println("Report successfully saved");
            } else {
                System.out.println("***Unexpected message received: "+message+"***");
            }
            
            System.out.println("----------------------------------------------------");
            driver.close();
        }
}
