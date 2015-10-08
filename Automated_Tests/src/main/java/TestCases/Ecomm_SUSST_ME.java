
package TestCases;

//A collection of order creation tests for Single User Single Sold-to

import AutomationFramework.TestSuite;
import PageObjects.WBA_ContinuePage;
import PageObjects.Ecomm_MainPage;
import PageObjects.WBA_LoginPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_PendingApprovalListPage;
import PageObjects.WBA_SelectionPage;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_SUSST_ME {
    
    @Test //Manual Entry Page :: Page checks, single line order with YMN and master shade code
    public void SUSST1() throws IOException, InterruptedException {
        //New chrome driver
        WebDriver driver = new ChromeDriver();

        //new base test to handle set up
        Ecomm_SUSST_Base susstTest4 = new Ecomm_SUSST_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.SUSST_SetUp("MANUAL ENTRY SUSST1: Single line, Your Material Number with master data shade code","G_OOC_ME_SUSST_1");

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\1Manual Entry Page.png"));
        
        System.out.println("Manual Entry loaded.");

        System.out.println("Asserting elements...");
        //Make assertions
        manualEntryPage.assertBaseElements();
        Assert.assertTrue("eComm page: navigation bar not displayed",manualEntryPage.getNavBar().isDisplayed());
        Assert.assertTrue("eComm page: breadcrumb not displayed",manualEntryPage.getBreadcrumb().isDisplayed());
        Assert.assertTrue("eComm page: customer details table not displayed",manualEntryPage.getCustomerTable().isDisplayed());
        Assert.assertTrue("eComm page: product details table not displayed",manualEntryPage.getProductTable().isDisplayed());
        Assert.assertTrue("eComm page: 'Next' button not displayed",manualEntryPage.getNextButton().isDisplayed());
        Assert.assertTrue("eComm page: 'Save draft' button not displayed",manualEntryPage.getSaveDraftButton().isDisplayed());
        Assert.assertTrue("eComm page: 'Cancel' button not displayed",manualEntryPage.getCancelButton().isDisplayed());

        System.out.println("Assertions successful.");

        System.out.println("Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerNameNew(TestSuite.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(TestSuite.custDetails[1]);
        manualEntryPage.setRequestorNew(TestSuite.custDetails[2]);
        manualEntryPage.setBuyersNew(TestSuite.custDetails[3]);
        manualEntryPage.setPoNumberNew(TestSuite.custDetails[4]);
        
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\2Customer details entered.png"));

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {TestSuite.yourMatNum,String.valueOf(TestSuite.quantity)}
            //Further line details, each in their own array
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }

        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\3Product details entered.png"));
        
        System.out.println("Product details entered. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForLoad();
        
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\4Next pressed.png"));

        System.out.println("Order confirmation page reached. Submitting order...");
        
        //Press Submit
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSubmit(); 
        pendPage.waitForLoad();
        
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\5Order submitted.png"));

        System.out.println("Order submitted. Viewing order...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = pendPage.getRow(TestSuite.lastUsedPO);
        Ecomm_OrderViewPage orderView = pendPage.pressView(rowNumber);       
        //wait for overlay to load
        orderView.waitForContent();
        orderView.switchTo();
        
        System.out.println("Order view displayed. Verifying values...");
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(TestSuite.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(TestSuite.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(TestSuite.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(TestSuite.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(TestSuite.expShadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified. Closing view...");
        
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\6View Order.png"));
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        System.out.println("View closed.");
        
        //Output order number for test reference
        String orderNumber = pendPage.getOrderNo(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    
        driver.close();
    
        System.out.println("----------------------------------------------------");
    
    }
    
    @Test //Manual Entry Page :: Multiple line order, YMN with master shade code
    public void SUSST2() throws InterruptedException {
        //New chrome driver
        WebDriver driver = new ChromeDriver();

        //new base test to handle set up
        Ecomm_SUSST_Base susstTest4 = new Ecomm_SUSST_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.SUSST_SetUp("MANUAL ENTRY SUSST2: Multiple lines, Your Material Number with master data shade code","G_OOC_ME_SUSST_2");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page loaded. Entering customer details...");
        
        //Input Customer Details
        manualEntryPage.setCustomerNameNew(TestSuite.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(TestSuite.custDetails[1]);
        manualEntryPage.setRequestorNew(TestSuite.custDetails[2]);
        manualEntryPage.setBuyersNew(TestSuite.custDetails[3]);
        manualEntryPage.setPoNumberNew(TestSuite.custDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {TestSuite.yourMatNum,String.valueOf(TestSuite.quantity)},
            //line 2 details
            {TestSuite.yourMatNum2,String.valueOf(TestSuite.quantity)}
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
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSubmit();
        pendPage.waitForLoad();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = pendPage.getRow(TestSuite.lastUsedPO);
        Ecomm_OrderViewPage orderView = pendPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getYourMatNumCell().getText().equals(TestSuite.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(TestSuite.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(TestSuite.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(TestSuite.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(TestSuite.expShadeCode),"Order view: Shade code does not match expected input");
    
        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = pendPage.getOrderNo(rowNumber);
        System.out.println("Order Number: " + orderNumber);
    
        System.out.println("----------------------------------------------------");
        
        driver.close();
    } 
    
    @Test //Manual Entry Page :: Single line order, YMN without master shade code
    public void SUSST3() throws InterruptedException {
        //New chrome driver
        WebDriver driver = new ChromeDriver();

        //new base test to handle set up
        Ecomm_SUSST_Base susstTest3 = new Ecomm_SUSST_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest3.SUSST_SetUp("MANUAL ENTRY SUSST3: Single line, Your Material Number without master data shade code","G_OOC_ME_SUSST_3");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page loaded. Entering customer details...");
        
        //Input Customer Details
        manualEntryPage.setCustomerNameNew(TestSuite.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(TestSuite.custDetails[1]);
        manualEntryPage.setRequestorNew(TestSuite.custDetails[2]);
        manualEntryPage.setBuyersNew(TestSuite.custDetails[3]);
        manualEntryPage.setPoNumberNew(TestSuite.custDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {TestSuite.yourMatNum,TestSuite.shadeCode,String.valueOf(TestSuite.quantity)},
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
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSubmit();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        pendPage.waitForLoad();
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = pendPage.getRow(TestSuite.lastUsedPO);
        Ecomm_OrderViewPage orderView = pendPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(TestSuite.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(TestSuite.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(TestSuite.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(TestSuite.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(TestSuite.shadeCode),"Order view: Shade code does not match expected input");
    
        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = pendPage.getOrderNo(rowNumber);
        System.out.println("Order Number: " + orderNumber);
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        
    }
    
    @Test //Manual Entry Page :: Multi-line order, YMN without master shade code
    public void SUSST4() throws InterruptedException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest4 = new Ecomm_SUSST_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.SUSST_SetUp("MANUAL ENTRY SUSST4: Multiple lines, Your Material Number without master data shade code","G_OOC_ME_SUSST_4");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input Customer Details
        manualEntryPage.setCustomerNameNew(TestSuite.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(TestSuite.custDetails[1]);
        manualEntryPage.setRequestorNew(TestSuite.custDetails[2]);
        manualEntryPage.setBuyersNew(TestSuite.custDetails[3]);
        manualEntryPage.setPoNumberNew(TestSuite.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {TestSuite.yourMatNum,TestSuite.shadeCode,String.valueOf(TestSuite.quantity)},
            {TestSuite.yourMatNum2,TestSuite.shadeCode,String.valueOf(TestSuite.quantity)}
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
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSubmit();
        pendPage.waitForLoad();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = pendPage.getRow(TestSuite.lastUsedPO);
        Ecomm_OrderViewPage orderView = pendPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
        
        Verify.verify(orderView.getYourMatNumCell().getText().equals(TestSuite.yourMatNum),"Order view: Your Material Number does not match input");
        Verify.verify(orderView.getArticleCell().getText().equals(TestSuite.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(TestSuite.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(TestSuite.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(TestSuite.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        //Output order number for test reference
        String orderNumber = pendPage.getOrderNo(rowNumber);
        System.out.println("Order Number: " + orderNumber);
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
    }
    
    @Test //Manual Entry Page :: Single line order, article and shade code
    public void SUSST5() throws InterruptedException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest5 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest5.SUSST_SetUp("MANUAL ENTRY SUSST5: Single line, using Article and shade code","G_OOC_ME_SUSST_5");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input customer details
        manualEntryPage.setCustomerNameNew(TestSuite.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(TestSuite.custDetails[1]);
        manualEntryPage.setRequestorNew(TestSuite.custDetails[2]);
        manualEntryPage.setBuyersNew(TestSuite.custDetails[3]);
        manualEntryPage.setPoNumberNew(TestSuite.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;
        
        //Order details to be entered
        String[][] details = {
            //line 1 details
            {TestSuite.expArticle,TestSuite.shadeCode,String.valueOf(TestSuite.quantity)},
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
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSubmit();
        pendPage.waitForLoad();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = pendPage.getRow(TestSuite.lastUsedPO);
        Ecomm_OrderViewPage orderView = pendPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
        
        Verify.verify(orderView.getArticleCell().getText().equals(TestSuite.expArticle),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(TestSuite.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(TestSuite.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(TestSuite.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = pendPage.getOrderNo(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.close();

        System.out.println("----------------------------------------------------");
        
    }
     
    @Test //Manual Entry Page :: Multi-line order, using Article and shade code
    public void SUSST6() throws InterruptedException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest6 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest6.SUSST_SetUp("MANUAL ENTRY SUSST6: Multiple lines, using Article and shade code","G_OOC_ME_SUSST_6");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input customer details
        manualEntryPage.setCustomerNameNew(TestSuite.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(TestSuite.custDetails[1]);
        manualEntryPage.setRequestorNew(TestSuite.custDetails[2]);
        manualEntryPage.setBuyersNew(TestSuite.custDetails[3]);
        manualEntryPage.setPoNumberNew(TestSuite.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;
        
        //Order details to be entered
        String[][] details = {
            //line 1 details
            {TestSuite.article,TestSuite.shadeCode,String.valueOf(TestSuite.quantity)},
            //line 2 details
            {TestSuite.expArticle2,TestSuite.expShadeCode2,String.valueOf(TestSuite.quantity)}
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
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSubmit();
        pendPage.waitForLoad();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = pendPage.getRow(TestSuite.lastUsedPO);
        Ecomm_OrderViewPage orderView = pendPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();

        Verify.verify(orderView.getArticleCell().getText().equals(TestSuite.article),"Order view: Article does not match expected input");
        Verify.verify(orderView.getBrandCell().getText().equals(TestSuite.expBrand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(TestSuite.expTicket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(TestSuite.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        //Output order number for test reference
        String orderNumber = pendPage.getOrderNo(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.close();

        System.out.println("----------------------------------------------------");
    } 
    
    @Test //Manual Entry Page :: Single line order, using brand/ticket/length/finish and shade code
    public void SUSST7() throws InterruptedException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest7 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest7.SUSST_SetUp("MANUAL ENTRY SUSST7: Single line, using brand, ticket, length, finish, and shade code","G_OOC_ME_SUSST_7");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        //Input customer details
        manualEntryPage.setCustomerNameNew(TestSuite.custDetails[0]);    
        manualEntryPage.setShipToPartyNew(TestSuite.custDetails[1]);
        manualEntryPage.setRequestorNew(TestSuite.custDetails[2]);
        manualEntryPage.setBuyersNew(TestSuite.custDetails[3]);
        manualEntryPage.setPoNumberNew(TestSuite.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;
        
        //Order details to be entered
        String[][] details = {
            //line 1 details
            {TestSuite.brand,TestSuite.ticket,TestSuite.length,TestSuite.finish,TestSuite.shadeCode,String.valueOf(TestSuite.quantity)},           
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
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSubmit();
        pendPage.waitForLoad();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = pendPage.getRow(TestSuite.lastUsedPO);
        Ecomm_OrderViewPage orderView = pendPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
       
        Verify.verify(orderView.getBrandCell().getText().equals(TestSuite.brand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(TestSuite.ticket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(TestSuite.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();
        
        //Output order number for test reference
        String orderNumber = pendPage.getOrderNo(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.close();

        System.out.println("----------------------------------------------------");
    }
    
    @Test //Manual Entry Page :: Multi-line order, using brand/ticket/length/finish and shade code
    public void SUSST8() throws InterruptedException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST8: Multiple lines, using brand, ticket, length, finish, and shade code","G_OOC_ME_SUSST_8");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page reached. Entering customer details...");
        
        //Input customer details
        manualEntryPage.setCustomerNameNew(TestSuite.custDetails[0]);  
        manualEntryPage.setShipToPartyNew(TestSuite.custDetails[1]);
        manualEntryPage.setRequestorNew(TestSuite.custDetails[2]);
        manualEntryPage.setBuyersNew(TestSuite.custDetails[3]);
        manualEntryPage.setPoNumberNew(TestSuite.custDetails[4]);
        
        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 2;
        
        //Order details to be entered
        String[][] details = {
            //line 1 details
            {TestSuite.brand,TestSuite.ticket,TestSuite.length,TestSuite.finish,TestSuite.shadeCode,String.valueOf(TestSuite.quantity)},  
            //line 2 details
            {TestSuite.brand2,TestSuite.ticket2,TestSuite.length2,TestSuite.finish2,TestSuite.shadeCode2,String.valueOf(TestSuite.quantity2)}
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
        Ecomm_PendingApprovalListPage pendPage = orderConf.pressSubmit();
        pendPage.waitForLoad();

        System.out.println("Order submitted. Verifying values in outstanding order against inputs...");
        
        //Verify values in outstanding orders tab
        //Get the row number of the order in the table and press view
        int rowNumber = pendPage.getRow(TestSuite.lastUsedPO);
        Ecomm_OrderViewPage orderView = pendPage.pressView(rowNumber);
        orderView.waitForContent();
        orderView.switchTo();
        
        Verify.verify(orderView.getBrandCell().getText().equals(TestSuite.brand),"Order view: Brand does not match expected input");
        Verify.verify(orderView.getTicketCell().getText().equals(TestSuite.ticket),"Order view: Ticket does not match expected input");
        Verify.verify(orderView.getShadeCodeCell().getText().equals(TestSuite.shadeCode),"Order view: Shade code does not match expected input");

        System.out.println("Values verified.");
        
        //Exit view
        orderView.exitView();
        orderView.waitForInvisibility();
        driver.switchTo().defaultContent();

        //Output order number for test reference
        String orderNumber = pendPage.getOrderNo(rowNumber);
        System.out.println("Order Number: " + orderNumber);

        driver.close();

        System.out.println("----------------------------------------------------");
    }
    
}
