
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BackendInProcessPage_EComm extends BasePage {
    
    //Locators
    By custNameField = By.id("s2id_filterInProcessFileCustomerId");
    By fileNameField = By.id("filterInProcessFileFileName");
    By searchButton = By.cssSelector("#FilterShowlistForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > table > tbody > tr > td > div > input");
    By resetButton = By.cssSelector("#FilterShowlistForm > div.grid_12 > table > tbody > tr:nth-child(2) > td > div > table > tbody > tr > td > a");
    
    public BackendInProcessPage_EComm(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getFileNameField() {
        return driver.findElement(fileNameField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public BackendInProcessPage_EComm setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public BackendInProcessPage_EComm setFileName(String item) {
        CommonTask.setTextField(driver, fileNameField, item);
        return this;
    }
    
    public BackendInProcessPage_EComm pressSearch() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public BackendInProcessPage_EComm pressReset() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(searchButton).click();
        return this;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForFileName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fileNameField));
        WebElement waitForSearch = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement waitForReset = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));

        //Assert all elements are displayed
        Assert.assertTrue("Backend In Process Files Page: Customer name field not displayed",getCustNameField().isDisplayed());
        Assert.assertTrue("Backend In Process Files Page: File name field not displayed",getFileNameField().isDisplayed());
        Assert.assertTrue("Backend In Process Files Page: Search button not displayed",getSearchButton().isDisplayed());
        Assert.assertTrue("Backend In Process Files Page: Reset button not displayed",getResetButton().isDisplayed());
    }
    
}
