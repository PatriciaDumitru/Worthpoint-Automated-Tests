
package PageObjects;

import AutomationFramework.CommonTask;
import static PageObjects.BasePage.driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FTPFailedFilesPage_EComm extends BasePage {

    //Locators
    By custNameField = By.id("s2id_filterIntegrationErrorLogCustomerId");
    By fileNameField = By.id("filterIntegrationErrorLogFileName");
    By salesOrgField = By.id("s2id_filterIntegrationErrorLogSalesOrgId");
    By searchButton = By.cssSelector("#FilterShowinerrorlistForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > table > tbody > tr > td > div > input");
    By resetButton = By.cssSelector("#FilterShowinerrorlistForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > table > tbody > tr > td > a");
    By viewButton = By.cssSelector("#FilterShowinerrorlistForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(6) > a");
    By downloadButton = By.cssSelector("#FilterShowinerrorlistForm > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(7) > a");
    By errorTitle = By.cssSelector("#content > h1");
    
    public FTPFailedFilesPage_EComm(WebDriver driver) {
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
    
    public FTPFailedFilesPage_EComm setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public FTPFailedFilesPage_EComm setFileName(String item) {
        CommonTask.setTextField(driver, fileNameField, item);
        return this;
    }
    
    public FTPFailedFilesPage_EComm setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return this;
    }
    
    public FTPFailedFilesPage_EComm pressSearch() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public FTPFailedFilesPage_EComm pressReset() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return this;
    }
    
    public FTPFailedFilesPage_EComm pressDownload() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(downloadButton));
        driver.findElement(downloadButton).click();
        
        try {
            WebElement waitForError = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(errorTitle));
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
    
    public OrderViewPage pressView() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
        driver.findElement(viewButton).click();
        return new OrderViewPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForSalesOrg = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement waitForFileName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fileNameField));
        WebElement waitForSearch = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForView = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButton));
        WebElement waitForDownload = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(downloadButton));
        
        //Assert all elements are displayed
        Assert.assertTrue("Backend Failed Files Page: Customer name field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Backend Failed Files Page: Sales Organisation field not displayed correctly",getSalesOrgField().isDisplayed());
        Assert.assertTrue("Backend Failed Files Page: File Name field not displayed correctly",getFileNameField().isDisplayed());
        Assert.assertTrue("Backend Failed Files Page: Search Button not displayed correctly",getSearchButton().isDisplayed());
        Assert.assertTrue("Backend Failed Files Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
        Assert.assertTrue("Backend Failed Files Page: View Button not displayed correctly",getViewButton().isDisplayed());
        Assert.assertTrue("Backend Failed Files Page: Download Button not displayed correctly",getDownloadButton().isDisplayed());
        
    }
    
}
