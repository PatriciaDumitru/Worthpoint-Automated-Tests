package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrderDraftPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Ecomm_SUSST_ME_IT extends DriverFactory {
    
    @Test //Manual Entry Page :: Page checks, single line order with YMN and master shade code
    (groups = {"QuickTest","eComm","eComm_Orders"})
    public void SUSST1() throws IOException, InterruptedException, Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_SUSST_Base susstTest4 = new Ecomm_SUSST_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.SUSST_SetUp("MANUAL ENTRY SUSST1: Single line, Your Material Number with master data shade code","G_OOC_ME_SUSST_1");

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\1Manual Entry Page.png"));
        
        System.out.println("Manual Entry loaded.");

        System.out.println("Asserting elements...");
        //Make assertions
        manualEntryPage.assertBaseElements();
        AssertJUnit.assertTrue("eComm page: navigation bar not displayed",manualEntryPage.getNavBar().isDisplayed());
        AssertJUnit.assertTrue("eComm page: breadcrumb not displayed",manualEntryPage.getBreadcrumb().isDisplayed());
        AssertJUnit.assertTrue("eComm page: customer details table not displayed",manualEntryPage.getCustomerTable().isDisplayed());
        AssertJUnit.assertTrue("eComm page: product details table not displayed",manualEntryPage.getProductTable().isDisplayed());
        AssertJUnit.assertTrue("eComm page: 'Next' button not displayed",manualEntryPage.getNextButton().isDisplayed());
        AssertJUnit.assertTrue("eComm page: 'Save draft' button not displayed",manualEntryPage.getSaveDraftButton().isDisplayed());
        AssertJUnit.assertTrue("eComm page: 'Cancel' button not displayed",manualEntryPage.getCancelButton().isDisplayed());

        System.out.println("Assertions successful.");

        System.out.println("Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyersNew(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
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
        orderConf.waitForLoad();
        
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\4Next pressed.png"));

        System.out.println("Order confirmation page reached. Submitting order...");
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForLoad();
        
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
        Ecomm_SUSST_Base susstTest4 = new Ecomm_SUSST_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.SUSST_SetUp("MANUAL ENTRY SUSST2: Multiple lines, Your Material Number with master data shade code","G_OOC_ME_SUSST_2");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page loaded. Entering customer details...");
        
        //Input Customer Details
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyersNew(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);

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
        orderConf.waitForLoad();
        
        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForLoad();

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
        Ecomm_SUSST_Base susstTest3 = new Ecomm_SUSST_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest3.SUSST_SetUp("MANUAL ENTRY SUSST3: Single line, Your Material Number without master data shade code","G_OOC_ME_SUSST_3");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page loaded. Entering customer details...");
        
        //Input Customer Details
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyersNew(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);

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
        
        outOrdersPage.waitForLoad();
        
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
        Ecomm_SUSST_Base susstTest4 = new Ecomm_SUSST_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.SUSST_SetUp("MANUAL ENTRY SUSST4: Multiple lines, Your Material Number without master data shade code","G_OOC_ME_SUSST_4");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input Customer Details
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyersNew(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
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
        orderConf.waitForLoad();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForLoad();

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
        Ecomm_SUSST_Base susstTest5 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest5.SUSST_SetUp("MANUAL ENTRY SUSST5: Single line, using Article and shade code","G_OOC_ME_SUSST_5");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input customer details
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyersNew(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
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
        orderConf.waitForLoad();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForLoad();

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
        Ecomm_SUSST_Base susstTest6 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest6.SUSST_SetUp("MANUAL ENTRY SUSST6: Multiple lines, using Article and shade code","G_OOC_ME_SUSST_6");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input customer details
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyersNew(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;
        
        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.article,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
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
        orderConf.waitForLoad();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForLoad();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = outOrdersPage.getRow(DataItems.lastUsedPO);
        Ecomm_OrderViewPage orderView = outOrdersPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getArticleCell().getText().equals(DataItems.article),"Order view: Article does not match expected input");
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

    @Test //Manual Entry Page :: Single line order, using brand/ticket/length/finish and shade code
    (groups = {"eComm","eComm_Orders"})
    public void SUSST7() throws InterruptedException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest7 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest7.SUSST_SetUp("MANUAL ENTRY SUSST7: Single line, using brand, ticket, length, finish, and shade code","G_OOC_ME_SUSST_7");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input customer details
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyersNew(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;
        
        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.brand,DataItems.ticket,DataItems.length,DataItems.finish,DataItems.shadeCode,String.valueOf(DataItems.quantity)},           
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsCombination(details[i], i);
        }

        System.out.println("Product details entered. Pressing next...");

        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForLoad();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForLoad();

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
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST8: Multiple lines, using brand, ticket, length, finish, and shade code","G_OOC_ME_SUSST_8");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        //Input customer details
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);  
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyersNew(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
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
        orderConf.waitForLoad();

        System.out.println("Order confirmation page reached. Submitting order...");

        //Press Submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForLoad();

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
    (groups = {"eComm","eComm_Orders"})
    public void SUSST9() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST9: Check validation at Manual Entry Page (no requester)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForLoad();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setBuyersNew(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
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
        mePage.waitForLoad();
        
        try {
            
            WebElement flashMessage = mePage.waitForError();
            
            System.out.println("Next pressed.");
            System.out.println("Error received: " + flashMessage.getText());
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\8Error received as expected.png"));
            
            System.out.println("Checking no order has been created...");
            
        } catch (Exception e) {
            
            try {
                Alert alert2 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert appeared: " + alert2.getText());
                alert2.accept();
            } catch (Exception f) {
                System.out.println("No alert appeared");
            }
            
            System.out.println("***NEXT PRESSED, UNEXPECTED RESULT***");
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\8Error - unexpected result.png"));
        }
        
        Ecomm_OutstandingOrdersPage outPage =  eCommPage.clickOutstandingOrders();
            
            System.out.println("Outstanding orders page reached.");
            
            int row = outPage.getRow(DataItems.lastUsedPO);
            
            if (row == -1) {
                System.out.println("No order found in Outstanding Orders Page, as expected");
            } else {
                System.out.println("***ORDER FOUND IN OUTSTANDING ORDERS PAGE***");
                System.out.println("Order PO No.: " + DataItems.lastUsedPO);
                System.out.println("Order No.: " + outPage.getOrderNumber(row));
                System.out.println("Table row: " + row);
                
                //Take a screenshot
                File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\9Error - order created without requester.png"));
            }
         
    }

    @Test //Manual Entry Page :: Validation tests, no ship to party name at manual entry page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST10() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST9: Check validation at Manual Entry Page (no requester)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
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
        mePage2.waitForLoad();
        
        try {

            WebElement flashMessage = mePage2.waitForError();
            
            System.out.println("Next pressed");
            System.out.println("Error received: " + flashMessage.getText());
            
            System.out.println("Checking order was not created...");
       
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\10Error received as expected.png"));
            
        } catch (Exception e) {
            System.out.println("***NEXT PRESSED, UNEXPECTED RESULT***");
            
            try {
                Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert appeared: " + alert.getText());
                alert.accept();
            } catch (Exception f) {
                System.out.println("No alert appeared.");
            }
            
            CommonTask.waitForPageLoad(driver);
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\11Error - unexpected result.png"));
        }
        
        Ecomm_OutstandingOrdersPage outPage =  eCommPage.clickOutstandingOrders();
            
            System.out.println("Outstanding orders page reached.");
            
            int row = outPage.getRow(DataItems.lastUsedPO);
            
            if (row == -1) {
                System.out.println("No order found in Outstanding Orders Page, as expected");
            } else {
                System.out.println("***ORDER FOUND IN OUTSTANDING ORDERS PAGE***");
                System.out.println("Order PO No.: " + DataItems.lastUsedPO);
                System.out.println("Order No.: " + outPage.getOrderNumber(row));
                System.out.println("Table row: " + row);
                
                //Take a screenshot
                File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\12Error - order created without shipto.png"));
            }

    }
    
    @Test //Manual Entry Page :: Validation tests, no buyer at manual entry page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST11() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST11: Check validation at Manual Entry Page (no buyer)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        CommonTask.resetSearchField(driver, "s2id_BuyerId");
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
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
        mePage.waitForLoad();
        
        try {
            
            WebElement flashMessage = mePage.waitForError();
            
            System.out.println("Next pressed");
            System.out.println("Error received: " + flashMessage.getText());
            
            System.out.println("Checking order was not created...");
       
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\14Error received as expected.png"));
            
        } catch (Exception e) {
            System.out.println("***NEXT PRESSED, NO ERROR RECEIVED***");
            
            try {
                Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert appeared: " + alert.getText());
                alert.accept();
            } catch (Exception f) {
                System.out.println("No alert appeared.");
            }
            
            CommonTask.waitForPageLoad(driver);
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\14Error - No error received.png"));
        }
        
        Ecomm_OutstandingOrdersPage outPage =  eCommPage.clickOutstandingOrders();
            
            System.out.println("Outstanding orders page reached.");
            
            int row = outPage.getRow(DataItems.lastUsedPO);
            
            if (row == -1) {
                System.out.println("No order found in Outstanding Orders Page, as expected");
            } else {
                System.out.println("***ORDER FOUND IN OUTSTANDING ORDERS PAGE***");
                System.out.println("Order PO No.: " + DataItems.lastUsedPO);
                System.out.println("Order No.: " + outPage.getOrderNumber(row));
                System.out.println("Table row: " + row);
                
                //Take a screenshot
                File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\15Error - order created without buyer.png"));
            }
        
    }
    
    @Test //Manual Entry Page :: Validation tests, no requester at confirmation page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST12() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST12: Check validation at Confirmation Page (no requester)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForLoad();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForLoad();
        
        System.out.println("Confirmation page reached, removing requestor...");
        
        orderConf.setRequestor("Select");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\16Requestor blank.png"));
        
        System.out.println("Requestor removed. Confirming...");
        
        Ecomm_OrderConfirmationPage orderConf2 = orderConf.pressSubmitExpectingFailure();
 
        try {
            Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert appeared: " + alert.getText());
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\17Alert open.png"));
            
            alert.accept();            
            orderConf2.waitForLoad();
            
            //Take a screenshot
            File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\18Confirmation page return.png"));
            
            Ecomm_OutstandingOrdersPage outPage = eCommPage.clickOutstandingOrders();
            int row = outPage.getRow(DataItems.lastUsedPO);
            
            if (row == -1) {
                System.out.println("No order created.");
            } else {
                System.out.println("***ERROR: ORDER CREATED ALTHOUGH VALIDATION FAILED***");
                
                //Take a screenshot
                File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\17Error - order created without requestor.png"));
                
                System.out.println("Order PO No.: " + DataItems.lastUsedPO);
                System.out.println("Order No.: " + outPage.getOrderNumber(row));
                System.out.println("Table row: " + row);
            }
            
        } catch (Exception e) {
            
            try {
                Alert alert3 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert appeared: " + alert3.getText());
                alert3.accept();
            } catch (Exception g) {
                System.out.println("No alert appeared");
            }
            
            WebElement flashMessage = orderConf2.waitForError();
            
            System.out.println("Error received as expected");
            System.out.println("Error: " + flashMessage.getText());
            
             //Take a screenshot
            File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\18Error displayed as expected.png"));
            
        }
    }
    
    @Test //Manual Entry Page :: Validation tests, no Ship To Party Name at confirmation page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST13() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST13: Check validation at Confirmation Page (no ship to)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForLoad();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
                
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForLoad();
        
        System.out.println("Confirmation page reached, removing Ship To Party Name...");
        
        orderConf.setShipToParty("Select");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\19Ship To Party blank.png"));
        
        System.out.println("Ship To Party removed. Confirming...");
        
        Ecomm_OrderConfirmationPage orderConf2 = orderConf.pressSubmitExpectingFailure();

        try {
            Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert appeared: " + alert.getText());
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\20Alert open.png"));
            
            alert.accept();
            orderConf2.waitForLoad();
            
            //Take a screenshot
            File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\21Confirmation page return.png"));
            
            Ecomm_OutstandingOrdersPage outPage = eCommPage.clickOutstandingOrders();
            int row = outPage.getRow(DataItems.lastUsedPO);
            
            if (row == -1) {
                System.out.println("No order created.");
            } else {
                System.out.println("***ERROR: ORDER CREATED ALTHOUGH VALIDATION FAILED***");
                
                //Take a screenshot
                File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\21Error - order created without ship to.png"));
                
                System.out.println("Order PO No.: " + DataItems.lastUsedPO);
                System.out.println("Order No.: " + outPage.getOrderNumber(row));
                System.out.println("Table row: " + row);
            }
            
        } catch (Exception e) {
            
            try {
                Alert alert3 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert appeared: " + alert3.getText());
                alert3.accept();
            } catch (Exception g) {
                System.out.println("No alert appeared");
            }
            
            WebElement flashMessage = orderConf2.waitForError();
            
            //Take a screenshot
            File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\15Error received as expected.png"));
            
            System.out.println("Error received as expected");
            System.out.println("Error: " + flashMessage.getText());
        }

    }
    
    @Test //Manual Entry Page :: Validation tests, no Buyer at confirmation page
    (groups = {"eComm","eComm_Orders"})
    public void SUSST14() throws InterruptedException, IOException, Exception {
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST14: Check validation at Confirmation Page (no buyer)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForLoad();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setArticle(DataItems.conOrdArticle, 0);
        manualEntryPage.setShadeCode(DataItems.conOrdShadeCode,0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
                
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForLoad();
        
        System.out.println("Confirmation page reached, removing Buyer...");
        
        CommonTask.resetSearchField(driver, "s2id_BuyerId_0");
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\22Buyer blank.png"));
        
        System.out.println("Buyer removed. Confirming...");
        
        Ecomm_OrderConfirmationPage orderConf2 = orderConf.pressSubmitExpectingFailure();
 
        try {
            Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert appeared: " + alert.getText());
            alert.accept();
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\23Alert open.png"));
            
            orderConf2.waitForLoad();
            
            //Take a screenshot
            File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\24Confirmation page return.png"));
            
            Ecomm_OutstandingOrdersPage outPage = eCommPage.clickOutstandingOrders();
            int row = outPage.getRow(DataItems.lastUsedPO);
            
            if (row == -1) {
                System.out.println("No order created.");
            } else {
                System.out.println("***ERROR: ORDER CREATED ALTHOUGH VALIDATION FAILED***");
                
                //Take a screenshot
                File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\25Error - order created without buyer.png"));
                
                System.out.println("Order PO No.: " + DataItems.lastUsedPO);
                System.out.println("Order No.: " + outPage.getOrderNumber(row));
                System.out.println("Table row: " + row);
            }
            
        } catch (Exception e) {
            
            try {
                Alert alert3 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert appeared: " + alert3.getText());
                alert3.accept();
            } catch (Exception g) {
                System.out.println("No alert appeared");
            }
            
            WebElement flashMessage = orderConf2.waitForError();
            
            //Take a screenshot
            File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\26Error received as expected.png"));
            
            System.out.println("Error received as expected");
            System.out.println("Error: " + flashMessage.getText());
        }

    }
    
    @Test //Manual Entry Page :: Order Draft creation and cancellation
    (groups = {"eComm","eComm_Orders"})
    public void SUSST15() throws InterruptedException, IOException, Exception {
        
        //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST15: Draft creation/order simulation","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForLoad();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\27Order details entered.png"));
        
        String date = manualEntryPage.getDate(0);
                
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForLoad();
        
        System.out.println("Confirmation page reached. Checking details...");
        
        Verify.verify(orderConf.getYourMatNum().equals(DataItems.yourMatNum),"Order Confirmation Page: Your Material Number does not match input");
        Verify.verify(orderConf.getOrderedQty() == 3,"Order Confirmation Page: Ordered Quantity does not match input");
        Verify.verify(orderConf.getRequiredDate().equals(date),"Order Confirmation Page: Required Date does not match input");
        
        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\28Confirmation page reached.png"));
        
        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();
        
        WebElement waitForVis = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\29Confirmation page reached scrolled.png"));
        
        
        System.out.println("Details checked. Cancelling order...");
        
        Ecomm_ManualEntryPage mePage = orderConf.pressCancel();
        mePage.waitForLoad();
        mePage.waitForElement();

        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\30Order cancelled.png"));
        
        System.out.println("Order cancelled. Checking no draft was saved...");

        Ecomm_OutstandingOrderDraftPage draftPage = mePage.clickOutstandingDraft();
        draftPage.waitForLoad();
        
        System.out.println("Draft page reached.");
        
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\31Draft page (no draft expected).png"));
        
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
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST16: Draft creation/order simulation - cancelling saved draft","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry Page...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf3 = manualEntryPage.pressNext();
        orderConf3.waitForLoad();
        
        System.out.println("Order confirmation reached. Saving draft...");
        
        Ecomm_OutstandingOrderDraftPage draftPage = orderConf3.pressSaveDraft();
        draftPage.waitForLoad();
        
        System.out.println("Outstanding Order Draft page reached. Editing top draft...");
       
        Ecomm_ManualEntryPage mePage2 = draftPage.pressEdit();
        mePage2.waitForLoad();
        
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\32Draft open.png"));
        
        DataItems.lastUsedPO = mePage2.getCustPONo();
        
        Ecomm_OrderConfirmationPage orderConf = mePage2.pressNext();
        orderConf.waitForLoad();
        
        System.out.println("Order confirmation page reached. Cancelling order draft...");
        
        Ecomm_ManualEntryPage mePage3 = orderConf.pressCancel();
        mePage3.waitForLoad();
        
        //Take a screenshot
        File scrFile12 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile12,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\33Draft cancelled.png"));
        
        System.out.println("Draft cancelled. Checking draft is deleted...");
        
        Ecomm_OutstandingOrderDraftPage draftPage2 = mePage3.clickOutstandingDraft();
        draftPage2.waitForLoad();
        
        //Take a screenshot
        File scrFile13 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile13,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\34Draft removed.png"));
   
        String orderNo = draftPage2.findDraft(DataItems.lastUsedPO);
        
        if (orderNo.equals("")) {
            System.out.println("No draft found, as expected");
        } else {
            System.out.println("***ORDER DRAFT NOT DELETED***");
        }
        
    }
    
    @Test //Manual Entry Page :: Order Draft continuation
    (groups = {"eComm","eComm_Orders"})
    public void SUSST17() throws InterruptedException, Exception {
         //New driver
        WebDriver driver = getDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST15: Draft creation/order simulation","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForLoad();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        manualEntryPage.setCustomerNameNew(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyNew(DataItems.custDetails[1]);
        manualEntryPage.setRequestorNew(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPoNumberNew(DataItems.custDetails[4]);
        System.out.println("Customer PO used: "+DataItems.lastUsedPO);
        
        System.out.println("Customer details entered. Entering line details...");
        
        manualEntryPage.setYourMaterialNumber(DataItems.yourMatNum, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);
        
        String date = manualEntryPage.getDate(0);
                
        System.out.println("Line details entered. Pressing next...");
        
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForLoad();
        
        System.out.println("Confirmation page reached. Saving as draft...");
        
        Actions scroller = new Actions(driver);
        scroller.moveToElement(orderConf.getCancelButton()).build().perform();
        
        WebElement waitForVis = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(orderConf.getCancelButton()));
        
        Ecomm_OutstandingOrderDraftPage draftPage = orderConf.pressSaveDraft();
        draftPage.waitForLoad();
        
        System.out.println("Draft saved. Continuing draft...");
        
        Ecomm_ManualEntryPage mePage = draftPage.pressEdit();
        mePage.waitForLoad();
        
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
    
}
