
package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class Ecomm_ME_Exceptions_Test extends DriverFactory {
    
    @Test //Manual Entry Exception :: SUMST :: Your Material Number does not exist
    (groups = {"eComm","eComm_Orders", "QuickTest"})
    public void MEex1() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("MANUAL ENTRY Exceptions ME_Ex1: Your Material Number not available","GE_OC_ME_Ex1",DataItems.validCoatsUsername2,DataItems.validCoatsPassword);

        eCommPage.waitForElement();

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry loaded. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        manualEntryPage.setYourMaterialNumber("InvalidMatNum",0);
        
        //System.out.println(manualEntryPage.getBrand(0));
        
        AssertJUnit.assertTrue("Manual Entry Page: Invalid/Non-existing material number causes auto-fill",manualEntryPage.getBrand(0).equals("Select"));
        
        System.out.println("No auto-fill triggered, as expected. Entering article and shade code...");
        
        manualEntryPage.setArticle(DataItems.article3,0);
        manualEntryPage.setShadeCode(DataItems.expShadeCode, 0);
        manualEntryPage.getQtyField().click();
        
        System.out.println("Details set. Checking alert appears...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Add Material alert did not appear",manualEntryPage.waitForAddMaterialMessage());
        
        System.out.println("Alert appeared: " + manualEntryPage.getAddMaterialAlertText());
    }
    
    @Test //Manual Entry Exception :: SUSST :: Missing Material (YMN/Shade Code)
    (groups = {"eComm","eComm_Orders"})
    public void MEex2() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest4 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest4.setUp("MANUAL ENTRY Exceptions ME_Ex2: SUSST Missing Material (YMN/Shade)","GE_OC_ME_Ex2",DataItems.validCoatsUsername2,DataItems.validCoatsPassword);

        eCommPage.waitForElement();

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();
        
        System.out.println("Manual Entry loaded. Entering customer details...");

        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        String yourMaterialNumber = CommonTask.generatePO("YourMatNum_");
        
        manualEntryPage.setYourMaterialNumber(yourMaterialNumber,0);
        
        System.out.println("Your Material Number used: " + yourMaterialNumber);
        
        System.out.println(manualEntryPage.getBrand(0));
        
        AssertJUnit.assertTrue("Manual Entry Page: Invalid/Non-existing material number causes auto-fill",manualEntryPage.getBrand(0).equals("Select"));
        
        System.out.println("No auto-fill triggered, as expected. Entering article and shade code...");
        
        manualEntryPage.setArticle(DataItems.article3,0);
        manualEntryPage.setShadeCode(DataItems.expShadeCode, 0);
        manualEntryPage.getQtyField().click();
        
        System.out.println("Details set. Checking alert appears...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Add Material alert did not appear",manualEntryPage.waitForAddMaterialMessage());
        
        System.out.println("Alert appeared: " + manualEntryPage.getAddMaterialAlertText());
    }
    
}
