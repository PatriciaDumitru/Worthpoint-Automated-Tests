
package PageObjects;

import AutomationFramework.CommonTask;
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
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getFileNameField() {
        return driver.findElement(fileNameField);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getViewButton() {
        return driver.findElement(viewButton);
    }
    
    public WebElement getDownloadButton() {
        return driver.findElement(downloadButton);
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
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public Ecomm_BackendFailedFilesPage pressReset() {
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public Ecomm_BackendFailedFilesPage pressDownload() {
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(downloadButton));
        driver.findElement(downloadButton).click();
        
        try {
            WebElement waitForError = new WebDriverWait(driver,8).until(ExpectedConditions.visibilityOfElementLocated(errorTitle));
            if (driver.findElement(errorTitle).getText().contains("Not Found")) {
                System.out.println("***Error downloading file: File not found. Check manually that first line file can be downloaded***");
            } else {
                System.out.println("***Error downloading file. Check manually that first line file can be downloaded***");
            }
        } catch (Exception e) {
            System.out.println("Item downloaded.");
        }
        
        return this;
    }
    
    public Ecomm_OrderViewPage pressView() {
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForSalesOrg = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement waitForFileName = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(fileNameField));
        WebElement waitForSearch = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForView = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForDownload = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(downloadButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Backend Failed Files Page: Customer name field not displayed correctly",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: Sales Organisation field not displayed correctly",getSalesOrgField().isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: File Name field not displayed correctly",getFileNameField().isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: View Button not displayed correctly",getViewButton().isDisplayed());
        AssertJUnit.assertTrue("Backend Failed Files Page: Download Button not displayed correctly",getDownloadButton().isDisplayed());
        
    }
    
    public boolean checkForRecords() {
        boolean returnMe = true;
        
        try {
            WebElement waitForElement = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(noRecordsField));
            returnMe = false;
        } catch (Exception e) {
            returnMe = true;
        }
        
        return returnMe;
    }
}
