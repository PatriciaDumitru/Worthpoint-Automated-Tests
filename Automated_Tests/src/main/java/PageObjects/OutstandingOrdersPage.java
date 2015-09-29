
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OutstandingOrdersPage extends BasePage {
    
    //
    
    public OutstandingOrdersPage(WebDriver driver) {
        super(driver);
    }
    
    public int getOrderRow(String poNumber) {
        //wait for table to load
        boolean waitForLoad = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(5) > label"), "Customer PO No."));
        boolean found = false;
        int i = 0;
        By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+i+" > td:nth-child(7)");
        while(!found) {
            if (driver.findElement(locator).getText().equals(poNumber)) {
                found = true;
            }
            i++;
        }
        
        return (i-1);
    }
    
    public OrderViewPage pressView(int orderRow) {
        //create locator for view button in correct row
        By viewButtonLocator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(2) > a > span");
        //wait for page to load and button to become clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButtonLocator));
        //click view button
        Actions clickView = new Actions(driver);
        clickView.click(driver.findElement(viewButtonLocator)).build().perform();
        return new OrderViewPage(driver);
    }
    
    public String getOrderNumber(int orderRow) {
        //Locator for order number cell in table
        By locator = By.cssSelector("#FilterOutstandingOrderForm > div.container > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr.row-remove_"+orderRow+" > td:nth-child(8)");
        //Wait for cell
        WebElement waitForCell = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
    
}
