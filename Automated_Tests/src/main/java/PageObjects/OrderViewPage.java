
package PageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderViewPage {
    WebDriver driver;
    
    //Table cell locators
    static By yourMatNumCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr:nth-child(1) > td:nth-child(3)");
    static By articleCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(4)");
    static By brandCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(5)");
    static By ticketCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(6)");
    static By shadeCodeCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(7)");
    static By requiredDateCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(16)");
    
    public OrderViewPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public WebElement getYourMatNumCell() {
        //find and return element
        return driver.findElement(yourMatNumCellLocator);
    }
    
    public WebElement getArticleCell() {
        //find and return element
        return driver.findElement(articleCellLocator);
    }
    
    public WebElement getBrandCell() {
        //find and return element
        return driver.findElement(brandCellLocator);
    }
    
    public WebElement getTicketCell() {
        //find and return element
        return driver.findElement(ticketCellLocator);
    }
    
    public WebElement getShadeCodeCell() {
        //find and return element
        return driver.findElement(shadeCodeCellLocator);
    }
    
    public WebElement getRequiredDateCell() {
        //find and return element
        return driver.findElement(requiredDateCellLocator);
    }
    
    public OutstandingOrdersPage exitView() {
        //New action to press escape
        Actions pressEsc = new Actions(driver);
        pressEsc.sendKeys(Keys.ESCAPE).build().perform();
        //Accept alert
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new OutstandingOrdersPage(driver);
    }
    
    
}
