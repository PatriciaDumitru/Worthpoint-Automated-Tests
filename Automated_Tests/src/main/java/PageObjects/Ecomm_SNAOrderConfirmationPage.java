
package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_SNAOrderConfirmationPage extends WBA_BasePage {
    
    //Locators
    By editButton = By.cssSelector("#remove_0 > td:nth-child(1) > a > span");
    By backButton = By.id("backLink");
    By cancelButton = By.id("cancel1");
    By sendForApprovalButton = By.cssSelector("#BulkOrderShadenotavailableConfirmForm > div:nth-child(7) > div:nth-child(3) > input");
    
    public Ecomm_SNAOrderConfirmationPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getEditButton() {
        return driver.findElement(editButton);
    }
    
    public WebElement getBackButton() {
        return driver.findElement(backButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public Ecomm_ShadeNotAvailablePage pressBack() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(backButton));
        driver.findElement(backButton).click();
        return new Ecomm_ShadeNotAvailablePage(driver);
    }
    
    public Ecomm_ShadeNotAvailablePage pressCancel() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        return new Ecomm_ShadeNotAvailablePage(driver);
    }
    
    public Ecomm_OrderInformationPage pressEdit() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(editButton));
        driver.findElement(editButton).click();
        return new Ecomm_OrderInformationPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage pressSend() {
        WebElement wait = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sendForApprovalButton));
        driver.findElement(sendForApprovalButton).click();
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public void checkFields() {
        WebElement waitForEdit = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(editButton));
        WebElement waitForBack = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(backButton));
        WebElement waitForCancel = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        Assert.assertTrue("Shade Not Available Order Confirmation Page: Edit button not displayed correctly",getEditButton().isDisplayed());
        Assert.assertTrue("Shade Not Available Order Confirmation Page: Back button not displayed correctly",getBackButton().isDisplayed());
        Assert.assertTrue("Shade Not Available Order Confirmation Page: Cancel button not displayed correctly",getCancelButton().isDisplayed());      
    }
    
}
