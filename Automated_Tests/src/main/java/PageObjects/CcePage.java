
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CcePage extends BasePage {
    
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
    
    public CcePage(WebDriver passedDriver) {
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
    
    public OrderSamplesPage_CCE pressOrderSamples() {

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
        return new OrderSamplesPage_CCE(driver);
    }
    
    public DNReprintPage_CCE pressDNReprint() {
        
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for DNReprint subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(dnReprintSubtab));
        //click subtab
        driver.findElement(dnReprintSubtab).click();
        return new DNReprintPage_CCE(driver);
        
    }
    
    public FeedbackPage_CCE pressFeedback() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for feedback subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(feedbackSubtab));
        //click subtab
        driver.findElement(feedbackSubtab).click();
        return new FeedbackPage_CCE(driver);
    }
    
    public FeedbackAwaitingPage_CCE pressFeedbackAwaiting() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for feedback awaiting subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(feedbackAwaitingSubtab));
        //click subtab
        driver.findElement(feedbackAwaitingSubtab).click();
        return new FeedbackAwaitingPage_CCE(driver);
    }
    
    public FeedbackCompletedPage_CCE pressFeedbackCompleted() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        //Click header
        driver.findElement(ordersHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(feedbackCompletedSubtab));
        //click subtab
        driver.findElement(feedbackCompletedSubtab).click();
        
        return new FeedbackCompletedPage_CCE(driver);
    }
    
    public HubSosPage_CCE pressHubSos() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(hubHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubHeader));
        //Click header
        driver.findElement(hubHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubSosSubtab));
        //click subtab
        driver.findElement(hubSosSubtab).click();
        
        return new HubSosPage_CCE(driver);
    }
    
    public ReceivedHubPage_CCE pressReceivedHub() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(hubHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hubHeader));
        //Click header
        driver.findElement(hubHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(receivedHubSubtab));
        //click subtab
        driver.findElement(receivedHubSubtab).click();
        
        return new ReceivedHubPage_CCE(driver);
    }

    public OrderStatusPage_CCE pressOrderStatus() {
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
        return new OrderStatusPage_CCE(driver);
    }
    
    public InboxPage_CCE pressInbox() {
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
        return new InboxPage_CCE(driver);
    }
    
    public InboxSAPPage_CCE pressInboxSAP() {
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
        return new InboxSAPPage_CCE(driver);
    }
    
    public ConfirmProductionPage_CCE pressConfirmProduction() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(conProdHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(conProdHeader));
        //Click header
        driver.findElement(conProdHeader).click();
        return new ConfirmProductionPage_CCE(driver);
    }
    
    public RefillCabinetPage_CCE pressRefillCabinet() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(refCabHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(refCabHeader));
        //Click header
        driver.findElement(refCabHeader).click();
        return new RefillCabinetPage_CCE(driver);
    }
    
    public FCETaskStatusPage_CCE pressFCETaskStatus() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(reportsHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        //Click header
        driver.findElement(reportsHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fceTaskStatusSubtab));
        //click subtab
        driver.findElement(fceTaskStatusSubtab).click();
        
        return new FCETaskStatusPage_CCE(driver);
    }
    
    public OrderCycleTimePage_CCE pressOrderCycleTime() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(reportsHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        //Click header
        driver.findElement(reportsHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderCycleTimeSubtab));
        //click subtab
        driver.findElement(orderCycleTimeSubtab).click();
        
        return new OrderCycleTimePage_CCE(driver);
    } 
    
    public TotalOrdersPage_CCE pressTotalOrders() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(reportsHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        //Click header
        driver.findElement(reportsHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(totalOrdersSubtab));
        //click subtab
        driver.findElement(totalOrdersSubtab).click();
        
        return new TotalOrdersPage_CCE(driver);
    }
    
    public LRMLogPage_CCE pressLRMLog() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(adminHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(adminHeader));
        //Click header
        driver.findElement(adminHeader).click();
        //Wait for lrm log subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(lrmLogSubtab));
        //click subtab
        driver.findElement(lrmLogSubtab).click();
        
        return new LRMLogPage_CCE(driver);
    }
    
    public SAPLogPage_CCE pressSAPLog() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(adminHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(adminHeader));
        //Click header
        driver.findElement(adminHeader).click();
        //Wait for lrm log subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sapLogSubtab));
        //click subtab
        driver.findElement(sapLogSubtab).click();
        
        return new SAPLogPage_CCE(driver);
    }
    
}
