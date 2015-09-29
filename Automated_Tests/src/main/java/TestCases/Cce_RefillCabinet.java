
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.ConfirmProductionPage_CCE;
import PageObjects.RefillCabinetPage_CCE;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cce_RefillCabinet {
    @Test
    public void RC1() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("Refill Cabinet RC1: Page checks, filter function", "C_CCE_RC_1-2");
        
        System.out.println("Navigating to Refill Cabinet...");
        
        RefillCabinetPage_CCE rcPage = ccePage.pressRefillCabinet();
        
        System.out.println("Refill Cabinet loaded.");
        
        //Assert base elements
        rcPage.assertBaseElements();
        
        System.out.println("Base elements asserted. Asserting page title is as expected...");
        
        Assert.assertTrue("Refill Cabinet Page: Title not as expected",rcPage.getBreadcrumb().getText().equals("Refill Cabinet"));
        
        System.out.println("Title correct. Checking fields...");
        
        rcPage.checkFields();
        
        System.out.println("Fields checked. Entering filter crtieria...");
        
        rcPage.setShipTo(TestSuite.custDetails[1]);
        
        System.out.println("Criteria entered. Pressing cancel...");
        
        rcPage.pressCancel();
        
        System.out.println("Cancel pressed.");
        
        System.out.println("----------------------------------------------------");
        driver.quit();
    }
}
