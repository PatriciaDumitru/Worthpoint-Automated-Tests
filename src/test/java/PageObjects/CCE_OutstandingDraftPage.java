
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class CCE_OutstandingDraftPage extends WBA_BasePage {
    
    //Locators
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By hubField = By.id("s2id_filterSampleOrderHubId");
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
    
    public CCE_OutstandingDraftPage pressListOrders() {
        WebElement button = Wait.clickable(driver,listOrdersButton);
        button.click();
        return new CCE_OutstandingDraftPage(driver);
    }
    
    public CCE_OutstandingDraftPage pressReset() {
        WebElement button = Wait.clickable(driver,resetButton);
        button.click();
        return new CCE_OutstandingDraftPage(driver);
    }
    
    public CCE_OrderViewPage pressView(int row) {
        By viewButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(row+2)+") > td:nth-child(13) > a");
        
        WebElement view = Wait.clickable(driver,viewButton);
        
        view.click();
        
        return new CCE_OrderViewPage(driver);
        
    }
    
    public CCE_AddOrderPage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(12) > a:nth-child(1) > span");
        
        WebElement edit = Wait.clickable(driver,editButton);
        
        edit.click();
        
        return new CCE_AddOrderPage(driver);
    }
    
    public CCE_CancelDraftPage pressCancel(int row) {
        By cancelButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(12) > a.thickbox > span");

        WebElement cancel = Wait.visible(driver,cancelButton);
        WebElement wait2 = Wait.clickable(driver,cancelButton);
        
        cancel.click();
        
        return new CCE_CancelDraftPage(driver);
    }
    
    public int findDraft(String creationDate) throws ParseException {
        int row = -1;
        
        for (int i = 2; i < 10; i++) {
            By dateHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4)");
            WebElement header = Wait.visible(driver,dateHeader);
            AssertJUnit.assertTrue("Outstanding Draft Page: Date column has moved, update locators",header.getText().trim().equals("Date"));
            
            By dateCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
            
            WebElement dateField = Wait.visible(driver,dateCell);
            
            //The creation date can differ by 1 minute between the drafts page and the creation page, producing false negatives
            String dateInCell = dateField.getText();
            
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
        
        
        
        for (int i = 2; i < 8; i++) {
            By orderNoHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(3)");
            WebElement header = Wait.visible(driver,orderNoHeader);
            AssertJUnit.assertTrue("Oustanding Draft Page: Order No column has moved, update locators",header.getText().trim().equals(orderNo));
            
            By orderNoCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
            WebElement orderNoField = Wait.visible(driver,orderNoCell);
            String cellOrderNo = orderNoField.getText();
            
            if (cellOrderNo.equals(orderNo)) {
                System.out.println("Draft found");
                System.out.println("Order No: " + orderNo);
                return true;
            }
        }   
        return false;
    }
    
    public String getOrderNo(int row) {
        By desiredOrderNoField = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(3)");
        return driver.findElement(desiredOrderNoField).getText();
    }
    
    public void checkFields() {
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement hub = Wait.clickable(driver,hubField);
        WebElement dateFrom = Wait.clickable(driver,dateFromField);
        WebElement dateTo = Wait.clickable(driver,dateToField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement listOrders = Wait.clickable(driver,listOrdersButton);
        WebElement reset = Wait.clickable(driver,resetButton);
        
        AssertJUnit.assertTrue("Oustanding Order Drafts Page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Oustanding Order Drafts Page: Hub Field not displayed correctly",hub.isDisplayed());
        AssertJUnit.assertTrue("Oustanding Order Drafts Page: Created Date From Field not displayed correctly",dateFrom.isDisplayed());
        AssertJUnit.assertTrue("Oustanding Order Drafts Page: Created Date To Field not displayed correctly",dateTo.isDisplayed());
        AssertJUnit.assertTrue("Oustanding Order Drafts Page: Customer Name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Oustanding Order Drafts Page: Sales Organisation Field not displayed correctly",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Oustanding Order Drafts Page: Requester Field not displayed correctly",requester.isDisplayed());
        AssertJUnit.assertTrue("Oustanding Order Drafts Page: List Orders Button not displayed correctly",listOrders.isDisplayed());
        AssertJUnit.assertTrue("Oustanding Order Drafts Page: Reset Button not displayed correctly",reset.isDisplayed());
        
        CommonTask.checkPagination(driver);
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,orderNoField);
    }
    
}
