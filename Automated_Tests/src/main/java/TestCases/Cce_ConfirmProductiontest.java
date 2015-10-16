
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.Categories.CCE;
import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_ConfirmProductionPage;
import PageObjects.CCE_InboxPage;
import PageObjects.CCE_OrderViewPage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Category(CCE.class)
public class Cce_ConfirmProductiontest {
    
    @Test
    public void CP1() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("Confirm Production CP1: Page checks, filter function, view button, confirm selection", "C_CCE_CP_1-2");
        
        System.out.println("Navigating to Confirm Production...");
        
        CCE_ConfirmProductionPage cpPage = ccePage.pressConfirmProduction();
        cpPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\1Confirm production page.png"));
        
        System.out.println("Confirm Production loaded.");
        
        //Assert base elements
        cpPage.assertBaseElements();
        
        System.out.println("Asserting page title is as expected...");
        
        Assert.assertTrue("Confirm Production Page: Title not displayed as expected.", cpPage.getBreadcrumb().getText().equals("Orders | Confirm Production"));
        
        System.out.println("Page title correct. Checking fields...");
        
        cpPage.checkFields();
        
        System.out.println("Fields checked. Entering filter criteria...");
        
        cpPage.setCustName(DataItems.custDetails[0]);
        cpPage.setRequestType("Sewing");
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\2Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Listing orders...");
        
        cpPage.pressListOrders();
        cpPage.waitForLoad();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\3Orders Listed.png"));
        
        System.out.println("Orders listed. Pressing confirm...");
        
        cpPage.pressConfirm();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\4Confirm selected.png"));
        
        System.out.println("Confirm selected. Pressing DN Print...");
        
        CCE_OrderViewPage viewPage = cpPage.pressDnPrint();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\5DN view.png"));
        
        System.out.println("View displayed. Pressing print...");
        
        viewPage.pressPrint();
        
        System.out.println("Closing view...");
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\6DN View.png"));
        
        try {
            Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
            String alertMsg = alert.getText();
        
            System.out.println("View closed. Alert appeared: "+alertMsg +". Dismissing alert...");
        
            alert.dismiss();
            
            System.out.println("Alert dismissed.");
        } catch (Exception e) {
            System.out.println("No additional alerts upon close.");
        }
        
        
        
        System.out.println("Selecting MUM Type...");
        
        cpPage.setMUMType(DataItems.copMUM);
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\7MUM type set.png"));
        
        System.out.println("MUM Type set. Setting quantity produced...");
        
        cpPage.setQtyProd(String.valueOf(DataItems.quantity));
        
        //Take a screenshot
        File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile8,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\8Quantity produced set.png"));
        
        System.out.println("Quantity produced set. Setting 'Send To' to 'Deliver to customer'...");
        
        cpPage.setSendTo("Deliver to Customer");
        
        //Take a screenshot
        File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\9Send to set.png"));
        
        System.out.println("Send To set. Saving...");
        
        cpPage.pressSave();
        cpPage.waitForLoad();
        
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\10Changes saved.png"));
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        
    } //Page check, filter, confirm/print/view/adjust/save
    
    @Test
    public void CP2() throws IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("Confirm Production CP2: Reset and cancel", "C_CCE_CP_x");
        
        System.out.println("Navigating to Confirm Production...");
        
        CCE_ConfirmProductionPage cpPage = ccePage.pressConfirmProduction();
        
        System.out.println("Confirm Production loaded. Entering filter criteria...");
        
        cpPage.setCustName(DataItems.custDetails[0]);
        cpPage.setShipToName(DataItems.custDetails[1]);
        
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\10Filter criteria entered.png"));
        
        System.out.println("Criteria entered. Pressing reset...");
        
        CCE_ConfirmProductionPage cpPage2 = cpPage.pressReset();
        cpPage2.waitForLoad();
        
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\11Reset pressed.png"));
        
        System.out.println("Filter reset. Pressing cancel...");
        
        cpPage2.pressCancel();
        cpPage2.waitForLoad();
        
        //Take a screenshot
        File scrFile12 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile12,new File(DataItems.screenshotsFilepath+"\\CCE\\Confirm Production\\12Page cancelled.png"));
        
        System.out.println("Cancel pressed.");
        
        System.out.println("----------------------------------------------------");
        driver.close();
    }
}
