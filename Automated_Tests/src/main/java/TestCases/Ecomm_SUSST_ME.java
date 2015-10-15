
package TestCases;

//A collection of order creation tests for Single User Single Sold-to

import AutomationFramework.Categories;
import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
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
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Alert;
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

@Category(Categories.eComm.class)
public class Ecomm_SUSST_ME {
    
    @Category({Categories.eComm_Orders_ManualEntry.class,Categories.QuickSuite.class})
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
        FileUtils.copyFile(scrFile,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\1Manual Entry Page.png"));
        
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
    
        driver.close();
        driver.quit();
    
        System.out.println("----------------------------------------------------");
    
    }
    
    @Category(Categories.eComm_Orders_ManualEntry.class)
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
    
        System.out.println("----------------------------------------------------");
        
        driver.close();
        driver.quit();
    } 
    
    @Category(Categories.eComm_Orders_ManualEntry.class)
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
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        driver.quit();
        
    }
    
    @Category({Categories.eComm_Orders_ManualEntry.class,Categories.QuickSuite.class})
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
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        driver.quit();
    }
    
    @Category(Categories.eComm_Orders_ManualEntry.class)
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

        driver.close();
        driver.quit();

        System.out.println("----------------------------------------------------");
        
    }
     
    @Category(Categories.eComm_Orders_ManualEntry.class)
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

        driver.close();
        driver.quit();

        System.out.println("----------------------------------------------------");
    } 
    
    @Category(Categories.eComm_Orders_ManualEntry.class)
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

        driver.close();
        driver.quit();

        System.out.println("----------------------------------------------------");
    }
    
    @Category(Categories.eComm_Orders_ManualEntry.class)
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

        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
    }
    
    @Category(Categories.eComm_Orders_ManualEntry_Validation.class)
    @Test //Manual Entry Page :: Validation tests, no requester at manual entry page
    public void SUSST9() throws InterruptedException, IOException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
        //new base test to handle set up
        Ecomm_SUSST_Base susstTest8 = new Ecomm_SUSST_Base(driver);
        //Set up returns an eComm main page
        Ecomm_MainPage eCommPage = susstTest8.SUSST_SetUp("MANUAL ENTRY SUSST9: Check validation at Manual Entry Page (no requester)","G_OOC_ME_SUSST_Unknown");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
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
        
        manualEntryPage.pressNextExpectingFailure();
        
        try {
            
            Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);
            
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
            
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
         
    }
    
    @Category(Categories.eComm_Orders_ManualEntry_Validation.class)
    @Test //Manual Entry Page :: Validation tests, no ship to party name at manual entry page
    public void SUSST10() throws InterruptedException, IOException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
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
        
        manualEntryPage.pressNextExpectingFailure();
        
        try {
            
            Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);
            WebElement flashMessage = mePage.waitForError();
            
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
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
    }
    
    @Category(Categories.eComm_Orders_ManualEntry_Validation.class)
    @Test //Manual Entry Page :: Validation tests, no buyer at manual entry page
    public void SUSST11() throws InterruptedException, IOException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
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
        
        manualEntryPage.pressNextExpectingFailure();
        
        try {
            
            Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);
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
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
    }
    
    @Category(Categories.eComm_Orders_ManualEntry_Validation.class)
    @Test //Manual Entry Page :: Validation tests, no requester at confirmation page
    public void SUSST12() throws InterruptedException, IOException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
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
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
    }
    
    @Category(Categories.eComm_Orders_ManualEntry_Validation.class)
    @Test //Manual Entry Page :: Validation tests, no Ship To Party Name at confirmation page
    public void SUSST13() throws InterruptedException, IOException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
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
        
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
    }
    
    @Category(Categories.eComm_Orders_ManualEntry_Validation.class)
    @Test //Manual Entry Page :: Validation tests, no Buyer at confirmation page
    public void SUSST14() throws InterruptedException, IOException {
        //New driver
        WebDriver driver = new ChromeDriver();
  
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
        
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
    }
    
}
