
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.FeedbackPage_CCE;
import PageObjects.HubSosPage_CCE;
import PageObjects.OrderViewPage_CCE;
import PageObjects.ReceivedHubPage_CCE;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Cce_Hub {
    
    @Test
    public void HS1() throws InterruptedException {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Hub SOS: Page and filter checks", "C_CCE_HS_1-4");
        
        System.out.println("Navigating to Hub SOS...");
        
        HubSosPage_CCE hsPage = ccePage.pressHubSos();
        
        System.out.println("Hub SOS reached. Checking title...");
        
        Assert.assertTrue("Hub SOS Page: Title not displayed as expected",hsPage.getTitle().equals("Orders | Hub SOS"));
        
        System.out.println("Title checked. Asserting base elements...");
        
        hsPage.assertBaseElements();
        
        System.out.println("Base elements asseted. Checking fields...");
        
        hsPage.checkFields();
        
        System.out.println("Entering filter criteria...");
        
        hsPage.setCustName(TestSuite.custDetails[0]);
        hsPage.setBrand(TestSuite.expBrand);
        
        System.out.println("Criteria entered. Pressing list orders...");
        
        hsPage.pressListOrders();
        
        System.out.println("Orders listed. Viewing first item...");
        
        hsPage.pressView();
        
        System.out.println("View reached. Closing view...");
        
        hsPage.closeView();
        
        System.out.println("View closed. Adjusting MUM type of first item...");
        
        String mumType = TestSuite.viconeMUM;
        hsPage.adjustMUMType(mumType);
        
        System.out.println("MUM type adjusted to " + mumType+". Adjusting SOS assignment...");
        
        String assignment = "SOS Lab";
        hsPage.adjustAssignment(assignment);
        
        System.out.println("SOS assignment set. Saving changes...");
        
        hsPage.pressSave();
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    } //Field checks, view check, adjust and save
    
    @Test public void HR1() {
        //New driver object
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Receieved Hub: Page and filter checks", "C_CCE_RH_1-3");
        
        System.out.println("Navigating to Received Hub...");
        
        ReceivedHubPage_CCE rhPage = ccePage.pressReceivedHub();
        
        System.out.println("Received Hub reached. Checking title...");
        
        Assert.assertTrue("Received Hub Page: Title not displayed as expected",rhPage.getTitle().equals("Orders | Received Hub"));
        
        System.out.println("Title checked. Asserting base elements...");
        
        rhPage.assertBaseElements();
        
        System.out.println("Base elements asserted. Checking fields...");
        
        rhPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        rhPage.setCustName(TestSuite.custDetails[0]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        rhPage.pressListOrders();
        
        System.out.println("Orders listed. Resetting filter...");
        
        rhPage.pressReset();
        
        System.out.println("Filter reset. Viewing first order...");
        
        OrderViewPage_CCE orderView = rhPage.pressView();
        
        System.out.println("View open. Closing view...");
        
        orderView.closeView();
        
        System.out.println("Order view closed. Pressing send to customer for first item...");
        
        rhPage.pressSendToCust();
        
        System.out.println("Send to customer selected. Saving...");
        
        rhPage.pressSave();
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
    } //Page checks, filter check, reset and view, send to customer
        
    
}
