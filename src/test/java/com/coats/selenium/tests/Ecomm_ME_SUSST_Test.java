
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import com.google.common.base.Verify;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class Ecomm_ME_SUSST_Test extends DriverFactory {
    
    @Test //Manual Entry Page :: SUSST :: Page checks and single line order using YMN and master shade
    (groups = {"eComm","eComm_Orders","Solo"})
    public void SUSST1() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY SUSST1: Single line, Your Material Number with master data shade code","G_OOC_ME_SUSST_1",DataItems.validCustUsername,DataItems.validCustPassword);

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry loaded.");

        manualEntryPage.assertBaseElements();

        System.out.println("Checking title...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Title not displayed as expected",manualEntryPage.getBreadcrumb().getText().equals("Orders | Manual Entry"));
        
        System.out.println("Title checked. Checking fields...");
        
        manualEntryPage.checkSUSSTFields();
        
        System.out.println("Fields checked. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setShipToParty(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.conOrdDetails[3]);
        manualEntryPage.setPONumber(DataItems.conOrdDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNumSUSST,String.valueOf(DataItems.quantity)}
            //Further line details, each in their own array
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.brandSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.ticketSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.lengthSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.finishSUSST));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill shade code not as expected in master data",manualEntryPage.getShadeCode(0).equals(DataItems.shadeSUSST));
        
        System.out.println("Auto-fill correct. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();
        orderConf.waitForElement();

        System.out.println("Order confirmation page reached. Checking line details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Your Material Number not maintained after manual entry page",orderConf.getYourMatNum().equals(DataItems.yourMatNumSUSST));
        AssertJUnit.assertTrue("Order Confirmation Page: Quantity not maintained after manual entry page",orderConf.getQuantity().equals(DataItems.quantity));
        
        //Press Submit
        Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit(); 
        outOrders.waitForElement();

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
    
}
