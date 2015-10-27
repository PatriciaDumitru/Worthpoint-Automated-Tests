
package PageObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CCE_OutstandingDraftPage extends WBA_BasePage {
    
    //Locators
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By hubField = By.id("ss2id_filterSampleOrderHubId");
    By dateFromField = By.id("filterSampleOrderCreatedFrom");
    By dateToField = By.id("filterSampleOrderCreatedTo");
    By custNameField = By.id("s2id_filterSampleOrderCustomerId");
    By salesOrgField = By.id("s2id_filterSampleOrderSalesOrgId");
    By requesterField = By.id("s2id_filterSampleOrderRequesterId");
    By listOrdersButton = By.cssSelector("#FilterDraftsForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterDraftsForm > div.actions > ul > li:nth-child(2) > a");
    
    public CCE_OutstandingDraftPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hubField);
    }
    
    public WebElement getDateFromField() {
        return driver.findElement(dateFromField);
    }
    
    public WebElement getDateToField() {
        return driver.findElement(dateToField);
    }
    
    public WebElement getCustomerNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
     
    public WebElement getListOrdersButton() {
        return driver.findElement(listOrdersButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public CCE_OutstandingDraftPage pressListOrders() {
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        driver.findElement(listOrdersButton).click();
        return new CCE_OutstandingDraftPage(driver);
    }
    
    public CCE_OutstandingDraftPage pressReset() {
        WebElement waitForButton = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
        return new CCE_OutstandingDraftPage(driver);
    }
    
    public CCE_OrderViewPage pressView(int row) {
        By viewButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(row+2)+") > td:nth-child(13) > a");
        
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButton));
        
        driver.findElement(viewButton).click();
        
        return new CCE_OrderViewPage(driver);
        
    }
    
    public CCE_AddOrderPage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(row+2)+") > td:nth-child(12) > a:nth-child(1)");
        
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(editButton));
        
        driver.findElement(editButton).click();
        
        return new CCE_AddOrderPage(driver);
    }
    
    public CCE_CancelDraftPage pressCancel(int row) {
        By cancelButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(row+2)+") > td:nth-child(12) > a.thickbox");

        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(cancelButton));
        WebElement wait2 = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        driver.findElement(cancelButton).click();
        
        return new CCE_CancelDraftPage(driver);
    }
    
    public int findDraft(String creationDate) throws ParseException {
        int row = -1;
        
        for (int i = 0; i < 10; i++) {
            By dateCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(i+2)+") > td:nth-child(4)");
            
            WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(dateCell));
            
            
            //The creation date can differ by 1 minute between the drafts page and the creation page, producing false negatives
            String dateInCell = driver.findElement(dateCell).getText();
            
            //Get the date and hour of each time to check they match
            String cellDayHour = dateInCell.substring(0, 13);
            String actualDayHour = creationDate.substring(0,13);
            
            //Get the minutes of each time to check they within 1 minute of each other
            int cellMins = Integer.valueOf(dateInCell.substring(14));
            int actualMins = Integer.valueOf(creationDate.substring(14));
            
            //If test is run at 59minutes past, a false negative can occur. Extension would be to use Date classes
            int difference = cellMins-actualMins;
            
            if (cellDayHour.equals(actualDayHour) && (difference <= 1 && difference >= -1)) {
                System.out.println("Draft found");
                row=i;
                break;
            }
        }
        return row;
    }
    
    public boolean findDraftByOrderNo(String orderNo) {
        boolean found = false;
        
        for (int i = 0; i < 5; i++) {
            By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(i+2)+") > td:nth-child(3)");
            WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(orderNoCell));
            String cellOrderNo = driver.findElement(orderNoCell).getText();
            
            if (cellOrderNo.equals(orderNo)) {
                System.out.println("Draft found");
                System.out.println("Order No: " + orderNo);
                return true;
            }
        }   
        return false;
    }
    
    public String getOrderNo(int row) {
        By orderNoField = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(3)");
        return driver.findElement(orderNoField).getText();
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderNoField));
    }
    
}
