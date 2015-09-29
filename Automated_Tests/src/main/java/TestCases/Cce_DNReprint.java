
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.CcePage;
import PageObjects.DNPrintPage_CCE;
import PageObjects.DNReprintPage_CCE;
import com.google.common.base.Verify;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cce_DNReprint {
    
    @Test
    public void DR1() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_1");
        
        System.out.println("Navigating to DN Reprint...");
        
        DNReprintPage_CCE dnReprint = ccePage.pressDNReprint();
        
        System.out.println("DN Reprint loaded.");
        
        //Assert base elements
        dnReprint.assertBaseElements();
        
        System.out.println("Asserting page title is as expected...");
        
        //Assert page title is correct
        Verify.verify(dnReprint.getBreadcrumb().getText().equals("Orders | Delivery Notes"));
        
        System.out.println("Page title correct. Checking fields are displayed as expected...");
        
        dnReprint.checkFields();
        
        System.out.println("Test complete.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Check fields
    
    @Test
    public void DR2() {
                
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_2");
        
        System.out.println("Navigating to DN Reprint...");
        
        DNReprintPage_CCE dnReprint = ccePage.pressDNReprint();
        
        System.out.println("DN Reprint loaded. Entering filter criteria...");
        
        dnReprint.setShipToPartyName(TestSuite.custDetails[1]);
        
        System.out.println("Criteria entered. Listing orders...");
        
        dnReprint.pressList();
        
        System.out.println("Orders listed. Pressing confirm...");
        
        dnReprint.pressConfirm();
        
        System.out.println("Confirm pressed. Pressing print...");
        
        DNPrintPage_CCE printPage = dnReprint.pressPrint();
        
        System.out.println("Print pressed. Displaying delivery notes...");
        
        //Ensure frame is selected
        printPage.switchTo();
        
        System.out.println("Delivery notes displayed.");
        
        if (TestSuite.printingEnabled) {           
            dnReprint = printPage.pressPrint();
            System.out.println("Delivery notes sent to printer.");
        } else {
            dnReprint = printPage.pressClose();
            System.out.println("Deliveyr notes view closed.");
        }
        
        System.out.println("Test complete.");
        
        System.out.println("----------------------------------------------------");

    } //Filter and confirm
    
    @Test
    public void DR3() {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_2");
        
        System.out.println("Navigating to DN Reprint...");
        
        DNReprintPage_CCE dnReprint = ccePage.pressDNReprint();
        
        System.out.println("DN reprint loaded. Pressing confirm...");
        
        dnReprint.pressConfirm();
        
        System.out.println("Confirm pressed. Pressing print...");
    } //Confirm and press print
    
    
}
