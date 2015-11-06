
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_BackendInProcessPage extends WBA_BasePage {
    
    //Locators
    By custNameField = By.id("s2id_filterInProcessFileCustomerId");
    By fileNameField = By.id("filterInProcessFileFileName");
    By fileNameCell = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(3)");
    By statusCell = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(6)");
    By searchButton = By.cssSelector("#FilterShowlistForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > table > tbody > tr > td > div > input");
    By resetButton = By.cssSelector("#FilterShowlistForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > table > tbody > tr > td > a");
    By noRecordsField = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > div > div");
    
    public Ecomm_BackendInProcessPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getFileNameField() {
        return driver.findElement(fileNameField);
    }
    
    public WebElement getFileNameCell() {
        WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(fileNameCell));
        return cell;
    }
    
    public WebElement getStatusCell() {
        WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(statusCell));
        return cell;
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public Ecomm_BackendInProcessPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public Ecomm_BackendInProcessPage setFileName(String item) {
        CommonTask.setTextField(driver, fileNameField, item);
        return this;
    }
    
    public Ecomm_BackendInProcessPage pressSearch() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public Ecomm_BackendInProcessPage pressReset() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForFileName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fileNameField));
        WebElement waitForSearch = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));

        //Assert all elements are displayed
        AssertJUnit.assertTrue("Backend In Process Files Page: Customer name field not displayed",getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Backend In Process Files Page: File name field not displayed",getFileNameField().isDisplayed());
        AssertJUnit.assertTrue("Backend In Process Files Page: Search button not displayed",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Backend In Process Files Page: Reset button not displayed",getResetButton().isDisplayed());
    }
    
    public boolean checkForRecords() {
        boolean returnMe = true;
        
        try {
            WebElement waitForElement = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.presenceOfElementLocated(noRecordsField));
            returnMe = false;
        } catch (Exception e) {
            returnMe = true;
        }
        
        return returnMe;
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
    }
    
}
