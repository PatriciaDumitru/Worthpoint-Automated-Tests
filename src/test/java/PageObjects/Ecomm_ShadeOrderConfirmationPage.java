
package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_ShadeOrderConfirmationPage extends WBA_BasePage {
    
    //Locators
    By editButton = By.cssSelector("#remove_0 > td:nth-child(1) > a > span");
    By backButton = By.id("backLink");
    By cancelButton = By.id("cancel1");
    By sendForApprovalButton = By.cssSelector("#BulkOrderShadenotavailableConfirmForm > div:nth-child(7) > div:nth-child(3) > input");
    By submitButton = By.id("submit1");
    
    public Ecomm_ShadeOrderConfirmationPage(WebDriver driver) {
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
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(backButton));
        driver.findElement(backButton).click();
        return new Ecomm_ShadeNotAvailablePage(driver);
    }
    
    public Ecomm_ShadeNotAvailablePage pressCancel() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        return new Ecomm_ShadeNotAvailablePage(driver);
    }
    
    public Ecomm_OrderInformationPage pressEdit() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        driver.findElement(editButton).click();
        return new Ecomm_OrderInformationPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage pressSend() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sendForApprovalButton));
        driver.findElement(sendForApprovalButton).click();
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public Ecomm_OutstandingOrdersPage pressSubmit() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(submitButton));
        driver.findElement(submitButton).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        try {
            Alert alert2 = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert appeared: " + alert2.getText());
            alert2.accept();
        } catch (Exception e) {
           
        }
        
        return new Ecomm_OutstandingOrdersPage(driver);
    }
    
    public void checkFields() {
        WebElement waitForEdit = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        WebElement waitForBack = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(backButton));
        WebElement waitForCancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        AssertJUnit.assertTrue("Shade Not Available Order Confirmation Page: Edit button not displayed correctly",getEditButton().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Order Confirmation Page: Back button not displayed correctly",getBackButton().isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Order Confirmation Page: Cancel button not displayed correctly",getCancelButton().isDisplayed());      
    }
    
}
