
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_OutstandingOrdersPage extends WBA_BasePage {
    
    By formLocator = By.id("FilterOutstandingOrderForm");
    
    public Ecomm_OutstandingOrdersPage(WebDriver driver) {
        super(driver);
    }
    
    public int getRow(String poNumber) {
        //wait for table to load
        boolean waitForLoad = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(5) > label"), "Customer PO No."));
        boolean found = false;
        int i = 0;
        while(!found && i < 8) {
            By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+i+" > td:nth-child(7)");
            if (driver.findElement(locator).getText().equals(poNumber)) {
                found = true;
            }
            i++;
        }
        
        if (found) {
            return i-1;
        } else {
            return -1;
        }
        
    }
    
    public Ecomm_OrderViewPage pressView(int orderRow) {
        //create locator for view button in correct row
        By viewButtonLocator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(2) > a > span");
        //wait for page to load and button to become clickable
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(viewButtonLocator));
        //click view button
        Actions clickView = new Actions(driver);
        clickView.click(driver.findElement(viewButtonLocator)).build().perform();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public String getOrderNumber(int orderRow) {
        //Locator for order number cell in table
        By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(8)");
        //Wait for cell
        WebElement waitForCell = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
    
    public void waitForElement() {
        WebElement waitForForm = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(formLocator));
    }
    
}
