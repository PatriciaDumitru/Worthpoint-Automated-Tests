
package PageObjects;

import AutomationFramework.CommonTask;
import static PageObjects.WBA_BasePage.driver;
import static PageObjects.Ecomm_ManualEntryPage.customerNameField;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_OutstandingUploadDraftPage extends WBA_BasePage{
    
    By custNameFieldLocator = By.cssSelector("#s2id_filterBulkOrderCustomerId > a");
    By custNameSearchLocator = By.cssSelector("#select2-drop > div > input");
    By custNameResultLocator = By.cssSelector("#select2-drop > ul > li");
    By searchButtonLocator = By.cssSelector("#FilterDraftsUploadForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > div > input");
    By resetButtonLocator = By.cssSelector("#FilterDraftsUploadForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > a");
    By editButtonLocator = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_0 > td:nth-child(1) > span > a > span");
    By deleteButtonLocator = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_0 > td:nth-child(2) > span > a > span");
    By messageLocator = By.id("flashMessage");
    By formLocator = By.id("FilterDraftsUploadForm");
    
    public Ecomm_OutstandingUploadDraftPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public Ecomm_OutstandingUploadDraftPage setCustomerName(String custName) {
        
        //Wait for customer name field to be clickable
        WebElement waitToClick = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custNameFieldLocator));
        
        //click field and type customer name
        Actions typeCustomerName = new Actions(driver);
        typeCustomerName.click(driver.findElement(custNameFieldLocator)).build().perform();
        WebElement waitForSearchField = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(custNameSearchLocator));
        driver.findElement(custNameSearchLocator).sendKeys(custName);
        
        //Wait for search result to load
        Boolean waitForResult = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElementLocated(custNameResultLocator, custName));
        
        //Press enter
        typeCustomerName.sendKeys(driver.findElement(custNameSearchLocator), Keys.ENTER).build().perform();
 
        return this;
    }
    
    public Ecomm_OutstandingUploadDraftPage pressSearch() {
        //Wait for button to be clickable
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
        
        //Click button
        Actions clickSearch = new Actions(driver);
        clickSearch.click(driver.findElement(searchButtonLocator)).build().perform();
        
        return this;
    }
    
    public Ecomm_OrderConfirmationPage pressEdit() {
        
        //Wait for button to be clickable
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(editButtonLocator));
        
        //Click button
        Actions clickSearch = new Actions(driver);
        clickSearch.click(driver.findElement(editButtonLocator)).build().perform();
        
        //Handle alert
        try {
            Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert appeared: "+alert.getText());
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert present.");
        }
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public Ecomm_OrderConfirmationPage pressEdit(By editButton) {
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(editButton));
        driver.findElement(editButton).click();
        try {
            Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert appeared: " + alert.getText());
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert appeared");
        }
             
        return new Ecomm_OrderConfirmationPage(driver);
        
    }
    
    public Ecomm_OrderConfirmationPage pressDelete() {
        
        //Wait for button to be clickable
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(deleteButtonLocator));
        
        //Click button
        Actions clickSearch = new Actions(driver);
        clickSearch.click(driver.findElement(deleteButtonLocator)).build().perform();
        
        //Handle alert
        Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        //Wait for message
        WebElement waitForMessage = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(messageLocator));
        
        return new Ecomm_OrderConfirmationPage(driver);
        
    }
    
    public boolean findDraft(String poNumber) {
        boolean found = false;
        
        for (int i = 0; i < 4; i++) {
            
            By editButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+i+" > td:nth-child(1) > span > a > span");
            
            Ecomm_OrderConfirmationPage orderConf = pressEdit(editButton);
            
            try {
                Alert alert = new WebDriverWait(driver,3).until(ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (Exception e) {
                
            }
            
            orderConf.waitForLoad();
            
            String poFound = orderConf.getUploadCustPOField().getText();
            
            if (poFound == poNumber) {
                System.out.println("***DRAFT CREATED UNEXPECTEDLY***");
                System.out.println("Table row: "+i);
                System.out.println("Customer PO No.: " + poFound);
                found = true;
                break;
            } else {
                driver.navigate().back();
                CommonTask.waitForPageLoad(driver);
            }
            
        }
        
        return found;
        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(formLocator));
    }
    
    
}
