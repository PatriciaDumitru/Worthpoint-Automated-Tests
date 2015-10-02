
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EcommPage extends BasePage {
    
    //Page element locators
    static By contentImageLocator = By.cssSelector("#header > div:nth-child(6) > img");
    static By navBarLocator = By.id("topnav");
    static By mainImageLocator = By.cssSelector("#welcomefrm1 > center > img");
    static By ordersTab = By.cssSelector("#topnav > li:nth-child(1)");
    static By outstandingOrdersTab = By.cssSelector("#topnav > li:nth-child(2)");
    static By sapInterfaceLogTab = By.cssSelector("#topnav > li:nth-child(3)");
    static By reportsTab = By.cssSelector("#topnav > li:nth-child(4)");
    static By outstOrderDraftSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(2)");
    static By outstUploadDraftSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(3)");
    static By manualEntrySubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(1)");
    static By uploadOrderSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(2)");
    static By fromExistingSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(3) > a");
    static By invoicesSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(1)");
    static By deliveryNotesSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(2)");
    static By summaryOfSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(3)");
    
    public EcommPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public static WebElement getContentImage() {
        //find and return element
        return driver.findElement(contentImageLocator);
    }
    
    public static WebElement getNavBar() {
        //find and return element
        return driver.findElement(navBarLocator);
    }
    
    public static WebElement getMainImage() {
        //find and return element
        return driver.findElement(mainImageLocator);
    }
    
    public static void hoverNavBar(String tabTitle) {
        By tabLocator = By.xpath("//a[contains(text(),'" + tabTitle+ "')]");
        //new action to hover over nav bar item
        Actions hoverItem = new Actions(driver);
        hoverItem.moveToElement(driver.findElement(tabLocator)).build().perform();     
    }
    
    public ManualEntryPage clickManualEntry() {
        //Wait for visibility of navigation bar
        WebElement waitForNavBar = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersTab));
        //navigate to the manual entry link
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(manualEntrySubTab)));
        action.click(driver.findElement(manualEntrySubTab)).build().perform();
        
        //return a manual entry page
        return new ManualEntryPage(driver);
    }
    
    public UploadOrderPage clickUploadOrder() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersTab));
        
        //navigate to the Upload Order link
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(uploadOrderSubTab)));
        action.click(driver.findElement(uploadOrderSubTab)).build().perform();
        
        //return a manual entry page
        return new UploadOrderPage(driver);
    }
    
    public FromExistingPage_Ecomm clickFromExisting() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersTab));
        
        //navigate to the Upload Order link
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(fromExistingSubTab)));
        action.click(driver.findElement(fromExistingSubTab)).build().perform();
        
        return new FromExistingPage_Ecomm(driver);
    }
    
    public OutstandingOrderDraftPage clickOutstandingDraft() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstOrderDraftSubTab)));
        action.click(driver.findElement(outstOrderDraftSubTab)).build().perform();
        
        return new OutstandingOrderDraftPage(driver);
    }
    
    public OutstandingUploadDraftPage clickOutstandingUploadDraft() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstUploadDraftSubTab)));
        action.click(driver.findElement(outstUploadDraftSubTab)).build().perform();
        
        return new OutstandingUploadDraftPage(driver);
    }

    public SAPInterfaceLogPage_EComm clickSAPInterfaceLog() {
    	//Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(sapInterfaceLogTab)));
        
        driver.findElement(sapInterfaceLogTab).click();
        
        return new SAPInterfaceLogPage_EComm(driver);
    }
    
    public InvoicesPage_EComm clickInvoices() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(invoicesSubTab)));
        driver.findElement(invoicesSubTab).click();
		
        return new InvoicesPage_EComm(driver);
	}
    
    public DeliveryNotesPage_EComm clickDeliveryNotes() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(deliveryNotesSubTab)));
        driver.findElement(deliveryNotesSubTab).click();
		
        return new DeliveryNotesPage_EComm(driver);
    }
    
    public SummaryOfPurchasePage_EComm clickSummaryOfPurchases() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(summaryOfSubTab)));
        driver.findElement(summaryOfSubTab).click();
		
        return new SummaryOfPurchasePage_EComm(driver);
    }
    
}
