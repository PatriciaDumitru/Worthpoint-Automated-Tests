
package com.coats.selenium.tests;

import PageObjects.CCE_AddOrderPage;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_OrderStatusPage;
import com.coats.selenium.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Cce_OrderStatus_Test extends DriverFactory {
    
    @Test //Order status page :: page and filter checks, reset function, export data
    (groups = {"CCE"})
    public void OS1() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("ORDER STATUS OS1: Export data", "G_CCE_OS_3");
        
        System.out.println("Navigating to Order Status...");
        
        CCE_OrderStatusPage orderStatus = ccePage.pressOrderStatus();
        orderStatus.waitForLoad();
        
        System.out.println("Order Status reached.");
        
        orderStatus.assertBaseElements();
        
        System.out.println("Checking fields...");
        
        orderStatus.checkFields();
        
       /* System.out.println("Fields checked. Entering filter criteria...");
        
        orderStatus.setCustName(DataItems.custDetails[0]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        orderStatus.pressListOrders();
        orderStatus.waitForLoad();
        
        System.out.println("Orders listed. Exporting data...");
        
        Ecomm_ExportDownloadPage dlPage = orderStatus.pressExport();
        dlPage.waitForDownloadCompletion();
        
        System.out.println("Exported. Resetting filter...");
        
        orderStatus.pressReset();
        orderStatus.waitForLoad();
        
        System.out.println("Filter reset.");*/

    }

}
