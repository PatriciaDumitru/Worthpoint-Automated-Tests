
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

        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for order samples subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderSamplesSubtab));
        //click subtab
        driver.findElement(orderSamplesSubtab).click();
        return new CCE_OrderSamplesPage(driver);
    }
    
    public CCE_OrderStatusPage pressOrderStatus() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for order samples subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderStatusSubtab));
        //click subtab
        driver.findElement(orderStatusSubtab).click();
        return new CCE_OrderStatusPage(driver);
    }
    
    public CCE_DNReprintPage pressDNReprint() {
        
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for DNReprint subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(dnReprintSubtab));
        //click subtab
        driver.findElement(dnReprintSubtab).click();
        return new CCE_DNReprintPage(driver);
        
    }
    
    public CCE_FeedbackPage pressFeedback() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for feedback subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(feedbackSubtab));
        //click subtab
        driver.findElement(feedbackSubtab).click();
        return new CCE_FeedbackPage(driver);
    }
    
    public CCE_FeedbackAwaitingPage pressFeedbackAwaiting() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for feedback awaiting subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(feedbackAwaitingSubtab));
        //click subtab
        driver.findElement(feedbackAwaitingSubtab).click();
        return new CCE_FeedbackAwaitingPage(driver);
    }
    
    public CCE_FeedbackCompletedPage pressFeedbackCompleted() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(feedbackCompletedSubtab));
        //click subtab
        driver.findElement(feedbackCompletedSubtab).click();
        
        return new CCE_FeedbackCompletedPage(driver);
    }
    
    public CCE_HubSosPage pressHubSos() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(hubHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubHeader));
        //Click header
        driver.findElement(hubHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubSosSubtab));
        //click subtab
        driver.findElement(hubSosSubtab).click();
        
        return new CCE_HubSosPage(driver);
    }
    
    public CCE_ReceivedHubPage pressReceivedHub() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(hubHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubHeader));
        //Click header
        driver.findElement(hubHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(receivedHubSubtab));
        //click subtab
        driver.findElement(receivedHubSubtab).click();
        
        return new CCE_ReceivedHubPage(driver);
    }

    
    
    public CCE_InboxPage pressInbox() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(inboxHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(inboxHeader));
        //Click header
        driver.findElement(inboxHeader).click();
        //Wait for inbox subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(inboxSubtab));
        //click subtab
        driver.findElement(inboxSubtab).click();
        return new CCE_InboxPage(driver);
    }
    
    public CCE_InboxSAPPage pressInboxSAP() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(inboxHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(inboxHeader));
        //Click header
        driver.findElement(inboxHeader).click();
        //Wait for inbox subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(inboxSAPSubtab));
        //click subtab
        driver.findElement(inboxSAPSubtab).click();
        return new CCE_InboxSAPPage(driver);
    }
    
    public CCE_ConfirmProductionPage pressConfirmProduction() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(conProdHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(conProdHeader));
        //Click header
        driver.findElement(conProdHeader).click();
        return new CCE_ConfirmProductionPage(driver);
    }
    
    public CCE_RefillCabinetPage pressRefillCabinet() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(refCabHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(refCabHeader));
        //Click header
        driver.findElement(refCabHeader).click();
        return new CCE_RefillCabinetPage(driver);
    }
    
    public CCE_FCETaskStatusPage pressFCETaskStatus() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(reportsHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        //Click header
        driver.findElement(reportsHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceTaskStatusSubtab));
        //click subtab
        driver.findElement(fceTaskStatusSubtab).click();
        
        return new CCE_FCETaskStatusPage(driver);
    }
    
    public CCE_OrderCycleTimePage pressOrderCycleTime() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(reportsHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        //Click header
        driver.findElement(reportsHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderCycleTimeSubtab));
        //click subtab
        driver.findElement(orderCycleTimeSubtab).click();
        
        return new CCE_OrderCycleTimePage(driver);
    } 
    
    public CCE_TotalOrdersPage pressTotalOrders() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(reportsHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        //Click header
        driver.findElement(reportsHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(totalOrdersSubtab));
        //click subtab
        driver.findElement(totalOrdersSubtab).click();
        
        return new CCE_TotalOrdersPage(driver);
    }
    
    public CCE_LRMLogPage pressLRMLog() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(adminHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(adminHeader));
        //Click header
        driver.findElement(adminHeader).click();
        //Wait for lrm log subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(lrmLogSubtab));
        //click subtab
        driver.findElement(lrmLogSubtab).click();
        
        return new CCE_LRMLogPage(driver);
    }
    
    public CCE_SAPLogPage pressSAPLog() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(adminHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(adminHeader));
        //Click header
        driver.findElement(adminHeader).click();
        //Wait for lrm log subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sapLogSubtab));
        //click subtab
        driver.findElement(sapLogSubtab).click();
        
        return new CCE_SAPLogPage(driver);
    }
    
}
