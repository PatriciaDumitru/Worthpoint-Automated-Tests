
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_BackendFailedFilesPage extends WBA_BasePage {
    
    //Locators
    By custNameField = By.id("s2id_filterErrorLogCustomerId");
    By fileNameField = By.id("filterErrorLogFileName");
    By salesOrgField = By.id("s2id_filterErrorLogSalesOrgId");
    By searchButton = By.cssSelector("#FilterErrorLogForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > table > tbody > tr > td > div > input");
    By resetButton = By.cssSelector("#FilterErrorLogForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > table > tbody > tr > td > a");
    By viewButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(6) > a");
    By downloadButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(7) > a");
    By errorTitle = By.cssSelector("#content > h1");
    By noRecordsField = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > div > div");
    
    public Ecomm_BackendFailedFilesPage(WebDriver driver) {
        super(driver);
    }
    
    public Ecomm_BackendFailedFilesPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public Ecomm_BackendFailedFilesPage setFileName(String item) {
        CommonTask.setTextField(driver, fileNameField, item);
        return this;
    }
    
    public Ecomm_BackendFailedFilesPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return this;
    }
    
    public Ecomm_BackendFailedFilesPage pressSearch() {
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return this;
    }
    
    public Ecomm_BackendFailedFilesPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return this;
    }
    
    public Ecomm_BackendFailedFilesPage pressDownload() {
        WebElement dl = Wait.clickable(driver,downloadButton);
        dl.click();
        
        try {
            WebElement error = Wait.visible(driver,errorTitle);
            if (error.getText().contains("Not Found")) {
                System.out.println("***Error downloading file: File/Page not found. Check manually that first line file can be downloaded***");
            } else {
                System.out.println("***Error downloading file. Check manually that first line file can be downloaded***");
            }
        } catch (Exception e) {
            System.out.println("Item downloaded.");
        }
        
        return this;
    }
    
    public Ecomm_OrderViewPage pressView() {
        WebElement view = Wait.clickable(driver,viewButton);
        view.click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement fileName = Wait.clickable(driver,fileNameField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        WebElement view = Wait.clickable(driver,viewButton);
        WebElement dl = Wait.clickable(driver,downloadButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Backend Failed Files Page: Customer name field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: Sales Organisation field not displayed correctly",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: File Name field not displayed correctly",fileName.isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: Search Button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: Reset Button not displayed correctly",reset.isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: View Button not displayed correctly",view.isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: Download Button not displayed correctly",dl.isDisplayed());
        
    }
    
    public boolean checkForRecords() {
        boolean returnMe = true;
        
        try {
            WebElement waitForElement = Wait.presence(driver,noRecordsField);
            returnMe = false;
        } catch (Exception e) {
            returnMe = true;
        }
        
        return returnMe;
    }
}
