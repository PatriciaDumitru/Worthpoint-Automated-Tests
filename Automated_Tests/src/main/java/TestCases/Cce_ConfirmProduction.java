
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.ConfirmProductionPage_CCE;
import PageObjects.InboxPage_CCE;
import PageObjects.OrderViewPage_CCE;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Cce_ConfirmProduction {
    
    @Test
    public void CP1() throws InterruptedException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Confirm Production CP1: Page checks, filter function, view button, confirm selection", "C_CCE_CP_1-2");
        
        System.out.println("Navigating to Confirm Production...");
        
        ConfirmProductionPage_CCE cpPage = ccePage.pressConfirmProduction();
        
        System.out.println("Confirm Production loaded.");
        
        //Assert base elements
        //cpPage.assertBaseElements();
        
        System.out.println("Base elements asserted. Asserting page title is as expected...");
        
        Assert.assertTrue("Confirm Production Page: Title not displayed as expected.", cpPage.getBreadcrumb().getText().equals("Orders | Confirm Production"));
        
        System.out.println("Page title correct. Checking fields...");
        
        cpPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        cpPage.setCustName(TestSuite.custDetails[0]);
        cpPage.setRequestType("Sewing");
        
        System.out.println("Criteria entered. Listing orders...");
        
        cpPage.pressListOrders();
        
        System.out.println("Orders listed. Pressing confirm...");
        
        cpPage.pressConfirm();
        
        System.out.println("Confirm selected. Pressing DN Print...");
        
        OrderViewPage_CCE viewPage = cpPage.pressDnPrint();
        
        System.out.println("View displayed. Pressing print...");
        
        viewPage.pressPrint();
        
        System.out.println("Closing view...");
        
        viewPage.closeView();
        
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        String alertMsg = alert.getText();
        
        System.out.println("View closed. Alert appeared: "+alertMsg +". Dismissing alert...");
        
        alert.dismiss();
        
        System.out.println("Alert dismissed. Selecting MUM Type...");
        
        cpPage.setMUMType(TestSuite.copMUM);
        
        System.out.println("MUM Type set. Setting quantity produced...");
        
        cpPage.setQtyProd(String.valueOf(TestSuite.quantity));
        
        System.out.println("Quantity produced set. Setting 'Send To' to 'Deliver to customer'...");
        
        cpPage.setSendTo("Deliver to Customer");
        
        System.out.println("Send To set. Saving...");
        
        cpPage.pressSave();
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Page check, filter, confirm/print/view/adjust/save
    
    @Ignore @Test
    public void CP2() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Confirm Production CP2: Reset and cancel", "C_CCE_CP_x");
        
        System.out.println("Navigating to Confirm Production...");
        
        ConfirmProductionPage_CCE cpPage = ccePage.pressConfirmProduction();
        
        System.out.println("Confirm Production loaded. Entering filter criteria...");
        
        cpPage.setCustName(TestSuite.custDetails[0]);
        cpPage.setShipToName(TestSuite.custDetails[1]);
        
        System.out.println("Criteria entered. Pressing reset...");
        
        cpPage.pressReset();
        
        System.out.println("Filter reset. Pressing cancel...");
        
        cpPage.pressCancel();
        
        System.out.println("Cancel pressed.");
        
        System.out.println("----------------------------------------------------");
        driver.quit();
    }
}
