
package TestCases;

import AutomationFramework.*;
import PageObjects.CcePage;
import PageObjects.OrderSamplesPage;
import PageObjects.OrderStatusPage_CCE;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_OrderStatus {
    
    @Test
    public void OS1() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("ORDER STATUS OS1: Export data", "G_CCE_OS_3");
        
        System.out.println("Navigating to Order Status...");
        
        OrderStatusPage_CCE orderStatus = ccePage.pressOrderStatus();
        
        System.out.println("Order Status reached. Asserting base elements...");
        
        orderStatus.assertBaseElements();
        
        System.out.println("Base elements asserted. Checking fields...");
        
        orderStatus.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        orderStatus.setCustName(TestSuite.custDetails[0]);
        orderStatus.setShadeCode(TestSuite.expShadeCode2);
        
        System.out.println("Criteria entered. Listing orders...");
        
        orderStatus.pressListOrders();
        
        System.out.println("Orders listed. Exporting data...");
        
        orderStatus.export();
        
        System.out.println("Exported. Resetting filter...");
        
        orderStatus.pressReset();
        
        System.out.println("Filter reset.");
        
        System.out.println("----------------------------------------------------");
        
    }
        
    
}
