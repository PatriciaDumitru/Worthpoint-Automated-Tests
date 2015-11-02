
package com.coats.selenium.tests;

import PageObjects.CCE_MainPage;
import PageObjects.CCE_OrderStatusPage;
import PageObjects.CCE_OutstandingDraftPage;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class TestTest extends DriverFactory{
    
    @Test
    (groups = {})
    public void Test1() throws Exception {
        WebDriver driver = getDriver();
        
        Cce_Base base = new Cce_Base(driver);
        CCE_MainPage mainPage = base.SUMST_SetUp("Enrich Orders EO1: Page and filter checks, Hub SOS selection", "G_CCE_EO_1");
        
        CCE_OutstandingDraftPage page = mainPage.pressOutstandingDraft();
        page.waitForElement();
        
        System.out.println("Checking fields...");
        
        page.checkFields();
    }
    
}
