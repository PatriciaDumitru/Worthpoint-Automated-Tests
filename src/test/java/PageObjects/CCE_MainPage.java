
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CCE_MainPage extends WBA_BasePage {
    
    //page element locators
    static By logoLocator = By.cssSelector("#header > a > img");
    static By navBarLocator = By.id("topnav");
    static By mainImageLocator = By.cssSelector("#welcomefrm > img");
    
    //heading locators
    static By navbarFrameLocator = By.cssSelector("#navigation > div");
    static By ordersHeader = By.cssSelector("#topnav > li:nth-child(2)");
    static By hubHeader = By.cssSelector("#topnav > li:nth-child(3)");
    static By inboxHeader = By.cssSelector("#topnav > li:nth-child(4)");
    static By conProdHeader = By.cssSelector("#topnav > li:nth-child(5)");
    static By refCabHeader = By.cssSelector("#topnav > li:nth-child(6)");
    static By reportsHeader = By.cssSelector("#topnav > li:nth-child(7)");
    static By adminHeader = By.cssSelector("#topnav > li:nth-child(8)");
    static By orderSamplesSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(1)");
    static By orderStatusSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(4)");   
    static By dnReprintSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(5)");
    static By feedbackSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(6)");
    static By feedbackAwaitingSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(8)");
    static By feedbackCompletedSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(7)");
    static By hubSosSubtab = By.cssSelector("#topnav > li:nth-child(3) > div > div > ul > li:nth-child(1)");
    static By receivedHubSubtab = By.cssSelector("#topnav > li:nth-child(3) > div > div > ul > li:nth-child(2)");
    static By inboxSubtab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(1)");
    static By inboxSAPSubtab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(2)");
    static By fceTaskStatusSubtab = By.cssSelector("#topnav > li:nth-child(7) > div > div > ul > li:nth-child(1)");
    static By orderCycleTimeSubtab = By.cssSelector("#topnav > li:nth-child(7) > div > div > ul > li:nth-child(2)");
    static By totalOrdersSubtab = By.cssSelector("#topnav > li:nth-child(7) > div > div > ul > li:nth-child(3)");
    static By lrmLogSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(4)");
    static By sapLogSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(5)");
    
    public CCE_MainPage(WebDriver passedDriver) {
        //Intialise driver
        super(passedDriver);
        
    }
    
    public WebElement getNavBar() {
        //find and return element
        return driver.findElement(navBarLocator);
    }
    
    public WebElement getMainImage() {
        //find and return element
        return driver.findElement(mainImageLocator);
    }
    
    public CCE_OrderSamplesPage pressOrderSamples() {

        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderSamplesSubtab));
        action.click(driver.findElement(orderSamplesSubtab)).build().perform();
        
        return new CCE_OrderSamplesPage(driver);
    }
    
    public CCE_OrderStatusPage pressOrderStatus() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderStatusSubtab));
        action.click(driver.findElement(orderStatusSubtab)).build().perform();
        
        return new CCE_OrderStatusPage(driver);
    }
    
    public CCE_DNReprintPage pressDNReprint() {
        
        WebElement waitForVisibility = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(dnReprintSubtab));
        action.click(driver.findElement(dnReprintSubtab)).build().perform();
        
        return new CCE_DNReprintPage(driver);
        
    }
    
    public CCE_FeedbackPage pressFeedback() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(feedbackSubtab));
        action.click(driver.findElement(feedbackSubtab)).build().perform();
        
        return new CCE_FeedbackPage(driver);
    }
    
    public CCE_FeedbackAwaitingPage pressFeedbackAwaiting() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(feedbackAwaitingSubtab));
        action.click(driver.findElement(feedbackAwaitingSubtab)).build().perform();
        
        return new CCE_FeedbackAwaitingPage(driver);
    }
    
    public CCE_FeedbackCompletedPage pressFeedbackCompleted() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(feedbackCompletedSubtab));
        action.click(driver.findElement(feedbackCompletedSubtab)).build().perform();
        
        return new CCE_FeedbackCompletedPage(driver);
    }
    
    public CCE_HubSosPage pressHubSos() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(hubHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(hubHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(hubSosSubtab));
        action.click(driver.findElement(hubSosSubtab)).build().perform();
        
        return new CCE_HubSosPage(driver);
    }
    
    public CCE_ReceivedHubPage pressReceivedHub() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(hubHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(hubHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(receivedHubSubtab));
        action.click(driver.findElement(receivedHubSubtab)).build().perform();
        
        return new CCE_ReceivedHubPage(driver);
    }
    
    public CCE_InboxPage pressInbox() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(inboxHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(inboxHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(inboxSubtab));
        action.click(driver.findElement(inboxSubtab)).build().perform();
        
        return new CCE_InboxPage(driver);
    }
    
    public CCE_InboxSAPPage pressInboxSAP() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(inboxHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(inboxHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(inboxSAPSubtab));
        action.click(driver.findElement(inboxSAPSubtab)).build().perform();
        
        return new CCE_InboxSAPPage(driver);
    }
    
    public CCE_ConfirmProductionPage pressConfirmProduction() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(conProdHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(conProdHeader));
        //Click header
        driver.findElement(conProdHeader).click();
        return new CCE_ConfirmProductionPage(driver);
    }
    
    public CCE_RefillCabinetPage pressRefillCabinet() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(refCabHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(refCabHeader));
        //Click header
        driver.findElement(refCabHeader).click();
        return new CCE_RefillCabinetPage(driver);
    }
    
    public CCE_FCETaskStatusPage pressFCETaskStatus() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(reportsHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        //Click header
        driver.findElement(reportsHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(fceTaskStatusSubtab));
        //click subtab
        driver.findElement(fceTaskStatusSubtab).click();
        
        return new CCE_FCETaskStatusPage(driver);
    }
    
    public CCE_OrderCycleTimePage pressOrderCycleTime() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(reportsHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(orderCycleTimeSubtab));
        action.click(driver.findElement(orderCycleTimeSubtab)).build().perform();
        
        return new CCE_OrderCycleTimePage(driver);
    } 
    
    public CCE_TotalOrdersPage pressTotalOrders() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(reportsHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(totalOrdersSubtab));
        action.click(driver.findElement(totalOrdersSubtab)).build().perform();
        
        return new CCE_TotalOrdersPage(driver);
    }
    
    public CCE_LRMLogPage pressLRMLog() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(lrmLogSubtab));
        action.click(driver.findElement(lrmLogSubtab)).build().perform();
        
        return new CCE_LRMLogPage(driver);
    }
    
    public CCE_SAPLogPage pressSAPLog() {
        
        WebElement waitForHeader = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(sapLogSubtab));
        action.click(driver.findElement(sapLogSubtab)).build().perform();
        
        return new CCE_SAPLogPage(driver);
    }
    
}
