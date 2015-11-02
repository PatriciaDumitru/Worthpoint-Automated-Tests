package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.Ecomm_DeliveryNotesPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ExportDownloadPage;
import PageObjects.Ecomm_InvoicesPage;
import PageObjects.Ecomm_MyReportsPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingPaymentsPage;
import PageObjects.Ecomm_SaveReportPage;
import PageObjects.Ecomm_SummaryOfPurchasePage;
import com.coats.selenium.DriverFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Ecomm_Reports_Test extends DriverFactory {
    
	@Test //Invoices Page :: SUMST :: Page and filter checks, reset, view, print, and export
	(groups = {"eComm"})
        public void SUMST_I1() throws IOException, InterruptedException, Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Invoice Report SUMST_I1: Page and filter checks, reset, view, print, export", "G_R_CU_SUMST_1");
				
            Ecomm_InvoicesPage invPage = eCommPage.clickInvoices();
            invPage.waitForLoad();
		
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\1Invoices Page.png"));
		
            System.out.println("Invoices page reached.");
		
            invPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("Invoice Report Page: Title not as expected",invPage.getBreadcrumbText3().equals("Reports | Invoices"));
            
            System.out.println("Title checked. Checking fields...");
		
            invPage.checkFields_SUMST();
		
            System.out.println("Fields checked. Entering filter criteria...");
		
            invPage.setCustName(DataItems.custDetails[0]);
            invPage.setShipToName(DataItems.custDetails[1]);
            
            System.out.println("Filter criteria entered. Listing invoices...");
            
            invPage.pressSearch();
            invPage.waitForElement();
            
            System.out.println("Invoices listed. Checking filtration...");
            
            AssertJUnit.assertTrue("Invoice Page: Records displayed are not consistent with filter",
                    invPage.checkFiltration(invPage.shipToPart1,invPage.shipToPart2 , invPage.noRecords,DataItems.conOrdDetails[1], 3));
		
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\2Filter criteria entered.png"));
		
            System.out.println("Filtration checked. Resetting filter...");
		
            invPage.pressReset();
            invPage.waitForLoad();
		
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\3Filter reset.png"));
		
            System.out.println("Filter reset. Viewing invoice...");
		
            Ecomm_OrderViewPage viewPage = invPage.pressView();
            viewPage.waitForContent();
		
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\4Order view.png"));
		
		System.out.println("View displayed. Closing view...");
		
		viewPage.closeView();
		viewPage.waitForInvisibility();
		
		System.out.println("View closed. Printing invoice...");
		
		Ecomm_OrderViewPage printPage = invPage.pressPrint();
		printPage.waitForContent();
		
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\5Invoice view.png"));
		
		printPage.pressPrint();
		invPage.waitForLoad();
                
                System.out.println("Re-entering filter criteria...");
                
                invPage.setCustName(DataItems.custDetails[0]);
                invPage.setShipToName(DataItems.custDetails[1]);
		
		System.out.println("Exporting records...");
		
		Ecomm_ExportDownloadPage dlPage = invPage.pressExport();
		dlPage.waitForDownloadCompletion();
		
		System.out.println("Records exported.");
		
	}
        
        @Test //Invoices Page :: SUSST :: Page and filter checks, reset, view, print, and export
        (groups = {"eComm"})
        public void SUSST_I1() throws Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Invoice Report SUSST_I1: Page and filter checks, reset, view, print, export", "G_R_CU_SUSST_1",DataItems.validCustUsername,DataItems.validCustPassword);
				
            Ecomm_InvoicesPage invPage = eCommPage.clickInvoices();
            invPage.waitForLoad();
		
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Invoices\\1Invoices Page.png"));
		
            System.out.println("Invoices page reached.");
		
            invPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("Invoice Report Page: Title not as expected",invPage.getBreadcrumbText3().equals("Reports | Invoices"));
            
            System.out.println("Title checked. Checking fields...");
		
            invPage.checkFields_SUSST();
		
            System.out.println("Fields checked. Entering filter criteria...");
            
            invPage.setShipToName(DataItems.conOrdDetails[1]);
		
            System.out.println("Filter criteria entered. Listing invoices...");
            
            invPage.pressSearch();
            invPage.waitForElement();
            
            System.out.println("Invoices listed. Checking filtration...");
            
            AssertJUnit.assertTrue("Invoice Page: Records displayed are not consistent with filter",
                    invPage.checkFiltration(invPage.shipToPart1,invPage.shipToPart2 , invPage.noRecords,DataItems.conOrdDetails[1], 3));
            
            System.out.println("Filtration checked. Resetting filter...");
		
            invPage.pressReset();
            invPage.waitForLoad();
		
            System.out.println("Filter reset. Viewing invoice...");
		
            Ecomm_OrderViewPage viewPage = invPage.pressView();
            viewPage.waitForContent();
	
            System.out.println("View displayed. Closing view...");
		
            viewPage.closeView();
            viewPage.waitForInvisibility();
		
            System.out.println("View closed. Printing invoice...");
		
            Ecomm_OrderViewPage printPage = invPage.pressPrint();
            printPage.waitForContent();
		
            printPage.pressPrint();
            
            invPage.waitForLoad();
		
            System.out.println("Exporting records...");
		
            Ecomm_ExportDownloadPage dlPage = invPage.pressExport();
            dlPage.waitForDownloadCompletion();
		
            System.out.println("Records exported.");
		
        }
	
	@Test //Delivery Notes Page :: SUMST :: Page and filter checks, view, print, and export
        (groups = {"eComm"})
        public void SUMST_D1() throws InterruptedException, IOException, Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Delivery Notes Report SUMST_D1: Page and filter checks, reset, view, print, export", "G_R_CU_SUMST_2");
				
            Ecomm_DeliveryNotesPage dnPage = eCommPage.clickDeliveryNotes();
            dnPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\1Delivery Notes Page.png"));
		
            System.out.println("Delivery Notes page reached.");
		
            dnPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("Delivery Notes Page: Title not displayed as expected",dnPage.getBreadcrumbText3().equals("Reports | Delivery Notes"));
            
            System.out.println("Title checked. Checking fields...");
		
            dnPage.checkFields_SUMST();
		
            System.out.println("Fields checked. Entering filter criteria...");
		
            dnPage.setCustName(DataItems.custDetails[0]);
            dnPage.setRequester(DataItems.custDetails[2]);
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\2Filter criteria entered.png"));
            
            System.out.println("Filter criteria entered. Listing orders...");
            
            dnPage.pressSearch();
            dnPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\3Orders listed.png"));
            
            System.out.println("Orders listed. Checking filtration...");
            
            AssertJUnit.assertTrue("Delivery Notes Report Page: Records displayed are inconsistent with filter criteria",
                    dnPage.checkFiltration(dnPage.requesterPart1, dnPage.requesterPart2, dnPage.noRecords, DataItems.custDetails[2], 3));
            
            System.out.println("Filter checked. Resetting filter...");
            
            dnPage.pressReset();
            dnPage.waitForLoad();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\4Filter reset.png"));
            
            System.out.println("Filter reset. Viewing record...");
            
            Ecomm_OrderViewPage viewPage = dnPage.pressView();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\5View displayed.png"));
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            
            System.out.println("View closed. Pressing print...");
            
            Ecomm_OrderViewPage printPage = dnPage.pressPrint();
            printPage.waitForContent();
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\6Print view.png"));
            
            System.out.println("Print view displayed. Sending item to printer...");
            
            printPage.pressPrint();
            
            System.out.println("Exporting records...");
            
            Ecomm_ExportDownloadPage dlPage = dnPage.pressExport();           
            dlPage.waitForDownloadCompletion();
            
            System.out.println("Records exported.");
        }
        
        @Test //Delivery Notes Page :: SUSST :: Page and filter checks, view, print, and export
        (groups = {"eComm"})
        public void SUSST_D1() throws InterruptedException, IOException, Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Delivery Notes Report SUSST_D1: Page and filter checks, reset, view, print, export", 
                    "G_R_CU_SUSST_2",DataItems.validCustUsername,DataItems.validCustPassword);
				
            Ecomm_DeliveryNotesPage dnPage = eCommPage.clickDeliveryNotes();
            dnPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Delivery Notes\\1Delivery Notes Page.png"));
		
            System.out.println("Delivery Notes page reached.");
		
            dnPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("Delivery Notes Page: Title not displayed as expected",dnPage.getBreadcrumbText3().equals("Reports | Delivery Notes"));
            
            System.out.println("Title checked. Checking fields...");
		
            dnPage.checkFields_SUSST();
		
            System.out.println("Fields checked. Entering filter criteria...");
		
            dnPage.setCustPO(DataItems.conOrdDetails[4]);
            
            System.out.println("Filter criteria entered. Listing orders...");
            
            dnPage.pressSearch();
            dnPage.waitForLoad();
            
            System.out.println("Orders listed. Checking filtration...");
            
            AssertJUnit.assertTrue("Delivery Notes Report Page: Records displayed are inconsistent with filter criteria",
                    dnPage.checkFiltration(dnPage.requesterPart1, dnPage.requesterPart2, dnPage.noRecords, DataItems.custDetails[2], 3));
            
            System.out.println("Filtration checked. Resetting filter...");
            
            dnPage.pressReset();
            dnPage.waitForLoad();
            
            System.out.println("Filter reset. Viewing record...");
            
            Ecomm_OrderViewPage viewPage = dnPage.pressView();
            viewPage.waitForContent();
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            
            System.out.println("View closed. Pressing print...");
            
            Ecomm_OrderViewPage printPage = dnPage.pressPrint();
            printPage.waitForContent();
            
            System.out.println("Print view displayed. Sending item to printer...");
            
            printPage.pressPrint();
            
            System.out.println("Exporting records...");
            
            Ecomm_ExportDownloadPage dlPage = dnPage.pressExport();           
            dlPage.waitForDownloadCompletion();
            
            System.out.println("Records exported.");
        }
	
        @Test //Summary of Purchases Page :: SUMST :: Page and filter checks,reset, view and export
        (groups = {"eComm"})
        public void SUMST_SoP1() throws IOException, InterruptedException, Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Summary of Purchases SUMST_SoP1: Page and filter checks, views and reset", "G_R_CU_SUMST_3");
				
            Ecomm_SummaryOfPurchasePage spPage = eCommPage.clickSummaryOfPurchases();
            spPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\1Summary of Purchases Page.png"));
            
            System.out.println("Summary of Purchases page reached.");
		
            spPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("Summary of Purchase Page: Title not displayed as expected",spPage.getBreadcrumbText3().equals("Reports | Summary of Purchase"));
            
            System.out.println("Title checked. Checking fields...");
		
            spPage.checkFields_SUMST();
		
            System.out.println("Fields checked. Entering filter criteria...");
            
            String tempRequester = "andy wisaksana";
            
            spPage.setCustName(DataItems.custDetails[0]);
            spPage.setRequester(tempRequester);
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\2Filter criteria entered.png"));
            
            System.out.println("Criteria entered. Listing records...");
            
            spPage.pressSearch();
            spPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\3Records listed.png"));
            
            System.out.println("Records listed. Checking filtration...");
            
            spPage.checkFiltration(spPage.brandPart1, spPage.brandPart2, spPage.noRecords,tempRequester, 3);
            
            System.out.println("Filtration checked. Resetting filter...");
            
            spPage.pressReset();
            spPage.waitForLoad();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\4Filter reset.png"));
            
            System.out.println("Filter reset. Viewing top item...");
            
            Ecomm_OrderViewPage viewPage = spPage.pressView();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\5View displayed.png"));
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            
            System.out.println("View closed. Exporting records...");

            Ecomm_ExportDownloadPage dlPage = spPage.pressExport();
            dlPage.waitForDownloadCompletion();
            
            System.out.println("Records exported.");

        }
        
        @Test //Summary of Purchases Page :: SUSST :: Page and filter checks,reset, view and export
        (groups = {"eComm"})
        public void SUSST_SoP1() throws IOException, InterruptedException, Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Summary of Purchases SUSST_SoP1: Page and filter checks, views and reset", "G_R_CU_SUSST_3",
                    DataItems.validCustUsername, DataItems.validCustPassword);
				
            Ecomm_SummaryOfPurchasePage spPage = eCommPage.clickSummaryOfPurchases();
            spPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\1Summary of Purchases Page.png"));
            
            System.out.println("Summary of Purchases page reached.");
		
            spPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("Summary of Purchase Page: Title not displayed as expected",spPage.getBreadcrumbText3().equals("Reports | Summary of Purchase"));
            
            System.out.println("Title checked. Checking fields...");
		
            spPage.checkFields_SUSST();
		
            System.out.println("Fields checked. Entering filter criteria...");
            
            spPage.setBrand(DataItems.expBrand);
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\2Filter criteria entered.png"));
            
            System.out.println("Criteria entered. Listing records...");
            
            spPage.pressSearch();
            spPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\3Records listed.png"));
            
            System.out.println("Records listed. Checking filtration...");
            
            spPage.checkFiltration(spPage.brandPart1, spPage.brandPart2, spPage.noRecords, DataItems.expBrand, 3);
            
            System.out.println("Filtration checked. Resetting filter...");
            
            spPage.pressReset();
            spPage.waitForLoad();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\4Filter reset.png"));
            
            System.out.println("Filter reset. Viewing top item...");
            
            Ecomm_OrderViewPage viewPage = spPage.pressView();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Summary of Purchases\\5View displayed.png"));
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            
            System.out.println("View closed. Exporting records...");

            Ecomm_ExportDownloadPage dlPage = spPage.pressExport();
            dlPage.waitForDownloadCompletion();
            
            System.out.println("Records exported.");

        }
        
        @Test //Outstanding Payments Page :: SUMST :: Page and filter checks, reset, view, and export
        (groups = {"eComm"})
        public void SUMST_OP1() throws IOException, Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Outstanding Payments Page SUMST_OP1: Page and filter checks, reset, view, and export", "G_R_CU_SUMST_4");
				
            Ecomm_OutstandingPaymentsPage opPage = eCommPage.clickOutstandingPayments();
            opPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\1Outstanding Payments Page.png"));
		
            System.out.println("Outstanding Payments page reached.");
		
            opPage.assertBaseElements();
            
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("Outstanding Payments Page: Title not displayed as expected",opPage.getBreadcrumbText3().equals("Reports | Outstanding Payments"));
		
            System.out.println("Title checked. Checking fields...");
		
            opPage.checkFields_SUMST();
		
            System.out.println("Fields checked. Entering filter criteria...");
            
            opPage.setCustName(DataItems.custDetails[0]);
            opPage.pressOverdue30();
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\2Filter criteria entered.png"));
            
            System.out.println("Filter criteria entered. Listing records...");
            
            opPage.pressSearch();
            opPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\3Records listed.png"));
            
            System.out.println("Records listed. Resetting filter...");
            
            opPage.pressReset();
            opPage.waitForLoad();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\4Filter reset.png"));
            
            System.out.println("Filter reset. Viewing oustsanding payment...");
            
            Ecomm_OrderViewPage viewPage = opPage.pressView();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\5View displayed.png"));
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("View closed. Re-entering filter criteria...");
            
            opPage.setCustName(DataItems.custDetails[0]);
            opPage.pressOverdue90();
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\6Overdue by 90 selected.png"));
            
            System.out.println("Criteria entered. Listing orders...");
            
            opPage.pressSearch();
            opPage.waitForLoad();
            
            System.out.println("Orders listed.");
            
            if (opPage.recordsPresent()) {
                System.out.println("Exporting file...");
                
                Ecomm_ExportDownloadPage dlPage = opPage.pressExport();
                dlPage.waitForDownloadCompletion();
            
                System.out.println("File exported.");
            } else {
                System.out.println("Export not tested, as no records are present");
            } 
        } 
        
        @Test //Outstanding Payments Page :: SUSST :: Page and filter checks, reset, view, and export
        (groups = {"eComm"})
        public void SUSST_OP1() throws IOException, Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("Outstanding Payments Page SUSST_OP1: Page and filter checks, reset, view, and export", "G_R_CU_SUSST_4",DataItems.validCustUsername,DataItems.validCustPassword);
				
            Ecomm_OutstandingPaymentsPage opPage = eCommPage.clickOutstandingPayments();
            opPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\1Outstanding Payments Page.png"));
		
            System.out.println("Outstanding Payments page reached.");
		
            opPage.assertBaseElements();
            
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("Outstanding Payments Page: Title not displayed as expected",opPage.getBreadcrumbText3().equals("Reports | Outstanding Payments"));
		
            System.out.println("Title checked. Checking fields...");
		
            opPage.checkFields_SUSST();
		
            System.out.println("Fields checked. Entering filter criteria...");

            opPage.pressOverdue30SUSST();
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\2Filter criteria entered.png"));
            
            System.out.println("Filter criteria entered. Listing records...");
            
            opPage.pressSearch();
            opPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\3Records listed.png"));
            
            System.out.println("Records listed. Resetting filter...");
            
            opPage.pressReset();
            opPage.waitForLoad();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\4Filter reset.png"));
            
            System.out.println("Filter reset. Viewing oustsanding payment...");
            
            Ecomm_OrderViewPage viewPage = opPage.pressView();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\5View displayed.png"));
            
            System.out.println("View displayed. Closing view...");
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("View closed. Re-entering filter criteria...");
            
            opPage.pressOverdue90SUSST();
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\Outstanding Payments\\6Overdue by 90 selected.png"));
            
            System.out.println("Criteria entered. Listing orders...");
            
            opPage.pressSearch();
            opPage.waitForLoad();
            
            System.out.println("Orders listed. Exporting file...");
            
            Ecomm_ExportDownloadPage dlPage = opPage.pressExport();
            dlPage.waitForDownloadCompletion();
            
            System.out.println("File exported.");
            
        } 
        
        @Test //My Reports Page :: SUMST :: Page and filter checks, print, export, save, and reset
        (groups = {"eComm"})
        public void SUMST_MR1() throws IOException, Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("My Report Page SUMST_MR1: Page and filter checks, reset, print, save, and export", "G_R_CU_SUMST_5");
				
            System.out.println("Navigating to My Reports page...");
            
            Ecomm_MyReportsPage mrPage = eCommPage.clickMyReports();
            mrPage.waitForLoad();
            
            //Take a screenshot
            File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\1My Reports Page.png"));
		
            System.out.println("My Reports page reached.");
		
            mrPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("My Reports (Customer care) Page: Title not displayed as expected",mrPage.getBreadcrumbText().equals("Reports | Customer Care"));
            
            System.out.println("Title checked. Checking fields...");
		
            mrPage.checkFields_SUMST();
		
            System.out.println("Fields checked. Selecting all items...");
            
            mrPage.setSelectAll();
            
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\2All fields selected.png"));
            
            System.out.println("All selected. Resetting filter...");
            
            mrPage.pressReset();
            mrPage.waitForLoad();
            
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\3Filter reset.png"));
            
            System.out.println("Filter reset. Selecting PO Number, Article, Brand, Ticket, Invoice No, Delivery No...");
            
            mrPage.setPONumber();
            mrPage.setArticle();
            mrPage.setBrand();
            mrPage.setTicket();
            mrPage.setInvoiceNo();
            mrPage.setDeliveryNo();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\4Fields set.png"));
            
            System.out.println("Items selected. Entering filter criteria...");
            
            mrPage.setCustName(DataItems.custDetails[0]);
            mrPage.setOrderNo(DataItems.bulkOrderNo);
            
            //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\5Filter criteria entered.png"));
            
            System.out.println("Criteria entered. Printing report...");
            
            Ecomm_OrderViewPage viewPage = mrPage.pressPrint();
            viewPage.waitForContent();
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\6Print view displayed.png"));
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("Exporting file...");
            
            Ecomm_ExportDownloadPage dlPage = mrPage.pressExport();        
            System.out.println("Export view displayed.");           
            dlPage.waitForDownloadCompletion();
            
            System.out.println("File exported. Resetting filter...");
            
            mrPage.pressReset();
            mrPage.waitForLoad();
            
            System.out.println("Filter reset. Checking for save button...");
            
            if (mrPage.getSaveMyReportButton().getText().contains("Save")) {
                Ecomm_SaveReportPage srPage = mrPage.pressSaveReport();
                srPage.waitForContent();

                //Take a screenshot
                File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\7Save report page.png"));

                System.out.println("Page reached. Entering title...");

                srPage.setTitle(DataItems.reportTitle);

                //Take a screenshot
                File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\8Title entered.png"));

                srPage.pressSave();
                srPage.waitForInvisibility();

                //Take a screenshot
                File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Reports\\My Reports\\9Report saved.png"));

                String message = mrPage.getFlashMessage().getText();
                if (message.contains("has been saved")) {
                    System.out.println("Report successfully saved");
                } else {
                    System.out.println("***Unexpected message received: "+message+"***");
                }
            } else {
                System.out.println("Save button not present, Save Report feature not tested");
            }       
        }
        
        @Test //My Reports Page :: SUSST :: Page and filter checks, print, export, save, and reset
        (groups = {"eComm"})
        public void SUSST_MR1() throws IOException, Exception {
            //new driver instance
            WebDriver driver = getDriver();
		
            //New eComm base test to handle log-in and navigation
            Ecomm_Base baseTest = new Ecomm_Base(driver);
            Ecomm_MainPage eCommPage = baseTest.SUSST_SetUp("My Report Page SUSST_MR1: Page and filter checks, reset, print, save, and export", "G_R_CU_SUSST_5",DataItems.validCustUsername,DataItems.validCustPassword);
				
            System.out.println("Navigating to My Reports page...");
            
            Ecomm_MyReportsPage mrPage = eCommPage.clickMyReports();
            mrPage.waitForElement();
		
            System.out.println("My Reports page reached.");
		
            mrPage.assertBaseElements();
		
            System.out.println("Checking title...");
            
            AssertJUnit.assertTrue("My Reports (Customer care) Page: Title not displayed as expected",mrPage.getBreadcrumbText().equals("Reports | Customer Care"));
            
            System.out.println("Title checked. Checking fields...");
		
            mrPage.checkFields_SUSST();
		
            System.out.println("Fields checked. Selecting all items...");
            
            mrPage.setSelectAll();
            
            System.out.println("All selected. Resetting filter...");
            
            mrPage.uncheckSelectAll();
            
            System.out.println("Filter reset. Selecting PO Number, Article, Brand, Ticket, Invoice No, Delivery No...");
            
            mrPage.setPONumber();
            mrPage.setArticle();
            mrPage.setBrand();
            mrPage.setTicket();
            mrPage.setInvoiceNo();
            mrPage.setDeliveryNo();
            
            System.out.println("Items selected");
            
            System.out.println("Printing report...");
            
            Ecomm_OrderViewPage viewPage = mrPage.pressPrint_SUSST();
            viewPage.waitForContent();
            viewPage.switchTo();
            
            System.out.println("Report printed. Closing...");
            
            viewPage.closeView();
            viewPage.waitForInvisibility();
            
            System.out.println("Print view closed. Resetting filter...");
            
            mrPage.pressReset();
            mrPage.waitForLoad();
            
            AssertJUnit.assertFalse("My Reports page: Reset button did not reset fields",mrPage.getSelectAllButton().isSelected());
            
            System.out.println("Filter reset. Checking for save button...");
            
            if (mrPage.getSaveMyReportButton().getAttribute("value").contains("Save")) {
                Ecomm_SaveReportPage srPage = mrPage.pressSaveReport();
                srPage.waitForContent();

                System.out.println("Page reached. Entering title...");

                srPage.setTitle(DataItems.reportTitle);

                srPage.pressSave();
                srPage.waitForInvisibility();

                String message = mrPage.getFlashMessage().getText();
                if (message.contains("has been saved")) {
                    System.out.println("Report successfully saved");
                } else {
                    System.out.println("***Unexpected message received: "+message+"***");
                }
            } else {
                System.out.println("Save button not present, Save Report feature not tested");
            }       
            

        }
}
