package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrderDraftPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_PendingApprovalListPage;
import PageObjects.Ecomm_SAPInterfaceLogPage;
import PageObjects.Ecomm_UploadProcessPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Ecomm_ME_Test extends DriverFactory {
    
    @Test //Manual Entry Page :: Page checks, single line order using YMN and shade code from master data
    (groups = {"QuickTest","eComm","eComm_Orders"})
    public void SUSST1() throws IOException, InterruptedException, Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.SUSST_SetUp("MANUAL ENTRY SUSST1: Single line, Your Material Number with master data shade code","G_OOC_ME_SUSST_1");

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\1Manual Entry Page.png"));
        
        System.out.println("Manual Entry loaded.");

        //Make assertions
        manualEntryPage.assertBaseElements();

        System.out.println("Checking title...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Title not displayed as expected",manualEntryPage.getBreadcrumb().getText().equals("Orders | Manual Entry"));
        
        System.out.println("Title checked. Checking fields...");
        
        manualEntryPage.checkFields();
        
        System.out.println("Fields checked. ");

        System.out.println("Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\2Customer details entered.png"));

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum,String.valueOf(DataItems.quantity)}
            //Further line details, each in their own array
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }

        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\3Product details entered.png"));
        
        System.out.println("Product details entered. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\4Next pressed.png"));

        System.out.println("Order confirmation page reached. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();
        
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\5Order submitted.png"));

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrders.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrders.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\6View Order.png"));
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = outOrders.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    
    }

    @Test //Manual Entry Page :: Multiple line order, YMN with master shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST2() throws InterruptedException, Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.SUSST_SetUp("MANUAL ENTRY SUSST2: Multiple lines, Your Material Number with master data shade code","G_OOC_ME_SUSST_2");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page loaded. Entering customer details...");
        
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum,String.valueOf(DataItems.quantity)},
            //line 2 details
            {DataItems.yourMatNum2,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");
    
        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);

    } 

    @Test //Manual Entry Page :: Single line order, YMN without master shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST3() throws InterruptedException, Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest3 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest3.SUSST_SetUp("MANUAL ENTRY SUSST3: Single line, Your Material Number without master data shade code","G_OOC_ME_SUSST_3");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page loaded. Entering customer details...");
        
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMNShadeCode(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        outOrdersPage.waitForElement();
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");
    
        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
        
    }

    @Test //Manual Entry Page :: Multi-line order, YMN without master shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST4() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.SUSST_SetUp("MANUAL ENTRY SUSST4: Multiple lines, Your Material Number without master data shade code","G_OOC_ME_SUSST_4");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
            {DataItems.yourMatNum2,DataItems.shadeCode,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMNShadeCode(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(DataItems.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
        
    }
    
    @Test //Manual Entry Page :: Single line order, article and shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST5() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest5 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest5.SUSST_SetUp("MANUAL ENTRY SUSST5: Single line, using Article and shade code","G_OOC_ME_SUSST_5");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input customer details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;
        
        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.expArticle,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
        
        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
        
    }

    @Test //Manual Entry Page :: Multi-line order, using Article and shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST6() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest6 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest6.SUSST_SetUp("MANUAL ENTRY SUSST6: Multiple lines, using Article and shade code","G_OOC_ME_SUSST_6");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input customer details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;
        
        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.expArticle,DataItems.expShadeCode,String.valueOf(DataItems.quantity)},
            //line 2 details
            {DataItems.expArticle2,DataItems.expShadeCode2,String.valueOf(DataItems.quantity)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsArticle(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);
        
    } 

    @Test //Manual Entry Page :: Single line order, using brand/ticket/length/finish and shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST7() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest7 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest7.SUSST_SetUp("MANUAL ENTRY SUSST7: Single line, using brand, ticket, length, finish, and shade code","G_OOC_ME_SUSST_7");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        //Input customer details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;
        
        manualEntryPage.setBrand(DataItems.brand, 0);
        manualEntryPage.setTicket(DataItems.ticket,0);
        manualEntryPage.setLength(DataItems.length,0);
        manualEntryPage.setFinish(DataItems.finish,0);
        manualEntryPage.setShadeCode(DataItems.shadeCode,0);
        manualEntryPage.setQty(DataItems.quantity,0);
        manualEntryPage.setDate(0);
        
        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
       
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.brand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.ticket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);

    }

    @Test //Manual Entry Page :: Multi-line order, using brand/ticket/length/finish and shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST8() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST8: Multiple lines, using brand, ticket, length, finish, and shade code","G_OOC_ME_SUSST_8");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        //Input customer details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);  
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;
        
        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.brand,DataItems.ticket,DataItems.length,DataItems.finish,DataItems.shadeCode,String.valueOf(DataItems.quantity)},  
            //line 2 details
            {DataItems.brand2,DataItems.ticket2,DataItems.length2,DataItems.finish2,DataItems.shadeCode2,String.valueOf(DataItems.quantity2)}
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsCombination(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
        
        Verify.verify(orderView.getBrandCell().getText().equals(DataItems.brand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(DataItems.ticket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(DataItems.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = outOrdersPage.getOrderNumber(rowNumber);
        System.out.println("Order Number: " + orderNumber);

    }
    
    @Test //Manual Entry Page :: Validation tests, no requester at manual entry page
    (groups = {"eComm","eComm_Orders","QuickTest"})
    public void SUSST9() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST9: Check validation at Manual Entry Page (no requester)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\7All but requester entered.png"));
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
          
        System.out.println("Line details entered. Pressing next expecting failure...");
        
        Ecomm_ManualEntryPage mePage = manualEntryPage.pressNextExpectingFailure();
        
        System.out.println("Next pressed");
        
        boolean errorDisplayed = false;
        
        try {
            WebElement flashMessage = mePage.waitForError();
            System.out.println("Error received: " + flashMessage.getText());
            errorDisplayed = true;
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\8Error received as expected.png"));
        } catch (Exception e) {
            System.out.println("No error dispayed");
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\8Error - unexpected result.png"));
        }
        
        AssertJUnit.assertTrue("Manual Entry Page: No error displayed despite missing mandatory field(s)",errorDisplayed);
        
        System.out.println("Checking no order has been created...");
        
        Ecomm_OutstandingOrdersPage outOrder = mePage.clickOutstandingOrders();
        outOrder.waitForLoad();
        
        System.out.println("Outstanding Orders Page reached. Searching for record...");
        
        int row = outOrder.getRow(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)"
                + "\n Table Row: " + row
                + "\n Customer PO No.: " + DataItems.lastUsedPO,row==-1);
        
        System.out.println("No order found, as expected. Checking no draft was created...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = outOrder.clickOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Outstanding Order Draft Page reached. Searching for record...");
        
        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Order Draft Page: Draft created after order submission failed due to missing mandatory field(s)"
                + "Customer PO No.: "+DataItems.lastUsedPO
                + "Order No.: " + orderNo,orderNo.equals(""));
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\9No Draft created.png"));
        
        System.out.println("No draft created");
         
    }

    @Test //Manual Entry Page :: Validation tests, no ship to party name at manual entry page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST10() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST10: Check validation at Manual Entry Page (no ship to)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\9All but Ship To entered.png"));
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        System.out.println("Line details entered. Pressing next expecting failure...");
        
        Ecomm_ManualEntryPage mePage2 = manualEntryPage.pressNextExpectingFailure();
        
        boolean errorDisplayed;
        
        try {
            WebElement flashMessage = mePage2.waitForError();
            System.out.println("Error received: " + flashMessage.getText());
            
            errorDisplayed = true;
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\10Error received as expected.png"));
        
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\11Error - unexpected result.png"));
        
        }
        
        AssertJUnit.assertTrue("Manual Entry Page: No error displayed despite missing mandatory field(s)", errorDisplayed);
        
        System.out.println("Checking no order has been created...");
        
        Ecomm_OutstandingOrdersPage outPage = mePage2.clickOutstandingOrders();
        outPage.waitForElement();
        
        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRow(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);
        
        System.out.println("No order created, as expected. Checking no draft was created...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Draft page reached. Searching for draft...");
        
        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Draft Page: Draft created despite missing mandatory field(s)"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,orderNo.equals(""));
        
        System.out.println("No draft found, as expected");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\12Error - order created without shipto.png"));

    }
    
    @Test //Manual Entry Page :: Validation tests, no buyer at manual entry page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST11() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST11: Check validation at Manual Entry Page (no buyer)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        CommonTask.resetSearchField(driver, "s2id_BuyerId");
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\13All but buyer entered.png"));
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        
        System.out.println("Line details entered. Pressing next expecting failure...");
        
        Ecomm_ManualEntryPage mePage = manualEntryPage.pressNextExpectingFailure();
        
        boolean errorDisplayed;
        
        try {
            WebElement flashMessage = mePage.waitForError();
            errorDisplayed = true;
        
            System.out.println("Error received: "+flashMessage.getText());
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\14Error received as expected.png"));
        } catch (Exception e) {
            errorDisplayed = false;
            System.out.println("No error displayed");
        }
        
        AssertJUnit.assertTrue("Manual Entry Page: No error displayed despite missing mandatory field(s)", errorDisplayed);
        
        System.out.println("Checking no order has been created...");
        
        Ecomm_OutstandingOrdersPage outPage = mePage.clickOutstandingOrders();
        outPage.waitForElement();
        
        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRow(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);
        
        System.out.println("No order created, as expected. Checking no draft was created...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Draft page reached. Searching for draft...");
        
        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Draft Page: Draft created despite missing mandatory field(s)"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,orderNo.equals(""));
        
        System.out.println("No draft found, as expected");
        
    }
    
    @Test //Manual Entry Page :: Validation tests, no requester at confirmation page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST12() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST12: Check validation at Confirmation Page (no requester)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Confirmation page reached, removing requestor...");
        
        orderConf.setRequestor("Select");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\16Requestor blank.png"));
        
        System.out.println("Requestor removed. Confirming...");
        
        Ecomm_UploadProcessPage errorPage = orderConf.pressSubmitExpectingFailure();
 
        boolean errorDisplayed;
        
        try {
            WebElement flashMessage = errorPage.waitForError();
            System.out.println("Error received: "+flashMessage.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }
                
        AssertJUnit.assertTrue("Order Confirmation Page: No error displayed despite missing mandatory field(s)",errorDisplayed);
        
        System.out.println("Checking no order was created...");
        
        Ecomm_OutstandingOrdersPage outPage = errorPage.clickOutstandingOrders();
        outPage.waitForLoad();
        
        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRow(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);
        
        System.out.println("No order created, as expected. Checking no draft was created...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Draft page reached. Searching for draft...");
        
        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Draft Page: Draft not created despite confirmation page reached"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,(!orderNo.equals("")));
        
        System.out.println("Draft found, as expected");
        
    }
    
    @Test //Manual Entry Page :: Validation tests, no Ship To Party Name at confirmation page
    (groups = {"eComm","eComm_Orders","QuickTest"})
    public void SUSST13() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST13: Check validation at Confirmation Page (no ship to)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
                
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Confirmation page reached, removing Ship To Party Name...");
        
        orderConf.setShipToParty("Select");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\19Ship To Party blank.png"));
        
        System.out.println("Ship To Party removed. Confirming...");
        
        Ecomm_UploadProcessPage errorPage = orderConf.pressSubmitExpectingFailure();

        boolean errorDisplayed;
        
        try {
            WebElement flashMessage = errorPage.waitForError();
            System.out.println("Error received: "+flashMessage.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }
                
        AssertJUnit.assertTrue("Order Confirmation Page: No error displayed despite missing mandatory field(s)",errorDisplayed);
        
        System.out.println("Checking no order was created...");
        
        Ecomm_OutstandingOrdersPage outPage = errorPage.clickOutstandingOrders();
        outPage.waitForLoad();
        
        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRow(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);
        
        System.out.println("No order created, as expected. Checking no draft was created...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Draft page reached. Searching for draft...");
        
        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Draft Page: Draft not created despite confirmation page reached"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,(!orderNo.equals("")));
        
        System.out.println("Draft found, as expected");

    }
    
    @Test //Manual Entry Page :: Validation tests, no Buyer at confirmation page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST14() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST14: Check validation at Confirmation Page (no buyer)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
                
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Confirmation page reached, removing Buyer...");
        
        CommonTask.resetSearchField(driver, "s2id_BuyerId_0");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\22Buyer blank.png"));
        
        System.out.println("Buyer removed. Confirming...");
        
        Ecomm_UploadProcessPage errorPage = orderConf.pressSubmitExpectingFailure();
 
        boolean errorDisplayed;
        
        try {
            WebElement flashMessage = errorPage.waitForError();
            System.out.println("Error received: "+flashMessage.getText());
            errorDisplayed = true;
        } catch (Exception e) {
            System.out.println("No error displayed");
            errorDisplayed = false;
        }
                
        AssertJUnit.assertTrue("Order Confirmation Page: No error displayed despite missing mandatory field(s)",errorDisplayed);
        
        System.out.println("Checking no order was created...");
        
        Ecomm_OutstandingOrdersPage outPage = errorPage.clickOutstandingOrders();
        outPage.waitForLoad();
        
        System.out.println("Outstanding orders page reached. Searching for record...");

        int row = outPage.getRow(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Orders Page: Order created despite missing mandatory field(s)",row==-1);
        
        System.out.println("No order created, as expected. Checking no draft was created...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = outPage.clickOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Draft page reached. Searching for draft...");
        
        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);
        
        AssertJUnit.assertTrue("Outstanding Draft Page: Draft not created despite confirmation page reached"
                +"\nCustomer PO No.: " + DataItems.lastUsedPO
                +"\nOrder No.: " + orderNo,(!orderNo.equals("")));
        
        System.out.println("Draft found, as expected");
    
    }
        
    @Test //Manual Entry Page :: Create order and cancel from confirmation page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST15() throws InterruptedException, IOException, Exception {
        
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST15: Draft creation/order simulation - check draft details and cancel","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\28Order details entered.png"));
        
        String date = manualEntryPage.getDate(0);
                
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Confirmation page reached. Checking details...");
        
        Verify.verify(orderConf.getYourMatNum().equals(DataItems.yourMatNum),"Order Confirmation Page: Your Material Number does not match input");
        Verify.verify(orderConf.getOrderedQty() == 3,"Order Confirmation Page: Ordered Quantity does not match input");
        Verify.verify(orderConf.getRequiredDate().equals(date),"Order Confirmation Page: Required Date does not match input");
        
        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\29Confirmation page reached.png"));
        
        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();
        
        WebElement waitForVis = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\30Confirmation page reached scrolled.png"));
        
        
        System.out.println("Details checked. Cancelling order...");
        
        Ecomm_ManualEntryPage mePage = orderConf.pressCancel();
        mePage.waitForElement();

        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\31Order cancelled.png"));
        
        System.out.println("Order cancelled. Checking no draft was saved...");

        Ecomm_OutstandingOrderDraftPage draftPage = mePage.clickOutstandingDraft();
        draftPage.waitForElement();
        
        System.out.println("Draft page reached.");
        
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\32Draft page (no draft expected).png"));
        
        String orderNo = draftPage.findDraft(DataItems.lastUsedPO);
        
        if (orderNo.equals("")) {
            System.out.println("Draft not created, as expected.");
        } else {
            System.out.println("***ORDER DRAFT UNEXPECTEDLY CREATED***");
        }
        
    }
    
    @Test //Manual Entry Page :: Order Draft continuation and cancellation
    (groups = {"eComm","eComm_Orders"})
    public void SUSST16() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST16: Draft creation/order simulation - cancelling saved draft","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry Page...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf3 = manualEntryPage.pressNext();
        orderConf3.waitForElement();
        
        System.out.println("Order confirmation reached. Saving draft...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = orderConf3.pressSaveDraft();
        draftPage.waitForElement();
        
        System.out.println("Outstanding Order Draft page reached. Editing top draft...");
       
        Ecomm_ManualEntryPage mePage2 = draftPage.pressEdit();
        mePage2.waitForElement();
        
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\33Draft open.png"));
        
        DataItems.lastUsedPO = mePage2.getCustPONo();
        
        Ecomm_OrderConfirmationPage orderConf = mePage2.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Order confirmation page reached. Cancelling order draft...");
        
        Ecomm_ManualEntryPage mePage3 = orderConf.pressCancel();
        mePage3.waitForElement();
        
        //Take a screenshot
        File scrFile12 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile12,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\34Draft cancelled.png"));
        
        System.out.println("Draft cancelled. Checking draft is deleted...");
        
        Ecomm_OutstandingOrderDraftPage draftPage2 = mePage3.clickOutstandingDraft();
        draftPage2.waitForElement();
        
        //Take a screenshot
        File scrFile13 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile13,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\35Draft removed.png"));
   
        String orderNo = draftPage2.findDraft(DataItems.lastUsedPO);
        
        if (orderNo.equals("")) {
            System.out.println("No draft found, as expected");
        } else {
            System.out.println("***ORDER DRAFT NOT DELETED***");
        }
        
    }
    
    @Test //Manual Entry Page :: Order Draft continuation
    (groups = {"eComm","eComm_Orders","QuickTest"})
    public void SUSST17() throws InterruptedException, Exception {
         //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST17: Draft creation/order simulation","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        String date = manualEntryPage.getDate(0);
                
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Confirmation page reached. Saving as draft...");
        
        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();
        
        WebElement waitForVis = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        Ecomm_OutstandingOrderDraftPage draftPage = orderConf.pressSaveDraft();
        draftPage.waitForElement();
        
        System.out.println("Draft saved. Continuing draft...");
        
        Ecomm_ManualEntryPage mePage = draftPage.pressEdit();
        mePage.waitForElement();
        
        System.out.println("Manual Entry Page reached. Checking details are consistent with input...");
        
        Verify.verify(mePage.getCustomerName().equals(DataItems.custDetails[0]),"Order Draft Edit: Customer name not consistent with input");
        Verify.verify(mePage.getShipToName().equals(DataItems.custDetails[1]),"Order Draft Edit: Ship To Name not consistent with input");
        Verify.verify(mePage.getRequestorName().equals(DataItems.custDetails[2]),"Order Draft Edit: Requester Name not consistent with input");
        Verify.verify(mePage.getBuyer().equals(DataItems.custDetails[3]),"Order Draft Edit: Buyers not consistent with input");
        Verify.verify(mePage.getCustPONo().equals(DataItems.lastUsedPO),"Order Draft Edit: Customer PO No. not consistent with input");
        Verify.verify(mePage.getYourMatNum(0).equals(DataItems.yourMatNum),"Order Draft Edit: Your Material Number not consistent with input");
        Verify.verify(mePage.getQuantity(0).equals("3"),"Order Draft Edit: Quantity not consistent with input");
        Verify.verify(mePage.getDate(0).equals(date),"Order Draft Edit: Date not consistent with input");
        
        System.out.println("Details checked.");
        
    }   
    
    @Test //Manual Entry Page :: SUMST :: Sub-account test. Field appears and data included in Flat File
    (groups ={"eComm","eComm_Orders"})
    public void SUSST18() throws Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST18: Sub-account availability and flat file inclusion","ME_SA_01");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.subCustDetails[0]);
        manualEntryPage.setShipToParty(DataItems.subCustDetails[1]);
        manualEntryPage.setRequestor(DataItems.subCustDetails[2]);
        manualEntryPage.setBuyers(DataItems.subCustDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Checking for sub-account field...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Sub-account field not displayed",manualEntryPage.getSubAccountField().isDisplayed());
        
        System.out.println("Sub-account field displayed. Entering sub-account...");
        
        manualEntryPage.setSubAccount(DataItems.subAccount);
        
        System.out.println("Sub-account entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.article, 0);
        manualEntryPage.setShadeCode(DataItems.shadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        String date = manualEntryPage.getDate(0);
                
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Order confirmation page reached. Checking sub-account field appears and is consistent...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Sub-account field does not appear",orderConf.getSubAccountField().isDisplayed());
        
        AssertJUnit.assertTrue("Order Confirmation Page: Sub-account field does not maintain value after simulation",orderConf.getSubAccount().equals(DataItems.subAccount));
        
        System.out.println("Sub-account field consistent. Sending for Approval...");
        
        Ecomm_PendingApprovalListPage appPage = orderConf.pressSendForApproval();
        appPage.waitForElement();
        
        System.out.println("Pending Approval Page reached. Checking order appears...");
        
        int row = appPage.getRow(DataItems.lastUsedPO);
        AssertJUnit.assertFalse("Pending Approval List Page: Order does not appear after Send for Approval pressed",row==-1);
        
        String orderNo = appPage.getOrderNo(row);
        
        System.out.println("Order appears. Order No.: " + orderNo);
        
        System.out.println("Logging in to approver account to approve order...");
        
        WBA_LoginPage liPage = appPage.pressLogout();
        liPage.waitForElement();
        
        WBA_ContinuePage contPage = liPage.loginAs(DataItems.approverUsername, DataItems.approverPassword);
        WBA_SelectionPage selectPage = contPage.pressContinue();
        Ecomm_MainPage mainPage = selectPage.pressEcomm();
        mainPage.waitForLoad();
        
        System.out.println("Logged in. Navigating to Pending Approval List Page...");

        Ecomm_PendingApprovalListPage pendPage = mainPage.clickPendingApprovalListPage();
        pendPage.waitForElement();
        
        System.out.println("Pending Approval page reached. Finding order...");
        
        AssertJUnit.assertTrue("Pending Approval Page: Order (Order No.: "+orderNo+") not approved.",pendPage.approveOrder(orderNo));
        
        System.out.println("Order approved. Logging into other account to view SAP Log...");
        
        WBA_LoginPage liPage2 = pendPage.pressLogout();
        liPage2.waitForElement();
        
        WBA_ContinuePage contPage2 = liPage.loginAs(DataItems.validCoatsUsername, DataItems.validCoatsPassword);
        WBA_SelectionPage selectPage2 = contPage.pressContinue();
        Ecomm_MainPage mainPage2 = selectPage.pressEcomm();
        
        mainPage2.waitForLoad();
        
        System.out.println("Logged in. Navigating to SAP Log Page...");
        
        Ecomm_SAPInterfaceLogPage sapPage = mainPage2.clickSAPInterfaceLog();
        sapPage.waitForElement();
        
        System.out.println("SAP Log Page reached. Finding order and viewing Flat File...");
        
        sapPage.getFlatFile(orderNo);
        
        
    }
    
    @Test //Manual Entry Page :: SUSST :: MOQ active, adjusted quantity rounds to nearest dye lot
    (groups = {})
    public void SUSST19() throws Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST19: MOQ Active using YMN and master shade","G_OOC_ME_SUSST_MOQ_1",DataItems.validCustUsername,DataItems.validCustPassword);
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.conOrdDetails[0]);
        manualEntryPage.setShipToParty(DataItems.conOrdDetails[1]);
        manualEntryPage.setRequestor(DataItems.conOrdDetails[2]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering line details...");
        
        
        
    }
    
    @Test //Manual Entry Page :: Send for approval feature activated
    (groups = {"eComm","eComm_Orders"})
    public void SUSST20() throws Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_Base susstTest8 = new Ecomm_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST19: MOQ Active using YMN and master shade","G_OOC_ME_SUSST_MOQ_1");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerName(DataItems.subCustDetails[0]);
        manualEntryPage.setShipToParty(DataItems.subCustDetails[1]);
        manualEntryPage.setBuyers(DataItems.subCustDetails[3]);
        manualEntryPage.setRequestor(DataItems.subCustDetails[2]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);
        
        System.out.println("Details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.article, 0);
        manualEntryPage.setShadeCode(DataItems.expShadeCode, 0);
        manualEntryPage.setQty(6, 0);
        manualEntryPage.setDate(0);
        
        System.out.println("Details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();
        
        System.out.println("Order confirmation page reached. Checking 'Send for Approval' button appears...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Send for Approval button not displayed as expected",orderConf.checkSendForApproval());
        
        System.out.println("Button correctly displayed.");

    }
    
}
